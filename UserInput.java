import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Button;
import java.util.UUID;

//UserInput class is the class that handles the users input and presents 
public class UserInput 
{
    private JPanel panel;
    private  JButton button1,button2,button3,button4;
    private  JLabel success;
    private  JLabel label1,label2,label3,label4;
    private  JTextField label1Text,label2Text,label3Text,label4Text;
    static int choice=0;
    static double Macid=0.0, Mbase=0.0,dissConst=0.0,startingVol=0.0;
    public UserInput(){
        panel=new JPanel();
        JFrame window=new JFrame("Please Select One of the Options");
        window.setSize(410,200);
        window.add(panel);
        
        panel.setLayout(null);
        
        //options for titration experiment
        button1=new JButton("Titrating Weak Acid with Strong Base");
        button1.setBounds (35,20,263,25);
        panel.add(button1);

        button2=new JButton("Titrating Weak Base with Strong Acid");
        button2.setBounds (35,50,263,25);
        panel.add(button2);
        
        button3=new JButton("Titrating Strong Acid with Strong Base");
        button3.setBounds (35,80,263,25);
        panel.add(button3);
        
        button4=new JButton("Titrating Strong Base with Strong Acid");
        button4.setBounds (35,110,263,25);
        panel.add(button4);
        
        window.setVisible(true);
        
        button1.addActionListener(new ListenerOne());
        button2.addActionListener(new ListenerTwo());
        button3.addActionListener(new ListenerThree());
        button4.addActionListener(new ListenerFour());
        
        window.setTitle("Please Enter the Data");
    }
    private class ListenerOne implements ActionListener{
        public void actionPerformed(ActionEvent e){
           panel.removeAll();
           choice=1;
           
           label1=new JLabel("enter Ka:");
            label1.setBounds(10,20,80,25);
            panel.add(label1);
        
            label1Text=new JTextField (20);
            label1Text.setBounds(70,20,165,25);
            panel.add(label1Text);
        
            label2=new JLabel("enter acid molarity:");
            label2.setBounds(10,50,150,25);
            panel.add(label2);
        
            label2Text=new JTextField (20);
            label2Text.setBounds(140,50,165,25);
            panel.add(label2Text);
        
            label3=new JLabel("enter base molarity:");
            label3.setBounds(10,80,150,25);
            panel.add(label3);
        
            label3Text=new JTextField (20);
            label3Text.setBounds(140,80,165,25);
            panel.add(label3Text);
        
            label4=new JLabel("enter volume of acid in flask (L):");
            label4.setBounds(10,110,230,25);
            panel.add(label4);
        
            label4Text=new JTextField (20);
            label4Text.setBounds(215,110,165,25);
            panel.add(label4Text);
            
            JButton buttonDone=new JButton("Submit!");
            buttonDone.setBounds (35,140,263,25);
            panel.add(buttonDone);
            buttonDone.addActionListener(new ListenerButtonDone1());
            
            
            panel.repaint();
            
        }    
    }
    private class ListenerTwo implements ActionListener{
        public void actionPerformed(ActionEvent e){
           panel.removeAll();
           choice=2;
           label1=new JLabel("enter Kb:");
            label1.setBounds(10,20,80,25);
            panel.add(label1);
        
            label1Text=new JTextField (20);
            label1Text.setBounds(70,20,165,25);
            panel.add(label1Text);
        
            label2=new JLabel("enter acid molarity:");
            label2.setBounds(10,50,150,25);
            panel.add(label2);
        
            label2Text=new JTextField (20);
            label2Text.setBounds(140,50,165,25);
            panel.add(label2Text);
        
            label3=new JLabel("enter base molarity:");
            label3.setBounds(10,80,150,25);
            panel.add(label3);
        
            label3Text=new JTextField (20);
            label3Text.setBounds(140,80,165,25);
            panel.add(label3Text);
        
            label4=new JLabel("enter volume of base in flask (L):");
            label4.setBounds(10,110,230,25);
            panel.add(label4);
        
            label4Text=new JTextField (20);
            label4Text.setBounds(215,110,165,25);
            panel.add(label4Text);
            
            JButton buttonDone=new JButton("Submit!");
            buttonDone.setBounds (35,140,263,25);
            panel.add(buttonDone);
            buttonDone.addActionListener(new ListenerButtonDone1());
            
            
            panel.repaint();
            
            
        }   
    }
    private class ListenerThree implements ActionListener{
        public void actionPerformed(ActionEvent e){
           panel.removeAll();
            choice=3;
            label2=new JLabel("enter acid molarity:");
            label2.setBounds(10,20,150,25);
            panel.add(label2);
        
            label2Text=new JTextField (20);
            label2Text.setBounds(140,20,165,25);
            panel.add(label2Text);
        
            label3=new JLabel("enter base molarity:");
            label3.setBounds(10,50,150,25);
            panel.add(label3);
        
            label3Text=new JTextField (20);
            label3Text.setBounds(140,50,165,25);
            panel.add(label3Text);
        
            label4=new JLabel("enter volume of acid in flask (L):");
            label4.setBounds(10,80,230,25);
            panel.add(label4);
        
            label4Text=new JTextField (20);
            label4Text.setBounds(215,80,165,25);
            panel.add(label4Text);
            
            JButton buttonDone=new JButton("Submit!");
            buttonDone.setBounds (35,110,263,25);
            panel.add(buttonDone);
            buttonDone.addActionListener(new ListenerButtonDone1());
            
            
            panel.repaint();
            
        }   
    }
    private class ListenerFour implements ActionListener{
        public void actionPerformed(ActionEvent e){
           panel.removeAll();
           choice=4;
           label2=new JLabel("enter acid molarity:");
            label2.setBounds(10,20,150,25);
            panel.add(label2);
        
            label2Text=new JTextField (20);
            label2Text.setBounds(140,20,165,25);
            panel.add(label2Text);
        
            label3=new JLabel("enter base molarity:");
            label3.setBounds(10,50,150,25);
            panel.add(label3);
        
            label3Text=new JTextField (20);
            label3Text.setBounds(140,50,165,25);
            panel.add(label3Text);
            label4=new JLabel("enter volume of base in flask (L):");
            label4.setBounds(10,80,230,25);
            panel.add(label4);
        
            label4Text=new JTextField (20);
            label4Text.setBounds(215,80,165,25);
            panel.add(label4Text);
            JButton buttonDone=new JButton("Submit!");
            buttonDone.setBounds (35,110,263,25);
            panel.add(buttonDone);
            buttonDone.addActionListener(new ListenerButtonDone1());
            
            
            panel.repaint();
            
        }   
    }
    //class converts the inputs from String to doubles and inputs the user's data to the correct titration class
    private class ListenerButtonDone1 implements ActionListener{
        public void actionPerformed(ActionEvent e){
           String acidM, baseM,volume;
           String dissociationConstant=null;
           int index;
            if (choice==1||choice==2)
            {
            dissociationConstant=label1Text.getText();
            index=dissociationConstant.indexOf("*");
        
            if (index!=-1)
            {
                String dissociationConstantTemp=dissociationConstant.substring(0,index);
                String dissociationConstantExp=dissociationConstant.substring(dissociationConstant.indexOf("^")+1);
                dissConst=Double.valueOf(dissociationConstantTemp)*Math.pow(10,Double.valueOf(dissociationConstantExp));
            }
            else 
            dissConst=Double.valueOf(dissociationConstant);
            }
          
            acidM=label2Text.getText();
            index=acidM.indexOf("*");
            if (index!=-1)
            {
                String acidMtemp=acidM.substring(0,index);
                String acidMExp=acidM.substring(acidM.indexOf("^")+1);
                Macid=Double.valueOf(acidMtemp)*Math.pow(10,Double.valueOf(acidMExp));
            }
            else 
            Macid=Double.valueOf(acidM);
            
            baseM=label3Text.getText();
            index=baseM.indexOf("*");
            if (index!=-1)
            {
                String baseMtemp=acidM.substring(0,index);
                String baseMExp=acidM.substring(baseM.indexOf("^")+1);
                Macid=Double.valueOf(baseMtemp)*Math.pow(10,Double.valueOf(baseMExp));
            }
            else 
            Mbase=Double.valueOf(baseM);
            
            volume=label4Text.getText();
            startingVol=Double.valueOf(volume);
            
            new MyFrame();
        }   
    }
}

