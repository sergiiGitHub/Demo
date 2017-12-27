      /**
      * Reference to Firebase database.
      * @const
      */  

	  var geoFire = new GeoFire(firebase);

      /**
      * Data object to be written to Firebase.
      */
      var data = {
        sender: null,
        timestamp: null,
        lat: null,
        lng: null
      };

      function makeInfoBox(controlDiv, map) {
        // Set CSS for the control border.
        var controlUI = document.createElement('div');
        controlUI.style.boxShadow = 'rgba(0, 0, 0, 0.298039) 0px 1px 4px -1px';
        controlUI.style.backgroundColor = '#fff';
        controlUI.style.border = '2px solid #fff';
        controlUI.style.borderRadius = '2px';
        controlUI.style.marginBottom = '22px';
        controlUI.style.marginTop = '10px';
        controlUI.style.textAlign = 'center';
        controlDiv.appendChild(controlUI);

        // Set CSS for the control interior.
        var controlText = document.createElement('div');
        controlText.style.color = 'rgb(25,25,25)';
        controlText.style.fontFamily = 'Roboto,Arial,sans-serif';
        controlText.style.fontSize = '100%';
        controlText.style.padding = '6px';
        controlText.textContent = 'The map shows all clicks made in the last 10 minutes.';
        controlUI.appendChild(controlText);
      }


      /**
       * Creates a map object with a click listener and a heatmap.
       */
      function initMap() {
	var uluru = {lat: -25.363, lng: 131.044};
        var map = new google.maps.Map(document.getElementById('map'), {
          center: uluru,
          zoom: 3,
          styles: [{
            featureType: 'poi',
            stylers: [{ visibility: 'off' }]  // Turn off POI.
          },
          {
            featureType: 'transit.station',
            stylers: [{ visibility: 'off' }]  // Turn off bus, train stations etc.
          }],
          disableDoubleClickZoom: true,
          streetViewControl: false,
        });
	
	var marker = new google.maps.Marker({
          position: uluru,
          map: map
        });


        // Create the DIV to hold the control and call the makeInfoBox() constructor
        // passing in this DIV.
        var infoBoxDiv = document.createElement('div');
        makeInfoBox(infoBoxDiv, map);
        map.controls[google.maps.ControlPosition.TOP_CENTER].push(infoBoxDiv);
	
	    getDataFromFirebase();
	
      }
      
	function getDataFromFirebase(){
		console.log("getDataFromFirebase: ok");
		console.log(firebase);

		var database = firebase.database();
		var ref = database.ref('scores');
		
		var date = {
			name: "sergii",
			score: 43
		};
		
		ref.push(data);
		console.log("write data: " + data);
		

		//	geoFire.get("firebase-hq").then(function(location) {
		//	  if (location === null) {
		//	    console.log("Provided key is not in GeoFire");
		//	  } else {
		//	    console.log("Provided key has a location of " + location);
		//	  }
		//	  }, function(error) {
		//	    console.log("Error: " + error);
		//	});
	}