
 function printError(elemId, hintMsg) {
    document.getElementById(elemId).innerHTML = hintMsg;
}

function submitForm(){
	document.getElementById("addDepartments").submit();
}

// Defining a function to validate form
function validateForm() {
    // Retrieving the values of form elements

    var name=document.addCompanies.name.value;

  	//var role = document.addCustomer.role.value;



    var  nameErr=true;

     console.log(name+"name")


    // Validate name

    //validate city
     if(name == "") {
        printError("nameErr", "Please  enter a company name");
    }  else {
            printError("nameErr", "");
            nameErr = false;
        }






  // validate password





    // Prevent the form from being submitted if there are any errors
    if((nameErr) == true) {
       return false;
       console.log(nameErr)

    } else {

        // Creating a string from input data for preview
        var dataPreview = "You've entered the following details: \n" +

                           "Name: " + name+ "\n"



       console.log(dataPreview)
        // Display input data in a dialog box before submitting the form
       if(dataPreview!=null){
	   alert(dataPreview);
	    window.location = "/admin/departmentView";
       }else{
	     alert("something went wrong");
	     window.location = "/admin/addCompany";
       }

}
    };
