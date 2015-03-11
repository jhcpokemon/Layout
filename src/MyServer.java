import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by jhcpokemon on 2015/03/07.
 */
public class MyServer {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(2000);
            Socket s = ss.accept();
            InputStream is = s.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            String userAps = br.readLine();
            String user = userAps.split("%")[0];
            String ps = userAps.split("%")[1];

            OutputStream os = s.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            PrintWriter pw = new PrintWriter(osw, true);
            if (user.equals("aaa") && ps.equals("111")) {
                pw.println("OK");
                while(true) {
                    String log = br.readLine();
                    System.out.println(log);
                }
            }
            else{
                pw.println("error");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
