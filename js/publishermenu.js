var pb,bp,bs,pp,ph;

pb = document.getElementById('pb');
bp = document.getElementById('bp');
bs = document.getElementById('bs');
pp = document.getElementById('pp');
ph = document.getElementById('ph');

pb.onclick = forwardPPBP;
bp.onclick = forwardPBPP;
bs.onclick = forwardPBSP;
pp.onclick = forwardPPP;
ph.onclick = forwardPHP;

function forwardPHP(){
	location.href = "phelp.jsp";
}

function forwardPBPP(){
	location.href = "pbppage.ppf";
}

function forwardPPBP(){
	location.href = "ppbpage.ppf";
}

function forwardPBSP(){
	location.href = "pbspage.ppf";
}

function forwardPPP(){
	location.href = "pppage.ppf";
}