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
		<%
			String name = (String) context.getAttribute("Name");
		context.setAttribute("Name",name);
		%>

		<%
			int tick = (Integer) context.getAttribute("Tickets");
		%>
		<%
			String date = (String) context.getAttribute("Date");
		%>
		<%
			String email = (String) context.getAttribute("Email");
		%>
		<%
			String total = (String) context.getAttribute("Total");
		%>
		<p id="name"><%=name%></p>
		<p id="tick"><%=tick%></p>
		<p id="date"><%=date%></p>
		<p id="email"><%=email%></p>
		<p id="total"><%=total%></p>

		<script>
			myFunction();
		</script>
		<script type="text/javascript">
			
			function myFunction() {

				var table = document.getElementById("myTable");
				alert(document.getElementById("name").innerText);
				var row = table.insertRow();
				var cell1 = row.insertCell(0);
				var cell2 = row.insertCell(1);
				var cell3 = row.insertCell(2);
				var cell4 = row.insertCell(3);
				var cell5 = row.insertCell(4);
				cell1.innerHTML = document.getElementById("name").textContent;
				cell2.innerHTML = document.getElementById("tick").innerHTML;
				cell3.innerHTML = document.getElementById("date").innerHTML;
				cell4.innerHTML = document.getElementById("email").innerHTML;
				cell5.innerHTML = document.getElementById("total").innerHTML;

			}
		</script>

	</div>
</body>