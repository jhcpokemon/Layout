import javax.naming.CompositeName;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;

/**
 * Created by jhcpokemon on 2015/03/07.
 */
public class MyServer {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(2000);
            while (true) {
                Socket s = ss.accept();
                MyService t = new MyService();
                t.setSocket(s);
                t.start();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
class MyService extends Thread{
    private Socket s;
    public void setSocket(Socket s){
        this.s = s;
    }
    public void run(){
        try{
            InputStream is = s.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            String userAps = br.readLine();
            String u = userAps.split("%")[0];
            String p = userAps.split("%")[1];

            OutputStream os = s.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            PrintWriter pw = new PrintWriter(osw, true);

            Class.forName("org.gjt.mm.mysql.Driver");
            Connection cn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/chat","root","940310");
            PreparedStatement ps = cn.prepareStatement("select * from users where username=? and password=?");
            ps.setString(1, u);
            ps.setString(2,p);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                pw.println("OK");
                while(true) {
                    String log = br.readLine();
                    System.out.println(log);
                }
            }
            else{
                pw.println("error");
            }
        }catch (Exception e){}
    }
}
