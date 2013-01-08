<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>Add question</title>
</head>
<body>
<% if(!(Boolean)request.getAttribute("submit")){ %>
<form action="Add_question" method="post">
Question<br>
<textarea rows="6" cols="22" name="question"></textarea><br>


Category<br>
<select name="category">
<option value="Математика"> Математика</option>
<option value="История"> История</option>
<option value="Астрономия"> Астрономия</option>
<option value="География"> География</option>
<option value="Физика"> Физика</option>
<option value="Религия"> Религия</option>


</select> <br>

Right answer<br>
<input type="text" name="right_answer" size="22"><br>
Other answers<br>
<input type="text" name="answer1" size="22"><br>
<input type="text" name="answer2" size="22"><br>
<input type="text" name="answer3" size="22"><br>
<input type="submit" name ="submit">

<%} else { %>
Въпросът ви е ${question} от категория ${category}. Верния отговор е  ${right_answer}.
Останалите отговори са ${answer1}, ${answer2} and ${answer3}


<!-- Oformlenie  na saobshtenieto // status==true or false -->
<h1>${status}</h1>

<% } %>

<br>
<br>



</form>

</body>
</html>