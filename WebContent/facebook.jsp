<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*" %>
    
    <%! ArrayList users; 
    String[] user;
    %>
   <% users=(ArrayList)request.getAttribute("users"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>facebook</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
</head>
<body>
<% if((Boolean)request.getAttribute("logged")){ %>
		<h1>Hello ${name} </h1>
	<% } else { %>
		<input type="button" value="login" onclick="login()" />
	<% } %>

	<% if((Boolean)request.getAttribute("has_users")) { %>
		<table border="1">
			<tr>
				<td>UID</td>
				<td>Name</td>
			</tr>
		<%for(int i=0; i<users.size(); i++){ 
		user=(String [])users.get(i); 
		%>
				<tr>
					<td>
						<a href="http://facebook.com/<%= user[0] %>" target="_blank"><%= user[0] %></a>
					</td>
					<td><%= user[1] %></td>
				</tr>
	
		</table>
	<% } } %>

	<div id="fb-root"></div>
	<script>
	  window.fbAsyncInit = function() {
	    // init the FB JS SDK
	    FB.init({
	      appId      : '249406605187123', // App ID from the App Dashboard
	      //channelUrl : '//WWW.YOUR_DOMAIN.COM/channel.html', // Channel File for x-domain communication
	      status     : true, // check the login status upon init?
	      cookie     : true, // set sessions cookies to allow your server to access the session?
	      xfbml      : true  // parse XFBML tags on this page?
	    });
	
	    // Additional initialization code such as adding Event Listeners goes here
	
	  };
	  
	  function login() {
		  FB.login(function(response) {
			   if (response.authResponse) {
					//window.location.href = '/inna/facebook?token=' + response.authResponse.accessToken;
					$.post("/inna/facebook/", {"token": response.authResponse.accessToken}, function(){
						window.location.href = '/inna/facebook/';
					})
			   } else {
			     console.log('User cancelled login or did not fully authorize.');
			   }
		  });
	  }
	
	  // Load the SDK's source Asynchronously
	  // Note that the debug version is being actively developed and might 
	  // contain some type checks that are overly strict. 
	  // Please report such bugs using the bugs tool.
	  (function(d, debug){
	     var js, id = 'facebook-jssdk', ref = d.getElementsByTagName('script')[0];
	     if (d.getElementById(id)) {return;}
	     js = d.createElement('script'); js.id = id; js.async = true;
	     js.src = "//connect.facebook.net/en_US/all" + (debug ? "/debug" : "") + ".js";
	     ref.parentNode.insertBefore(js, ref);
	   }(document, /*debug*/ false));
	</script>
</body>
</html>