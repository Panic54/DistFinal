$(document).ready(function() {

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
				console.log("Token i buy accepted.");
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
	
	//Fjern item fra tabel og database
	$(document).on("click", "tr", function(event) {

		if (!$(this).text().includes("Name")) {	
			var dataName = $(this).find("td:first").text();
			var dataItem = $(this).find("td:nth-child(2)").text();
			var dataPrice = $(this).find("td:nth-child(3)").text();
			console.log("First table data: " + dataName);
			console.log("Second table data: " + dataItem);
			console.log("Third table data: " + dataPrice);
		//console.log("tr to text: " + $(this).text() + " and false/true: " + $(this).text().includes("Name"));
		//var objectString = $(this).text();
			if (confirm('Do you want to buy this item?')) {
				//$.ajax({
					//url: "rest/rest2/buy1",
					//data: dataName,
					//contentType: "text/plain",
					//method: "POST",
					//success: function(data, status, jqXHR) {
						//console.log("success in buy1, data: " + data + " status: " + status + " jqXHR: " + jqXHR);
						var newData = buyItem(dataName, dataItem, dataPrice);
						$.ajax({
							url: "rest/rest2/buy2",
							data: JSON.stringify(newData),
							contentType: "application/json",
							method: 'POST',
							success: function(data, status, jqXHR){
								location.reload(true);
								console.log("success in buy2, data: " + data, " status: " + status + " jqXHR: " + jqXHR);
							},
							error: function(data, status, jqXHR){
								console.log("error in buy2, data: " + data, " status: " + status + " jqXHR: " + jqXHR);
							}
						});
					//},
					//error: function(data, status, jqXHR) {
					//	console.log("error in buy1, data: " + data + " status: " + status + " jqXHR: " + jqXHR);
					//}
				//});
			}	
		}		
	});
	
	function download() {
		console.log("download kaldt i Buy.js");
		$.ajax({
			url: "rest/rest2/otherStuff",
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
				$('<th>').text("Name"),
				$('<th>').text("Item"),
				$('<th>').text("Price")
		).appendTo("#table");

		$.each(users, function(i, item) {
			$('<tr>').append(
					$('<td>').text(item.name),
					$('<td>').text(item.item),
					$('<td>').text(item.price)
			).appendTo("#table");

		});
	}
	
	
	function buyItem(name, item, price) {
		
		//Manipulating string and then creating JSON object...
		/*
		var objData = object.valueOf();
		var length = name.length;
		var objLength = objData.length;
		var indexOfFirstNumber = object.search("[0-9]");
		
		var finalName = objData.substring(0, length);
		var finalItem = objData.substring(length, indexOfFirstNumber);
		var finalPrice = objData.substring(indexOfFirstNumber, objLength);
		
		console.log("objData: " + objData + " length: " + length + " objLength: " + objLength + " indexOfFirstNumber: " + indexOfFirstNumber);
		console.log("finalName: " + finalName + " finalItem: " + finalItem + " finalPrice: " + finalPrice);
		*/
		//finalObj = {"name":finalName,"item":finalItem,"price":finalPrice};
		var stringObj = '{"name":' + "\"" + name + "\"" + ',"item":'+ "\"" + item + "\"" + ',"price":' + "\"" + price + "\"" + '}';
		console.log(stringObj);
		var finalObj = JSON.parse(stringObj);
		console.log(finalObj);
		
		return finalObj;
		
	}
	

});