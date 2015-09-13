function validate(){
	var msg = " should not be Empty";
	if(document.addEmp.emailId.value=="" ){
        window.alert( "EmailId"+msg );
       document.addEmp.emailId.focus();
         return false;
    }
	if(document.addEmp.password.value=="" ){
        window.alert( "password"+msg );
       document.addEmp.password.focus();
         return false;
    }
	if(document.addEmp.password.value!=document.addEmp.cpassword.value ){
        window.alert( "These passwords don't match. Try again? " );
       document.addEmp.cpassword.focus();
         return false;
    }
	
	if(document.addEmp.name.value=="" ){
        window.alert( "name"+msg );
       document.addEmp.name.focus();
         return false;
    }
	if(document.addEmp.address.value=="" ){
        window.alert( "address"+msg );
       document.addEmp.address.focus();
         return false;
    }
	if(document.addEmp.manager.value=="" ){
        window.alert( "manager"+msg );
       document.addEmp.manager.focus();
         return false;
    }
	if(document.addEmp.phoneNo.value=="" ){
         window.alert( "phoneNo"+msg );
         document.addEmp.phoneNo.focus();
           return false;
      }
	var phoneno = /^\d{10}$/;  
	if(!document.addEmp.phoneNo.value.match(phoneno)){
		window.alert( "Please enter valid phone number" );
		document.addEmp.phoneNo.focus();
      return false;
 }
   }