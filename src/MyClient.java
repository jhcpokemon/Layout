import java.net.Socket;

/**
 * Created by jhcpokemon on 2015/03/07.
 */
public class MyClient {
    public static void main(String[] args) {
        try {
            Socket s = new Socket("127.0.0.1", 1600);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
