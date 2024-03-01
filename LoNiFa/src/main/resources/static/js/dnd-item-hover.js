document.addEventListener('DOMContentLoaded', (event) => {
    let tooltipsCache = {};
    console.log('DOM fully loaded and parsed');

    document.querySelectorAll('.item-name').forEach(item => {
        item.addEventListener('mouseenter', function (event) {
            const itemId = this.dataset.itemId;
            if (!itemId) return; // Keine Aktion, wenn keine Item-ID vorhanden ist
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
        });

        item.addEventListener('mousemove', function (event) {
            updateTooltipPosition(event);
        });

        item.addEventListener('mouseleave', function () {
            const tooltip = document.getElementById('item-tooltip');
            tooltip.style.display = 'none';
        });
    });

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
});