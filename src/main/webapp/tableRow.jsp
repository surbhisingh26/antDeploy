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
	<div class="container">

		<%
			ServletContext context = getServletContext();
		%>
		<script type="text/javascript">
			
			function myFunction() {
				var table = document.getElementById("myTable");
				var row = table.insertRow();
				var cell1 = row.insertCell(0);
				var cell2 = row.insertCell(1);
				var cell3 = row.insertCell(2);
				var cell4 = row.insertCell(3);
				var cell5 = row.insertCell(4);
				var cell6 = row.insertCell(5);
				cell1.innerHTML = '<%=(String) context.getAttribute("Name")%>';
				cell2.innerHTML = '<%=(Integer) context.getAttribute("Tickets")%>';
				cell3.innerHTML = '<%=(String) context.getAttribute("Date")%>';
				cell4.innerHTML = '<%=(String) context.getAttribute("Email")%>';
				cell5.innerHTML = '<%=(String) context.getAttribute("Total")%>';
				cell6.innerHTML = '<a href="#" style="margin-left:10px;">Edit</a><a href="#">Delete</a>'
			}
			myFunction();
		</script>

	</div>
</body>