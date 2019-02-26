<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<title>Courier Orders</title>
		<link rel='stylesheet' type='text/css' href='css/common.css' />
		<link rel='stylesheet' type='text/css' href='css/courierorderupdate.css' />
		<script src='js/jquery/jquery.js' ></script>
	</head>
	<body>
		<%@ include file='courierheader.jsp'%>
		<%@ include file='couriernavbar.jsp'%>
		<div id='main'>
			<div class='searchbox'>
				Order Id :-
				<input id='search' placeholder='123456'type="text" />
				<button class='btn' id='findorder'>Find</button>
			</div>
			<div id='box'>
				<div id='detail'>
					<div id='dethead'>Details</div>
					<div class='prop'>Order-Id :-</div>
					<div class='propd' id='ordid'></div><br/>
					<div class='prop'>Ordered-Date :-</div>
					<div class='propd' id='orddat'></div><br/>
					<div class='prop'>Customer-Name :-</div>
					<div class='propd' id='cusname'></div><br/>
					<div class='prop'>E-mail :-</div>
					<div class='propd' id='cusemail'></div><br/>
					<div class='prop'>Address :-</div>
					<div class='propd' id='cusaddr'></div><br/>
					<div class='prop'>City :-</div>
					<div class='propd' id='cuscit'></div><br/>
					<div class='prop'>State :-</div>
					<div class='propd' id='cussta'></div><br/>
				</div>
				<div id='selection'>
					State :- 
					<select id='states'>
						<option value='0'> - select -</option>
						<c:forEach var="state" items="${states}">
							<option value="${state.stateId}">${state.stateName}</option>
						</c:forEach>
					</select>
					City :-
					<select id='cities'>
						<option value='0'> - select - </option>
					</select>
					<button class='btd' id='ciupdate'>Update</button>
					<button class='btd' id='cidelivered'>Delivered</button>
				</div>
			</div>
		</div>
		<script src='js/courierorderupdate.js'></script>
	</body>
</html>