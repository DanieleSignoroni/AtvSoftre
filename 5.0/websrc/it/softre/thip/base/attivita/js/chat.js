var formData = null;
var fetchInterval;

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

	function startFetchInterval() {
		fetchInterval = setInterval(function() {
			if (isAtBottom()) {
				fetchChatMessages();
			}
		}, 3000);
	}

	function stopFetchInterval() {
		clearInterval(fetchInterval);
	}

	function isAtBottom() {
		const scrollTop = $('#chat-container').scrollTop(); // Check scroll position of #chat-container
		const innerHeight = $('#chat-container').innerHeight(); // Get inner height of #chat-container
		const scrollHeight = $('#chatBody')[0].scrollHeight; // Still refer to the inner content for scroll height
		let lengthFigli = $('#chat-container').children().length;

		const isAtBottom = scrollTop + innerHeight >= scrollHeight;
		if (lengthFigli == 0)
			isAtBottom = false;
		return isAtBottom;
	}

	$('#chat-container').on('scroll', function() {
		if (!isAtBottom()) {
			stopFetchInterval(); // Stop fetching if not at bottom
		} else {
			startFetchInterval(); // Restart fetching if back at bottom
		}
	});

	$('#file-upload-icon').on('click', function() {
		$('#file-input').click(); // Opens the file picker dialog
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
			// Check if the file is an image
			if (file.type.startsWith('image/')) {
				const reader = new FileReader();
				reader.onload = function(event) {
					displayImagePreview(event); // Display image preview
				};
				reader.readAsDataURL(file); // Read the image file as a data URL
			} else {
				displayFileStatus(file.name);
			}
			formData.append('file', file);
		}
		$('#file-input').val(''); // Reset file input for future selections
	});


	startFetchInterval();
	//	document.getElementById('file-input').addEventListener('change', handleFileSelect);
	document.getElementById('message-input').addEventListener('paste', handlePaste);
});

function displayImagePreview(event) {
	// Create a container for the image
	const imageContainer = document.createElement('div');
	imageContainer.style.position = 'relative'; // Position for the cross button

	const img = document.createElement('img');
	img.src = event.target.result;
	img.style.maxWidth = '100%'; // Ensure the image fits the container

	const cross = document.createElement('span');
	cross.className = 'cross-button'; // Assign a class for styling

	// Add event listener to remove the image on click
	cross.addEventListener('click', function() {
		imageContainer.remove();
	});

	// Append the image and cross to the container
	imageContainer.appendChild(img);
	imageContainer.appendChild(cross); // Append the cross to the container
	document.getElementById('message-input').appendChild(imageContainer);
}

function displayFileStatus(fileName) {
	const fileStatusContainer = document.getElementById('file-status-container');
	fileStatusContainer.innerHTML = ''; // Clear previous status if any

	const fileStatus = document.createElement('div');
	fileStatus.className = 'file-status';
	fileStatus.id = 'file-status';

	const fileNameSpan = document.createElement('span');
	fileNameSpan.textContent = `File: ${fileName}`;

	const removeButton = document.createElement('button');
	removeButton.textContent = 'X';
	removeButton.className = 'remove-button';

	// Add event listener to remove the file from formData and UI
	removeButton.addEventListener('click', function() {
		fileStatusContainer.innerHTML = ''; // Clear file status
		formData.delete('file'); // Remove file from formData
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
	let file = null;
	if (formData.has('file')) {
		file = formData.get('file');
	}

	const data = new FormData();
	data.append('IdAttivita', idAttivita);
	data.append('Message', $('#message-input').text());
	if (file) {
		data.append('file', file); // Add the image if it exists
	}

	fetch(getURLWS() + '/softre/attivita/chat/ricevi', {
		method: 'POST',
		body: data,
		headers: {
			'Authorization': getBearerTokenFromLocalStorage() // Include auth token
		}
	}).then(response => {
		if (!response.ok) {
			throw new Error('Network response was not ok ' + response.statusText);
		} else {
			$('#message-input').html('');
			formData.forEach(function(key) {
				formData.delete(key);
			});
			if (document.getElementById('file-status') != undefined && document.getElementById('file-status') != null) {
				document.getElementById('file-status').innerHTML = '';
			}
			document.getElementById('file-status-container').innerHTML = '';

		}
		return response;
	}).catch(error => {
		console.error('Error sending message:', error);
	});
}

function downloadAttachment(element) {
	var input = element.parentNode.querySelectorAll('.attachment-content')[0];
	var base64Content = input.value;
	const decodedContent = atob(base64Content);

	const blob = new Blob([decodedContent], { type: 'text/plain' });

	const downloadLink = document.createElement('a');
	downloadLink.href = URL.createObjectURL(blob);
	downloadLink.download = input.dataset.filename;

	downloadLink.click();

	URL.revokeObjectURL(downloadLink.href);
}

function deleteMessage(idMessage) {
	fetch(getURLWS() + '/softre/attivita/chat/messaggio/elimina', {
		method: 'POST',
		body: JSON.stringify({
			"ChiaveMessaggio": idMessage
		}),
		headers: {
			'Authorization': getBearerTokenFromLocalStorage(), // Include auth token
			'Content-Type': 'application/json' // Specify the content type as JSON
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
			var elem = document.getElementById('chatBody');
			elem.scrollTop = elem.scrollHeight;
		},
		error: function(xhr, status, error) {
			console.error('Error fetching chat HTML:', error);
		}
	});

}