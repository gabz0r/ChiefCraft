import network.game.connection.MinecraftConnection;
import network.http.LoginHandler;
import minecraft.User;
import network.util.Constants;
import network.util.Log;
import ui.MainForm;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: Gabriel
 * Date: 10.06.13
 * Time: 10:33
 * To change this template use File | Settings | File Templates.
 */
public class Main {

    public static void main(String[] args) throws ClassNotFoundException,
                                                  UnsupportedLookAndFeelException,
                                                  InstantiationException,
                                                  IllegalAccessException {
        //<editor-fold desc="Benutzername & Passwort">
        User u = User.getUser("user", "pw");
        //</editor-fold>

        LoginHandler.login(u, Constants.LAUNCHER_VERSION);

	    Log.log("User logged in: " + User.getUser().getUsername() + ":" + User.getUser().getSessionId());

	    MinecraftConnection mcCon = new MinecraftConnection();
	    mcCon.connect("gabdev.de", 25565);

        UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        MainForm.getMainForm().setVisible(true);
    }
}
