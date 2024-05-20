package com.thera.thermfw.persist;

import java.sql.*;
import java.sql.Connection;
import java.util.*;
import java.lang.reflect.*;
import javax.jms.*;
import javax.naming.*;

import com.thera.thermfw.base.*;

/* Revisions:
 * Number   Date          Owner      Description
 *  10661   16/04/2009    DM         Ridisegnata completamente
 *  41683   12/03/2021    PJ         Irrobustimento pool connessioni
 */

public class ConnectionManager
{
    protected static AbstractConnectionManager cvInstance;

    private static final String TEST_STATEMENT_STR = "SELECT * FROM " + SystemParam.getFrameworkSchema() + "REPLACEMENT WHERE 1 = 0";
    private static Map cvTestStatements = Collections.synchronizedMap(new HashMap());

    static
    {
        String dataSourceName = IniFile.getValue("Web", "DataSourceName");
        // Se non uso questo test posso usare sempre e solo un DataSourceConnectionManager.
        // Infatti posso costruire una DataSource a partire dal nome db therm.
        // Si tratta solo di usare i ConnectionDescriptor per accedere alla DataSource corretta,
        // e se ne deve prevedere più d'una.

        if (dataSourceName == null)
            cvInstance = new JDBCConnectionManager();
        else
        {
            String initialContextFactoryName = IniFile.getValue("Web", "InitialContextFactoryName");
            cvInstance = new DataSourceConnectionManager(initialContextFactoryName, dataSourceName);
        }
    }

    private ConnectionManager()
    {
    }

    public static boolean isDead(Connection c)
    {
        boolean dead = false;
        try
        {
            if (Trace.isLogEnabled(Trace.PERS_CONN))
                Trace.println(Trace.PERS_CONN, "Executing test statement");
            PreparedStatement test = (PreparedStatement)cvTestStatements.get(c);
            if (test == null)
            {
                test = c.prepareStatement(TEST_STATEMENT_STR);
                cvTestStatements.put(c, test);
            }
            ResultSet rs = test.executeQuery();
            rs.close();
            if (Trace.isLogEnabled(Trace.PERS_CONN))
                Trace.println(Trace.PERS_CONN, "Test statement OK");
        }
        catch (Exception e)
        {
            Trace.println(Trace.PERS_CONN, "Test statement failed with exception: " + e);
            dead = true;
            cvTestStatements.remove(c);
        }
        return dead;
    }

    public static boolean isDeadOnRollback(Connection c)
    {
        boolean dead = false;
        try
        {
            c.rollback();
        }
        catch (SQLException e)
        {
            dead = true;
        }
        return dead;
    }

    /**
     * Restituisce l'utente di default per un certo database.
     * La ricerca viene effettuata tra i database definiti nel file INI del fw
     * Nel caso in cui il database sia sconosciuto viene restituito NULL
     * @param databaseName nome del database
     * @return utente di default
     */
    public static String getDefaultUserId(String databaseName)
    {
        String toParse = getDatabaseInfo(databaseName);
        String res = null;
        if (toParse != null)
        {
            StringTokenizer t = new StringTokenizer(toParse, ",");
            try
            {
                res = t.nextToken().trim();
            }
            catch (Throwable e)
            {
                Trace.excStream.println("Undefined User Id in THERMFW.INI file for database " + databaseName);
            }
        }
        return res;
    }

    /**
     * Restituisce la password di default per un certo database.
     * La ricerca viene effettuata tra i database definiti nel file INI del fw
     * Nel caso in cui il database sia sconosciuto viene restituito NULL
     * @param databaseName nome del database
     * @return password di default
     */
    public static String getDefaultPassword(String databaseName)
    {
        String toParse = getDatabaseInfo(databaseName);
        String res = null;
        if (toParse != null)
        {
            StringTokenizer t = new StringTokenizer(toParse, ",");
            try
            {
                String user = t.nextToken();
                res = t.nextToken().trim();
            }
            catch (Throwable e)
            {
                Trace.excStream.println("Undefined password in THERMFW.INI file for database " + databaseName);
            }
        }
        return res;
    }

