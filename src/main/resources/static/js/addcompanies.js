$(document).ready(
		function() {

			// SUBMIT FORM
			$("#addCompanies").submit(function(event) {
				// Prevent the form from submitting via the browser.
				event.preventDefault();
				ajaxPost();
			});

			function ajaxPost() {

				// PREPARE FORM DATA
				var formData = {
					name:$("#name").val(),






				}

				// DO POST
				$.ajax({
					type : "POST",
					contentType : "application/json",
					url : "/company",
					data : JSON.stringify(formData),
					dataType : 'json',
					success : function(data)
				 {
				   console.log(data)
		        	if (data != null)
					{

						 alert("You are successfully added Company");
                   		 window.location = "/admin/home";
					}
					else
					{
						 alert("something went wrong");
                   		 window.location = "/admin/addCompany";

					}
					},


				});

			}

		})
