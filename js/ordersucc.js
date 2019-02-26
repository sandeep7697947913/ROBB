window.onload = initAll;

function initAll(){
	getAllElements();
	setAllActions();
}

var time,t=5;
function getAllElements(){
	time = document.getElementById('time');
}

var interval;
function setAllActions(){
	time.innerHTML = t--; 
	interval = setInterval(setTime,1000);
}

function setTime(){
	if(t==0){
		clearInterval(interval);
		location.href = "index.jsp";
	}else{
		time.innerHTML = t--;
	}
}