window.onload = initAll;

function initAll(){
	getAllElements();
	setAllActions();
}

var form,table,lblname,nh,aha,nhlink,rlink;
nhlink='needregishelp.do';
rlink='loginpageforward.do';
coulink = 'courregist.jsp';
function getAllElements(){
	form = document.getElementsByTagName("form");
	table = document.getElementsByTagName("table");
	lblname = document.getElementById("lblname");
	nh = document.getElementById("nh");
	aha = document.getElementById("aha");
}

function setAllActions(){
	
}

function psignup(){	
	table[0].caption.innerHTML = 'Register as Publisher';
	form[0].action = 'psignup.do';
	lblname.placeholder = "publisher@gmail.com";
}

function ssignup(){
	table[0].caption.innerHTML = 'Register as Seller';
	form[0].action = 'ssignup.do';
	lblname.placeholder = "seller@gmail.com";
}
function csignup(){
	table[0].caption.innerHTML = 'Register as Customer';
	form[0].action = 'csignup.do';
	lblname.placeholder = "abc@gmail.com";
}

function registrationhelp(){
	location.href=nhlink;
}
	
function createaccount(){
	location.href=rlink;
}

function courier(){
	location.href = coulink;
}