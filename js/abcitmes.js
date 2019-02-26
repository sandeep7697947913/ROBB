window.onload = initAll;

function initAll(){
	getAllElements();
	setAllActions();
}

var paybutton;
function getAllElements(){
	paybutton = document.getElementById('paybutton');
}

function setAllActions(){
	paybutton.onclick = function(){
		location.href = 'paymentpage.cpf';
	}
}