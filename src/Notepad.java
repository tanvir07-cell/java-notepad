import java.awt.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import javax.swing.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.filechooser.*;

public class Notepad extends JFrame implements ActionListener{
//   for menubar:
JMenuBar menubar = new JMenuBar();
JMenu file = new JMenu("File");
JMenu edit = new JMenu("Edit");
JMenu help = new JMenu("Help");

// for submenubar to the file menubar:
JMenuItem newFile = new JMenuItem("New");
JMenuItem openFile = new JMenuItem("Open");
JMenuItem saveFile = new JMenuItem("Save");
JMenuItem print = new JMenuItem("Print");
JMenuItem exit = new JMenuItem("Exit");

// for submenubar to the edit menubar:
JMenuItem cut = new JMenuItem("Cut");
JMenuItem copy = new JMenuItem("Copy");
JMenuItem paste = new JMenuItem("Paste");
JMenuItem selectAll = new JMenuItem("Select All");

// forr about:
JMenuItem about = new JMenuItem("About");

// for the text area:
JTextArea textArea  =  new JTextArea();




//   constructor to Notepad class:

Notepad(){
    setTitle("Hurrah! Create My Own NotePad");
    setBounds(100,100,800,600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // for icon:
    ImageIcon icon = new ImageIcon(getClass().getResource("notepad.png"));
    setIconImage(icon.getImage());

    // see this menubar:
    setJMenuBar(menubar);
    menubar.add(file);
    menubar.add(edit);
    menubar.add(help);

    // see this submenubar to the file menubar:
   file.add(newFile);
   file.add(openFile);
   file.add(saveFile);
   file.add(print);
   file.add(exit);

//    see this submenubar to the edit menubar:
edit.add(cut);
edit.add(copy);
edit.add(paste);
edit.add(selectAll);

// see the submenubar to the help menubar:
help.add(about);

// write text on the notepad:
JScrollPane scrollPane = new JScrollPane(textArea);
add(scrollPane);

// increasing font-size:
textArea.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));

// prevent horizontal(x-axis) scrolling:
scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
// removing border:
scrollPane.setBorder(BorderFactory.createEmptyBorder());

textArea.setLineWrap(true);
textArea.setWrapStyleWord(true);


// when clicking submenubar:
newFile.addActionListener(this);
openFile.addActionListener(this);
saveFile.addActionListener(this);
print.addActionListener(this);
exit.addActionListener(this);
cut.addActionListener(this);
copy.addActionListener(this);

// for keyboard shortcut:
newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
openFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
saveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK));
exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, KeyEvent.CTRL_DOWN_MASK));
cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK));
copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK));
paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK));
selectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK));
about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_J, KeyEvent.CTRL_DOWN_MASK));



}

public static void main(String[] args) throws Exception{
//    modern good looking UI
    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

   

    new Notepad().setVisible(true);
}







@Override
public void actionPerformed(ActionEvent e) {
    if(e.getActionCommand().equals("New")){
        textArea.setText(null);
    }

    else if(e.getActionCommand().equalsIgnoreCase("Save")){
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter textFilter = new FileNameExtensionFilter("Only Text Files(.txt)", "txt");
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(textFilter);

        int action = fileChooser.showSaveDialog(null);
        if (action != JFileChooser.APPROVE_OPTION) {
            return;
        } else {
            String fileName = fileChooser.getSelectedFile().getAbsolutePath().toString();
            if (!fileName.contains(".txt"))
                fileName = fileName + ".txt";

            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
                textArea.write(writer);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }


    }
//  for open:
else if (e.getActionCommand().equalsIgnoreCase("Open")) {
    JFileChooser fileChooser = new JFileChooser();
    FileNameExtensionFilter textFilter = new FileNameExtensionFilter("Only Text Files(.txt)", "txt");
    fileChooser.setAcceptAllFileFilterUsed(false);
    fileChooser.addChoosableFileFilter(textFilter);

    int action = fileChooser.showSaveDialog(null);
    if (action != JFileChooser.APPROVE_OPTION) {
        return;
    } else {
        String fileName = fileChooser.getSelectedFile().getAbsolutePath().toString();
        if (!fileName.contains(".txt"))
            fileName = fileName + ".txt";

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            textArea.write(writer);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}



    // for print case:

    else if (e.getActionCommand().equalsIgnoreCase("Print")) {
        try {
            textArea.print();
        } catch (PrinterException ex) {
            Logger.getLogger(Notepad.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    else if(e.getActionCommand().equalsIgnoreCase("Exit")){
        System.exit(0);
    }
    else if(e.getActionCommand().equalsIgnoreCase("Cut")){
         textArea.cut();
    }

    else if(e.getActionCommand().equalsIgnoreCase("Copy")){
        textArea.copy();
    }

    else if(e.getActionCommand().equalsIgnoreCase("Paste")){
        textArea.paste();
    }
    else if(e.getActionCommand().equalsIgnoreCase("Select All")){
        textArea.selectAll();
    }
    else if(e.getActionCommand().equalsIgnoreCase("About")){
        // from the about.java file:
        new About().setVisible(true);
    }
    
}




    
    
}
