window.onload = initAll;

function initAll(){
	getAllElements();
	setAllActions();
}

var couriersele,trs,updrowdet;
function getAllElements(){
	couriersele = document.getElementById('courier');
	trs = document.getElementsByTagName('tr');
	updrowdet = document.getElementById('updrowdet');
}

function setAllActions(){
	showCouriers();
	for(let tr=1;tr<trs.length;tr++){
		trs[tr].onclick = activeRow;
	}
	updrowdet.onclick = updateAndDelete;
	couriersele.onchange = disablingKey;
}

function updateAndDelete(){
	let tmp = document.getElementsByClassName("active");
	let requp  = null;
	if(tmp.length==1){
		let orderId = tmp[0].cells[0].getAttribute("value");
		let courierId = couriersele.value;
		//alert(tmp[0].rowIndex);
		let a = tmp[0].rowIndex;
		//alert(trs[a].parentNode.removeChild(trs[a]));
		//trs.removeChild(trs.childNodes);
		requp = new XMLHttpRequest();
		requp.open('get','UCIOTServlet.do?orderId='+orderId+'&courierId='+courierId,true);
		requp.onreadystatechange = doRemove(trs,a);
		requp.send(null);
		couriersele.value = 0;
		updrowdet.disabled = true;
		updrowdet.style.cursor = 'not-allowed';
	}

	function doRemove(collection,index){
		collection[index].parentNode.removeChild(collection[index]);
		if(requp.readyState==4&&requp.status==200){
			switch(requp.responseText){
				case("true"):{
					collection[index].parentNode.removeChild(collection[index]);
					break;
				}
				case("false"):{
					alert("something went wrong");
					break;
				}
				case("sessionisgone"):{
					location.href = "login.jsp";
					break;
				}
			}
		}
	}
}

function disablingKey(){
	let tmp = document.getElementsByClassName("active");
	if(tmp.length==1){
		if(this.value!=0){
			updrowdet.disabled = false;
			updrowdet.style.cursor = 'pointer';
		}else{
			updrowdet.disabled = true;
			updrowdet.style.cursor = 'not-allowed';
		}
	}else{
		alert('select a row for update');
		this.value=0;
	}
}

function activeRow(){
	if(this.className=="active"){
		this.className = "";
	}else{
		for(let tr=1;tr<trs.length;tr++){
			trs[tr].className='';
		}
		this.className = "active";
	}
}

var req1;
function showCouriers(){
	req1 = new XMLHttpRequest();
	req1.open('get','GACServlet.do',true);
	req1.onreadystatechange = addOptions;
	req1.send(null);
}

function addOptions(){
	if(req1.status==200&&req1.readyState==4){
		var couriers = eval(req1.responseText)
		for(let courier of couriers){
			let opt = document.createElement('option');
			opt.value = courier.courierId;
			opt.text = courier.name
			couriersele.appendChild(opt);
		}
	}
}