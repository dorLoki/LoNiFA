document.addEventListener('DOMContentLoaded', (event) => {
    var scene = new THREE.Scene();
    var camera = new THREE.PerspectiveCamera(75, window.innerWidth / window.innerHeight, 0.1, 1000);
    var renderer = new THREE.WebGLRenderer();
    var diceMesh;
    var shouldRotate = false;
    renderer.setSize(window.innerWidth, window.innerHeight);
    document.body.appendChild(renderer.domElement);

    var loader = new THREE.GLTFLoader();
    loader.load('glb/dice20.glb', function (gltf) {
        diceMesh = gltf.scene;
        scene.add(gltf.scene);
    }, undefined, function (error) {
        console.error(error);
    });

    camera.position.z = 5;
    camera.lookAt(scene.position);

    var light = new THREE.HemisphereLight(0xffffff, 0x444444);
    light.position.set(0, 0, 5);
    scene.add(light);

    var angleSliderX = document.getElementById('angleSliderX');
    var angleValueXDisplay = document.getElementById('angleValueX');

    var angleSliderY = document.getElementById('angleSliderY');
    var angleValueYDisplay = document.getElementById('angleValueY');

    var angleSliderZ = document.getElementById('angleSliderZ');
    var angleValueZDisplay = document.getElementById('angleValueZ');

    angleSliderX.addEventListener('input', function () {
        var angleValue = angleSliderX.value;
        angleValueXDisplay.textContent = angleValue;
        diceMesh.rotation.x = angleValue;
        // Aktualisieren Sie hier Ihre JavaScript-Zahl mit dem neuen Winkelwert
    });

    angleSliderY.addEventListener('input', function () {
        var angleValue2 = angleSliderY.value;
        angleValueYDisplay.textContent = angleValue2;
        diceMesh.rotation.y = angleValue2;
        // Aktualisieren Sie hier Ihre JavaScript-Zahl mit dem neuen Winkelwert
    });

    angleSliderZ.addEventListener('input', function () {
        var angleValue3 = angleSliderZ.value;
        angleValueZDisplay.textContent = angleValue3;
        diceMesh.rotation.z = angleValue3;
        // Aktualisieren Sie hier Ihre JavaScript-Zahl mit dem neuen Winkelwert
    }
    );

    var frameRotationX = 0;
    var frameRotationY = 0;
    var frameRotationZ = 0;

    var remainingFrames = 0;
    function animate() {
        requestAnimationFrame(animate);

        if (remainingFrames > 0 && diceMesh) {
            // Berechnen Sie die Drehung für diesen Frame


            // Aktualisieren Sie die aktuelle Rotation
            diceMesh.rotation.x += frameRotationX;
            diceMesh.rotation.y += frameRotationY;
            diceMesh.rotation.z += frameRotationZ;

            //debug
            //diceMesh.rotation.x = 0;
            //diceMesh.rotation.y = 0;
            //diceMesh.rotation.z = 0;



            remainingFrames--;
        }

        renderer.render(scene, camera);
    }

    const dieder = 41.81 * Math.PI / 180;
    const angle20 = 20 * Math.PI / 180;
    const angle35 = 35 * Math.PI / 180;
    const angle60 = 60 * Math.PI / 180;
    const angle90 = Math.PI / 2;
    const angle120 = 120 * Math.PI / 180;
    const angle240 = 240 * Math.PI / 180;
    const angle210 = 210 * Math.PI / 180;
    const angle215 = 215 * Math.PI / 180;
    const angle245 = 245 * Math.PI / 180;
    const angle300 = 300 * Math.PI / 180;
    const angle325 = 325 * Math.PI / 180;
    const angle200 = 200 * Math.PI / 180;
    const angle180 = Math.PI;
    const huso = dieder - angle35;

    const rotations = {
        1: { x: Math.PI * 2, y: Math.PI * 2, z: Math.PI * 2 },
        2: { x: dieder + Math.PI, y: Math.PI * 2, z: angle20 + dieder },
        3: { x: angle20 + dieder, y: angle325, z: huso },
        4: { x: angle200 + dieder, y: angle35, z: Math.PI * 2 - huso },
        5: { x: angle20 + dieder, y: angle35, z: angle240 - huso },
        6: { x: angle20 + dieder, y: angle215, z: angle60 + huso },
        7: { x: dieder, y: Math.PI * 2, z: angle300 },
        8: { x: dieder, y: Math.PI, z: Math.PI * 2 },
        9: { x: angle20 + dieder, y: angle35, z: angle120 - huso },
        10: { x: angle20 + dieder, y: angle215, z: angle300 + huso },
        11: { x: angle20 + dieder, y: angle325, z: angle120 + huso },
        12: { x: angle200 + dieder, y: angle35, z: angle120 - huso },
        13: { x: dieder + Math.PI, y: Math.PI, z: Math.PI * 2 },
        14: { x: dieder + Math.PI, y: Math.PI * 2, z: angle300 },
        15: { x: angle20 + dieder, y: angle325, z: angle240 + huso },
        16: { x: angle200 + dieder, y: angle35, z: angle240 - huso },
        17: { x: angle20 + dieder, y: angle35, z: (Math.PI * 2) - huso },
        18: { x: angle20 + dieder, y: angle215, z: angle180 + huso },
        19: { x: dieder, y: Math.PI * 2, z: angle20 + dieder },
        20: { x: Math.PI, y: Math.PI * 2, z: Math.PI * 2 }
    };

    const rotationsDice6 = {
        1: { x: Math.PI / 2, y: Math.PI * 2, z: Math.PI * 3 / 2 },
        2: { x: Math.PI, y: Math.PI, z: Math.PI },
        3: { x: Math.PI / 2, y: Math.PI * 2, z: Math.PI * 2 },
        4: { x: Math.PI * 1.5, y: Math.PI * 1, z: Math.PI * 2 },
        5: { x: Math.PI * 2, y: Math.PI, z: Math.PI },
        6: { x: Math.PI / 2, y: Math.PI * 2, z: Math.PI / 2 }
    };

    const rotationsDice4 = {
        1: { x: Math.PI * 2, y: Math.PI * 2, z: Math.PI * 2 },
        2: { x: Math.PI * 2, y: Math.PI * 2, z: Math.PI * 2 / 3 },
        3: { x: 2, y: Math.PI * 2, z: Math.PI },
        4: { x: Math.PI * 2, y: Math.PI * 2, z: Math.PI * 4 / 3 }
    };



    animate();

    function getRandomNumber() {
        shouldRotate = true;
        fetch('/api/random-20')
            .then(response => response.json())
            .then(number => {
                console.log("Die zufällige Zahl ist: ", number);
                startDiceRotation(number);
            })
            .catch(error => {
                shouldRotate = true;
                console.error('Fehler beim Abrufen der Zahl:', error)
            }
            );
    }
    var targetRotation = { x: 0, y: 0, z: 0 };

    function startDiceRotation(number) {
        diceMesh.rotation.x = 0;
        diceMesh.rotation.y = 0;
        diceMesh.rotation.z = 0;
        targetRotation = rotations[number];
        //targetRotation.x += (Math.PI * 2) * 5;
        //targetRotation.y += (Math.PI * 2) * 5;
        remainingFrames = 120;


        frameRotationX = (targetRotation.x - diceMesh.rotation.x) / remainingFrames;
        frameRotationY = (targetRotation.y - diceMesh.rotation.y) / remainingFrames;
        frameRotationZ = (targetRotation.z - diceMesh.rotation.z) / remainingFrames;


    }

    document.getElementById('rollDiceButton').addEventListener('click', getRandomNumber);

});
