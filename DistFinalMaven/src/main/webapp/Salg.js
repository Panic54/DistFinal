$(document).ready(function() {
		
	//alert("token is: " + sessionStorage.getItem("jwt"));
	
	
	if (sessionStorage.getItem("jwt") == null) {
		console.log("token is empty");
	}
	
	$.ajax({
		url: "rest/rest2/validate",
		data: sessionStorage.getItem("jwt"),
		contentType: "text/plain",
		method: "POST",
		success: function(data, status, jqXHR) {
			console.log("data: " + data + " status: " + status + " jqXHR:" + jqXHR);
			if(status == "forbidden") {
				console.log("Token not accepted!");
				window.location.replace("/DistFinalMaven/");
			}
			else if (status == "success") {
				console.log("Token accepted.");
			}
			else {
				console.log("An error occured.");
				window.location.replace("/DistFinalMaven/");
			}
		},
		error: function(data, status, jqXHR) {
			console.log("error: " + status);
			window.location.replace("/DistFinalMaven/");
		}
	});
	
	$("#buy").click(function() {
		
		window.location.replace("/DistFinalMaven/Buy.html");
		
	});
	
});