// statusHeader.js

document.addEventListener('DOMContentLoaded', function() {
    var statusHeader = document.querySelector('.statusHeader');

    if (statusHeader) {
        setTimeout(function() {
            statusHeader.classList.add('hidden');
        }, 5000); // 5000 Millisekunden (5 Sekunden)
    }
});
