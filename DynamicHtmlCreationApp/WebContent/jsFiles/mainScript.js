var JSON_VALUE_POPULATE=[];

function onloadOfApplication(){
	
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
					JSON_VALUE_POPULATE=JSON.parse(result);
					populate("form", JSON_VALUE_POPULATE);
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
					stopLoader();
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
function populate(frm, data) {   
    $.each(data, function(key, value) {  
        var ctrl = $('[name='+key+']', frm);  
        switch(ctrl.prop("type")) {
            case "radio": case "checkbox":   
                ctrl.each(function() {
                    //if($(this).attr('value') == value) $(this).attr("checked",value);
                	$("input[name="+key+"][value=" + value + "]").prop('checked', true);
                });   
                break;  
            default:
                ctrl.val(value); 
        }  
    });  
}
