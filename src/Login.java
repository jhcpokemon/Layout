import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by jhcpokemon on 2015/03/02.
 */
public class Login extends JFrame implements ActionListener{
    JTextField input1 = new JTextField();
    JPasswordField input2 = new JPasswordField();
    Login(){
        this.setTitle("登陆");
        this.setSize(300, 150);
        this.setLocationRelativeTo(null);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        JLabel user = new JLabel("用户名");
        JLabel pw = new JLabel("密码");
        JButton b1 = new JButton("登陆");
        JButton b2 = new JButton("注册");
        JButton b3 = new JButton("取消");

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2,2));
        inputPanel.add(user);
        inputPanel.add(input1);
        inputPanel.add(pw);
        inputPanel.add(input2);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(b1);
        buttonPanel.add(b2);
        buttonPanel.add(b3);

        this.setLayout(new BorderLayout());
        this.add(inputPanel, BorderLayout.NORTH);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args){
        Login f = new Login();
        f.setVisible(true);
    }

    public void actionPerformed(ActionEvent arg0){
        if(arg0.getActionCommand().equals("登陆")){
            String user = input1.getText();
            String pw = input2.getText();
            if(user.equals("aaa")&&pw.equals("111")){
                MainWindow f = new MainWindow();
                f.setVisible(true);
                this.setVisible(false);
            }
            System.out.print("登陆");
        }
        if(arg0.getActionCommand().equals("注册")){
            System.out.print("注册");
        }
        if(arg0.getActionCommand().equals("取消")){
            System.out.print("取消");
        }
    }
}
