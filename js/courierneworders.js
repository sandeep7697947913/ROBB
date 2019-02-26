$(document).ready(function(){
	$('tr').data("active",false);
	
	$('.btn').click(function(){
		let _this = $(this).parents()[1];
		let orderId = $($(this).parents()[1]).children().get(0).innerHTML;
		$.get("orderDispatch.do",{orderId},function(data){
			switch(data){
				case("true"):{
					_this.remove();
					break;
				}
				case("false"):{
					alert("Internal Error");
					break;
				}
				case("sessiongone"):{
					location.href = "courierlogin.jsp";
					break;
				}
			}
			//_this.remove();
		});
	});
	
	$(window).keydown(function(e){
		if(e.ctrlKey&&e.altKey&&e.keyCode==66){
			if($('.active').get(0)){
				let orderId = $('tr').get($('.active').get(0).rowIndex).children[0].innerHTML;
				var ev = window.open('bill.do?orderId='+orderId,"","");
				ev.print();
				ev.onafterprint = function(e){
					this.close();
				};
				ev.onunload = function(){
					this.onunload = ()=>{
						/*
						let tr = $('tr').get($('.active').get(0).rowIndex);
						$(tr).data('active',true).find('.btn').get(0).disabled = false;
						*/
						let tr = $('tr').get($('.active').get(0).rowIndex);
						$($(tr).data('active',true).find('.btn').get(0)).css("cursor","pointer").prop("disabled",false);
					};
				};
			}
		}
	});

	$('tbody tr').click(function(){
		//alert($(this).find('.btn').get(0).disabled = false);
		//alert($(this).data("active"));
		if($(this).hasClass('active')){
			$('tbody tr').removeClass('active');
		}else{
			$('tbody tr').removeClass('active');
			$(this).addClass('active');
		}
	});
});