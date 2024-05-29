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
if(utenteAzienda.getDipendente() != null){
	if(utenteAzienda.getDipendente().getURLImmagineDipendente() != null){
		image = utenteAzienda.getDipendente().getURLImmagineDipendente();
	}
}
if(image == null)
	image = "https://tacm.com/wp-content/uploads/2018/01/no-image-available.jpeg";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Chat</title>
<!-- MDBootstrap CSS -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/3.10.2/mdb.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="it/softre/thip/base/font-awesome/css/font-awesome.min.css">
<style>
.chat-container {
	position: relative;
	height: 70vh;
	overflow: auto;
}
</style>
</head>
<body>
	<div id="chat-container"></div>
	<div
		class="card-footer text-muted d-flex justify-content-start align-items-center p-3">
		<img
			src="<%=image %>"
			alt="avatar 3" style="width: 40px; height: 100%;"> <textarea
			class="form-control form-control-lg" id="messageInput"
			placeholder="Type message"></textarea> <a class="ms-3" onclick="sendMessageDaButton()"><i
			class="fa fa-paper-plane fa-3x"></i></a>
	</div>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script>
        $(document).ready(function() {
        	fetchChatMessages();
            var pollingInterval = 5000;
//             var pollingTimer = setInterval(fetchChatMessages, pollingInterval);
            $('#messageInput').on('keydown', function(e) {
            	if ((e.ctrlKey || e.metaKey) && (e.keyCode == 13 || e.keyCode == 10)) {
                    event.preventDefault(); 
                    const message = $('#messageInput').val(); 
                    debugger;
                    sendMessage(<%=attivita.getId()%>,message);
                    $('#messageInput').val('');
                    fetchChatMessages();
                }
            });
        });
        
        function fetchChatMessages() {
            $.ajax({
                url: getURLWS() + '/softre/attivita/chat/html',
                method: 'GET',
                data: {
                    "IdAttivita": '<%=attivita.getId()%>',
                },
				beforeSend: function(xhr) {
					xhr.setRequestHeader('Authorization', getBearerTokenFromLocalStorage());
				},
                success: function(response) {
                    $('#chat-container').html(response);
                    var elem = document.getElementById('chatBody');
                    elem.scrollTop = elem.scrollHeight;
                },
                error: function(xhr, status, error) {
                    console.error('Error fetching chat HTML:', error);
                }
            });
    	}
        
        function sendMessageDaButton(){
        	const message = $('#messageInput').val(); 
            sendMessage(<%=attivita.getId()%>,message);
            $('#messageInput').val('');
            fetchChatMessages();
        }
        
    </script>
</body>
</html>
