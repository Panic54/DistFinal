$(document).ready(function() {
    
	//alert("Javascript ready");
	
	$("#btn").click(function() {
		
		//Prevent form from reseting on incorrect login credentials
		//event.preventDefault();

		var loginData = $("#loginForm").serializeObject();
		
		$.ajax({
			url: "rest/rest2/javabog",
			data: JSON.stringify(loginData),
			contentType: "application/json",
			method: 'POST',
			success: function(data, status, jqXHR){
				alert(status);
				if(status == "success"){
					$.ajax({
						url: "rest/rest2/build",
						data: JSON.stringify(loginData),
                        contentType: "application/json",
                        method: "POST",
                        success: function(resp) {
                        	//alert("start/" + resp + "/end");
                            sessionStorage.setItem("jwt", resp);
                            window.location.replace("/DistFinalMaven/Salg.html");
                        },
                        error: function(resp) {
                        	console.log("error: " + resp);
                        }
					}); 
				} else{
					$('.login-error: ' + status).show();
                }
			},
			error: function(data, status, jqXHR){
				$('.login-error' + status).show();
			}
		});
                return false;
	});
});