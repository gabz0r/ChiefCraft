package minecraft.actions;

import network.game.connection.MinecraftConnection;
import network.game.packet.classes.Packet;
import network.game.packet.classes.Packet3Chat;
import ui.MainForm;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Gabriel
 * Date: 11.06.13
 * Time: 12:40
 * To change this template use File | Settings | File Templates.
 */
public class Chat {
    private static DataOutputStream out;
    private static DataInputStream in;

    static {
        out = MinecraftConnection.getHandler().getOut();
        in = MinecraftConnection.getHandler().getIn();
    }

    public static void sendChatMessage(String message) {
        Packet3Chat ch = new Packet3Chat(message);
        try {
            out.write(ch.getPacketId());
            ch.writePacketData(out);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public static void receiveChatMessage(Packet p) {
        Packet3Chat ch = (Packet3Chat) p;
        MainForm.getMainForm().appendNewMessage(ch.message);
    }
}
