(function($) {
    "use strict";
	
	function setDataCard(){
		var stringData = document.getElementById("cardData").value.split(" ");
		for(let i = 0; i < stringData.length; i++){
			let percent = parseFloat(stringData[i]);
			let color = getClassColor(percent);
			let id="td" + (i+1).toString();
			document.getElementById("title-Tc" + (i+1).toString()).innerHTML = percent.toString() + '%';
			document.getElementById(id).style = "width :" + percent + '%';
			$("#" + id).addClass(color);
		}
	}
	
	function getClassColor(percent) {
		var colors = ['bg-danger', 'bg-warning', 'bg-primary', 'bg-info', 'bg-success'];
		for(let i = 0; i < 4; i++) {
			if(percent <= (i+1)*20) return colors[i];
		}
		return colors[4];
	}
	
	$(document).ready(function() {
		setDataCard();
	});

}(jQuery));