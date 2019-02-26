window.onload = initAll;

function initAll(){
	getAllElements();
	setAllActions();
}

var bag;// is coming from header jsp here we declare the bag or cart button
var removebtns,chab,quantities;
function getAllElements(){
	bag = document.getElementById('bag');
	chab = document.getElementById('chab');
	removebtns = document.getElementsByClassName("removebtn");
	quantities = document.getElementsByName("quantity");
}

function setAllActions(){
	for(removebtn in removebtns){
		removebtns[removebtn].onclick = removeFromBag;
	}
	bag.style.display = 'none';// to hide it from bag or cart page
	chab.onclick = checkOutBooks;
	for(quantitiy in quantities){
		quantities[quantitiy].onchange = updateQuantity;
	}
}

var ajaxReq2;
function updateQuantity(){
	var quantity = this.value;
	var SBId = this.getAttribute('SBId'); 
	ajaxReq2 = new XMLHttpRequest();
	ajaxReq2.open("get","UCCBQ.cpf?quantity="+quantity+"&SBId="+SBId,'true');
	ajaxReq2.onreadystatechange = checkUpdateResult;
	ajaxReq2.send(null);
}

function checkUpdateResult(){
	if(ajaxReq2.readyState==4&&ajaxReq2.status==200){
		var temp = ajaxReq2.respponseText;
		switch(temp){
			case("successful"):{
				//alert('successful');
				break;
			}
			case("unsuccessful"):{
				alert("internal error");
				break;
			}
			case("sessionGone"):{
				location.href='login.jsp';
				break;
			}
		}
	}
}

function checkOutBooks(){
	location.href='chabc.cpf';
}

var ajaxReq1;
function removeFromBag(){
	var SBId = this.getAttribute('SBId');
	ajaxReq1 = new XMLHttpRequest();
	ajaxReq1.open('get','RIFCart.cfp?SBId='+SBId,true);
	ajaxReq1.onreadystatechange = checkUpdate;
	ajaxReq1.send(null);
}

function checkUpdate(){
	if(ajaxReq1.readyState==4&&ajaxReq1.status==200){
		switch(ajaxReq1.responseText){
			case("successfull"):{
				location.href='ccfpage.cpf';
				break;
			}
			case("unsuccessfull"):{
				location.href='login.jsp';
				break;
			}
			case("Internal_Error"):{
				alert("Internal_Error");
				break;
			}
		}
	}
}

