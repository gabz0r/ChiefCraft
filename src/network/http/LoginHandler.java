package network.http;

import minecraft.User;
import network.util.Http;
import network.util.Log;
import network.util.crypto.CryptManager;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: Gabriel
 * Date: 10.06.13
 * Time: 10:34
 * To change this template use File | Settings | File Templates.
 */
public class LoginHandler {
    public static boolean login(User u, int version) {
        String parameters = "user=" + u.getUsername() + "&password=" + u.getPassword() + "&version=" + version;
        String loginURL = "https://login.minecraft.net";

        try {
            String fullResponse = Http.POSTRequest("login.minecraft.net", 80, "", "user=" + u.getUsername() + "&password=" + u.getPassword() + "&version=" + version);
            String[] response = fullResponse.split(":");

            if(fullResponse.equals("Bad login")
                    || fullResponse.equals("User not premium")
                    || fullResponse.equals("Account migrated, use e-mail")
                    || response.length != 5) {
	            Log.error("Login failed");
                return false;
            }

            u.setSessionId(response[3]);
            u.setUid(response[4]);

        } catch (IOException e) {
	        Log.error("Login failed");
            return false;
        }

        return false;
    }

	public static void joinServer(byte[] serverhash) {
		try {
			Http.GETRequest("session.minecraft.net", 80, "game/joinserver.jsp",
					"user=" + User.getUser().getUsername() +
					"&sessionId=" + User.getUser().getSessionId() +
					"&serverId=" + CryptManager.getHexString(serverhash));

		} catch (MalformedURLException e) {
			Log.error("Joining failed" + e.getMessage());
		} catch (ProtocolException e) {
			Log.error("Joining failed" + e.getMessage());
		} catch (IOException e) {
			Log.error("Joining failed" + e.getMessage());
		}
	}
}
