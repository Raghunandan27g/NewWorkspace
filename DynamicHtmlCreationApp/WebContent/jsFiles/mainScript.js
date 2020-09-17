function onloadOfApplication(){
	startLoader();
	setTimeout(function(){ stopLoader(); }, 1000);
}

function callToServer(){
	$.ajax(
			{ 
				type: "POST",
				url: "/DynamicHtmlCreationApp/ServletInitializer",
				async: true,
				data:  $("form").serialize(),
				success: function(result)
				{
					if(result=="true"){
						console.log(result);
						stopLoader();
					}
					else{
						stopLoader();
					}
				},
				error : function(result)
				{
					alert("ERROR");
				}
			});
}
function stopLoader()
{
	$("#loader").animate({opacity: '0.0'}, 280,loaderHide());
}
function loaderHide(){
	setTimeout(function(){$("#loader").hide();}, 300);
}
function startLoader()
{
	$("#loader").css('opacity', '1');
	$("#loader").show(100);
}
$(window).load(function(){
	   // PAGE IS FULLY LOADED  
	   // FADE OUT YOUR OVERLAYING DIV
	   $('#overlay').fadeOut();
	});