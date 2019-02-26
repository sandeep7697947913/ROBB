window.onload = initAll;

function initAll(){
	getAllElements();
	setAllActions();
}

var button,bimage,bookImageContainer,closeImage;
function getAllElements(){
	button = document.getElementsByClassName('button');
	bimage = document.getElementById('bimage');
	bookImageContainer = document.getElementById('bookImageContainer');
	closeImage = document.getElementById('closeImage');
}

function setAllActions(){
	for(var i=0;i<button.length;i++){
		button[i].onclick = showBookImage;
	}	
}

function showBookImage(){
	var thistr = this.parentNode.parentNode;
	var last = (window.location.href).lastIndexOf('/');
	var string = (window.location.href).substring(0,last);
	var url = decodeURIComponent(string.concat("/data/booksimages/"+thistr.getAttribute('bookId')+".jpg"));
	bimage.src = url;
	bookImageContainer.style.width = "20%";
		closeImage.onclick = function(){
			bookImageContainer.style.width = "0%";
		};
	
	/*
	// dont touch it works
	var thistr = this.parentNode.parentNode;
	var isbn = (thistr.cells[2].innerHTML);
	var last = (window.location.href).lastIndexOf('/');
	var string = (window.location.href).substring(0,last);
	window.open(decodeURIComponent(string.concat("/data/booksimages/"+thistr.getAttribute('bookId')+".jpg")), "myWindow");
	*/
}