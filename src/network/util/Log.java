package network.util;

/**
 * Created with IntelliJ IDEA.
 * User: Gabriel
 * Date: 10.06.13
 * Time: 21:42
 * To change this template use File | Settings | File Templates.
 */
public class Log {
	public static void log(String msg) {
		System.out.println("LOG >> " + msg);
	}
	public static void error(String msg) {
		System.out.println("ERROR >> " + msg);
	}
}
