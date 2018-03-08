function checkLogin(){
	var user = '<%=(String)session.getAttribute("user")';
	if(user==null){
		alert("Login to buy tickets");
		return false;
	}
}