import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.*;

public class About extends JFrame{

    About(){
        setTitle("About My Note Pad Application");
        setBounds(100,100,500,400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

           // for icon:
    ImageIcon icon = new ImageIcon(getClass().getResource("notepad.png"));
    setIconImage(icon.getImage());

    // for html text in the about:
    JLabel textLabel = new JLabel("<html>Note It Down<br>Conventional Notepad Version 2.0<br> All rights reserved<br><br>Note It Down is a word processing program, <br>which allows changing of text in a computer file.<br>Note It Down is a simple text editor for basic text-editing program which enables computer users to create documents. </html>");
    textLabel.setFont(new Font("SAN_SERIF", Font.PLAIN, 18));
    textLabel.setBounds(150, 130, 500, 300);
    add(textLabel);

        
      
    }

    public static void main(String[] args) {
        new About().setVisible(true);
    }
    
}
