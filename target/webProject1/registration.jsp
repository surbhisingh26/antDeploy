<!DOCTYPE html>
<html lang="en">
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link href="https://fonts.googleapis.com/css?family=Lato"
	rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Montserrat"
	rel="stylesheet" type="text/css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>
	<div class="container">
	<div class="col-xs-2"></div>
	<div class="col-xs-8">
		<h1>
			<center>Registration</center>
		</h1>
		<form action="Register" method="post">
			<div class="form-group inline">
				<label for="text">Name :</label> <input type="text"
					class="form-control" name="fname" placeholder="First Name" required>
				<input type="text" class="form-control" name="lname"
					placeholder="Last Name" required>
			</div>
			<div class="form-group">
				<div>
					<label for="text">Username :</label> <input type="text"
						class="form-control" name="username"
						placeholder="Username or email" required>
				</div>
				<br><label>Gender :</label>
				<div class="radio">
					<label><input type="radio" name="gender" value = "female" required>Female </label>
				</div>
				<div class="radio">
					<label><input type="radio" name="gender" value = "male" required>Male </label>
				</div>
				<div>
					<label for="text">Mobile :</label> <input type="text"
						class="form-control" id="mobile" name="mobile" placeholder="Mobile Number" required>
						<p id = "nan" style="color:red;"></p>
				</div>
				<div>
					<label for="pass">Password :</label> <input type="password"
						class="form-control" name="pass" placeholder="Password"id= "pass" required>
				</div>
				<div>
					<label for="pass">Confirm Password :</label> <input type="password"
						class="form-control" name="passConfirm"
						placeholder="Re-Type Your Password" id="passConfirm" required>
					<p id="wrong" style="color:red;"></p>
				</div>
				<div>
					<label for="text">Country :</label> <input type="text"
						class="form-control" name="country" placeholder="Country Name" required>
				</div>
				<div>
					<label for="text">City :</label> <input type="text"
						class="form-control" name="city" placeholder="City Name" required>
				</div>
				<br><br>
					<button id = "Btn" class="btn btn-info pull-right" type="submit" onclick="return check()" name="Btn">Submit</button>
				
			</div>
		</form>
		</div>
		<div class="col-xs-2"></div>
	</div>
	<script>
function check(){
	var pass=document.getElementById("pass").value;
	var passConfirm=document.getElementById("passConfirm").value;
	var mobile =parseInt(document.getElementById("mobile").value);
	var fname =document.getElementsByName("lname").value;
	if(passConfirm!=pass){
		document.getElementById("wrong").innerText = "Passwords do not match!!!";
		return false;
 	}
	else{
		document.getElementById("wrong").innerText = "";
		
	}
	//document.getElementById("nan").innerText = mobile;
	if(isNaN(mobile)){
		document.getElementById("nan").innerText = "Only numeric values allowed!!!";
		return false;
	}
	else{
		document.getElementById("nan").innerText = "";
	}
	
	return true;
	//document.getElementById("Btn").submit();
}
</script>
</body>
</html>