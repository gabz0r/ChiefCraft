package minecraft.actions;

import network.game.packet.classes.Packet;
import network.game.packet.classes.Packet201PlayerInfo;
import ui.MainForm;

import java.util.HashSet;

/**
 * Created with IntelliJ IDEA.
 * User: Gabriel
 * Date: 11.06.13
 * Time: 13:09
 * To change this template use File | Settings | File Templates.
 */
public class PlayerList {
    private static HashSet<String> tabList = new HashSet<String>();

    public static void updateTabList(Packet p) {
        Packet201PlayerInfo pi = (Packet201PlayerInfo) p;

        if(!pi.isConnected) {
            tabList.remove(pi.playerName);
        }
        else {
            tabList.add(pi.playerName);
        }

        MainForm.getMainForm().updateUserSource(tabList);
    }
}
