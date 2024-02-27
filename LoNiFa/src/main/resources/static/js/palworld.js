$(document).ready(function() {
	$("#restartPalWorld").click(function() {
		$.ajax({
			type: 'POST',
			url: '/palworld/restart',
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
});