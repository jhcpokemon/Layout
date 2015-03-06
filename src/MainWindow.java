import javax.swing.*;
import java.awt.*;

/**
 * Created by jhcpokemon on 2015/03/02.
 */
public class MainWindow extends JFrame{
    MainWindow(){
        this.setTitle("聊天");
        this.setSize(300,750);

        int screenX = Toolkit.getDefaultToolkit().getScreenSize().width;
        int frameX = this.getSize().width;
        this.setLocation(screenX - frameX,0);
        JTextField chat = new JTextField();
        JComboBox cmbUser = new JComboBox();
        JButton b1 = new JButton("发送");
        JTextArea view = new JTextArea();

        JPanel smallPanel = new JPanel();
        smallPanel.setLayout(new GridLayout(1,2));
        smallPanel.add(cmbUser);
        smallPanel.add(b1);

        JPanel bigPanel = new JPanel();
        bigPanel.setLayout(new GridLayout(2,1));
        bigPanel.add(chat);
        bigPanel.add(smallPanel);

        this.setLayout(new BorderLayout());
        this.add(bigPanel, BorderLayout.NORTH);
        this.add(view, BorderLayout.CENTER);

    }
}
