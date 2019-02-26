<html>
	<head>
		<title>Books</title>
		<link rel='stylesheet' type='text/css' href='css/common.css' />
		<link rel='stylesheet' type='text/css' href='css/pcommon.css' />
		<link rel='stylesheet' type='text/css' href='css/jquery-ui.css' />
		<link rel='stylesheet' type='text/css' href='css/publisherbpublished.css' />
		<script src='js/jquery/jquery.js'></script>
		<script src='js/jquery/jquery-ui.js'></script>
	</head>
	<body>
		<%@ include file="sellerpublisherheader.jsp" %>
		<%@ include file="publishermenu.jsp" %>
		<div id="main">
			<div id="upform">
				<div id="heading">
					Find Books via
				</div>
				<div id="serachcontent">
					<div id="findBox">
						<select id='selopt' class="searchtab">
							<option value='1'>ISBN</option>
							<option value='2'>Author</option>
							<option value='3'>Category</option>
						</select>
						<input id='search' class="searchtab" type="text" placeholder="Search for..."/>
						<button id='find' >Find</button>
					</div>
					<button id="getAll" >All Books</button>		
				</div>
			</div>
			<div id='loform'>
				<div id='norec'>
					No Records Found...
				</div>
				<div id='book'>
					<img id='bookimage' src='data/booksimages/1.jpg'/>
					<div class='bp'>Book Name</div><span> : </span><div  class='bp'>Sandeep</div><br />
					<div class='bp'>ISBN</div><span> : </span><div  class='bp'>Sandeep</div><br />
					<div class='bp'>Author</div><span> : </span><div  class='bp'>Sandeep</div><br />
					<div class='bp'>MRP</div><span> : </span><div  class='bp'>rs</div><br />
					<div class='bp'>Category</div><span> : </span><div  class='bp'>Sandeep</div><br />
					<div class='bp'>Edition</div><span> : </span><div  class='bp'>1st edition</div><br />
				</div>
				<div id='books'>
					<table id='authbook'>
						<thead>
							<tr>
								<th>ISBN</th>
								<th>Book Name</th>
								<th>Category</th>
								<th>MRP</th>
							</tr>
						</thead>
						<tbody id='authbooktbody'>
							<tr>
								<td>ISBN</td>
								<td>Book Name</td>
								<td>Category</td>
								<td>MRP</td>
							</tr>
						</tbody>
					</table>
					<table id='catebook'>
						<thead>
							<tr>
								<th>ISBN</th>
								<th>Book Name</th>
								<th>Author</th>
								<th>MRP</th>
							</tr>
						</thead>
						<tbody id='catebooktbody'>
							<tr>
								<td>ISBN</td>
								<td>Book Name</td>
								<td>Author</td>
								<td>MRP</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<%@ include file="footer.jsp" %>
		<script src="js/publisherbpublished.js"></script>
	</body>
</html>