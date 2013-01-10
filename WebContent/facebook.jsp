<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*" %>
    
   

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>facebook</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
</head>
<body>
<% if((Boolean)request.getAttribute("logged")){ %>
		<h1>Hello ${name} </h1> <br>
		<h1>Uid ${uid} </h1> <br>
		<a href="Add_question">Добави въпрос</a> <br>
		<a href="Game">Igrai</a>
		
	<% } else { %>
		<input type="button" value="login" onclick="login()" />
	<% } %>

	
	
	

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
					//window.location.href = '/inna/Login?token=' + response.authResponse.accessToken;
					$.post("/inna/Login/", {"token": response.authResponse.accessToken}, function(){
						window.location.href = '/inna/Login/';
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