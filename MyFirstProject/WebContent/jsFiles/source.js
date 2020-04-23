function callToServlet(){
	$.ajax({
		
	    type: "POST",
	    url: "/DoLogin",
	    contentType: "application/json", // NOT dataType!
	    data: JSON.stringify(data),
	    success: function(response) {
	        // ...
	    }
	});
}
