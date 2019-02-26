window.onload = initAll;

function initAll(){
	getAllElements();
	setAllElements();
}

$(document).ready(function(){
	
});

var findButton,selopt,search,book,bp,bookimage,norec,authbooktbody,authbook,catebook,catebooktbody,getAll;
function getAllElements(){
	findButton = document.getElementById('find');
	selopt = document.getElementById('selopt');
	search = document.getElementById('search');
	bookdiv = document.getElementById('book');
	bookimage = document.getElementById('bookimage');
	norec = document.getElementById('norec');
	authbook = document.getElementById('authbook');
	catebook = document.getElementById('catebook');
	authbooktbody = document.getElementById('authbooktbody');
	catebooktbody = document.getElementById('catebooktbody');
	getAll = document.getElementById('getAll');
	bp = document.getElementsByClassName('bp');
}

function setAllElements(){
	selopt.onchange = hide;
	search.onkeyup = getDropDown;
	findButton.onclick = findBook;	
	getAll.onclick = function(){
		location.href = 'pabp.ppf';
	};
}

function getDropDown(){
	if($('#selopt').val()==2){
		//author
		$.ajax({
			url:'AjaxCollectAuthor.aja',
			dataType:'json',
			data:{auth:$("#search").val()},
			success:function(data){
				$('#search').autocomplete({
					delay:0,
					source:data
				});
			}
		});
	}
	if($('#selopt').val()==3){
		//category
		$.ajax({
			url:"AjaxCollectCategories.aja",
			data:{category:$("#search").val()},
			dataType:'json',
			success:function(data){
				$('#search').autocomplete({
					delay:0,
					source:data
				});
			}
		});
	}
}

function findBook(){
	switch(parseInt(selopt.value)){
		case(1):{//isbn
			let req = new XMLHttpRequest();
			req.open("get","fpbi.ppf?isbn="+search.value,true);
			req.onreadystatechange = function(){
				if(req.status==200&req.readyState==4){
					let rest = req.responseText;
					switch(rest){
						case("sessiongone"):{
							location.href="login.jsp";
							break;
						}
						case("norec"):{
							if(bookdiv.style.display=="block"){
								bookdiv.style.display="none";
								norec.style.display = "block";
							}
							break;
						}
						default:{
							let book = JSON.parse(rest);
							bp[1].innerHTML = book.bookName;
							bp[3].innerHTML = book.isbn;
							bp[5].innerHTML = book.author;
							bp[7].innerHTML = book.mrp+" Rs.";
							bp[9].innerHTML = book.category.category;
							bp[11].innerHTML = book.edition+" edition";
							bookimage.src = `data/booksimages/${book.bookId}.jpg`;
							norec.style.display = "none";
							bookdiv.style.display = "block";
							break;
						}
					}
				}
			};
			req.send(null);
			break;
		}
		case(2):{//author
			let req = new XMLHttpRequest();
			req.open('get','fpba.ppf?author='+search.value,true);
			req.onreadystatechange = function(){
				if(req.status==200&&req.readyState==4){
					let rest = req.responseText;
					switch(rest){
						case("sessiongone"):{
							location.href="login.jsp";
							break;
						}
						case("fail"):{
							alert("internal Error");
							break;
						}
						default:{
							let bookArr = JSON.parse(rest);
							if(bookArr.length==0){
								norec.style.display = "block";
								authbook.style.display = "none";
							}else{
								norec.style.display = "none";
								authbook.style.display = "block";
								authbooktbody.innerHTML='';
								for(let book of bookArr){
									let tr = document.createElement('tr');
									let td1 = document.createElement('td');
									let td2 = document.createElement('td');
									let td3 = document.createElement('td');
									let td4 = document.createElement('td');
									td1.innerHTML=book.isbn;
									td2.innerHTML=book.bookName;
									td3.innerHTML=book.category.category;
									td4.innerHTML=book.mrp;
									tr.appendChild(td1);
									tr.appendChild(td2);
									tr.appendChild(td3);
									tr.appendChild(td4);
									//tr.appendChild(document.createElement('td').text(book.category.category));
									//tr.appendChild(document.createElement('td').text(book.mrp));
									authbooktbody.appendChild(tr);
								}
							}
						}
					}
				}
			};
			req.send(null);
			break;
		}
		case(3):{//category
			let req = new XMLHttpRequest();
			req.open('get','fpbc.ppf?category='+search.value,true);
			req.onreadystatechange = function(){
				if(req.status==200&&req.readyState==4){
					let rest = req.responseText;
					switch(rest){
						case("sessiongone"):{
							location.href="login.jsp";
							break;
						}
						case("fail"):{
							alert("internal Error");
							break;
						}
						default:{
							let bookArr = JSON.parse(rest);
							if(bookArr.length==0){
								norec.style.display = "block";
								catebook.style.display = "none";
							}else{
								norec.style.display = "none";
								catebook.style.display = "block";
								catebooktbody.innerHTML='';
								for(let book of bookArr){
									let tr = document.createElement('tr');
									let td1 = document.createElement('td');
									let td2 = document.createElement('td');
									let td3 = document.createElement('td');
									let td4 = document.createElement('td');
									td1.innerHTML=book.isbn;
									td2.innerHTML=book.bookName;
									td3.innerHTML=book.author;
									td4.innerHTML=book.mrp;
									tr.appendChild(td1);
									tr.appendChild(td2);
									tr.appendChild(td3);
									tr.appendChild(td4);
									//tr.appendChild(document.createElement('td').text(book.category.category));
									//tr.appendChild(document.createElement('td').text(book.mrp));
									catebooktbody.appendChild(tr);
								}
							}
						}
					}
				}
			};
			req.send(null);
			break;
		}
	}
}

function hide(){
	$('#search').autocomplete({
		delay:0,
		source:[]
	});
	search.value = "";
	norec.style.display = "block";
	bookdiv.style.display = "none";
	authbook.style.display = "none";
	catebook.style.display = "none";
}