<html>
	<head>
		<title>Book Rent</title>
		<link rel='stylesheet' type='text/css' href='css/common.css' />
		<link rel='stylesheet' type='text/css' href='css/scommon.css' />
		<link rel='stylesheet' type='text/css' href='css/sellchebook.css' />
		<script src='js/jquery/jquery.js'></script>
	</head>
	<body>
		<%@ include file='sellerpublisherheader.jsp' %>
		<%@ include file='sellermenu.jsp' %>
		<div id="main">
			<div id='searchbox'>
				<div class='shead'>
				Search Book
				</div>
				<div>ISBN No. <input id='isbn' type='text' />
				<button id='fb'>Search</button>
				</div>
			</div>
			<div id='norec'>sandeep</div>
			<div id='book'>
				<div class='detail'>Book Details</div>
				<img />
				<div>Book Name : <span id='bn'>sandeep</span></div>
				<div>Author : <span id='ba'>sandeep</span></div>
				<div>Mrp : <span id='bm'>sandeep</span> Rs.</div>
				<div>Quantity left: <span id='bql'>10</span></div>
				<div>Selling Price : <span id='bsp'>10</span> Rs.</div>
			</div>
			<div id='upbook'>
				Quantity :- <input id='nq' type='text' />
				Price :- <input id='np' type='text' />
				<button id='ub'>Update</button>
			</div>
		</div>
		<%@ include file='footer.jsp' %>
		<script src='js/sellchebook.js'></script>
	</body>
</html>