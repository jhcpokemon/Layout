import sun.util.locale.StringTokenIterator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

/**
 * Created by jhcpokemon on 2015/03/02.
 */
public class Login extends JFrame implements ActionListener{
    JTextField input1 = new JTextField();
    JPasswordField input2 = new JPasswordField();

    Login() {
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
        JLabel ps = new JLabel("密码");
        JButton b1 = new JButton("登陆");
        JButton b2 = new JButton("注册");
        JButton b3 = new JButton("取消");

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 2));
        inputPanel.add(user);
        inputPanel.add(input1);
        inputPanel.add(ps);
        inputPanel.add(input2);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(b1);
        buttonPanel.add(b2);
        buttonPanel.add(b3);

        this.setLayout(new BorderLayout());
        this.add(inputPanel, BorderLayout.NORTH);
        this.add(buttonPanel, BorderLayout.SOUTH);

        Thread t = new Thread();
        t.start();
    }

    public static void main(String[] args) {
        Login f = new Login();
        f.setVisible(true);
    }

    public void actionPerformed(ActionEvent arg0) {
        if (arg0.getActionCommand().equals("登陆")) {
            String user = input1.getText();
            String ps = input2.getText();
            try {
                Socket s = new Socket("127.0.0.1", 2000);
                OutputStream os = s.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(os);
                PrintWriter pw = new PrintWriter(osw, true);
                pw.println(user + "%" + ps);

                InputStream is = s.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String rep = br.readLine();
                if (rep.equals("OK")) {
                    this.setVisible(false);
                    MainWindow f = new MainWindow();
                    f.setSocket(s);
                    f.setVisible(true);
                } else {
                    System.out.println("error");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.print("登陆");
        }
        if (arg0.getActionCommand().equals("注册")) {
            System.out.print("注册");
        }
        if (arg0.getActionCommand().equals("取消")) {
            System.out.print("取消");
        }
    }
}
