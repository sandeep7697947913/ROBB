$(document).ready(function(){	

	$('#hbtn').click(function(){
		if($('#cities').val()!=0){
			$('#foot').show();
			$('#body').hide();
			$.ajax({
				url:'courregiskeysend.do',
				data:{email:$('#email').val()},
				success:function(data){
					switch(data){
						case("true"):{
							$('#nmessage').text('Registration key is sended to your E-mail !');
							$('.inreg').show();
							break;
						}
						case("false"):{
							alert('Serveer error');
							break;
						}
					}
				}
			});
		}else{
			alert('please select city');
		}
	});

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

});