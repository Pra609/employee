

var cid=localStorage.getItem("companyId");
console.log(cid+"cid")

 $(document).ready(
		function() {

			// SUBMIT FORM
			$("#addDepartments").submit(function(event) {
				// Prevent the form from submitting via the browser.
				event.preventDefault();

				//if noerror
				if(validated)
				{
					console.log('post call happened')
					ajaxPost();
				}

			});

			function ajaxPost() {

				// PREPARE FORM DATA
				var formData = {

						dname:$("#dname").val(),




				}


				// DO POST
				$.ajax({
					type : "POST",
					contentType : "application/json",
				    url : "/department/"+cid,
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
