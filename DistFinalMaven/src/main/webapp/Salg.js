$(document).ready(function() {
		
	//alert("token is: " + sessionStorage.getItem("jwt"));
	
	
	$.ajax({
		url: "rest/rest2/validate",
		data: sessionStorage.getItem("jwt"),
		contentType: "text/plain",
		method: "POST",
		success: function(data, textStatus, jqXHR) {
			console.log("data: " + data + " textStatus: " + textStatus + " jqXHR:" + jqXHR);
			if(textStatus == "forbidden") {
				console.log("Token not accepted!");
				window.location.replace("/DistFinalMaven/");
			}
			else if (textStatus == "success") {
				console.log("Token accepted.");
			}
			else {
				console.log("An error occured.");
				window.location.replace("/DistFinalMaven/");
			}
		},
		error: function(resp) {
			console.log("error: " + resp.status);
			window.location.replace("/DistFinalMaven/");
		}
	});
	
	$("#buy").click(function() {
		
		window.location.replace("http://localhost:8080/DistFinalMaven/Køb.html");
		
	});

	$("#btn").click(function() {
		var data = $("#itemform").serializeObject();
		//alert(data.name +" " + data.item +" " + data.price)
		$.ajax({
			url: "rest/rest2/sell",
			data: JSON.stringify(data),
			contentType: "application/json",
			method: 'POST',
			success: function(loginOk){
                            alert(loginOk);
			},
			error: function(resp){
                            $('.login-error').show();
			}
		});
		
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