    /**
     * Restituisce il driver di default per un certo database.
     * La ricerca viene effettuata tra i database definiti nel file INI del fw
     * Nel caso in cui il database sia sconosciuto viene restituito NULL
     * @param databaseName nome del database
     * @return driver di default
     */
    public static Database getDefaultDriver(String databaseName)
    {
        String toParse = getDatabaseInfo(databaseName);
        if (toParse != null)
        {
            int first = toParse.indexOf(',');
            int second = toParse.indexOf(',', first + 1);
            return getDriverFromString(toParse.substring(second + 1));
        }
        return null;
    }

    /**
     * Costruisce il driver di database.
     * La stringa ricevuta è nel formato <code>driver,param1,param2,...</code>
     * Tramite reflection viene istanziato il driver utilizzando il costruttore (che deve
     * essere unico) e passandogli i parametri elencati di seguito al nome del driver
     * @param driverInfo nome driver ed eventuali parametri
     * @return driver istanziato - null in caso di errore
     */
    public static Database getDriverFromString(String driverInfo)
    {
        if (driverInfo != null)
        {
            StringTokenizer t = new StringTokenizer(driverInfo, ",");
            try {
                String driver = t.nextToken().trim();
                Class driverClass = Class.forName(driver);
                Constructor[] constructors = driverClass.getDeclaredConstructors();
                if (constructors.length == 0)
                {
                    Trace.excStream.println("Constructor for class " + driver + " not found");
                    return null;
                }
                if (constructors.length > 1)
                {
                    Trace.excStream.println("The class " + driver + " must declare a unique constructor");
                    return null;
                }
                Class[] paramTypes = constructors[0].getParameterTypes();
                Object params[] = new Object[paramTypes.length];
                for (int i = 0; i < paramTypes.length; i++)
                {
                    if (!paramTypes[i].equals(java.lang.String.class))
                    {
                    Trace.excStream.println("All the parameters of the constructor of the class " + driver + " must be of type String");
                    return null;
                }
                try
                {
                    String param = t.nextToken().trim();
                    params[i] = param;
                }
                catch (NoSuchElementException e)
                {
                    Trace.excStream.println("Missing parameter in database definition in THERMFW.INI file");
                    return null;
                }
                }
                return (Database) constructors[0].newInstance(params);
            }
            catch (Throwable e)
            {
                e.printStackTrace(Trace.excStream);
                return null;
            }
        }
        return null;
    }

    /**
     * Restituisce la stringa delle informazioni relative ad un database.
     * La ricerca viene effettuata tra i database definiti nel file INI del fw
     * Nel caso in cui il database sia sconosciuto viene restituito NULL
     * @param databaseName nome del database
     * @return driver di default
     */
    protected static String getDatabaseInfo(String databaseName)
    {
        String section = (SystemParam.applet == null ? SystemParam.DB_SECTION : SystemParam.APPLET_DB_SECTION);
        String temp = IniFile.getValue(section, databaseName);
        if (temp == null)
            Trace.excStream.println("Database <" + databaseName + "> undefined in file THERMFW.INI");
        return temp;
    }

    /**
     * Apre la connessione principale.
     * @deprecated Usare openMainConnection()
     * @see com.thera.thermfw.persist.ConnectionManager#openMainConnection(java.lang.String, java.lang.String, java.lang.String, com.thera.thermfw.persist.Database)
     */
    public static void openConnection(String aDBName, String aUser, String aPassword, Database aDB) throws SQLException
    {
        openMainConnection(new ConnectionDescriptor(aDBName, aUser, aPassword, aDB));
    }

    /**
     * Ottiene la connessione corrente.
     * @deprecated Usare getCurrentConnection()
     * @see com.thera.thermfw.persist.ConnectionManager#getCurrentConnection()
     * @return la connessione corrente.
     */
    public static Connection getConnection()
    {
        return getCurrentConnectionDescriptor().getConnection();
    }

