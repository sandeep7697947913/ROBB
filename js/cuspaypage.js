window.onload = initAll;

function initAll(){
	getAllElements();
	setAllActions();
}

var pmbc,pmbd,paybox,code,rcaptcha,verify,captchaans,cvv,atmno,otpbox,proceedb;
var date;
function getAllElements(){
	pmbc = document.getElementById('pmbc');
	pmbd = document.getElementById('pmbd');
	paybox = document.getElementById("paybox");
	code = document.getElementById('code');
	rcaptcha = document.getElementById('rcaptcha');
	verify = document.getElementById('verify');
	captchaans = document.getElementById('captchaans');
	cvv = document.getElementById('cvv');
	atmno = document.getElementById('atmno');
	otpbox = document.getElementById('otpbox');
	proceedb = document.getElementById('proceedb');
	date = document.getElementById('date');
}

function setAllActions(){
	GCTIB();//giving color to initial button
	randomQuestion();
	rcaptcha.onclick = randomQuestion;
	verify.onclick = verifyCaptcha;
	pmbd.onclick = debitBox;
	pmbc.onclick = cashOnBox;
	proceedb.onclick = verifyATMDetails;
	atmno.onkeyup = disableButton;
	cvv.onkeyup = disableButton;
	date.onchange = disableButton;
}

function verifyATMDetails(){
	let ano = atmno.value;
	let cno = cvv.value;
	let dva = date.value;
	let req = new XMLHttpRequest();
	//req.open('get',"http://localhost/bank/ROBB/verifyatm.php?ano="+ano+"&cno="+cno+"&dva="+dva,true);
	req.open('get',"verifyatm.page?ano="+ano+"&cno="+cno+"&dva="+dva,true);
	req.onreadystatechange = result;
	req.send(null);

	function result(){
		if(req.status==200&&req.readyState==4){
			let con = req.responseText;
			switch(con){
				case("false"):{
					location.href="payfailpage.jsp";
					break;
				}
				case("true"):{
					location.href="paysuccpage.jsp";
					break;
				}
				case("sessionexpired"):{
					location.href="login.jsp";
					break;
				}
			}
		}
	}
}

function disableButton(){
	let ano =atmno.value;
	let cno = cvv.value;
	let dva = date.value;
	if((ano!='')&&(cno!='')&&(dva!='')){
		proceedb.removeAttribute("disabled");
	}else{
		proceedb.setAttribute("disabled","");
	}
}

function debitBox(){
	paybox.style.display='block';
	paycod.style.display = 'none';
	pmbd.style.backgroundColor = "#66ff99";
	pmbc.style.backgroundColor = "";
}

function cashOnBox(){
	randomQuestion();
	paybox.style.display = 'none';
	paycod.style.display = 'block';
	pmbc.style.backgroundColor = "#66ff99";
	pmbd.style.backgroundColor = "";
}

function GCTIB(){
	pmbc.style.backgroundColor = "#66ff99";
}


var reqo;
function verifyCaptcha(){
	let ques = code.ques;
	if(captchaans.value==eval(ques)){
		captchaans.value = '';
		//alert("true hai");
		reqo = new XMLHttpRequest();
		reqo.open("get","allcartorder.tk",true);
		reqo.onreadystatechange = result;
		reqo.send(null);
	}else{
		alert("false hai ");
		captchaans.value = '';
		randomQuestion();
	}

	function result(){
		if(reqo.readyState==4&&reqo.status==200){
			let data = reqo.responseText;
			switch(data){
				case("false"):{
					location.href="orderfail.jsp";
					break;
				}
				case("true"):{
					location.href="ordersucc.jsp";
					break;
				}
				case("sessionexpired"):{
					location.href="login.jsp";
					break;
				}
			}
		}
	}
}

function randomQuestion(){
	var bodmass = ['+','-'];
	let mark = Math.floor(Math.random()*2+1);
	let ques = Math.floor(Math.random()*9+1);
	let tmp1;
	let tmp2 = Math.floor(Math.random()*9+1);
	for(let i=0;i<mark;i++){
		tmp1 = bodmass[Math.floor(Math.random()*2)];
		tmp2 = Math.floor(Math.random()*9+1);
		ques += tmp1 + tmp2;
	}
	code.ques = ques;
	code.innerHTML = ques+" = ?";
}
