$(document).ready(function(){

	var urlArr = ['cec.do','pec.do','sec.do','couec.do'];
	var urlCP = ['fccp.do','fpcp.ppf','fscp.spf','fcoucp.coupf'];

	let url = function(){
			let url = urlArr[$('.btnactive').index()];
			return url;
		};
	
	let urlcp = function(){
			let urlc = urlCP[$('.btnactive').index()];
			return urlc;
		};
	

	$('button').click(function(){
		$('button').removeClass('btnactive');
		$("#emailsend,#email").prop("disabled",false);
		$("#email,#otp,#newpass").val('');
		$('#emessage').text('');
		$('#esuc').hide();
		switch($(this).index()){
			case(0):{
				$('legend').text('Customer');
				break;
			}
			case(1):{
				$('legend').text('Publisher');
				break;
			}
			case(2):{
				$('legend').text('Seller');
				break;
			}
			case(3):{
				$('legend').text('Courier');
				break;
			}
		}
		$(this).addClass('btnactive');
	});

	$('#emailsend').click(function(){
		$('#emessage').text('Sending mail....');
		
		$.ajax({
			url:url(),
			method:'post',
			data:{email:$('#email').val()},
			success:function(data){
				switch(data){
					case("true"):{
					$('#esuc').slideDown();
					$("#emessage").text("otp is valid for 15 minutes.");
					$("#emailsend,#email").prop("disabled",true);
					break;
					}
					case("false"):{
						$("#emessage").text("email is not found!");
						break;
					}
				}
			}
		});
	});
	
	$('#uppass').click(function(){
		$.ajax({
			url:urlcp(),
			method:'post',
			data:{
				otp:$('#otp').val(),
				newpass:$('#newpass').val()
			},
			success:function(data){
				switch(data){
					case("true"):{
						$('#esuc').slideUp();
						$("#emessage").text("Password is changed");
						$("#emailsend,#email").prop("disabled",false);
						$("#email,#otp,#newpass").val('');
						break;
					}
					case("false"):{
						$('#esuc').slideUp();
						$("#emessage").text("OTP is Wrong");
						$("#emailsend,#email").prop("disabled",false);
						$("#email,#otp,#newpass").val('');
						break;
					}
				}	
			}
		});
	});
});

