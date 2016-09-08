
import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;

public class Main extends JFrame {
  JTextArea textArea;

  public Main() {
    super();
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    Container contentPane = this.getContentPane();
    textArea = new JTextArea();
    JScrollPane pane = new JScrollPane(textArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    contentPane.add(pane, BorderLayout.CENTER);
    pane.setBounds(120,120,50,50);
  }

  public static void main(String[] args) {
    JFrame f = new Main();
    f.setSize(300, 200);
    f.setVisible(true);
  }
} 
 
 
