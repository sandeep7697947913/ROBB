window.onload = initAll;


function initAll(){
	getAllElements();
	setAllActions();
}

var bsearch,bsearchb,lowerform,norec,flds,quantity,price,button,main,bookimage;
function getAllElements(){
	bsearch = document.getElementById('bsearch');
	bsearchb = document.getElementById('bsearchb');
	lowerform = document.getElementById('lowerform');
	norec = document.getElementById('norec');
	flds = document.getElementsByClassName('fld');
	quantity = document.getElementById('quantity');
	price = document.getElementById('price');
	button = document.getElementById('butt');
	main = document.getElementById('main');
	bookimage = document.getElementById('bookimage');
}

function setAllActions(){
	bsearchb.onclick = findBook;
	butt.onclick = sellBook;
}
//alert(book.bookId);
var reqs;
function sellBook(){	
	if((!(quantity.value==""))&&(!(price.value==""))){
		reqs = new XMLHttpRequest();
		reqs.open('post','sellersellbook.tk',true);
		reqs.onreadystatechange = sellersellbook;
		reqs.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		reqs.send("book="+JSON.stringify(book)+"&quantity="+quantity.value+"&price="+price.value);
	}else{
		alert('fill the quantity and price');
	}
}

function sellersellbook(){
	var t;
	if(reqs.readyState==4&&reqs.status==200){
		switch(reqs.responseText){
			case("true"):{
				main.removeChild(document.getElementById('alert'));
				lowerform.style.display = 'none';
				var div = document.createElement('div');
				div.innerHTML = 'Book successfully added';
				norec.innerHTML = 'Book successfully added for selling';
				norec.style.display ='block';
				div.id = 'alert';
				div.style.display = 'block';
				div.style.borderColor = '#0cd611';
				div.style.backgroundColor = '#66ff66';
				main.appendChild(div);
				break;
			}
			case("false"):{
				main.removeChild(document.getElementById('alert'));
				lowerform.style.display = 'none';
				var div = document.createElement('div');
				div.innerHTML = 'Can not publish book';
				norec.innerHTML = 'Internal error occurs';
				norec.style.display ='block';
				div.id = 'alert';
				div.style.display = 'block';
				div.style.borderColor = '#3abe4f';
				div.style.backgroundColor = '#56ee3c';
				main.appendChild(div);
				break;
			}
			case("duplicate"):{
				main.removeChild(document.getElementById('alert'));
				lowerform.style.display = 'none';
				var div = document.createElement('div');
				div.innerHTML = 'Duplicate entry found';
				norec.innerHTML = 'Duplicate books for selling not allowed';
				norec.style.display ='block';
				div.id = 'alert';
				div.style.display = 'block';
				div.style.borderColor = '#ffff00';
				div.style.backgroundColor = '#e2e820';
				main.appendChild(div);
				break;
			}
		}

	}else{
		//alert("some errors");
	}
}

var req;
function findBook(){
	req = new XMLHttpRequest();
	req.open("get","ajaxfindbookISDN.tk?isbn="+bsearch.value,true);
	req.onreadystatechange = getBook;
	req.send(null);
}

var book;
function getBook(){
	if(req.readyState==4&&req.status==200){
		//alert('"'+req.responseText+'"');
		book = JSON.parse(req.responseText);
		//alert(window.encodeURI(JSON.stringify(book)));
		if(book!=null){//data/booksimages/1.jpg
			bookimage.src='data/booksimages/'+book.bookId+'.jpg';
			flds[0].innerHTML=book.bookName;
			flds[1].innerHTML=book.author;
			flds[2].innerHTML=book.category.category;
			flds[3].innerHTML=book.publisher.publication;
			flds[4].innerHTML=book.edition;
			flds[5].innerHTML=book.mrp+' Rs.';
			norec.style.display = 'none'; 
			lowerform.style.display = 'block';
		}else{
			norec.innerHTML = 'No Records found...';
			norec.style.display = 'block';
			lowerform.style.display = 'none';
		}
	}
}