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


<style>
/* Note: Try to remove the following lines to see the effect of CSS positioning */
.affix {
	top: 0;
	width: 100%;
	z-index: 9999 !important;
}

.affix+.container-fluid {
	padding-top: 70px;
}

body {
	font: 400 15px/1.8 Lato, sans-serif;
	color: #777;
}

</style>
<!-- <link rel="import" href="home.jsp">-->

</head>
<body data-spy="scroll" data-target=".navbar" data-offset="50">

	<div class="container-full-width">
		<nav class="navbar navbar-fixed-top navbar-inverse"
			style="opacity: 0.8;" data-spy="affix" data-offset-top="197">

			<div class="navbar-header">
				<a class="navbar-brand" href="#">Logo</a>
			</div>

			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav navbar-right">
					<li class="active"><a href="/webProject1/#home">Home</a></li>
					<li><a href="/webProject1/#band">Band</a></li>
					<li><a href="/webProject1/#tour">Tour</a></li>
					<li><a href="/webProject1/#contact">Contact</a></li>
					
					<%
					String name=(String)session.getAttribute("name");
							if (name == null) {
						%>
					<li><a href="login.jsp">Login</a></li>
					<%
							} else {
								
						%><li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#"> <%=name%> <span class="caret"></span></a>
						<%
								}
							%>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item" href="Profile">View Profile</a></li>
							<li><a class="dropdown-item" href="#">Settings</a></li>
							<li><a class="dropdown-item" href="logout">Logout</a></li>
							<li><a class="dropdown-item" href="table">History</a></li>
						</ul></li>
					<li style="margin-right: 15px"><a href="#"><i
							class="glyphicon glyphicon-search"></i></a></li>
				</ul>
			</div>
		</nav>
		<div id="home1"><p></p></div>
		</div>
		<script >
		function home3(){
			alert("Hello3");
			var h= document.getElementById("home1").innerHTML;
			 <link rel="import" href="home.jsp"> 
			h = document.getElementById("home").innerHTML;
		}
		</script>
		</body>
		</html>