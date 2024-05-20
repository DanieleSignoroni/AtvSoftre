package it.thera.thip.ws;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thera.thermfw.base.IniFile;
import com.thera.thermfw.base.Trace;
import com.thera.thermfw.base.Utils;
import com.thera.thermfw.persist.ConnectionDescriptor;
import com.thera.thermfw.persist.ConnectionManager;
import com.thera.thermfw.security.Security;
import com.thera.thermfw.security.User;
 
/*
 * Revisions:
 * Number       Date               Owner     Descrizione
 * 31635        22/07/2020         LW        Modifica del metodo doFilter(...)
 * 34658        15/11/2021         AJ        Modifica del metodo doFilter
 * 35230        20/02/2021         PJ        JAX-RS  
 * 35783        04/05/2022         HED       Modifica del metodo doFilter
 * 37891        21/02/2023         PJ        CORS: aggiunto header x-pth-ng
 * 40904        23/01/2024         FG        CORS: aggiunto header x-pth-jwt
 * 41683        12/03/2024         PJ        Irrobustimento pool connessioni
 * 41948        04/04/2024         PJ        Ripulitura security session
*/

/**
 * Servlet Filter implementation class PTHFilter
 */
// Enable it for Servlet 3.x implementations
/* @ WebFilter(asyncSupported = true, urlPatterns = { "/*" }) */
public class PTHFilter implements Filter {
	protected boolean secure = false;
	
  public static final String FILE_RES = "it/thera/thip/ws/resources/PTHFilter";
	protected static ResourceBundle resource;

    /**
     * Default constructor.
     */
    public PTHFilter() {
        // TODO Auto-generated constructor stub
    }
 
    /**
     * @see Filter#destroy()
     */
    public void destroy() {
        // TODO Auto-generated method stub
    }
 
    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
    		throws IOException, ServletException {

    	HttpServletRequest request = (HttpServletRequest) servletRequest;
    	HttpServletResponse response = (HttpServletResponse) servletResponse;
    	//34658 inizio
    	response.setHeader("X-Robots-Tag", "noindex, nofollow");
    	//34658 fine
    	//gestione session cookie nel caso di tomcat7 e uso di https
  		if(isSecure() && request.isSecure()) {
  			addSessionCookie(request, response);
  		}
    	
    	Object enableCORSFilter = resource.getObject("enableCORSFilter");


    	if (enableCORSFilter != null && enableCORSFilter.toString().equals("true")) {
    		manageCORSFilter(servletRequest, servletResponse, chain);
    	}

    	// For HTTP OPTIONS verb/method reply with ACCEPTED status code -- per CORS handshake
    	if (request.getMethod().equals("OPTIONS")) {
    		response.setStatus(HttpServletResponse.SC_ACCEPTED);
    		return;
    	}
		// 31635 inizio
		// pass the request along the filter chain
		// chain.doFilter(request, servletResponse);
		try {
			//35783 GN inizio
			//chain.doFilter(request, servletResponse);
			PTHServletRequestWrapper wrapper = new PTHServletRequestWrapper(request);
			chain.doFilter(wrapper, servletResponse);
			//35783 GN fine
		} catch (FileNotFoundException ex) {
			Trace.excStream.println(ex.getMessage());
		}
		//fix 41683 - inizio
		finally {
			connectionStackOperations(request);
		}
		//fix 41683 - fine
		// 31635 fine
    }
 
	//fix 41683 - inizio
    private void cleanConnectionStack() {
    	boolean broken = false;
    	int loops = 0;

    	/*
		non vorrei mai che, andando in errore il blocco di chiusura connessione, si generasse un loop eterno 
    	 */
    	int MAX_LOOPS = 20;

    	cleaningLoop : while (ConnectionManager.getCurrentStackSize() > 0) {
    		if (++loops > MAX_LOOPS) {
    			broken = true;
    			break cleaningLoop;
    		}

    		boolean ok = false;
    		String cdString = null;
    		try {
	    		ConnectionDescriptor cd = ConnectionManager.getCurrentConnectionDescriptor();
	    		
	    		if (cd != null) {
	    			cdString = cd.toString();
	    		}
	    		
	    		//popConnection e, per sicurezza, non la rimetto manco nel pool
	        	ConnectionManager.popConnection(cd);
	    		cd.closeConnection();
	    		ok = true;
    		}
        	catch (Exception e) {
    			e.printStackTrace(Trace.excStream);
    		}
    		
    		if (ok) {
    			log("cleanConnectionStack: pop(" + cdString + ")");    	
    		}
    		else {
    			log("cleanConnectionStack: error during pop(" + cdString + ")");    	
    		}
    	}
    	
    	if (broken) {
    		log("cleanConnectionStack: too many loops (20)");    	
    	}

    }
    
