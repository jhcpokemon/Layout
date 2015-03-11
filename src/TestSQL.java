/**
 * Created by jhcpokemon on 2015/03/10.
 */
import java.sql.*;
public class TestSQL {
    public static void main(String[] args) {
        String u = "bbb";
        String p = "222";
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/chat", "root", "940310");
            ps = cn.prepareStatement("select * from users where username = ? and password = ?");
            ps.setString(1, u);
            ps.setString(2, p);
            rs = ps.executeQuery();
            if(rs.next()){
                System.out.println("OK");
            }
            else{
                System.out.print("error");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                cn.close();
                ps.close();
            } catch (Exception e) {
            }
        }
    }
}
