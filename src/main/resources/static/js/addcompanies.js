

 $(document).ready(
		function() {

			// SUBMIT FORM
			$("#addCompanies").submit(function(event) {
				// Prevent the form from submitting via the browser.
				event.preventDefault();

				//if noerror
				if(companyValidated)
				{
					console.log('post call happened')
					ajaxPost();
				}

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
					encode: true,
					})
					.done(function(data){
		        			console.log(data);
		        			alert("you are successfully submitted");
					 });



				}


				});
