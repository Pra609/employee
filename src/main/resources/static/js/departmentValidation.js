 var validated=false
 function printError(elemId, hintMsg) {
    document.getElementById(elemId).innerHTML = hintMsg;
}

function submitForm(){
	document.getElementById("addDepartments").submit();
}

// Defining a function to validate form

                                             // Defining a function to validate form
              function validateForm() {
                                                 // Retrieving the values of form elements

                                                 var name=document.addDepartments.dname.value;

                                               	//var role = document.addCustomer.role.value;
                                                    var  nameErr =true;

                                                  console.log(name)

                                                 // Validate name

                                                 //validate city



                                               // validate password

                                                 if(name == "") {
                                                     printError("nameErr", "Please enter department name");
                                                 } else {
                                                         printError("nameErr", "");
                                                         nameErr = false;
                                                     }





                                                 // Prevent the form from being submitted if there are any errors
                                                 if(nameErr== true) {
                                                 console.log(nameErr)
                                                    return false;
                                                 } else {
                                                     // Creating a string from input data for preview
                                                     console.log("else")

                                                     var dataPreview = "You've entered the following details: \n" +


                                                                        "Name: " + name + "\n";


                                                    console.log(dataPreview)
                                                     // Display input data in a dialog box before submitting the form

                                                     submitForm()
                                             	   alert(dataPreview);
                                             	  window.location = "/admin/departmentView";
                                             	   validated=true;
                                             	   return true;


                                                 }

                                             };


