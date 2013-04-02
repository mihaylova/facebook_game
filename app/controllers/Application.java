package controllers;

import models.Fb_user;
import play.*;
import play.data.DynamicForm;
import play.mvc.*;


import play.data.*;
import static play.data.Form.*;

import views.html.*;
import views.html.canvas.index;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.codehaus.jackson.JsonNode;
import org.json.simple.parser.JSONParser;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.User;
import com.restfb.types.Photo;
import com.typesafe.config.ConfigFactory;

public class Application extends Controller {
	private final static String api_secret = ConfigFactory.load().getString("fbapp.secret");
	//private final static String api_secret = "7ddab6b600525e43530e930f2ec2c53d";
	//private static Fb_user current_user_object = null;

	public static Fb_user current_user() {
//		if (current_user_object != null) {
//			return current_user_object;
//		} else if (session().containsKey("user_id")) {
//			current_user_object = Fb_user.find.byId(Long.parseLong(session("user_id")));
//			
//			return current_user_object;
//		}
		
		if (session().containsKey("user_id")) {
			return Fb_user.find.byId(Long.parseLong(session("user_id")));
		}
		
		return null;
	}

    public static Boolean parse_facebook_signed_request() {
    	
	  DynamicForm form = form().bindFromRequest();
	 
	  if (form.get("signed_request") != null) {
		  String sig_req = form.get("signed_request");
		  
		  Map parsed_request = null;
		  try {
			  parsed_request = parse_signed_request(sig_req, api_secret);
		  } catch(Exception e) {
			  e.printStackTrace();
			  
			  return false;
		  }
		  
		  if (parsed_request.containsKey("user_id")) {
		  
			  Long uid = Long.parseLong(parsed_request.get("user_id").toString());
			  String access_token = parsed_request.get("oauth_token").toString();
			  session("access_token", access_token);
	
			  Fb_user user = Fb_user.find_by_uid(uid);
			  
			  if (user == null) {
				  FacebookClient facebookClient = new DefaultFacebookClient(access_token);
				  
				  User fuser = facebookClient.fetchObject("me", User.class);
				  //Photo picture = facebookClient.fetchObject("me?fields=picture", Photo.class);
	
				  user = new Fb_user();
				  
				  user.uid = uid;
				  user.name = fuser.getName();
				  user.points = 200;
				  user.coins = 10;
				  user.picture="https://graph.facebook.com/"+uid+"/picture?type=large";
				  
				  user.save();
			  }
			 
			 
			  session("user_id", Long.toString(user.id));
			  
			  
			  return true;
		  }
	  }
	  
	  return false;
    }

    public static byte[] base64_url_decode(String input) throws IOException {
      return new Base64(true).decode(input);
    }

    public static Map parse_signed_request(String input, String secret) throws Exception {
      return parse_signed_request(input, secret, 3600);
    }

    public static Map parse_signed_request(String input, String secret, int max_age) throws Exception {
      String[] split = input.split("[.]", 2);

      String encoded_sig = split[0];
      String encoded_envelope = split[1];
      JSONParser parser = new JSONParser();
      Map envelope = (Map) parser.parse(new String(base64_url_decode(encoded_envelope)));

      String algorithm = (String) envelope.get("algorithm");

      if (!algorithm.equals("HMAC-SHA256")) {
        throw new Exception("Invalid request. (Unsupported algorithm.)");
      }

      if (((Long) envelope.get("issued_at")) < System.currentTimeMillis() / 1000 - max_age) {
        throw new Exception("Invalid request. (Too old.)");
      }

      byte[] key = secret.getBytes();
      SecretKey hmacKey = new SecretKeySpec(key, "HMACSHA256");
      Mac mac = Mac.getInstance("HMACSHA256");
      mac.init(hmacKey);
      byte[] digest = mac.doFinal(encoded_envelope.getBytes());

      if (!Arrays.equals(base64_url_decode(encoded_sig), digest)) {
        throw new Exception("Invalid request. (Invalid signature.)");
      }

      return envelope;
    }
    
    public class ProbaAction extends Action.Simple {

	  public Result call(Http.Context ctx) throws Throwable {
	    Logger.info("Calling action for " + ctx);
	    return delegate.call(ctx);
	  }
	}

}