    /**
     * Ottiene il tipo di database relativo alla connessione corrente.
     * @deprecated Usare getCurrentDatabase()
     * @see com.thera.thermfw.persist.ConnectionManager#getCurrentDatabase()
     * @return il tipo di database corrente.
     */
    public static Database getCurrDatabase()
    {
        return getCurrentConnectionDescriptor().getDatabase();
    }

    /**
     * Ottiene l'URL del database relativo alla connessione corrente.
     * @deprecated Usare getCurrentDBURL()
     * @see com.thera.thermfw.persist.ConnectionManager#getCurrentDBURL()
     * @return lo URL del database corrente.
     */
    public static String getURL()
    {
        return getCurrentConnectionDescriptor().getDBURL();
    }

    /**
     * Ottiene il nome del database relativo alla connessione corrente.
     * @deprecated Usare getCurrentDBName()
     * @see com.thera.thermfw.persist.ConnectionManager#getCurrentDBName()
     * @return il nome del database corrente.
     */
    public static String getDbName()
    {
        return getCurrentConnectionDescriptor().getDBName();
    }

    /**
     * Ottiene il nome dell'utente relativo alla connessione corrente.
     * @deprecated Usare getCurrentUser()
     * @see com.thera.thermfw.persist.ConnectionManager#getCurrentUser()
     * @return il nome dell'utente database corrente.
     */
    public static String getUserId()
    {
        return getCurrentConnectionDescriptor().getUser();
    }

    /**
     * Ottiene la password dell'utente relativo alla connessione corrente.
     * @deprecated Usare getCurrentPassword()
     * @see com.thera.thermfw.persist.ConnectionManager#getCurrentPassword()
     * @return la password dell'utente database corrente.
     */
    public static String getPassword()
    {
        return getCurrentConnectionDescriptor().getPassword();
    }

    /**
     * Chiude tutte le connessioni.
     * @deprecated Usare closeAllConnections()
     * @see com.thera.thermfw.persist.ConnectionManager#closeAllConnections()
     * @exception java.sql.SQLException se ci sono problemi durante la chiusura della connessione.
     */
    public static void closeConnection() throws SQLException
    {
        closeAllConnections();
    }

    /**
     * Apre la connessione principale.
     * @param aDBName il nome del database.
     * @param aUser l'utente.
     * @param aPassword la password dell'utente.
     * @param aDB il tipo di database.
     * @exception java.sql.SQLException se ci sono problemi durante l'apertura della connessione.
     * @see com.thera.thermfw.persist.ConnectionDescriptor#openConnection()
     */
    public static void openMainConnection(String aDBName, String aUser, String aPassword, Database aDB) throws SQLException
    {
        openMainConnection(new ConnectionDescriptor(aDBName, aUser, aPassword, aDB));
    }

    /**
     * Apre la connessione principale.
     * @param cd Un ConnectionDescriptor contenente i parametri di connessione.
     * @exception java.sql.SQLException se ci sono problemi durante l'apertura della connessione.
     * @see com.thera.thermfw.persist.ConnectionDescriptor#openConnection()
     */
    public static synchronized void openMainConnection(ConnectionDescriptor cd) throws SQLException
    {
        cvInstance.openMainConnection(cd);
    }

    /**
     * Ottiene il ConnectionDescriptor principale.
     * @return il ConnectionDescriptor contenente la connessione principale
     * e i suoi parametri.
     */
    public static ConnectionDescriptor getMainConnectionDescriptor()
    {
        return cvInstance.getMainConnectionDescriptor();
    }

    /**
     * Chiude la connessione principale.
     * @exception java.sql.SQLException se ci sono problemi durante la chiusura.
     * @see com.thera.thermfw.persist.ConnectionDescriptor#closeConnection()
     */
    public static synchronized void closeMainConnection() throws SQLException
    {
        cvInstance.closeMainConnection();
    }

