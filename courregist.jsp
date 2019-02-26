<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
	<head>
		<title>Courier Regist</title>
		<link rel='stylesheet' type='text/css' href='css/courregist.css' />
		<script src='js/jquery/jquery.js'></script>
	</head>
	<body>
		<div id='logoimg'>
			<img src='images/logo.png' />
		</div>
		<form action='courregist.do'>
			<table>
				<caption>Courier Registration -</caption>
				<tbody id='body'>
					<tr>
						<th>Name</th>
						<td>:</td>
						<td><input type='text' name='name'/></td>
					</tr>
					<tr>
						<th>E-mail</th>
						<td>:</td>
						<td><input type='text' id='email' name='email'/></td>
					</tr>
					<tr>
						<th>Password</th>
						<td>:</td>
						<td><input type='password' name='password'/></td>
					</tr>
					<tr>
						<th>Address</th>
						<td>:</td>
						<td><input type='text' name='address' /></td>
					</tr>
					<tr>
						<th>State</th>
						<td>:</td>
						<td>
							<select id='states'>	
								<option value='0'> -select- </option>
								<c:forEach var='state' items='${states}'>
								<option value='${state.stateId}'>${state.stateName}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<th>City</th>
						<td>:</td>
						<td>
							<select name='cityId' id='cities'>
								<option value='0'> -select- </option>
							</select>
						</td>
					</tr>
					<tr>
						<td class='tdb' colspan='4'>
							<input id='hbtn' value='proceed' type='button' />
						</td>
					</tr>
				</tbody>
				<tfoot id='foot'>
					<tr>
						<td id='nmessage' class='tdb' colspan='4'>
							Registration key is sending ....
						</td>
					</tr>
					<tr class='inreg'>
						<th>Registration-key</th>
						<td>:</td>
						<td><input name='regkey' type='text' /></td>
					</tr>
					<tr>
						<td class='tdb inreg' colspan='3'>
							<input id='subbtn' type='submit' />
						</td>
					</tr>
				</tfoot>
			</table>
		</form>
		<script src='js/courregist.js'></script>
	</body>
</html>