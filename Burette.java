import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Button;
import java.util.UUID;
public class Burette
{
    private JPanel panel;
    private  JButton button1,button2,button3,button4;
    private  JLabel success;
    private  JLabel label1,label2,label3,label4;
    private  JTextField label1Text,label2Text,label3Text,label4Text;
    private int choice=0;
    public Burette(){
        panel=new JPanel();
        JFrame window=new JFrame("Please Select One of the Options");
        window.setSize(410,200);
        window.add(panel);
        
        panel.setLayout(null);
        window.setVisible(true);

    }
    public static void main(String[] args){
         UserInput demo = new UserInput();        
        }
}
