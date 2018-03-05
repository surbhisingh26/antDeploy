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
	position: relative;
	top: -22px;
	font: 400 15px/1.8 Lato, sans-serif;
	color: #777;
}
</style>
</head>
<body>
	<%request.getRequestDispatcher("header.jsp").include(request, response); %>
	<div class="container-fluid" style="background-color: #c1c1c1;">
		<div class="col-xs-3"></div>
		<div class="col-xs-6">
			<div class="jumbotron"
				style="background-color: #eafbf2; margin-bottom: 183px; margin-top: 183px">
				<form action="loginHome" method="post">
					<div class="form-group">
						<label for="text">Username</label> <input type="text"
							class="form-control " placeholder="Username" name="uname"
							required>
					</div>
					<div class="form-group">
						<label for="pass">Password</label> <input type="password"
							class="form-control" name="pass" placeholder="Password" required>
					</div>

					<button type="submit" class="btn btn-info">Sign in</button>
				</form>
				<div>
					<form action=registration.jsp>

						<button type="submit" class="btn btn-warning pull-right">Sign
							up</button>
						<label class="pull-right" style="margin-right: 10px">New
							User? </label>
					</form>
				</div>
			</div>

		</div>
		<div class="col-xs-3"></div>

	</div>
</body>