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
</head>
<body>

	<div class="container">
		<div class="well well-lg" id="1">3</div>
		<div class="jumbotron" id="2">1</div>
		<div class="panel panel-default" id="3">2</div>
		<script>
			var i = 1;
			var a = [];
			for (i = 1; i < 4; i++)
				a[i] = document.getElementById(i);
			for (i = 1; i < 4; i++) {
				
				if (a[i].innerHTML > a[i + 1].innerHTML){
					var temp = a[i].innerHTML;
				a[i].innerHTML = a[i + 1].innerHTML;
				a[i + 1].innerHTML = temp;
				}
			}
		</script>
	</div>
</body>
</html>