    /**
     * Chiude la connessione principale e tutte quelle secondarie.
     * @exception java.sql.SQLException se ci sono problemi durante la chiusura.
     * @see com.thera.thermfw.persist.ConnectionDescriptor#closeConnection()
     */
    public synchronized static void closeAllConnections() throws SQLException
    {
        cvInstance.closeAllConnections();
    }

    /**
     * Ottiene il ConnectionDescriptor corrente.
     * @return il ConnectionDescriptor contenente la connessione corrente
     * e i suoi parametri.
     */
    public static ConnectionDescriptor getCurrentConnectionDescriptor()
    {
        return cvInstance.getCurrentConnectionDescriptor();
    }

    /**
     * Ottiene la sessione JMS corrente.
     * @return la sessione JMS corrente.
     */
    public static QueueSession getCurrentQueueSession() throws NamingException, JMSException
    {
        return getCurrentConnectionDescriptor().getQueueSession();
    }

    /**
     * Ottiene la connessione corrente.
     * @return la connessione corrente.
     */
    public static Connection getCurrentConnection()
    {
        return getCurrentConnectionDescriptor().getConnection();
    }

    /**
     * Ottiene il tipo di database relativo alla connessione corrente.
     * @return il tipo di database corrente.
     */
    public static Database getCurrentDatabase()
    {
        return getCurrentConnectionDescriptor().getDatabase();
    }

    /**
     * Ottiene lo URL del database relativo alla connessione corrente.
     * @return lo URL del database corrente.
     */
    public static String getCurrentDBURL()
    {
        return getCurrentConnectionDescriptor().getDBURL();
    }

    /**
     * Ottiene il nome del database relativo alla connessione corrente.
     * @return il nome del database corrente.
     */
    public static String getCurrentDBName()
    {
        return getCurrentConnectionDescriptor().getDBName();
    }

    /**
     * Ottiene il nome dell'utente relativo alla connessione corrente.
     * @return il nome dell'utente database corrente.
     */
    public static String getCurrentUser()
    {
        return getCurrentConnectionDescriptor().getUser();
    }

    /**
     * Ottiene la password dell'utente relativo alla connessione corrente.
     * @return la password dell'utente database corrente.
     */
    public static String getCurrentPassword()
    {
        return getCurrentConnectionDescriptor().getPassword();
    }

    /**
     * Esegue l'operazione di commit sulla connessione corrente.
     * @exception java.sql.SQLException se ci sono problemi durante l'operazione.
     */
    public static void commit() throws SQLException
    {
        getCurrentConnectionDescriptor().commit();
    }

    /**
     * Esegue l'operazione di rollback sulla connessione corrente.
     * @exception java.sql.SQLException se ci sono problemi durante l'operazione.
     */
    public static void rollback() throws SQLException
    {
        getCurrentConnectionDescriptor().rollback();
    }

    /**
     * Duplica la connessione corrente.
     * In cima allo stack viene inserito un ConnectionDescriptor contenente una
     * connessione, pescata dal pool o nuova, con gli stessi parametri della connessione
     * corrente.
     * @return il ConnectionDescriptor contente la connessione e i suoi parametri.
     * @exception java.sql.SQLException se ci sono problemi durante l'eventuale
     * apertura della connessione.
     */
    public static ConnectionDescriptor pushConnection() throws SQLException
    {
        return cvInstance.pushConnection();
    }

    /**
     * Inserisce in cima allo stack un ConnectionDescriptor.
     * Il comportamento dipende dalla presenza o meno della connessione all'interno
     * del ConnectionDescriptor passato.
     * <ul>
     * <li>Se la connessione è presente, il ConnectionDescriptor passato viene semplicemente
     * messo in cima allo stack.</li>
     * <li>Se la connessione non è presente, viene cercata all'interno del pool una connessione
     * con lo stesso URL e lo stesso utente contenuti nel ConnectionDescriptor passato. Se questa
     * non viene trovata, viene aperta una nuova connessione con i parametri contenuti nel
     * ConnectionDescriptor. Altrimenti viene usata la connessione trovata nel pool.</li>
     * </ul>
     * @param cd Il ConnectionDescriptor da inserire in cima allo stack.
     * @return il ConnectionDescriptor passato.
     * @exception java.sql.SQLException se ci sono problemi durante l'eventuale
     * apertura della connessione.
     */
    public static ConnectionDescriptor pushConnection(ConnectionDescriptor cd) throws SQLException
    {
        return cvInstance.pushConnection(cd);
    }

