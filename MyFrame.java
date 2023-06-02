import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class MyFrame extends JFrame 
{
    private  JButton button1, button2, button3;
    private JLabel label1, label2;
    MyPanel panel;
    
    public MyFrame()
    {
        panel=new MyPanel();
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel);
        panel.setLayout(null);//this is what allow you to use setBounds 
        //after eveyrhting
        //button1=new JButton("close/open");
        //button1.setBounds (35,300,100,25);
        //panel.add(button1);
        //button1.addActionListener(new MyPanel().new ListenerOne());
        //Slider rate=new Slider();
        //rate.slider.setBounds(100, 600, 100, 100);
        //add(rate.slider);
        this.setTitle("Titration Simulation");
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
