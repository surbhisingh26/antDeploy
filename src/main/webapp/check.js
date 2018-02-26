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
			if(pass.length<8){
				document.getElementById("len").innerText="Password should be of minimum 8 characters";
				
			}
			else
				document.getElementById("len").innerText="bfd";
			
		}