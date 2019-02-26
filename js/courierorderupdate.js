$(document).ready(function(){

	$("#cidelivered").click(function(){
		$.post({
			url:"COUODelivered.do",
			data:{
				orderId:$("#ordid").html()
			},
			success:function(data){
				switch(data){
					case("true"):{
							$("#box").slideUp();
							break;
						}
					case("false"):{
						$("#box").hide();
						break;
					}
					case("sessiongone"):{
						location.href="courierlogin.jsp";
						break;
					}
				}
			}
		});
	});
	
	$('#ciupdate').click(function(){
		if($("#cities").val()!=0){
			$.post({
				url:"UPCOUOC.tk",
				data:{
					orderId:$("#ordid").html(),
					cityId:$("#cities").val()
				},
				success:function(data){
					switch(data){
						case("true"):{
							$("#box").slideUp();
							break;
						}
						case("false"):{
							$("#box").hide();
							break;
						}
						case("sessiongone"):{
							location.href="courierlogin.jsp";
							break;
						}
					}
				}
			});
		}
		$("#states,#cities").val(0);
	});

	$("#findorder").click(function(){
		if($('#search').val()!=''){
			$.ajax({
				method:'post',
				//dataType:"json",
				url:'COUGOCD.tk',
				data:{
					orderId:$('#search').val()
				},
				success:function(data){	
					switch(data){
						case("false"):{
							$("#box").hide();
							alert("No Records Found!");
							break;
						}
						case("sessiongone"):{
							location.href = "courierlogin.jsp";
							break;
						}
						default:{
							$("#box").slideDown("1000");
							$("#ordid").html($('#search').val());
							let _data = JSON.parse(data);
							$("#orddat").html(_data.date);
							$("#cusname").html(_data.cart.customer.customerName);
							$("#cusemail").html(_data.cart.customer.email);
							$("#cusaddr").html(_data.cart.customer.address);
							$("#cuscit").html(_data.cart.customer.city.cityName);
							$("#cussta").html(_data.cart.customer.city.state.stateName);
							break;
						}
					}
					
				}
			});
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