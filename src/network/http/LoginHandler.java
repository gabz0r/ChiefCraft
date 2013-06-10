package network.http;

import minecraft.User;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: Gabriel
 * Date: 10.06.13
 * Time: 10:34
 * To change this template use File | Settings | File Templates.
 */
public class LoginHandler {
    public static void login(User u,  String version) {
        String parameters = "user=" + u.getUsername() + "&password=" + u.getPassword() + "&version=" + version;
        String loginURL = "https://login.minecraft.net";

        try {
            URL url = new URL(loginURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setInstanceFollowRedirects(false);
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            con.setRequestProperty("charset", "utf-8");
            con.setRequestProperty("Content-Length", Integer.toString(parameters.getBytes().length));
            con.setUseCaches(false);

            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(parameters);
            wr.flush();
            wr.close();

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String fullResponse = br.readLine();
            String[] response = fullResponse.split(":");

            if(fullResponse.equals("Bad login")
                    || fullResponse.equals("User not premium")
                    || fullResponse.equals("Account migrated, use e-mail")
                    || response.length != 5) {
                System.out.println("Login failed");
                System.exit(1);
            }

            u.setSessionId(response[3]);
            u.setUid(response[4]);

        } catch (MalformedURLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