	//fix 41948 - inizio
    private boolean cleanSecuritySession() {
    	boolean opened = Security.isSessionOpened(); 
    	if (opened) {
    		User user = Security.getCurrentUser();
    		log("cleanSecuritySession: found an opened session for user " + (user == null ? null : user.getId()));    	
    		boolean ok = false;
    		try {
    			Security.closeSession();
    			ok = true;
    		}
    		catch (Exception e) {
        		log("cleanSecuritySession: error during Security.closeSession() -> " + e);    	
    		}
    		if (ok) {
        		log("cleanSecuritySession: session closed.");    	
    		}
    	}
    	return opened;
    }
	//fix 41948 - fine
    
    private void connectionStackOperations(HttpServletRequest request) {
    	/*
    	se alla fine della request nello stack del thread corrente
    	ci sono ancora delle connessioni, stampo errore e le chiudo 
    	*/
    	
    	//fix 41948 - inizio
    	/*
		chiudo anche l'eventuale sessione di security, se rimasta erroneamente aperta sul thread corrente
    	*/
    	//fix 41948 - fine
    	
    	if (!ConnectionManager.isCurrentStackAvailable()) {
    		//request a risorse statiche (immagini, js, etc)
    		return;
    	}
    	
    	int cSize = ConnectionManager.getCurrentStackSize();
    	if (cSize == 0) {
    		return;
    	}

    	//fix 41948 - inizio
    	String qs = request.getQueryString();
		String url = request.getRequestURL().toString() + (qs != null ? "?" + qs : "");
		log("StackSize: " + cSize + " on completed request: " + url);    	

		int cSizeAfterSessionCleaning = cSize;

		if (cleanSecuritySession()) {
	    	cSizeAfterSessionCleaning = ConnectionManager.getCurrentStackSize();
			log("StackSize after security session cleaning operations: " + cSizeAfterSessionCleaning);
		}

		if (cSizeAfterSessionCleaning > 0) {
			cleanConnectionStack();
	    	int cSizeAfterCleaning = ConnectionManager.getCurrentStackSize();
			log("StackSize after cleaning operations: " + cSizeAfterCleaning);
		}
    	//fix 41948 - fine

    }
    
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
    
    private static void log(String message) {
    	String ts = sdf.format(new java.util.Date());
    	System.err.println("[CHK-ERR-CONN][PTHFilter][" + ts + "]" + message);
    }
	//fix 41683 - fine
    
    
    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
        // TODO Auto-generated method stub
    	resource = ResourceBundle.getBundle(FILE_RES);
    	secure = (IniFile.getValue("thermfw.ini", "Web", "securePort") != null) && Utils.areEqual("true", resource.getString("enableSecure"));
    }
 
    public void manageCORSFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
    		throws IOException, ServletException {
    	
    	HttpServletRequest request = (HttpServletRequest) servletRequest;
    	HttpServletResponse response = (HttpServletResponse) servletResponse;

		// Get client's origin
		String clientOrigin = request.getHeader("origin");

		// Get client's IP address
		String ipAddress = request.getHeader("x-forwarded-for");
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}  

		// Authorize (allow) all domains to consume the content
		String originAllowed = (String)resource.getObject("Access-Control-Allow-Origin");
		response.addHeader("Access-Control-Allow-Origin", originAllowed);        	
		response.addHeader("Access-Control-Allow-Credentials", "true");
		response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");

		//35230 - aggiunto header Authorization		
		//37891 - aggiunto header X-Pth-Ng	
		//40904 - aggiunto header X-Pth-Jwt
		response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept, Authorization, X-Pth-Ng, X-Pth-Jwt");
		//response.addHeader("Access-Control-Max-Age", "1728000");
    }

    //gestione session cookie nel caso di tomcat7 e uso di https : inizio
  	public boolean isSecure() {
  		return secure;
  	}

  	protected String getSessionIdCookieName() {
  		return "JSESSIONID";
  	}
   
  	protected void addSessionCookie(HttpServletRequest request, HttpServletResponse response) {
  		Cookie cookie = new Cookie(getSessionIdCookieName(), request.getSession().getId());
  		cookie.setPath(request.getContextPath());
  		cookie.setSecure(false);
  		response.addCookie(cookie);
  	}	
  	//gestione session cookie nel caso di tomcat7 e uso di https : fine
}
