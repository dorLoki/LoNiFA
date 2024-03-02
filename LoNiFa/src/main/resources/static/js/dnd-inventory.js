let tooltipsCache = {};

function allowDrop(event) {
    event.preventDefault();
}

function drag(event) {
    hideTooltip();
    event.dataTransfer.setData("text", event.target.id);
}

function drop(event) {
    event.preventDefault();
    var sourceId = event.dataTransfer.getData("text");
    var targetId = event.target.id;
    swapItems(sourceId, targetId);
}

function swapItems(sourceId, targetId) {
    if (sourceId == null || targetId == null) {
        return;
    }
    if (sourceId === '' || targetId === '') {
        return;
    }
    if (sourceId === targetId) {
        return;
    }

    var sourceElement = document.getElementById(sourceId);
    var targetElement = document.getElementById(targetId);

    // Temporäre Speicherung der Daten zum Tauschen
    var tempHtml = sourceElement.innerHTML;
    var tempDataItemId = sourceElement.getAttribute('data-item-id');
    var tempClass = sourceElement.className;
    var tempDraggable = sourceElement.draggable;
    var tempOnmouseenter = sourceElement.onmouseenter;
    var tempOnMouseMove = sourceElement.onmousemove;
    var tempOnmouseleave = sourceElement.onmouseleave;

    // Tauschen der Inhalte und data-item-id Attribute
    sourceElement.innerHTML = targetElement.innerHTML;
    sourceElement.setAttribute('data-item-id', targetElement.getAttribute('data-item-id'));
    sourceElement.className = targetElement.className;
    sourceElement.draggable = targetElement.draggable;
    sourceElement.onmouseenter = targetElement.onmouseenter;
    sourceElement.onmousemove = targetElement.onmousemove;
    sourceElement.onmouseleave = targetElement.onmouseleave;


    targetElement.innerHTML = tempHtml;
    targetElement.setAttribute('data-item-id', tempDataItemId);
    targetElement.className = tempClass;
    targetElement.draggable = tempDraggable;
    targetElement.onmouseenter = tempOnmouseenter;
    targetElement.onmousemove = tempOnMouseMove;
    targetElement.onmouseleave = tempOnmouseleave;


    var inventoryId = document.querySelector('.inventory-list').getAttribute('data-inventory-id');

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
                console.log('Items tauschen erfolgreich!');
            } else {
                console.log('Items tauschen fehlgeschlagen! Fehler: ' + response.status + ' ' + response.statusText);
                // Tauschen Sie die Elemente zurück, wenn der Server-Call fehlschlägt
                targetElement.innerHTML = sourceElement.innerHTML;
                targetElement.setAttribute('data-item-id', sourceElement.getAttribute('data-item-id'));
                targetElement.className = sourceElement.className;
                targetElement.draggable = sourceElement.draggable;
                targetElement.onmouseenter = sourceElement.onmouseenter;
                targetElement.onmousemove = sourceElement.onmousemove;
                targetElement.onmouseleave = sourceElement.onmouseleave;


                sourceElement.innerHTML = tempHtml;
                sourceElement.setAttribute('data-item-id', tempDataItemId);
                sourceElement.className = tempClass;
                sourceElement.draggable = tempDraggable;
                sourceElement.onmouseenter = tempOnmouseenter;
                sourceElement.onmousemove = tempOnMouseMove;
                sourceElement.onmouseleave = tempOnmouseleave;


                console.log('Items wurden zurück getauscht!');
            }
        });
}

function itemHoverMouseEnter(obj, event) {
    const itemId = obj.getAttribute("data-item-id");
    if (!itemId) {
        const tooltip = document.getElementById('item-tooltip');
        tooltip.style.display = 'none';
        tooltip.innerHTML = null;
        return;
    }
    if (tooltipsCache[itemId]) {
        displayTooltip(tooltipsCache[itemId]);
    } else {
        console.log('Fetching tooltip for itemId ' + itemId)
        fetch('get-item-tooltip/' + itemId, { headers: { 'Accept': 'application/json' } })
            .then(response => {
                if (!response.ok) {
                    if (response.status === 404) {
                        return "Error 404 not found";
                    } else {
                        throw new Error('Server response wasn\'t OK');
                    }
                }
                return response.json();
            })
            .then(data => {
                let toolTipContent = `<Strong>Name:</Strong> ${data.name} <br> <Strong>Beschreibung:</Strong> ${data.description} <br> <Strong>Gewicht:</Strong> ${data.weight} kg <br> <Strong>Wert:</Strong> ${data.value} Gold <br> <Strong>Basiswert:</Strong> ${data.itemValue} <br> <Strong>Typ:</Strong> ${data.equipmentType} <br>`;

                for (const [key, value] of Object.entries(data.attributes)) {
                    if (value > 0) {
                        toolTipContent += `<span class="attribute">+${value} ${key}</span> <br>`;
                    }
                }

                tooltipsCache[itemId] = toolTipContent;
                displayTooltip(toolTipContent);
            })
            .catch(error => {
                displayTooltip("Error loading data from server: " + error.message);
                return;
            });
    }
}

function itemHoverMouseMove(event) {
    updateTooltipPosition(event);
}

function itemHoverMouseLeave() {
    hideTooltip();
}


function hideTooltip() {
    const tooltip = document.getElementById('item-tooltip');
    tooltip.style.display = 'none';
}

function displayTooltip(data) {
    const tooltip = document.getElementById('item-tooltip');
    tooltip.innerHTML = data;
}

function updateTooltipPosition(event) {
    const tooltip = document.getElementById('item-tooltip');
    tooltip.style.left = (event.clientX + 15) + 'px';
    tooltip.style.top = (event.clientY + 15) + 'px';
    if (tooltip.style.display !== 'block') {
        tooltip.style.display = 'block';
    }
}