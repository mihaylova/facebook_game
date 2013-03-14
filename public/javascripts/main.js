//(function() {
//	var Game;
//	
//	Game = (function() {
//	
//	  function Game() {}
//	
//	  Game.prototype.start = function() {
//	    var _this = this;
//	    return $.ajax("/game", {
//	      dataType: 'json',
//	      success: function(data, textStatus, jqXHR) {
//	        if (data.action === 'join_game') {
//	          return _this.join();
//	        } else {
//	          return alert("bad return status: " + (JSON.stringify(data)));
//	        }
//	      }
//	    });
//	  };
//	
//	  Game.prototype.join = function() {
//	    this.connection = new WebSocket('ws://localhost:9000/game/join');
//	    this.connection.onerror = function() {
//	      return alert("WebSocket Error " + error);
//	    };
//	    this.connection.onopen = function() {
//	      return console.log('Connection opened');
//	    };
//	    this.connection.onmessage = function(e) {
//	      alert("Server: " + e.data);
//	      return this.close();
//	    };
//	  };
//	
//	  return Game;
//	
//	})();
//	
//	window.game = new Game();
//	
//	$('##start_new_game').click(function(e){
//		e.preventDefault();
//		
//		window.game.start();
//	});
//})();


$('#form-add-question').submit(function(e) {
	var form = $(this),
		inputs = form.serializeArray(),
		inputs_length = inputs.length;
	
	document.getElementById("error").style.display = 'none';
	document.getElementById("error1").style.display = 'none';
	
	form.find('.error').removeClass('error');
	
	
	for(var i = 0; i < inputs_length; i++) {
		
		if (inputs[i].value === '') {
			document.getElementById("error").style.display = 'block';
			form.find('[name=' + inputs[i].name + ']').parent().parent().addClass('error');
			e.preventDefault();
		}
		
	}
	
	 if (inputs[0].value.length > 300) {
			document.getElementById("error1").style.display = 'block';
			form.find('[name=' + inputs[0].name + ']').parent().parent().addClass('error');
			e.preventDefault();
		}
	 

	for(var i = 1; i < inputs_length; i++) {
			
			if (inputs[i].value.length > 100) {
				document.getElementById("error1").style.display = 'block';
				form.find('[name=' + inputs[i].name + ']').parent().parent().addClass('error');
				e.preventDefault();
			}
		}
	
});



//$('.btn-no-dblclick').dblclick(function(e){ 
//    e.preventDefault();
//});
function modifyOffset() {
	var el, newPoint, newPlace, offset, siblings, k;
	width    = this.offsetWidth;
	newPoint = (this.value - this.getAttribute("min")) / (this.getAttribute("max") - this.getAttribute("min"));
	offset   = -7;
	if (newPoint < 0) { newPlace = 0;  }
	else if (newPoint > 1) { newPlace = width; }
	else { newPlace = width * newPoint + offset; offset -= newPoint;}
	siblings = this.parentNode.childNodes;
	for (var i = 0; i < siblings.length; i++) {
		sibling = siblings[i];
		if (sibling.id == this.id) { k = true; }
		if ((k == true) && (sibling.nodeName == "OUTPUT")) {
			outputTag = sibling;
		}
	}
	outputTag.style.left       = newPlace + "px";
	outputTag.style.marginLeft = offset + "%";
	outputTag.innerHTML        = this.value;
}
function modifyInputs() {
    
	var inputs = document.getElementsByTagName("input");
	for (var i = 0; i < inputs.length; i++) {
		if (inputs[i].getAttribute("type") == "range") {
			inputs[i].onchange = modifyOffset;
			// the following taken from http://stackoverflow.com/questions/2856513/trigger-onchange-event-manually
			if ("fireEvent" in inputs[i]) {
			    inputs[i].fireEvent("onchange");
			} else {
			    var evt = document.createEvent("HTMLEvents");
			    evt.initEvent("change", false, true);
			    inputs[i].dispatchEvent(evt);
			}
		}
	}
}
modifyInputs();


