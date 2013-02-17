import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.json.simple.parser.JSONParser;

class SignedRequestParser {

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

}
