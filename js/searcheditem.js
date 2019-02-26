$(document).ready(function(){
	$('.bookbox').click(function(){
		location.href = `itemdetail.cpf?bookId=${this.getAttribute('rf')}`;
	});
});