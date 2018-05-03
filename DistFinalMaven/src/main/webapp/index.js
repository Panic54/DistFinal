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
			success: function(loginOk){
				
				alert(loginOk);
                            if(loginOk){
                                window.location.replace("/DistFinalMaven/Salg.html");                                
                            }else{
                                $('.login-error').show();
                            }
			},
			error: function(resp){
                            $('.login-error').show();
			}
		});
                return false;
	});
});