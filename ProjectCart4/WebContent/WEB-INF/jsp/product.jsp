<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
td.avail{
color:red}
</style>
<script>
	var request;
	function sendInfo() {
		var id = document.product.id.value;
		var pname = document.product.pname.value;
		var price = document.product.price.value;
		var quantity = document.product.quantity.value;
	
		var url = "checkAvailability?id=" + id+"&pname="+pname+"&price="+price+"&quantity="+quantity;

		if (window.XMLHttpRequest) {
			request = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			request = new ActiveXObject("Microsoft.XMLHTTP");
		}

		try {
			request.onreadystatechange = getInfo;
			request.open("GET", url, true);
			request.send();
		} catch (e) {
			alert("Unable to connect to server");
		}
	}

	function getInfo() {
		if (request.readyState == 4) {
			var val = request.responseText;
			document.getElementById('availability').innerHTML = val;
			/* document.getElementById("myForm").reset(); */
		}
	}
</script>

<title>Insert title here</title>
</head>

<body>
<form name="product">
	<table>
		<tr>
			<td></td>
			<td><input type="hidden" name="id" value="1"></td>
		</tr>
		<tr>
			<td>Name</td>
			<td><input type="text" name="pname" value="phone"></td>
		</tr>
		<tr>
			<td>Price</td>
			<td><input type="text" name="price" value="800"></td>
		</tr>
		<tr>
			<td>Quantity</td>
			<td><input type="text"  name="quantity" placeholder="Enter quantity" onfocus="this.value=''" required="required"></td><td class="avail"><span id="availability"> </span> </td>
			<td><input type="button" value="CheckAvailability" onClick="sendInfo()">
			</td>
		</tr>
	</table>
</form>

</body>
</html>