window.onload = initAll;

function initAll(){
	getAllElements();
	setAllActions();
}

var states,cities;
function getAllElements(){
	states = document.getElementById("states");
	cities = document.getElementById("cities");
}

function setAllActions(){
	states.onchange = getCities;
}

var creq;
function getCities(){
	creq = new XMLHttpRequest();
	creq.open('get','ajaxcollectcities.tk?stateId='+this.value,true);
	creq.onreadystatechange = showCities;
	creq.send(null);
}

function showCities(){
	if(creq.readyState==4&&creq.status==200){
		var cit = eval(creq.responseText);
		cities.innerHTML="<option value='0'>-select-</option>";
		for(i=0;i<cit.length;i++){
			var opt = document.createElement('option');
			opt.text = cit[i].cityName;
			opt.value = cit[i].cityId;
			cities.appendChild(opt);
		}
	}
}