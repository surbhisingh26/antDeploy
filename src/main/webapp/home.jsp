<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container-fluid">
<nav class="navbar navbar-inverse">
  
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Logo</a>
    </div>
    <ul class="nav navbar-nav navbar-right">
      <li class="active"><a href="#">Home</a></li>
      <li><a href="#">Band</a></li>
      <li><a href="#">Tour</a></li>
      <li><a href="#">Contact</a></li>
      <li class="dropdown"><a class="deopdown-toggle" data-toggle="dropdown" href="#">More<span class="caret"></span></a>
      <ul class="dropdown-menu">
          <li><a href="#">Merchandise</a></li>
          <li><a href="#">Extras</a></li>
          <li><a href="#">Media</a></li>
        </ul>
      </li>
      <li><button class="btn btn-default btn-sm" type="submit" style="background-color:black;">
            <i class="glyphicon glyphicon-search" ></i>
          </button></li>
    </ul>
  
</nav>

  
     <div id="myCarousel" class="carousel slide" data-ride="carousel" >
       <ol class="carousel-indicators">
      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
      <li data-target="#myCarousel" data-slide-to="1"></li>
      <li data-target="#myCarousel" data-slide-to="2"></li>
    </ol>
    <div class="carousel-inner">
      <div class="item active">
        <img src="images/la.jpg" alt="Los Angeles" style="width:100%;">
        <div class="carousel-caption">
          <h3>Los Angeles</h3>
          <p>LA is always so much fun!</p>
        </div>
      </div>

      <div class="item">
        <img src="images/chicago.jpg" alt="Chicago" style="width:100%;">
        <div class="carousel-caption">
          <h3>Chicago</h3>
          <p>Thank you, Chicago!</p>
        </div>
      </div>
    
      <div class="item">
        <img src="images/chicago.jpg" alt="New york" style="width:100%;">
      
      <div class="carousel-caption">
          <h3>New York</h3>
          <p>We love the Big Apple!</p>
        </div>
    </div>
</div>
    
    <a class="left carousel-control" href="#myCarousel" data-slide="prev">
      <span class="glyphicon glyphicon-chevron-left"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#myCarousel" data-slide="next">
      <span class="glyphicon glyphicon-chevron-right"></span>
      <span class="sr-only">Next</span>
    </a>
    <br><br>
  </div>
</div>
<div class="container-fluid" style="background-color:white;">
<h4 class="text-center">THE BAND</h4>
<p class="text-center"><small>We love music!</small><br>

We have created a fictional band website. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p><br><br>
<div class="row">
    <div class="container-fluid" style="background-color:white;">
    <div class="col-xs-4 ">
    <p class="text-center"><b>Name</b><br><br><img src="images/bandmember.jpg" class="img-circle person" alt="member"  width="255" height="255"></p></div>
    <div class="col-xs-4" >
    <p class="text-center"><b>Name</b><br><br><img src="images/bandmember.jpg" class="img-circle person" alt="member"  width="255" height="255"></p></div>
    <div class="col-xs-4">
    <p class="text-center"><b>Name</b><br><br><img src="images/bandmember.jpg" class="img-circle person" alt="member"  width="255" height="255"></p></div>
    </div>
    <br><br><br><br><br><br><br>
  </div>
</div>
<div class="container-fluid" style="background-color:black;">
<div class="container" style="background-color:black;">
<br><br><br>
<h4 class="text-center">T O U R &nbsp;&nbsp;&nbsp; D A T E S </h4><br>
<p class="text-center">Lorem ipsum we'll play you some music.<br>
Remember to book your tickets!</p>
<br>
<br><br>

  <ul class="list-group">
    <li class="list-group-item">September <span class="label label-danger">Sold Out!</span></li>
    <li class="list-group-item">October <span class="label label-danger">Sold Out!</span></li>
    <li class="list-group-item">November <span class="badge">3</span></li>
  </ul>
  <div class="row">
    
    <div class="col-xs-4" >
    <p class="text-center"><img src="images/paris.jpg" alt="paris"  style="width:100%;"></p>
    <div style="background-color:white;">
    <p class="text-center"><strong>Paris</strong><br>
    Friday 27 November 2015<br>
    <button type="button" style="background-color:gray;" class="btn" data-toggle="modal" data-target="#myModal">Buy Tickets</button><br><br></p>
    </div>
    <br>
    </div>
    <div class="col-xs-4">
    <p class="text-center"><img src="images/newyork.jpg" alt="newyork" style="width:100%;"></p>
    <div style="background-color:white;">
    <p class="text-center"><strong>New York</strong><br>
    Saturday 28 November 2015<br>
    <button type="button" style="background-color:gray;"class="btn" data-toggle="modal" data-target="#myModal">Buy Tickets</button><br><br></p>
    </div><br></div>
    <div class="col-xs-4">
    <p class="text-center"><img src="images/sanfran.jpg" alt="sanfran"  style="width:100%;"></p>
    <div style="background-color:white;">
    <p class="text-center"><strong>San Francisco</strong><br>
    Sunday 29 November 2015<br>
    <button type="button" style="background-color:gray;" class="btn" data-toggle="modal" data-target="#myModal">Buy Tickets</button><br><br></p></div><br>
    </div>
    <br><br><br><br><br><br><br>
  </div>
  <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header" style="background-color:black;">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Tickets</h4>
        </div>
        <div class="modal-body" style="background-color:white;">
          <p></p>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>
</div>
</div>
</body>
</html>
