<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<% if(!(Boolean)request.getAttribute("play")){ %>
${category}
<form method="post" action="Game">
<input type="submit" name="play" value="YES">
<input type="submit" name="noplay" value="NO">

</form>

<%} else { %>

${message }

<%} %>
</body>
</html>