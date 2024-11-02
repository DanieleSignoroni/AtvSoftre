var formData = null;
var fetchInterval;

$(document).ready(function() {

	formData = new FormData();

	fetchChatMessages();

	$('#message-input').on('keydown', function(e) {
		if ((e.ctrlKey || e.metaKey) && (e.keyCode == 13 || e.keyCode == 10)) {
			e.preventDefault();
			var message = $('#message-input').text().trim();
			const file = formData.get('file');
			if (message != "" || file != null) {
				sendMessage(idAttivita, message, file);
				$('#message-input').html('');
				$('#preview-container').html('');
				//fetchChatMessages();
				formData.forEach(function(key) {
					formData.delete(key);
				});
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
		}
		return response;
	}).catch(error => {
		console.error('Error sending message:', error);
	});
}


//function viewPlainText(element) {
//	var hiddenInput = $(element).siblings('.attachment-content');
//	var base64Content = hiddenInput.val();
//	var decodedContent = atob(base64Content);
//
//	var modalHtml = '<div class="modal">' +
//		'<div class="modal-content">' +
//		'<span class="close">&times;</span>' +
//		'<pre>' + escapeHtml(decodedContent) + '</pre>' +
//		'</div>' +
//		'</div>';
//	$('body').append(modalHtml);
//
//	$(document).on('click', '.close, .modal', function() {
//		$('.modal').remove();
//	});
//}
//
//function escapeHtml(text) {
//	var div = document.createElement('div');
//	div.textContent = text;
//	return div.innerHTML;
//}

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