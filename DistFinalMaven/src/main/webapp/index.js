$(document).ready(function() {
    
	//alert("Javascript ready");
	
	$("#btn").click(function() {
		
		//Prevent form from reseting on incorrect login credentials
		//event.preventDefault();

		var data = $("#loginForm").serializeObject();
		
		$.ajax({
			url: "rest/rest2/javabog",
			data: JSON.stringify(data),
			contentType: "application/json",
			method: 'POST',
			success: function(loginOk){
				if(loginOk){
					$.ajax({
						url: "rest/rest2/build",
						data: JSON.stringify(data),
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
				} else {
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