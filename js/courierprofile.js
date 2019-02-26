$(document).ready(function(){

	$('#up').click(function(){
		$.ajax({
			method:'post',
			url:'coucp.coupf',
			data:{
				oldpass:$('#op').val(),
				newpass:$('#np').val()
			},
			success:function(data){
				switch(data){
					case("sessiongone"):{
						location.href="login.jsp";
						break;
					}
					case("false"):{
						let temp = "old password is wrong";
						$('#tblpass').hide(()=>{$('#chp').slideDown(()=>{$('#message').html(temp).css({backgroundColor:'red',color:'white'}).fadeIn().delay(500).fadeOut()})});
						break;
					}
					case("true"):{
						let temp = "password is changed successfully";
						$('#tblpass').hide(()=>{$('#chp').slideDown(()=>{$('#message').html(temp).css({backgroundColor:'green',color:'white'}).fadeIn().delay(500).fadeOut()})});
						break;
					}
				}
			}
		});
	});

	$('#chp').click(function(){
		$(this).slideUp(function(){
			$('#tblpass').slideDown();
		});
	});
	
	$("#np,#cp,#op").keyup(function(){
		if(($('#np').val()==$('#cp').val())&&($('#op').val()!='')){
			$('#up').addClass('enable').removeClass('disabled').prop('disabled',false);	
		}else{
			if($('#up').hasClass('enable')){
				$('#up').removeClass('enable').addClass('disabled').prop('disabled',true);
			}
		}
	});

});