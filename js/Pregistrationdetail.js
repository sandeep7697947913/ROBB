window.onload = initAll;

function initAll(){
	getAllElements();

	setAllActions();
}

var nh,backlink,selectstate,selectcity;

backlink = "registrationpageforward.do";

function getAllElements(){
	selectstate = document.getElementById('selectstate');
	selectcity = document.getElementById('selectcity');
	nh = document.getElementById('nh');
}

function setAllActions(){
	selectstate.onchange = collectCities;
}

var reqCC;
function collectCities(){
		reqCC = new XMLHttpRequest();
		reqCC.open('get','ajaxcollectcities.tk?stateId='+selectstate.value,true);
		reqCC.onreadystatechange = showcities;
		reqCC.send(null);
}

function showcities(){
	if(reqCC.readyState==4&&reqCC.status==200){
		var cities = eval(reqCC.responseText);
		//alert(reqCC.responseText);
		//alert(cities);
		//alert(cities.length);
		selectcity.innerHTML="<option value='0'>-select-</option>";
		for(i=0;i<cities.length;i++){
			var opt = document.createElement('option');
			opt.text = cities[i].cityName;
			opt.value = cities[i].cityId;
			//alert(opt.value);
			selectcity.appendChild(opt);
		}
	}
}

function validateForm(){
	if(selectcity.value==0){
		alert("please select city");
		return false;
	}
}

function forwardRegistrationPage(){
	location.href = backlink;
}