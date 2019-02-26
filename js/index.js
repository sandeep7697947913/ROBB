window.onload = initAll;

function initAll(){
	getAllElements();
	setAllActions();
}

var imagetds;
function getAllElements(){
	imagetds = document.getElementsByClassName('imagetd'); 
}

function setAllActions(){
	for(var i=0;i<imagetds.length;i++){
		imagetds[i].onclick = forwardBookPage;
	}
}
 
function forwardBookPage(){
	open('itemdetail.cpf?bookId='+this.getAttribute('bookId'));
}
