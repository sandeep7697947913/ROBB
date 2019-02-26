<html>
	<head>
		<title>Sell Book</title>
		<link rel="stylesheet" type="text/css" href="css/sellersellbook.css" />
		<link rel="stylesheet" type="text/css" href="css/common.css" />
		<link rel="stylesheet" type="text/css" href="css/scommon.css" />
		<link rel="stylesheet" type="text/css" href="css/sellersellbook.css" />
	</head>
	<body>

		<%@ include file="sellerpublisherheader.jsp" %>
		<%@ include file="sellermenu.jsp" %>
		<div id="main">
			<div id="upperform">
				<div id="fheading">Find Book</div>
				<div id='fsearch'>
					<input type='text' autocomplete="off" id="bsearch" placeholder="ISBN"/>
					<input type="button" value="search" id="bsearchb" />
				</div>
			</div>
			<div id='alert'></div>
			<div id="norec"> No Records found...</div>
			<div id="lowerform">
				<div id="bheading">Book Details</div>
				<img id="bookimage" src="">
				<div class="fields">
					<div class="lbl">Book Name : </div>
					<div class='fld'>sandeep</div>
				</div>
				<div class="fields">
					<div class="lbl">Author : </div>
					<div class='fld'>sandeep</div>
				</div>
				<div class="fields">
					<div class="lbl">Category : </div>
					<div class='fld'>sandeep</div>
				</div>
				<div class="fields">
					<div class="lbl">Publisher : </div>
					<div class='fld'>sandeep</div>
				</div>
				<div class="fields">
					<div class="lbl">Edition : </div>
					<div class='fld'>sandeep</div>
				</div>
				<div class="fields">
					<div class="lbl">MRP :</div>
					<div class='fld'>sandeep</div>
				</div>
				<div class="fields">
					<div class="lbl">Quantity : </div>
					<div class='fld'><input type="text" id="quantity" required></div>
				</div>
				<div class="fields">
					<div class="lbl">Price :</div>
					<div class='fld'><input type="text" id="price" required></div>
				</div>
				<div class="fields">
					<input id="butt" type="submit" value="Sell this book"/>
				</div>
			</div>
		</div>
		<script src="js/sellersellbook.js"></script>
		<%@ include file="footer.jsp" %>
	</body>
</html>