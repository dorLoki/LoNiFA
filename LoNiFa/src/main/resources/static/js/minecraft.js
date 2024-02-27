$(document).ready(function() {
	var consoleContainer = $('#consoleContainer');
	var commandInput = $('#commandInput');

	function updateConsole() {
		$.ajax({
			type: 'GET',
			url: '/minecraft/console',
			success: function(data) {
				consoleContainer.html(data);
				scrollToBottom();

			},
			complete: function() {
				setTimeout(updateConsole, 1000); // Aktualisiere alle 1000 Millisekunden (1 Sekunde)
			}
		});
	}

	window.handleCommandInput = function(event) {
		if (event.key === 'Enter') {
			event.preventDefault(); // Standardaktion unterbinden
			// Hier kannst du den eingegebenen Befehl abrufen und verarbeiten
			var command = commandInput.val();
			if (command !== '') {
				sendCommand(command);
			}

			// Setze den Wert des Eingabefelds zurück
			commandInput.val('');
		}
	}

	function sendCommand(command) {
		// Sende den Befehl an den Server, um verarbeitet zu werden
		$.ajax({
			type: 'POST',
			url: '/minecraft/send',
			data: { command: command },
			success: function(data) {
				// Hier wird der Status aus der Antwort extrahiert und in die Variable status gespeichert
				var status = data;
				console.log(data);
				//set status header
				var statusHeader = document.querySelector('.statusHeader');
				if (statusHeader) {
					if (status !== '') {
						statusHeader.innerHTML = '<span>Status: ' + status + '</span>';
						statusHeader.classList.remove('hidden');
						setTimeout(function() {
							statusHeader.classList.add('hidden');
						}, 5000); // 5000 Millisekunden (5 Sekunden)
					}
				}
			}
		});
	}

	$("#restartMinecraft").click(function() {
		$.ajax({
			type: 'POST',
			url: '/minecraft/restart',
			success: function(data) {
				// Hier wird der Status aus der Antwort extrahiert und in die Variable status gespeichert
				var status = data;
				console.log(data);
				//set status header
				var statusHeader = document.querySelector('.statusHeader');
				if (statusHeader) {
					if (status !== '') {
						statusHeader.innerHTML = '<span>Status: ' + status + '</span>';
						statusHeader.classList.remove('hidden');
						setTimeout(function() {
							statusHeader.classList.add('hidden');
						}, 5000); // 5000 Millisekunden (5 Sekunden)
					}
				}
			}
		});
	});

	function scrollToBottom() {
		var consoleDiv = $('#console');
		consoleDiv.scrollTop(consoleDiv[0].scrollHeight);
	}

	// Füge das Event-Handling für die Eingabezeile hinzu
	commandInput.on('keydown', handleCommandInput);

	// Rufe die Methode zum ersten Mal auf und starte das periodische Update
	updateConsole();
});
