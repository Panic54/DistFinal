$(document).ready(function() {

	$("#btn").click(function() {
		
		getName(getCallBackData);

                return false;
	});
	
	
	
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
	
	$("#buy").click(function() {
		
		window.location.replace("/DistFinalMaven/Køb.html");
		
	});
	
	function getCallBackData(result) {
		console.log("result from callbackdata: " + result);
		var name = result;
		console.log("name from callbackdata: " + name);
		
		var itemData = $("#itemform").serializeObject();
		
		//creating final JSON object...
		var obj = {
				"name":name.valueOf(),
				"item":itemData.item,
				"price":itemData.price
		};
		
		$.ajax({
			url: "rest/rest2/sell",
			data: JSON.stringify(obj),
			contentType: "application/json",
			method: 'POST',
			success: function(data, status, jqXHR){
				location.reload(true);
				console.log("success, data: " + data, " status: " + status + " jqXHR: " + jqXHR);
			},
			error: function(data, status, jqXHR){
				console.log("error, data: " + data, " status: " + status + " jqXHR: " + jqXHR);
			}
		});
		
	}
	
	function getName(callbackData) {
		$.ajax({
			url: 			"rest/rest2/getName",
			data: 			sessionStorage.getItem("jwt"),
			contentType:	"text/plain",
			method: 		"POST",
			async:			false,
			success:		callbackData,		//Kalder metoden callbackData... som er parameter for metoden getName.
			error:			function(data, status, jqXHR) {
				console.log("error in get name.");
			}			
		});
	}
	
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
		
});