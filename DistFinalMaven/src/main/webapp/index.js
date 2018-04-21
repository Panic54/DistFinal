$(document).ready(function() {
    
	//alert("Javascript ready");
	
	$("#btn").click(function() {
		
		//Prevent form from reseting on incorrect login credentials
		//event.preventDefault();

		var data = $("#loginForm").serializeObject();
		
		alert("Name: " + data.username + " Password: " + data.password);
			
		$.ajax({
			url: "rest/login/test",
			data: JSON.stringify(data),
			contentType: "application/json",
			method: 'POST',
			success: function(resp){
				alert(resp);
				window.location.replace("http://localhost:8080/DistFinalMaven/DineTing.html");
				//Giv token...
				//Skift html side...
			},
			error: function(resp){
				alert("Fejl: " + resp);
			}
		}) 
		
		//Det nedenunder virkede...
		/*
		$.ajax({
			url: "rest/test",
			method: "GET",
			success: function(result) {
				alert(result);
			},
			error: function(result) {
				alert(result);
			}
		}) */
		
	});
	
});