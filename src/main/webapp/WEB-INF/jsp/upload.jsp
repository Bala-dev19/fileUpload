<html>
<body>

	<h1>Spring Boot - File Upload</h1>

	<form method="POST" action="/upload" enctype="multipart/form-data">
		<input type="file" name="file" /><br />
		threats:
		<input type="number" name="noOfThreats" /><br />
		partion:
    	<input type="number" name="noOfPartions" /><br />
		<br /> <input type="submit" value="Submit" />
	</form>

</body>
</html>
