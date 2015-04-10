import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.HashMap;

/**
 * Created by jhcpokemon on 2015/03/07.
 */
public class MyServer {
    public static void main(String[] args) {
        try {
            HashMap<String,Socket> hm = new HashMap<String, Socket>();
            ServerSocket ss = new ServerSocket(2000);
            while (true) {
                Socket s = ss.accept();
                MyService t = new MyService();
                t.setSocket(s);
                t.setHashMap(hm);
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
    private HashMap<String,Socket> hm;
    public void setHashMap(HashMap hm){
        this.hm = hm;
    }
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

            File f = new File("./src/SQL.ini");
            FileReader fr = new FileReader(f);
            BufferedReader br1 = new BufferedReader(fr);
            String driver = br1.readLine();
            String url = br1.readLine();
            String username = br1.readLine();
            String password = br1.readLine();

            Class.forName(driver);
            Connection cn = DriverManager.getConnection(url,username,password);
            PreparedStatement ps = cn.prepareStatement("select * from users where username=? and password=?");
            ps.setString(1, u);
            ps.setString(2,p);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                pw.println("OK");
                for(Socket ts : hm.values()){
                    OutputStream tos = ts.getOutputStream();
                    OutputStreamWriter tosw = new OutputStreamWriter(tos);
                    PrintWriter tpw = new PrintWriter(tosw,true);
                    tpw.println("add%"+u);
                }
                for(String tu:hm.keySet()){
                    pw.println("add%"+tu);
                }
                hm.put(u,s);
                while(true) {
                    String log = br.readLine();
                    if(log.equals("{exit}")){
                        hm.remove(u);
                        for(Socket ts:hm.values()){
                            OutputStream tos = ts.getOutputStream();
                            OutputStreamWriter tosw = new OutputStreamWriter(tos);
                            PrintWriter tpw = new PrintWriter(tosw,true);
                            tpw.println("exit%"+u);
                        }
                        return;
                    }
                    String to = log.split("%")[0];
                    String mess = log.split("%")[1];
                    Socket ts = hm.get(to);
                    OutputStream tos = ts.getOutputStream();
                    OutputStreamWriter tosw = new OutputStreamWriter(tos);
                    PrintWriter tpw = new PrintWriter(tosw,true);
                    tpw.println("mess%" + mess);
                    System.out.println(log);
                }
            }
            else{
                pw.println("error");
            }
        }catch (Exception e){}
    }
}
