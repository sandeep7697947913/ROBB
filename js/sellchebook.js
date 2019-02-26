$(document).ready(function(){
	var sb;

	$('#ub').click(function(){
		$.post({
			url:'unqnpsb.sb',
			data:{
				sellerBook:JSON.stringify(sb),
				quantity:$('#nq').val(),
				price:$('#np').val()
			},
			success:function(data){
				switch(data){
					case("true"):{
						$('input').val('');
						$('#book,#upbook').slideUp(function(){
							$('#norec').show();
						});
						break;
					}
					case("false"):{
						alert('false');
						break;
					}
					case("sessiongone"):{
						location.href='login.jsp';
						break;
					}
				}
			}
		});
	});

	$('#fb').click(function(){
		$.get({
			url:'sfbi.spf',
			data:{
				isbn:$('#isbn').val()
			},
			success:function(data){
				switch(data){
					case("sessiongone"):{
						location.href = "login.jsp";
						break;
					}
					case("norecord"):{
						$('#book,#upbook').slideUp(function(){
							$('#norec').show();
						});
						break;
					}
					default:{
						$('#norec').hide();
						sb = JSON.parse(data);
						//$('img')[1].src = $($('img')[1]).attr('src')+sb.book.bookId+".jpg";
						$('img')[1].src = "data/booksimages/"+sb.book.bookId+".jpg";
						$('#bn').html(sb.book.bookName);
						$('#ba').html(sb.book.author);
						$('#bm').html(sb.book.mrp);
						$('#bql').html(sb.quantity);
						$('#bsp').html(sb.price);
						$('#book,#upbook').slideDown();
						break;
					}
				}
			}
		});
	});
});