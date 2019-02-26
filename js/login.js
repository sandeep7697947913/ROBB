window.onload = initAll;

function initAll(){
	getAllElements();

	setAllActions();
}

var form,table,lblname,fp,ca,flink,rlink,cour;
flink='forgetpassword.jsp';
rlink='registrationpageforward.do';
function getAllElements(){
	form = document.getElementsByTagName("form");
	table = document.getElementsByTagName("table");
	lblname = document.getElementById("lblname");
	fp = document.getElementById("fp");
	ca = document.getElementById("ca");
	cour = document.getElementById("cour");
}

function setAllActions(){
	
}

function plogin(){	
	table[0].caption.innerHTML = 'Publisher Login';
	form[0].action = 'plogin.do';
	//alert(form[0].action);
	lblname.placeholder = "publisher@gmail.com";
}
function slogin(){
	table[0].caption.innerHTML = 'Seller Login';
	form[0].action = 'slogin.do';
	lblname.placeholder = "seller@gmail.com";
	//alert(form[0].action);
}
function clogin(){
	table[0].caption.innerHTML = 'Customers login';
	form[0].action = 'clogin.do';
	lblname.placeholder = "abc@gmail.com";
	//alert(form[0].action);
}

function forgotpass(){
	location.href=flink;
}
	
function createaccount(){
	location.href=rlink;
}

function courlogin(){
	location.href='courierlogin.do';
}