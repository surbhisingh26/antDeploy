function check() {
	var pass = document.getElementById("pass").value;
	var passConfirm = document.getElementById("passConfirm").value;

	var mobile = document.getElementById("mobile").value;
	if (mobile != "")
		mobile = parseInt(mobile);
	//var fname =document.getElementsByName("lname").value;
	if (passConfirm != pass) {
		document.getElementById("wrong").innerText = "Passwords do not match!!!";
		return false;
	} else {
		document.getElementById("wrong").innerText = "";

	}
	//document.getElementById("nan").innerText = mobile;
	if (isNaN(mobile)) {
		document.getElementById("nan").innerText = "Only numeric values allowed!!!";
		return false;
	} else {
		document.getElementById("nan").innerText = "";
	}

	return true;
	//document.getElementById("Btn").submit();
}
function passLength(){
	var pass = document.getElementById("pass").value;
	if(pass.length<7){
		document.getElementById("len").innerText="Password should be of minimum 8 characters";

	}
	else
		document.getElementById("len").innerText="";

}

function fnames(){
	fname = document.getElementById("fname").value;
	if(fname.length==1){

		document.getElementById("fname").value=fname.toUpperCase();
	}
}

function lnames(){
	lname = document.getElementById("lname").value;
	if(lname.length==1){

		document.getElementById("lname").value =lname.toUpperCase();

	}


}
/*function checkLogin(){
	document.getAttribute("name").innerHtml
	
}*/