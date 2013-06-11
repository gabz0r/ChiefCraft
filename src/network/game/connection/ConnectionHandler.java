package network.game.connection;

import minecraft.User;
import network.game.packet.classes.*;
import network.http.LoginHandler;
import network.util.Constants;
import network.util.Log;
import network.util.crypto.CryptManager;

import javax.crypto.SecretKey;
import java.io.*;
import java.net.*;
import java.security.PublicKey;

/**
 * Created with IntelliJ IDEA.
 * User: Gabriel
 * Date: 10.06.13
 * Time: 20:22
 * To change this template use File | Settings | File Templates.
 */
public class ConnectionHandler extends Thread {
	private Socket connectedSocket;
	private DataOutputStream out;
	private DataInputStream in;

	public ConnectionHandler(Socket mcs) {
		try {
			connectedSocket = mcs;
			out = new DataOutputStream(mcs.getOutputStream());
			in = new DataInputStream(mcs.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		}
	}

	@Override
	public void run() {
		try {
			doHandshake();
			while(true) {
				int opcode = in.read();
				Packet current = Packet.getNewPacket(opcode);
				if(current != null) {
					current.readPacketData(in);

                    //Handle keepalive packets directly
                    if(current instanceof Packet0KeepAlive) {
                        out.write(0);
                        current.writePacketData(out);
                    }

                    //Log.log(current.toString());
                    current.processPacket();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		}
	}

	private void doHandshake() {

		try {
			Log.log("Beginning handshake");
			SecretKey key = CryptManager.createSecretKey();

			//C->S id2
			Packet prot = new Packet2ClientProtocol(Constants.PROTOCOL_VERSION, User.getUser().getUsername(), "", connectedSocket.getPort());
			out.write(prot.getPacketId());
			prot.writePacketData(out);

			//S->C id253
			int opcode = in.read();
			Packet253ServerAuthData auth = (Packet253ServerAuthData) Packet.getNewPacket(opcode);
			auth.readPacketData(in);
			Log.log("Received 253 - public key / server auth");

			PublicKey publicKey = auth.getPublicKey();
			byte[] serverHash = CryptManager.func_75895_a(auth.getServerId(), publicKey, key);
			LoginHandler.joinServer(serverHash);

			Log.log("Sending 252 - secret key / auth token");
			//C->S id252
			Packet252SharedKey sharedKey = new Packet252SharedKey(key, publicKey, auth.getVerifyToken());
			out.write(sharedKey.getPacketId());
			sharedKey.writePacketData(out);

			opcode = in.read();
			Packet252SharedKey response = (Packet252SharedKey) Packet.getNewPacket(opcode);
			response.readPacketData(in);

			Log.log("Got server response");

			in = new DataInputStream(CryptManager.decryptInputStream(key, connectedSocket.getInputStream()));
			out = new DataOutputStream(CryptManager.encryptOuputStream(key, connectedSocket.getOutputStream()));

			//0xCD -> Login complete
			out.write(0xCD);
			out.write(0);
			out.flush();

			Log.log("Handshake done!");
		} catch (IOException e) {
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		}
	}

    public DataOutputStream getOut() {
        return out;
    }

    public DataInputStream getIn() {
        return in;
    }
}
