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
	position: relative;
	top: -22px;
	font: 400 15px/1.8 Lato, sans-serif;
	color: #777;
}
</style>
</head>
<body>
	<%
		request.getRequestDispatcher("header.jsp").include(request, response);
	%>
	<div class="container">

		<h2>Sorting the data in the container</h2>
		<div class="well well-lg" id="box1">3</div>
		<div class="jumbotron" id="box2">1</div>
		<div class="panel panel-default" id="box3"
			style="margin-bottom: 80px;">2</div>
		<script>
			var i = 1;
			var a = [];
			for (i = 1; i < 4; i++)
				a[i] = document.getElementById("box" + i);
			for (i = 1; i < 4; i++) {
				if (a[i].innerHTML > a[i + 1].innerHTML) {
					var temp = a[i].innerHTML;
					a[i].innerHTML = a[i + 1].innerHTML;
					a[i + 1].innerHTML = temp;
				}
			}
		</script>
	</div>
	<div class="container">
		<h2>Sorting the container according to data using arrays</h2>
		<div class="well well-lg" id="box11">3</div>
		<div class="jumbotron" id="box12">1</div>
		<div class="panel panel-default" id="box13">2</div>
		<script>
			var i = 1;
			var a = [];
			for (i = 1; i < 4; i++)
				a[i] = document.getElementById("box1" + i);
			//document.write("Hello");
			for (i = 1; i < 4; i++) {
				if (a[i].innerHTML > a[i + 1].innerHTML) {

					var temp = a[i].className;
					var t = a[i].innerHTML;
					a[i].className = a[i + 1].className;
					//a[i].innerHTML = a[i + 1].innerHTML;
					a[i + 1].className = temp;
					a[i + 1].innerHTML = t;
				}
			}
		</script>
	</div>

	<div class="container" id="div1">
		<h2>Sorting the container according to data using nodes</h2>
		<div class="well well-lg" id="box21">3</div>
		<div class="jumbotron" id="box22">1</div>
		<div class="panel panel-default" id="box23">2</div>
		<script>
			var i = 1, j;
			var a = [];
			for (i = 1; i < 4; i++) {

				a[i] = document.getElementById("box2" + i);
			}
			var element = document.getElementById("div1");
			for (i = 1; i < 4; i++) {
				for (j = i + 1; j < 4; j++) {
					if (a[i].innerHTML > a[j].innerHTML) {
						element.insertBefore(a[j], a[i]);
					}
				}
			}
		</script>
	</div>
	<span alt="Surabhi">Hello</span>
	<script>
		var text = document.getElementsByTagName("span")[0].innerHTML;
		var alt = document.getElementsByTagName("span")[0].getAttribute("alt");
		alert(text + " " + alt + " " + "All boxes are arranged");
	</script>
</body>
</html>
