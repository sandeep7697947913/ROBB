var sb = document.getElementById("sb");
var slb = document.getElementById('slb');
var od = document.getElementById('od');
var cb = document.getElementById('cb');
var pr = document.getElementById('pr');
var help = document.getElementById('help');

help.onclick = function(){
	location.href = 'shelp.jsp';
}

slb.onclick = function(){
	location.href = 'sslbpage.spf';
}

cb.onclick = function(){
	location.href = 'scbpage.spf';
}

sb.onclick = function(){
	location.href="ssbpage.spf";
};

od.onclick = function(){
	location.href = "sopage.spf";
}

pr.onclick = function(){
	location.href = "sppage.spf";
}