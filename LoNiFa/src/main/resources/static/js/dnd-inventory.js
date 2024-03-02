function allowDrop(event) {
    event.preventDefault();
}

function drag(event) {
    event.dataTransfer.setData("text", event.target.id);
}

function drop(event) {
    event.preventDefault();
    var sourceId = event.dataTransfer.getData("text");
    var targetId = event.target.id;
    swapItems(sourceId, targetId);
}

function swapItems(sourceId, targetId) {
    if(sourceId == null || targetId == null) {
        return;
    }
    if(sourceId === '' || targetId === '') {
        return;
    }
    if (sourceId === targetId) {
        return;
    }

    console.log('swapItems send request to server');
    // Senden Sie eine AJAX-Anfrage, um die Items zu vertauschen
    var inventoryTable = document.querySelector('.inventory-list');
    var inventoryId = inventoryTable.getAttribute('data-inventory-id');

    fetch('/dnd/swap-inventory-items', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            inventoryId: inventoryId,
            sourceItemId: sourceId,
            targetItemId: targetId
        })
    })
        .then(response => {
            if (response.ok) {
                location.reload();
            } else {
                // Fehlerbehandlung
            }
        });
}