<html>
	<head>
		<title>Book Rent</title>
		<link rel='stylesheet' type='text/css' href='css/common.css' />
		<link rel='stylesheet' type='text/css' href='css/pcommon.css' />
		<link rel='stylesheet' type='text/css' href='css/publisherpbook.css' />
	</head>
	<body>
		<%@ include file='sellerpublisherheader.jsp' %>
		<%@ include file='publishermenu.jsp' %>
		<div id="main">
			<form action="bookpublish.upd" method="post" enctype="multipart/form-data">
				<div id="title">
					Add Books
				</div>
					<img id="previmage" src="" />
				<div id="fields">
					<div class="rows">
						<div class="label">Book Name : </div>
						<input type="text" name="bookname" required/>
					</div>
					<div class="rows">
						<div class="label">ISBN : </div>
						<input type="text" name="isbn" required/>
					</div>
					<div  class="rows">
						<div class="label">Author : </div>
						<input type="text" name="author" required/>
					</div>
					<div class="rows">
						<div class="label">B. Image : </div>
						<input id="secletedimage" type="file" name="file" required/>
					</div>
					<div class="rows">
						<div class="label">Category : </div>
						<select id="seleccategory" name="categoryId">
							<option value="0"> - select - </option>
							<%-- options via ajax --%>
						</select>
					</div>
					<div  class="rows">
						<div class="label">Edition : </div>
						<select name="edition" required >
							<option value="1">1 edition</option>
							<option value="2">2 edition</option>
							<option value="3">3 edition</option>
							<option value="4">4 edition</option>
							<option value="5">5 edition</option>
							<option value="6">6 edition</option>
							<option value="7">7 edition</option>
							<option value="8">8 edition</option>
							<option value="9">9 edition</option>
							<option value="10">10 edition</option>
						</select>
					</div>
					<div  class="rows">
						<div class="label">Price : </div>
						<input type="text" name="price" required/>
					</div>
					<div class="rows" id="button">
						<input type="submit" value="upload details" required/>
					</div>
				</div>				
			</form>
		</div>
		<%@ include file='footer.jsp' %>
		<script src="js/publisherpbook.js"></script>
	</body>
</html>