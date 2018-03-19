function table() {
alert("Hello");
				var table = document.getElementById("myTable");
				var row = table.insertRow();
				var cell1 = row.insertCell(0);
				var cell2 = row.insertCell(1);
				var cell3 = row.insertCell(2);
				var cell4 = row.insertCell(3);
				var cell5 = row.insertCell(4);
				var cell6 = row.insertCell(5);
				var cell7 = row.insertCell(6);
				
				cell1.innerHTML = {{name}};
				cell2.innerHTML = document.getElementById('tick').getAttribute('value');
				cell3.innerHTML = document.getElementById('email').getAttribute('value');
				cell4.innerHTML = document.getElementById('total').getAttribute('value');
				cell5.innerHTML = document.getElementById('date').getAttribute('value');
				cell6.innerHTML = document.getElementById('time').getAttribute('value');
				cell7.innerHTML = '<a href="#" style="margin-left:10px;">Edit </a><a href="#"> Delete</a>'
			}