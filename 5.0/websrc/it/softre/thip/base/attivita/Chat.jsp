<%@page import="com.thera.thermfw.web.WebForm"%>
<%@page import="it.softre.thip.base.attivita.AttivitaChat"%>
<%@page import="java.util.List"%>
<%@page import="it.softre.thip.base.attivita.AttivitaSoftre"%>
<%
WebForm parentForm = (WebForm) request.getAttribute("parentForm");
AttivitaSoftre attivita = (AttivitaSoftre) parentForm.getBODataCollector().getBo();
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
<style>
.chat-container {
	position: relative;
	height: 78vh;
	overflow-y: auto;
}

.message {
	max-width: 70%;
}

.message.sent {
	background-color: #a2aab7;
}

.message.received {
	background-color: #f5f6f7;
}
</style>
</head>
<body>
	<div id="chat-container"></div>

	<!-- MDBootstrap JS -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/3.10.2/mdb.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script>
	var jwt = null;
	jwt = 'Bearer ' + localStorage.getItem("pth-"+location.pathname.split("/")[1].toLowerCase()+"-jwt");
        $(document).ready(function() {
        	function fetchChatMessages() {
	            $.ajax({
	                url: getURLWS() + '/softre/attivita/chat/html', // Adjust the URL to your JAX-RS endpoint
	                method: 'GET',
	                data: {
	                    "IdAttivita": '<%=attivita.getId()%>',
	                },
					beforeSend: function(xhr) {
						xhr.setRequestHeader('Authorization', jwt);  // Replace with your actual Bearer token
					},
	                success: function(response) {
	                    $('#chat-container').html(response);
	                },
	                error: function(xhr, status, error) {
	                    console.error('Error fetching chat HTML:', error);
	                }
	            });
        	}
            var pollingInterval = 5000;
            // Set up polling using setInterval
            var pollingTimer = setInterval(fetchChatMessages, pollingInterval);
        });
        
        function getURLWS() {
			var ris;
			var url = window.location.href;
			var cut = url.indexOf(webAppPath);
			ris = url.substring(0, cut);
			ris += webAppPath;
			ris += "/api";
			return ris;
		}
        
    </script>
</body>
</html>
