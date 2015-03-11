import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.net.Socket;

/**
 * Created by jhcpokemon on 2015/03/02.
 */
public class MainWindow extends JFrame implements ActionListener,KeyListener{
    private Socket s;
    public void setSocket(Socket value){
        s = value;
    }
    JTextArea view = new JTextArea();
    JTextField chat = new JTextField();
    MainWindow(){
        this.setTitle("聊天");
        this.setSize(300,750);
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

        int screenX = Toolkit.getDefaultToolkit().getScreenSize().width;
        int frameX = this.getSize().width;
        this.setLocation(screenX - frameX,0);
        JComboBox cmbUser = new JComboBox();
        JButton b1 = new JButton("发送");
        chat.addKeyListener(this);

        JPanel smallPanel = new JPanel();
        smallPanel.setLayout(new GridLayout(1,2));
        smallPanel.add(cmbUser);
        smallPanel.add(b1);
        b1.addActionListener(this);

        JPanel bigPanel = new JPanel();
        bigPanel.setLayout(new GridLayout(2,1));
        bigPanel.add(chat);
        bigPanel.add(smallPanel);

        this.setLayout(new BorderLayout());
        this.add(bigPanel, BorderLayout.NORTH);
        this.add(view, BorderLayout.CENTER);

        try{
            File f = new File("./src/log.txt");
            FileReader fw = new FileReader(f);
            BufferedReader br = new BufferedReader(fw);
            while(br.ready()) {
                view.append(br.readLine() + "\n");
            }
            br.close();
        }
        catch (Exception e){}

    }
    public void actionPerformed(ActionEvent arg0){
        if(arg0.getActionCommand().equals("发送")) {
            view.append(chat.getText() + "\n");
            try {
                File f = new File("./src/log.txt");
                FileWriter fw = new FileWriter(f, true);
                PrintWriter pw = new PrintWriter(fw);
                pw.println(chat.getText());
                pw.close();
            }catch (Exception e) {
                e.printStackTrace();
            }

            try{
                OutputStream os = s.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(os);
                PrintWriter pw = new PrintWriter(osw);
                pw.println(chat.getText());
            } catch (Exception e) {
                e.printStackTrace();
            }
            chat.setText("");
        }
    }
    public void keyPressed(KeyEvent arg0){
        if(arg0.getKeyCode() == KeyEvent.VK_ENTER){
            view.append(chat.getText() + "\n");
            try {
                File f = new File("./src/log.txt");
                FileWriter fw = new FileWriter(f, true);
                PrintWriter pw = new PrintWriter(fw , true);
                pw.println(chat.getText());
            }catch (Exception e) {
                e.printStackTrace();
            }

            try{
                OutputStream os = s.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(os);
                PrintWriter pw = new PrintWriter(osw , true);
                pw.println(chat.getText());
            } catch (Exception e) {
                e.printStackTrace();
            }
            chat.setText("");
        }
    }
    public void keyReleased(KeyEvent arg0){}
    public void keyTyped(KeyEvent arg0){}
}
