
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
				var cell7 = row.insertCell(6);
				cell1.innerHTML = '<%=(String) context.getAttribute("Name")%>';
				cell2.innerHTML = '<%=(Integer) context.getAttribute("Tickets")%>';
				cell3.innerHTML = '<%=(String) context.getAttribute("Date")%>';
				cell4.innerHTML = '<%=(String) context.getAttribute("Time")%>';
				cell5.innerHTML = '<%=(String) context.getAttribute("Email")%>';
				cell6.innerHTML = '<%=(Integer) context.getAttribute("Total")%>';
				cell7.innerHTML = '<a href="#" style="margin-left:10px;">Edit </a><a href="#"> Delete</a>'
			}
			myFunction();
		</script>

	</div>
</body>