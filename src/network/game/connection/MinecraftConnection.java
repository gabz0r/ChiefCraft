package network.game.connection;

import network.util.Log;

import java.io.IOException;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: Gabriel
 * Date: 10.06.13
 * Time: 19:25
 * To change this template use File | Settings | File Templates.
 */
public class MinecraftConnection {
	Socket mcSocket;

	public void connect(String host, int port) {
		try {
			mcSocket = new Socket(host, port);
			Log.log("Connected to server!");
			ConnectionHandler ch = new ConnectionHandler(mcSocket);
			ch.start();

		} catch (IOException e) {
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		}
	}
}
