$(document).ready(function(){
	
	$('#states').change(function(){
		$.ajax({
			method:'get',
			url:"ajaxcollectcities.tk",
			data:{
				stateId:$('#states').val()
			},
			success:function(data){
				let _opt = $("#cities").children()[0];
				let obj = eval(data);
				$("#cities").empty().append(_opt);
				for(let ct of obj){
					$("#cities").append($('<option>').val(ct.cityId).html(ct.cityName));
				}
			}
		});
	});

	$('#chps').click(function(){
		if(($('#ntp,#rtp').val()!='')&&($('#ntp').val())==($('#rtp').val())){
			$.ajax({
				url:'cuschangepass.do',
				method:'get',
				data:{
					pass:$('#pass').val(),
					newpass:$('#ntp').val()
				},
				success:function(data){
					switch(data){
						case("true"):{
							$('#pmess').html('Password Successfully changed').css({backgroundColor:"green"}).slideDown('500').delay('500').slideUp('500');
							setTimeout(()=>{
								$("#emailpass").slideUp("1000");
								$("#options").slideDown("1000");
							},2000);
							break;
						}
						case("false"):{
							$('#pmess').html('old-password is wrong').css({backgroundColor:"green"}).slideDown('500').delay('500').slideUp('500');
							break;
						}
						case("sessiongone"):{
							location.href='login.jsp';
							break;
						}
					}
				}
			});	
		}else{
			$('#pmess').html('new password are not same').css({backgroundColor:"#ffcc00"}).slideDown('500').delay('500').slideUp('500');
		}
	});

	$('.cbtn img').click(function(){
		$("#orderstable,#emailpass,#form").slideUp("1000");
		$("#options").slideDown("1000");
	});
	
	$('#order').click(function(){
		$("#orderspage").load('getDelOrder.page');
		$('#ohead').html('Orders');
		$("#options").slideUp("1000");
		$("#orderstable").slideDown("1000");
	});

	$('#nameaddress').click(function(){
		$("#options").slideUp();
		$("#form").slideDown();
	});
	
	$('#emailpassword').click(function(){
		$("#options").slideUp("1000");
		$("#emailpass").slideDown("1000");
	});
	
	$("#torder").click(function(){
		$("#orderspage").load('getTrackOrder.page');
		$("#ohead").html("Track Orders");
		$("#options").slideUp("1000");
		$("#orderstable").slideDown("1000");
	});

	$('#logout').click(function(){
		location.href = 'logout.tk';
	});

});// document end
