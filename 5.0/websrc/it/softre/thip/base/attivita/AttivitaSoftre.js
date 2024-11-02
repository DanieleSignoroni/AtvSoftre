function AttivitaSoftreOL() {
	window.resizeTo(window.screen.availWidth, window.screen.availHeight);
	document.getElementById('QuotazioneOre').addEventListener('change', valorizzaGiorni);
	document.getElementById('DocumentazioneFrame').src = document.getElementById('LinkDocumentazione').value;
	document.getElementById('AnalisiFrame').src = document.getElementById('LinkAnalisi').value;
	//Se non ho il cliente non permetto di inserire una fix
	var oldeditGridNewRow = editGridNewRow;
	editGridNewRow = function(classHdrName) {
		if (classHdrName == 'AttivitaFixes') {
			let idCliente = document.getElementById('IdClienteSoftre').value;
			if (idCliente != null && idCliente != undefined && idCliente != "")
				oldeditGridNewRow(classHdrName);
		} else {
			oldeditGridNewRow(classHdrName);
		}
	}
	document.getElementById('NomeAttivita').focus();
}

function valorizzaGiorni(event) {
	let ore = document.getElementById('QuotazioneOre');
	let giorni = document.getElementById('QuotazioneGg');
	if (ore != null && ore != undefined && ore != "") {
		let ggs = (parseFloat(ore.value) / 8).toFixed(2);
		ggs = ggs.toString().replace('.', ',');
		giorni.value = ggs;
	}
}

function getURLWS() {
	var ris;
	var url = window.location.href;
	var cut = url.indexOf(webAppPath);
	ris = url.substring(0, cut);
	ris += webAppPath;
	ris += "/api";
	return ris;
}

function getBearerTokenFromLocalStorage() {
	var jwt = null;
	jwt = 'Bearer ' + localStorage.getItem("pth-" + location.pathname.split("/")[1].toLowerCase() + "-jwt");
	return jwt;
}

function sendMessage(idAttivita, message, file) {
    var formData = new FormData();
    formData.append("IdAttivita", idAttivita);
    formData.append("Message", message);

    if (file) {
        formData.append("file", file);
    }

    $.ajax({
        url: getURLWS() + '/softre/attivita/chat/ricevi',
        method: 'POST',
        data: formData,
        processData: false,
        contentType: false,
        beforeSend: function(xhr) {
            xhr.setRequestHeader('Authorization', getBearerTokenFromLocalStorage());
        },
        success: function(response) {
            console.log('Message sent successfully:', response);
            fetchChatMessages();
        },
        error: function(xhr, status, error) {
            console.error('Error sending message:', error);
        }
    });
}

function deleteMessage(idMessage){
	 $.ajax({
        url: getURLWS() + '/softre/attivita/chat/messaggio/elimina',
        method: 'POST',
        contentType: 'application/json',
		data: JSON.stringify({
			"ChiaveMessaggio": idMessage
		}),
        beforeSend: function(xhr) {
            xhr.setRequestHeader('Authorization', getBearerTokenFromLocalStorage());
        },
        success: function(response) {
            console.log('Message sent successfully:', response);
            fetchChatMessages();
        },
        error: function(xhr, status, error) {
            console.error('Error deleting message:', error);
        }
    });
}


