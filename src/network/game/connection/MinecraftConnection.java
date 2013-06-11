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
	private Socket mcSocket;
    private static ConnectionHandler handler;

	public void connect(String host, int port) {
		try {
			mcSocket = new Socket(host, port);
			Log.log("Connected to server!");
			handler = new ConnectionHandler(mcSocket);
			handler.start();

		} catch (IOException e) {
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		}
	}

    public static ConnectionHandler getHandler() {
        return handler;
    }
}
