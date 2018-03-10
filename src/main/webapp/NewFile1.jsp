<!DOCTYPE html>
<html lang="en">
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
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
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.1/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.1/locales/bootstrap-datepicker.de.min.js"></script>
<script type="text/javascript">
	$(function() {
		$('.date_picker input').datepicker({
			format : "dd/mm/yyyy",
			todayBtn : "linked",

		});
	});
</script>
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
<link rel="import" href="header.jsp" id="templates">
</head>
<body data-spy="scroll" data-target=".navbar" data-offset="50">
	<%
		request.getRequestDispatcher("header.jsp").include(request, response);
	%>


	<div id="home" class="container-full-width">

		<div id="myCarousel " class="carousel slide" data-ride="carousel">
			<ol class="carousel-indicators">
				<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
				<li data-target="#myCarousel" data-slide-to="1"></li>
				<li data-target="#myCarousel" data-slide-to="2"></li>
			</ol>
			<div class="carousel-inner">
				<div class="item active">
					<img src="images/la.jpg" alt="Los Angeles" style="width: 100%;">
					<div class="carousel-caption">
						<h3>Los Angeles</h3>
						<p>LA is always so much fun!</p>
					</div>
				</div>

				<div class="item">
					<img src="images/chicago.jpg" alt="Chicago" style="width: 100%;">
					<div class="carousel-caption">
						<h3>Chicago</h3>
						<p>Thank you, Chicago!</p>
					</div>
				</div>

				<div class="item">
					<img src="images/ny.jpg" alt="New york" style="width: 100%;">

					<div class="carousel-caption">
						<h3>New York</h3>
						<p>We love the Big Apple!</p>
					</div>
				</div>
			</div>

			<a class="left carousel-control" href="#myCarousel" data-slide="prev">
				<span class="glyphicon glyphicon-chevron-left"></span> <span
				class="sr-only">Previous</span>
			</a> <a class="right carousel-control" href="#myCarousel"
				data-slide="next"> <span
				class="glyphicon glyphicon-chevron-right"></span> <span
				class="sr-only">Next</span>
			</a>

		</div>
		<br> <br> <br> <br>
	</div>


	<div id="band" class="container-fluid" style="background-color: white;">
		<br> <br>
		<h4 class="text-center">THE BAND</h4>
		<p class="text-center">
			<small>We love music!</small><br> We have created a fictional
			band website. Lorem ipsum dolor sit amet, consectetur adipiscing
			elit, sed do eiusmod tempor incididunt ut labore et dolore magna
			aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco
			laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure
			dolor in reprehenderit in voluptate velit esse cillum dolore eu
			fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
			proident, sunt in culpa qui officia deserunt mollit anim id est
			laborum consectetur adipiscing elit, sed do eiusmod tempor incididunt
			ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis
			nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
			consequat.
		</p>
		<br> <br>
		<div class="row">
			<div class="container-fluid" style="background-color: white;">
				<div class="col-xs-4 ">
					<p class="text-center">
						<b>Name</b><br> <br> <img src="images/bandmember.jpg"
							class="img-circle person" alt="member" width="255" height="255">
					</p>
				</div>
				<div class="col-xs-4">
					<p class="text-center">
						<b>Name</b><br> <br> <img src="images/bandmember.jpg"
							class="img-circle person" alt="member" width="255" height="255">
					</p>
				</div>
				<div class="col-xs-4">
					<p class="text-center">
						<b>Name</b><br> <br> <img src="images/bandmember.jpg"
							class="img-circle person" alt="member" width="255" height="255">
					</p>
				</div>
			</div>
			<br> <br> <br> <br> <br> <br> <br>
		</div>
	</div>
	<div id="tour" class="container-fluid" style="background-color: black;">
		<div class="container" style="background-color: black;">
			<br> <br> <br>
			<h4 class="text-center">T O U R &nbsp;&nbsp;&nbsp; D A T E S</h4>
			<br>
			<p class="text-center">
				Lorem ipsum we'll play you some music.<br> Remember to book
				your tickets!
			</p>
			<br> <br> <br>

			<ul class="list-group">
				<li class="list-group-item">September <span
					class="label label-danger">Sold Out!</span></li>
				<li class="list-group-item">October <span
					class="label label-danger">Sold Out!</span></li>
				<li class="list-group-item">November <span class="badge">3</span></li>
			</ul>
			<div class="row">

				<div class="col-xs-4">
					<p class="text-center">
						<img src="images/paris.jpg" alt="paris" style="width: 100%;">
					</p>
					<div style="background-color: white;">
						<p class="text-center">
							<strong>Paris</strong><br> Friday 27 November 2015<br>
							<button type="button" style="background-color: #A9A9A9;"
								class="btn" data-toggle="modal" data-target="#myModal">Buy
								Tickets</button>
							<br> <br>
						</p>
					</div>
					<br>
				</div>
				<div class="col-xs-4">
					<p class="text-center">
						<img src="images/newyork.jpg" alt="newyork" style="width: 100%;">
					</p>
					<div style="background-color: white;">
						<p class="text-center">
							<strong>New York</strong><br> Saturday 28 November 2015<br>
							<button type="button" style="background-color: #A9A9A9;"
								class="btn" data-toggle="modal" data-target="#myModal">Buy
								Tickets</button>
							<br> <br>
						</p>
					</div>
					<br>
				</div>
				<div class="col-xs-4" style="margin-bottom: 80px;">
					<p class="text-center">
						<img src="images/sanfran.jpg" alt="sanfran" style="width: 100%;">
					</p>
					<div style="background-color: white;">
						<p class="text-center">
							<strong>San Francisco</strong><br> Sunday 29 November 2015<br>
							<button type="button" style="background-color: #A9A9A9;"
								class="btn" data-toggle="modal" data-target="#myModal">Buy
								Tickets</button>
							<br> <br>
						</p>
					</div>

				</div>

			</div>
			<!-- Modal -->
			<div class="modal fade" id="myModal" role="dialog">
				<div class="modal-dialog">

					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header" style="background-color: #323232;">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h1 class="modal-title text-center" style="font-size: 16">Tickets</h1>
							<br> <br> <br>
						</div>
						<div class="modal-body" style="background-color: white;">
							<br>
							<form action="GetData" method="post">
								<div class="form-group">
									<label for="text">Name</label> <input type="text"
										class="form-control" placeholder="Enter Name" name="name">
								</div>
								<div class="form-group ">
									<label for="text"> Tickets, $23 per person</label>
								</div>
								<div class="input-group number-spinner">
									<span class="input-group-btn data-dwn">
										<button class="btn btn-default " data-dir="dwn" onclick=" return down()">
											<span class="glyphicon glyphicon-minus"></span>
										</button>
									</span> <input id = "spin" type="text" class="form-control text-center" value="1"
										min="1" max="40" id="tickets" class="tickets"> <span
										class="input-group-btn data-up">
										<button class="btn btn-default" data-dir="up" onclick="return up()">
											<span class="glyphicon glyphicon-plus"></span>
										</button>
									</span>
								</div>
								<div class="form-group">
									<label for="text">Travel Date</label>
									<div class="date_picker input-group date">
										<input type="text" id="date" name="date" class="form-control"
											placeholder="DD/MM/YYYY"> <span
											class="input-group-addon"> <span
											class="glyphicon glyphicon-calendar"></span>
										</span>
									</div>
								</div>
								<div class="form-group input-append spinner"
									data-trigger="spinner">
									<label for="email">Send To</label> <input type="email"
										class="form-control " id="email" placeholder="Enter Email"
										name="email">
								</div>
								<button type="submit" class="btn btn-block">Pay</button>
							</form>
						</div>
						<div class="modal-footer">
							<p class=text-left>
								<button type="button" class="btn "
									style="background-color: black;" data-dismiss="modal">Cancel</button>
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="contact" class="container-fluid"
		style="background-color: white;">
		<div class="container">
			<br> <br> <br>
			<h4 class="text-center">
				<strong>C o n t a c t</strong>
			</h4>
			<br>
			<p class="text-center">We love fans</p>
			<br>
			<div class="col-xs-4">
				<p class="text-left">Fan? Drop a note.</p>

				<p class="text-left">
					<i class="glyphicon glyphicon-map-marker"></i>Chicago, US
				</p>
				<p class="text-left">
					<i class=" glyphicon glyphicon-phone"></i>Phone: +00 1515151515
				</p>

				<p class="text-left">
					<i class="glyphicon glyphicon-envelope"></i>Email: mail@mail.com
				</p>

			</div>
			<div class="col-xs-8">

				<form class="form-inline" action="/action_page.php">
					<div class="form-group">
						<input type="text" class="form-control input-lg"
							placeholder="Name">
					</div>
					<div class="form-group">
						<input type="email" class="form-control input-lg" id="email"
							placeholder="Email" name="email">
					</div>
				</form>
				<br> <br>
				<div>
					<form class="form-horizontal">
						<div class="form-group">

							<textarea class="form-control " rows="5" id="comment"
								placeholder="Comment"></textarea>
						</div>
						<p class="text-right">
							<button type="submit" class="btn"
								style="background-color: #A9A9A9;">Send</button>
						</p>
					</form>
				</div>
			</div>
			<br> <br>
			<h4 class="text-center">
				<strong>From The Blog</strong>
			</h4>
			<br>
			<ul class="nav nav-tabs">
				<li class="active"><a data-toggle="tab" href="#home">Mike</a></li>
				<li><a data-toggle="tab" href="#menu1">Chandler</a></li>
				<li><a data-toggle="tab" href="#menu2">Peter</a></li>

			</ul>

			<div class="tab-content">
				<div id="home" class="tab-pane fade in active">
					<h3>Mike Ross, Manager</h3>
					<p>Man, we've been on the road for some time now. Looking
						forward to lorem ipsum.</p>
				</div>
				<div id="menu1" class="tab-pane fade">
					<h3>Chandler Bing, Guitarist</h3>
					<p>Always a pleasure people! Hope you enjoyed it as much as I
						did. Could I BE.. any more pleased?</p>
				</div>
				<div id="menu2" class="tab-pane fade">
					<h3>Peter Griffin, Bass player</h3>
					<p>I mean, sometimes I enjoy the show, but other times I enjoy
						other things.</p>
				</div>
				<br> <br>
			</div>
		</div>
	</div>
<script>
var val =document.getElementById("spin");
var n;
function down(){
	
	if(parseInt(val.value).trim()>=1)
		n -= 1;
	return(n);
}
function up(){
	if(val.value<=40)
	val.value += 1;
	return(val.value);
}
</script>
</body>
</html>
