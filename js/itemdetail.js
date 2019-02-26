window.onload = initAll;

function initAll(){
	getAllElements();
	setAllActions();
}

var bookId = bookId; //re-declaration no effet to code it just for simplification book is come from script in itemdetail.jsp
var bookMRP = bookMRP; //re-declaration no effet to code it just for simplification book is come from script in itemdetail.jsp
var sellerselect,bookPrice,moresellertext;

function getAllElements(){
	moresellertext = document.getElementById('moresellertext');
	sellerselect = document.getElementById('sellerselect');
	bookPrice = document.getElementById('bookPrice');
	getSeller(bookId);
}

function setAllActions(){
	sellerselect.onchange = setPrice;
}

var reqGetSeller;
function getSeller(tmpBookId){
	reqGetSeller = new XMLHttpRequest();
	reqGetSeller.open("get","findSellers.id?bookId="+tmpBookId,"true");
	reqGetSeller.onreadystatechange = addSeller;
	reqGetSeller.send(null);
}

var bookSellers;
function addSeller(){
	if(reqGetSeller.readyState==4&&reqGetSeller.status==200){
		bookSellers = eval(reqGetSeller.responseText);
		var lowestBookPrice = true;
		for(bookSeller in bookSellers){
			var opt = document.createElement('option');
			opt.value = bookSellers[bookSeller].sellerBookId;
			opt.text =  bookSellers[bookSeller].seller.sellerName;
			sellerselect.appendChild(opt);
			setPrice();
		}
		if(bookSellers.length>1){
			moresellertext.innerHTML= "("+(bookSellers.length-1)+" more sellers available)";
		}
	}
}
var oneTimeSetPrice = true,priceSetTxt;
function setPrice(){
	if(oneTimeSetPrice){
		priceSetTxt = bookPrice.innerHTML;
		oneTimeSetPrice = false;
	}
	var discount = Math.floor(100-(bookSellers[sellerselect.selectedIndex].price/bookMRP)*100)+"% discount";
	bookPrice.innerHTML = priceSetTxt + bookSellers[sellerselect.selectedIndex].price +" Rs"+" ("+discount+")";
}

