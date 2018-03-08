<!DOCTYPE html>
<html lang="en">
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>
body {
	font: 400 15px/1.8 Lato, sans-serif;
	color: #777;
}
</style>
</head>
<body>
	<div class="container" onload="myFunction()">
	<h3  style="margin-top:40px;">Your Reservation History</h3>
		<table class="table table-bordered" id="myTable" style="margin-top:40px;">
			<tr>
				<th>Name</th>
				<th>Number of Tickets</th>
				<th>Travel date</th>
				<th>Email</th>
				<th>Total</th>
			</tr>			
			
			
		</table>
	</div>
	
</body>