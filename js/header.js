var hhome = document.getElementById('hhome');
var huser = document.getElementById('huser');
var habout = document.getElementById('habout');
var hhelp = document.getElementById('hhelp');

hhome.onclick = function(){
	location.href = 'index.jsp';
};

huser.onclick = function(){
	location.href = 'cuspropage.cpf'; 
};

habout.onclick = function(){
	location.href = 'about.jsp'; 
};

hhelp.onclick = function(){
	location.href = 'cushelp.jsp'; 
};

$(document).ready(function(){
		$.ajax({
			method:'get',
			url:'getsellingbooks.aja',
			dataType:'json',
			success:function(data){
				$('#hsearch').autocomplete({
					delay:0,
					source:data
				});
			}
		});

		$('#hsearchb').click(function(){
			location.href = `sbi.do?sbi=${$('#hsearch').val()}`;
		});
});



