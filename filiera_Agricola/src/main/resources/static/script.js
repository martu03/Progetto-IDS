// // Inizializza la mappa
// const map = L.map('map').setView([43.1381, 13.0684], 14); // Coordinate di Camerino, MC come punto di partenza
//
// var Esri_WorldImagery = L.tileLayer('https://server.arcgisonline.com/ArcGIS/rest/services/World_Imagery/MapServer/tile/{z}/{y}/{x}', {
//     maxZoom: 19,
//     attribution: 'Tiles © Esri — Source: Esri, i-cubed, USDA, USGS, AEX, GeoEye, Getmapping, Aerogrid, IGN, IGP, UPR-EGP, and the GIS User Community'
// }).addTo(map);
//
// let marker;
//
// // Funzione per aggiungere un marker alla mappa con popup
// async function addMarker(lat, lng, name = null) {
//     console.log(`Adding marker at: ${lat}, ${lng}, name: ${name}`);
//     let marker = L.marker([lat, lng]).addTo(map);
//     let address = await reverseGeocode(lat, lng);
//     let popupContent = `Coordinate: ${lat}, ${lng}<br>Indirizzo: ${address}`;
//     if (name) {
//         popupContent += `<br>Nome: ${name}`;
//     }
//     marker.bindPopup(popupContent);
// }
//
// // Funzione per ottenere l'indirizzo da coordinate (Reverse Geocoding)
// async function reverseGeocode(lat, lng) {
//     const url = `https://nominatim.openstreetmap.org/reverse?lat=${lat}&lon=${lng}&format=json`;
//     try {
//         const response = await fetch(url);
//         const data = await response.json();
//         return data.display_name;
//     } catch (error) {
//         console.error("Errore durante il reverse geocoding:", error);
//         return "Indirizzo non disponibile";
//     }
// }
//
// // Funzione per cercare un indirizzo o coordinate
// let isSearching = false; // Flag per evitare多重 richieste
//
// async function search() {
//     if (isSearching) return; // Ignora se già in corso
//     isSearching = true;
//
//     const button = document.getElementById('searchButton');
//     button.innerHTML = '<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span>';
//     button.disabled = true;
//
//     try {
//         const input = document.getElementById('searchInput').value.trim();
//         if (!input) {
//             alert("Inserisci un valore valido!");
//             return;
//         }
//
//         let lat, lng, address;
//
//         if (/^-?\d+(\.\d+)?,\s*-?\d+(\.\d+)?$/.test(input)) {
//             [lat, lng] = input.split(',').map(Number);
//             addMarker(lat, lng); // Aggiungi marker direttamente
//             const url = `https://nominatim.openstreetmap.org/search?q=${encodeURIComponent(input)}&format=json`;
//             const response = await fetch(url);
//             const data = await response.json();
//
//             if (data.length === 0) throw new Error("Indirizzo non trovato!");
//             ({ lat, lon: lng, display_name: address } = data[0]);
//             addMarker(lat, lng, address); // Aggiungi marker con indirizzo
//         }
//     } catch (error) {
//         alert(error.message);
//     } finally {
//         const button = document.getElementById('searchButton');
//         button.innerHTML = '<i class="fas fa-search"></i>';
//         button.disabled = false;
//         isSearching = false; // Resetta il flag
//     }
// }
//
// // Funzione per mostrare la posizione corrente dell'utente
// function showCurrentLocation() {
//     if (navigator.geolocation) {
//         navigator.geolocation.getCurrentPosition((position) => {
//             const { latitude, longitude } = position.coords;
//             addMarker(latitude, longitude, "La tua posizione attuale");
//             const button = document.getElementById('currentLocationButton');
//             button.classList.add('active');
//             map.on('move', () => {
//                 button.classList.remove('active');
//             });
//         }, (error) => {
//             alert("Impossibile ottenere la posizione corrente: " + error.message);
//         });
//     } else {
//         alert("Geolocalizzazione non supportata dal browser.");
//     }
// }
//
// // Funzione per gestire il tasto Invio nell'input di ricerca
// function handleKeyPress(event) {
//     if (event.key === 'Enter') {
//         search();
//     }
//
//
//     // Funzione per caricare i punti geografici dall'API
//     function loadPointsFromApi() {
//         fetch('/api/mappa/punti', {
//             method: 'GET'
//         })
//             .then(response => {
//                 if (!response.ok) {
//                     throw new Error("Errore durante il caricamento dei punti.");
//                 }
//                 return response.json();
//             })
//             .then(data => {
//                 data.forEach(location => {
//                     addMarker(location.latitudine, location.longitudine, "Saved Point");
//                 });
//             })
//             .catch(error => {
//                 console.error("Error loading data:", error);
//                 alert("Impossibile caricare i punti dalla mappa.");
//             });
//     }
//
// // Chiama la funzione quando la pagina viene caricata
//     document.addEventListener('DOMContentLoaded', () => {
//         loadPointsFromApi();
//     });
//
//
//
//
//
// // Chiama la funzione quando la pagina viene caricata
//     document.addEventListener('DOMContentLoaded', () => {
//         loadPointsFromApi();
//     });
//
//
// }