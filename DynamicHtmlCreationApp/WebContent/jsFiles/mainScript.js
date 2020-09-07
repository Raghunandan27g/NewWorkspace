
function callToServer(){
	$.ajax(
			{ 
				type: "POST",
				url: "/DynamicHtmlCreationApp/ServletInitializer",
				async: true,
				data:
				{
					generationType=$("input[name='generationType']:checked").val(),
					argnum : "0"
				},
				dataType: "json",
				success: function(result)
				{
					console.log(success);
				},
				error : function(result)
				{
					alert("ERROR");
				}
			});
}

