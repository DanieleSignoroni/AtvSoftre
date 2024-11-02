<%@page import="com.thera.thermfw.web.WebJSTypeList"%>
<%@page import="it.thera.thip.base.profilo.UtenteAzienda"%>
<%@page import="com.thera.thermfw.web.WebForm"%>
<%@page import="it.softre.thip.base.attivita.AttivitaChat"%>
<%@page import="java.util.List"%>
<%@page import="it.softre.thip.base.attivita.AttivitaSoftre"%>
<%
WebForm parentForm = (WebForm) request.getAttribute("parentForm");
AttivitaSoftre attivita = (AttivitaSoftre) parentForm.getBODataCollector().getBo();
UtenteAzienda utenteAzienda = UtenteAzienda.getUtenteAziendaConnesso();
String image = null;
if (utenteAzienda.getDipendente() != null) {
	if (utenteAzienda.getDipendente().getURLImmagineDipendente() != null) {
		image = utenteAzienda.getDipendente().getURLImmagineDipendente();
	}
}
if (image == null)
	image = "https://tacm.com/wp-content/uploads/2018/01/no-image-available.jpeg";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Chat</title>
<%
out.print(WebJSTypeList.getImportForCSS("it/softre/thip/base/attivita/css/bootstrap.min.css", request));
out.print(WebJSTypeList.getImportForCSS("it/softre/thip/base/font-awesome/css/font-awesome.min.css", request));
out.print(WebJSTypeList.getImportForCSS("it/softre/thip/base/attivita/css/chat.css", request));
out.print(WebJSTypeList.getImportForJSLibrary("it/softre/thip/base/attivita/js/bootstrap.min.js", request));
out.print(WebJSTypeList.getImportForJSLibrary("it/softre/thip/base/attivita/js/jquery.js", request));
out.print(WebJSTypeList.getImportForJSLibrary("it/softre/thip/base/attivita/js/chat.js", request));
%>
</head>
<body>
	<div id="chat-container"></div>
	<div id="footer-container">
	    <div class="user-image-container">
	        <img class="chat-user-img" src="<%=image%>" alt="avatar">
	    </div>
	    <div class="upload-file-message">
	    	<i id="file-upload-icon" class="fa fa-upload fa-3x" aria-hidden="true"></i>
	    </div>
	    <div class="input-container">
	        <div id="preview-container"></div>
	        <div id="message-input" class="message-input-contenteditable" contenteditable="true"></div>
	    </div>
	    <div class="btn-send-message">
	    	<i class="fa fa-paper-plane fa-3x" aria-hidden="true"></i>
	    </div>
	    <input type="file" id="file-input" style="display: none;">
	</div>
	<script>
		var idAttivita = <%=attivita.getId()%>;
	</script>
</body>
</html>
