import network.game.connection.MinecraftConnection;
import network.http.LoginHandler;
import minecraft.User;
import network.util.Constants;
import network.util.Log;

/**
 * Created with IntelliJ IDEA.
 * User: Gabriel
 * Date: 10.06.13
 * Time: 10:33
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args) {
        User u = User.getUser("gabs1994", "xx");
        LoginHandler.login(u, Constants.LAUNCHER_VERSION);

	    Log.log("User logged in: " + User.getUser().getUsername() + ":" + User.getUser().getSessionId());

	    MinecraftConnection mcCon = new MinecraftConnection();
	    mcCon.connect("gabdev.de", 25565);
    }
}
