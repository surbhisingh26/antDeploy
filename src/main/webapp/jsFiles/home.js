
					function login(){
						alert("hello");
					var element =document.getElementById("ref");
					var add =document.createElement("span");
					var log = document.getElementById("login");
					var ul = document.createElement("ul");
					var name=session.getAttribute("name");
							if (name == null) {
					log.innerHTML="Login";
					element.setAttribute("href","login.jsp");
					
					
							} else {
				
					document.getElementById("login").seAttribute("class","dropdown");
					element.setAttribute("class","dropdown-toggle");
					document.getElementById("ref").seAttribute("data-toggle","dropdown");
					element.appendChild(add);
					add.setAttribute("class","caret");
					
						log.appendChild(ul);
						ul.setAttribute("class","dropdown-menu");
						ul.appendChild(document.createElement("li"));
								}							
					}				