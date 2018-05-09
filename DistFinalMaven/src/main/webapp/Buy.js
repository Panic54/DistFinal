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
				download();
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
	
	$("#sell").click(function() {
		
		window.location.replace("/DistFinalMaven/Salg.html");
		
	});

	$('ul#menu li').click(function(e) { 
		alert($(this).text() + " value: " + $(this).val());
	});
	
	function download() {
		$.ajax({
			url: "rest/rest2/yourStuff",
			data: sessionStorage.getItem("jwt"),
			contentType: "text/plain",
			method: "POST",
			success: function(data, status, jqXHR) {
				console.log("success, data: " + data + " status: " + status + " jqXHR: " + jqXHR);
				loadTable(data);
			},
			error: function(data, status, jqXHR) {
				console.log("error, data: " + data + " status: " + status + " jqXHR: " + jqXHR);
			}
		});
	}
	
	function loadTable(users) {
		
		$('<tr>').append(
				$('<th>').text("Item"),
				$('<th>').text("Price")
		).appendTo("#table");
		
		$.each(users, function(i, item) {
			$('<tr>').append(
					$('<td>').text(item.item),
					$('<td>').text(item.price)
			).appendTo("#table");
			
		});
	}

//	$("#btn").click(function() {
//		var data = $("#itemform").serializeObject();
//		//alert(data.name +" " + data.item +" " + data.price)
//		$.ajax({
//			url: "rest/rest2/sell",
//			data: JSON.stringify(data),
//			contentType: "application/json",
//			method: 'POST',
//			success: function(loginOk){
//                            alert(loginOk);
//			},
//			error: function(resp){
//                            $('.login-error').show();
//			}
//		});
//		
//	});
	
	
});