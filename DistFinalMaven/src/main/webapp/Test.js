$(document).ready(
			
			//var obj = {"username":"s154102","password":"abc123"};
			//var myJSON = JSON.stringify(obj);
			
			function() {
				$("#btns").click(
						function() {
							var data = $('#formID').serializeObject();
							alert(data.username);
							$.ajax({
								url : "rest/login/test",
								method : "POST",
								data     :  JSON.stringify(data),
								contentType : "application/json",
								success : function(resp) {
									alert(resp);
									//window.location.replace("www.google.com");
									//window.location.href = ;
								},
								
								error : function(xhr) {
									alert('an error occured ' + xhr.status
											+ ' status text ' + xhr.statusText
											+ ' ' + xhr.responseText);
								}
								/*,
								complete: function () {
				                    window.location.href = "www.google.com";
				                }*/
							});
						});
			});