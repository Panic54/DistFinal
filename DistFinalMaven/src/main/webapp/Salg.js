$(document).ready(function() {
		
	$("#buy").click(function() {
		
		window.location.replace("http://localhost:8080/Gruppe50/AndresTing.html");
		
	});

	
	//JSON object: {"name":"bil","item":"bil","price":"500"}
	
	$("#sell").click(function(){
		
		var data = $("#loginForm").serializeObject();
		
		$.ajax({
			url: "rest/rest2/sell",
			data: JSON.stringify(data),
			contentType: "application/json",
			method: 'POST',
			success: function(loginOK){
				//window.location.replace("https://www.google.com/?gws_rd=ssl");
				alert(loginOk);
                         
			},
			error: function(resp){
                            $('.login-error').show();
			}
		});
                return false;
		
	});
	
});