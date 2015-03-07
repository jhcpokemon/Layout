import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by jhcpokemon on 2015/03/07.
 */
public class MyServer {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(1600);
            System.out.print("监听前……");
            Socket s = ss.accept();
            System.out.print("监听后……");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
