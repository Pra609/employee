

var cid=localStorage.getItem("companyId");
console.log(cid+"cid")

$(document).ready(
		function() {

			// SUBMIT FORM
			$("#adddepartments").submit(function(event) {
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
					url : "/company/addDepartment/"+cid,
					data : JSON.stringify(formData),
					dataType : 'json',
					success : function(data)
				 {
				   console.log(data)
		        	if (data != null)
					{

						 alert("You are successfully added department");
                   		 window.location = "/admin/departmentView";
					}
					else
					{
						 alert("something went wrong");
                   		 window.location = "/admin/addDepartment";

					}
					},


				});

			}

		})
