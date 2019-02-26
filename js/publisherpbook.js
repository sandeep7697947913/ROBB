window.onload = initAll;

function initAll(){
	getAllElements();

	setAllActions();
}


var seleccategory,previmage,secletedimage;

function getAllElements(){
	seleccategory = document.getElementById('seleccategory');
	previmage = document.getElementById('previmage');
	secletedimage = document.getElementById('secletedimage');
}

function setAllActions(){
	collectShowCategories();
	secletedimage.onchange = showImage;
}

var catReq;
function collectShowCategories(){
	catReq = new XMLHttpRequest();
	catReq.open('get','ajaxCollectCategories.tk',true);
	catReq.onreadystatechange = showCategories; 
	catReq.send(null);
}

function showCategories(){
	if(catReq.readyState==4&&catReq.status==200){
		var categories = eval(catReq.responseText);
		for(var temp in categories){
			var opt = document.createElement('option');
			opt.text = categories[temp].category;
			opt.value = categories[temp].categoryId;
			seleccategory.appendChild(opt);
		}
	}
}

function showImage(){
	if(this.files&&this.files[0]){
		var obj = new FileReader();
		obj.onload = function(data){
			previmage.src =	data.target.result;
			previmage.style.visibility='visible';
		}
		obj.readAsDataURL(this.files[0]);
	}
}
