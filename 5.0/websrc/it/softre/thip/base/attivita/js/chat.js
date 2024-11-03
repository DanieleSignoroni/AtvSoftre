var formData = null;
var fetchInterval;
var fetching = false;

$(document).ready(function() {

	formData = new FormData();

	fetchChatMessages();

	$('#message-input').on('keydown', function(e) {
		if ((e.ctrlKey || e.metaKey) && (e.keyCode == 13 || e.keyCode == 10)) {
			e.preventDefault();
			let message = $('#message-input').text().trim();
			const file = formData.get('file');
			if (message != "" || file != null) {
				sendMessage(idAttivita, message, file);
			}
		}
	});

	$('#fileInput').change(function() {
		var file = $('#fileInput')[0].files[0];
		if (file) {
			formData.append('file', file);

			$('#fileInput').val('');
		}
	});

	$(document).on('click', '.attachment-image', function() {
		var imageUrl = $(this).attr('src');

		var modalHtml = '<div class="modal">' +
			'<div class="modal-content">' +
			'<span class="close">&times;</span>' +
			'<img src="' + imageUrl + '" class="modal-image">' +
			'</div>' +
			'</div>';

		$('body').append(modalHtml);

		$(document).on('click', '.close, .modal', function() {
			$('.modal').remove();
		});

		return false;
	});

	$('#chat-container').on('scroll', function() {
		if (!isAtBottom()) {
			stopFetchInterval();
		} else {
			startFetchInterval();
		}
	});

	$('#file-upload-icon').on('click', function() {
		$('#file-input').click();
	});

	$('#send-message-icon').on('click', function() {
		const file = formData.get('file');
		let message = $('#message-input').text().trim();
		if (message != "" || file != null) {
			sendMessage(idAttivita, message, file);
		}
	});

	$('#file-input').on('change', function(event) {
		const file = event.target.files[0];
		if (file) {
			if (file.type.startsWith('image/')) {
				const reader = new FileReader();
				reader.onload = function(event) {
					displayImagePreview(event);
				};
				reader.readAsDataURL(file);
			} else {
				displayFileStatus(file.name);
			}
			formData.append('file', file);
		}
		$('#file-input').val('');
	});

	startFetchInterval();
	document.getElementById('message-input').addEventListener('paste', handlePaste);
});

function startFetchInterval() {
	fetchInterval = setInterval(function() {
		if (isAtBottom() && !fetching) {
			fetchChatMessages();
			fetching = true;
			console.log('Sono alla fine quindi fetch mex');
		}
	}, 3000);
}

function stopFetchInterval() {
	clearInterval(fetchInterval);
	fetching = false;
}

function isAtBottom() {
	const scrollTop = $('#chat-container').scrollTop();
	const innerHeight = $('#chat-container').innerHeight();
	//const scrollHeight = $('#chatBody')[0].scrollHeight;
	const scrollHeight = $('#chat-container')[0].scrollHeight;
	let lengthFigli = $('#chatBody').children().length;

	let isAtBottom = scrollTop + innerHeight >= scrollHeight;
	if (lengthFigli == 0)
		isAtBottom = false;
	return isAtBottom;
}

function displayImagePreview(event) {
	const imageContainer = document.createElement('div');
	imageContainer.style.position = 'relative';

	const img = document.createElement('img');
	img.src = event.target.result;
	img.style.maxWidth = '100%';

	const cross = document.createElement('span');
	cross.className = 'cross-button';

	cross.addEventListener('click', function() {
		imageContainer.remove();
	});

	imageContainer.appendChild(img);
	imageContainer.appendChild(cross);
	document.getElementById('message-input').appendChild(imageContainer);
}

function displayFileStatus(fileName) {
	const fileStatusContainer = document.getElementById('file-status-container');
	fileStatusContainer.innerHTML = '';

	const fileStatus = document.createElement('div');
	fileStatus.className = 'file-status';
	fileStatus.id = 'file-status';

	const fileNameSpan = document.createElement('span');
	fileNameSpan.textContent = `File: ${fileName}`;

	const removeButton = document.createElement('button');
	removeButton.textContent = 'X';
	removeButton.className = 'remove-button';

	removeButton.addEventListener('click', function() {
		fileStatusContainer.innerHTML = '';
		formData.delete('file');
	});

	fileStatus.appendChild(fileNameSpan);
	fileStatus.appendChild(removeButton);
	fileStatusContainer.appendChild(fileStatus);
}

function handlePaste(event) {
	const clipboardItems = event.clipboardData.items;
	for (let i = 0; i < clipboardItems.length; i++) {
		const item = clipboardItems[i];
		if (item.type.startsWith('image/')) {
			event.preventDefault();
			const blob = item.getAsFile();
			formData.append('file', blob);
			const reader = new FileReader();
			reader.onload = function(event) {
				displayImagePreview(event);
			};
			reader.readAsDataURL(blob);
		}
	}
}

function sendMessage(idAttivita) {
	stopFetchInterval();
	let file = null;
	if (formData.has('file')) {
		file = formData.get('file');
	}

	const data = new FormData();
	data.append('IdAttivita', idAttivita);
	data.append('Message', $('#message-input').text());
	if (file) {
		data.append('file', file);
	}

	fetch(getURLWS() + '/softre/attivita/chat/ricevi', {
		method: 'POST',
		body: data,
		headers: {
			'Authorization': getBearerTokenFromLocalStorage()
		}
	}).then(response => {
		if (!response.ok) {
			throw new Error('Network response was not ok ' + response.statusText);
		} else {
			$('#message-input').html('');
			formData.delete('file');
			if (document.getElementById('file-status') != undefined && document.getElementById('file-status') != null) {
				document.getElementById('file-status').innerHTML = '';
			}
			document.getElementById('file-status-container').innerHTML = '';

			fetchChatMessages();

		}
		return response;
	}).catch(error => {
		console.error('Error sending message:', error);
	});
}

function downloadAttachment(element) {
	var input = element.parentNode.parentNode.querySelectorAll('.attachment-content')[0];
	if (input != undefined) {
		var base64Content = input.value;
		const decodedContent = atob(base64Content);

		const blob = new Blob([decodedContent], { type: 'text/plain' });

		const downloadLink = document.createElement('a');
		downloadLink.href = URL.createObjectURL(blob);
		downloadLink.download = input.dataset.filename;

		downloadLink.click();

		URL.revokeObjectURL(downloadLink.href);
	}
}

function deleteMessage(idMessage) {
	fetch(getURLWS() + '/softre/attivita/chat/messaggio/elimina', {
		method: 'POST',
		body: JSON.stringify({
			"ChiaveMessaggio": idMessage
		}),
		headers: {
			'Authorization': getBearerTokenFromLocalStorage(),
			'Content-Type': 'application/json'
		}
	}).then(response => {
		if (!response.ok) {
			throw new Error('Network response was not ok ' + response.statusText);
		}
		return response;
	}).catch(error => {
		console.error('Error sending message:', error);
	});
	fetchChatMessages();
}

function fetchChatMessages() {
	$.ajax({
		url: getURLWS() + '/softre/attivita/chat/html',
		method: 'GET',
		data: {
			"IdAttivita": idAttivita,
		},
		beforeSend: function(xhr) {
			xhr.setRequestHeader('Authorization', getBearerTokenFromLocalStorage());
		},
		success: function(response) {
			$('#chat-container').html(response);
			let chatContainer = document.getElementById("chat-container");
			//chatContainer.scrollTop = chatContainer.scrollHeight;
			fetching = false;
		},
		error: function(error) {
			console.error('Error fetching chat HTML:', error);
		}
	});

}