import network.http.LoginHandler;
import minecraft.User;

/**
 * Created with IntelliJ IDEA.
 * User: Gabriel
 * Date: 10.06.13
 * Time: 10:33
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args) {
        User u = new User("gabs1994", "xx");
        LoginHandler.login(u, Constants.LAUNCHER_VERSION);
        System.out.println(u.getSessionId());
    }
}