    /**
     * Rimuove il ConnectionDescriptor in cima allo stack.
     * Il ConnectionDescriptor rimosso viene inserito nel pool senza chiudere
     * la sua connessione, e rimane disponibile per soddisfare una futura richiesta
     * di <tt>pushConnection()</tt>.
     * @return il ConnectionDescriptor rimosso.
     */
    public static ConnectionDescriptor popConnection()
    {
        return cvInstance.popConnection();
    }

    /**
     * Rimuove il ConnectionDescriptor in cima allo stack.
     * Il ConnectionDescriptor <b>non</b> viene inserito nel pool.
     * @param cd Il ConnectionDescriptor da rimuovere.
     * @return il ConnectionDescriptor rimosso.
     * @exception java.lang.IllegalArgumentException se il ConnectionDescriptor
     * passato non si trova in cima allo stack.
     */
    public static ConnectionDescriptor popConnection(ConnectionDescriptor cd)
    {
        return cvInstance.popConnection(cd);
    }

    /**
     * Ottiene una stringa che descrive lo stato interno del ConnectionManager.
     * @return una stringa che descrive lo stato interno del ConnectionManager.
     */
    public static String dump()
    {
        return cvInstance.dump();
    }

    public static int getCurrentStackSize()
    {
        return cvInstance.getCurrentStackSize();
    }

    //fix 41683 - inizio
    public static boolean isCurrentStackAvailable() {
    	if (cvInstance == null) {
    		return false;
    	}
    	
    	if (cvInstance.getCurrentStack() == null) {
    		return false;
    	}
    	
    	return true;
    }
    //fix 41683 - fine
    
    // Se non è riuscito a fare push restituisce -1, ma niente eccezioni.
    // Se ce ne sono le scrive in Trace.excStream.
    // Anche se non riesce a impostare il livello ma la push() è riuscita restituisce
    // un numero != -1 per indicare che si deve fare la pop()
    public static int beginUncommittedRead()
    {
        int ret = -1;
        if (getCurrentDatabase() instanceof OracleDatabase)
            return ret;
        try
        {
            pushConnection();
        }
        // se non riesco a fare push() me ne vado con -1
        catch (SQLException e)
        {
            e.printStackTrace(Trace.excStream);
            return ret;
        }
        try
        {
            ret = getCurrentConnection().getTransactionIsolation();
            getCurrentConnection().setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
        }
        // questo non dovrebbe succedere, la connessione è "pulita":
        catch (SQLException e)
        {
            e.printStackTrace(Trace.excStream);
        }
        return ret;
    }

    // -1 segnala che la begin non aveva avuto successo, e quindi non facciamo niente.
    public static void endUncommittedRead(int oldLevel)
    {
        if (oldLevel == -1)
            return;
        boolean oldLevelRestored = false;
        try
        {
            oldLevelRestored = getCurrentConnection().getTransactionIsolation() == oldLevel;
            if (!oldLevelRestored)
            {
                commit();
                getCurrentConnection().setTransactionIsolation(oldLevel);
            }
            popConnection();
        }
        catch (SQLException e)
        {
            if (!oldLevelRestored) // non è possibile ripristinare, tolgo la connessione dal manager e la chiudo.
                throwAwayCurrentConnection();
        }
    }

    // verificare... con DS cosa si fa?
    protected static void throwAwayCurrentConnection()
    {
        ConnectionDescriptor cd = getCurrentConnectionDescriptor();
        popConnection(cd);
        try
        {
            cd.closeConnection();
        }
        catch (SQLException e)
        {
            e.printStackTrace(Trace.excStream);
        }
    }
}