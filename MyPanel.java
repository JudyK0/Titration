import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.JLabel;
import java.math.RoundingMode;
import java.text.DecimalFormat;
public class MyPanel extends JPanel implements ActionListener
{
    final int PANEL_WIDTH=700;
    final int PANEL_HEIGHT=790;
    private boolean open=false;
    private int toNotOverlap=2;
    private double newRectangleHeight=62;
    static double howFast=0.05;//0.05 is perf
    private double heightCutOff=0;
    private JLabel label1, label2, label3, label4,currentpH;
    Timer timer1, timer2;
    static Timer timer3;
    private String currentVolumeString;
    private double currentVolume=0.00;
    private static final DecimalFormat df=new DecimalFormat("0.00");
    private double solnCuty=0.0;
    private double solnCutx=0.0;
    private double owidth=106.0;
    private double oheight=60.0;
    private double ox=72.0;
    private double oy=680.0;
    private int count=0;
    static double solnUpy,solnUpx;
    int droplety1=620;
    int droplety2=620;
    int dropletPlus=10;
    static double speedDrop=165;//165
    private boolean check=true;
    private JButton button1, button2, button3;
    private String pH="n/a";
    WeakAcidStrongBase lab1;
    WeakBaseStrongAcid lab2;
    StrongAcidStrongBase lab3;
    StrongBaseStrongAcid lab4;
    static Color shade= new Color(232,235,225);//light
    static Color fillSoln= new Color(226,230,216);//dark
    static Color outline= new Color(141,148,121);
    public MyPanel()
    {
        this.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
        
        //int for the delay(how often it fire), action listener
        timer1=new Timer(100, this);
        timer2=new Timer(1,new ListenerTwo());//5
        timer3=new Timer ((int)speedDrop, new ListenerThree());//100
         button1=new JButton("close/open");
        button1.setBounds (500,625,100,25);
        add(button1);
         button2=new JButton("take pH");
        button2.setBounds(500,500,100,25);
        add(button2);
        //show graph
        button3=new JButton("Show pH Graph");
        button3.setBounds(480,420,150,25);
        add(button3);
        
        label1=new JLabel("Buret Stopper");
        label1.setBounds(510,580,200,25);
        add(label1);
        //slider
        label1=new JLabel("Titrant Drop Rate");
        label1.setBounds(290,580,200,25);
        add(label1);
        button1.addActionListener(new ListenerOne());
        button2.addActionListener(new ListenerFour());
        button3.addActionListener(new ListenerFive());
        //button3.addActionListener(new ListenerFive());
        
        Slider rate=new Slider();
        rate.slider.setBounds(250, 600, 200, 100);
        add(rate.slider);
        //buret reading
        label4=new JLabel("Volume Reading = ");
        label4.setBounds(260,500,200,25);
        add(label4);
        label3=new JLabel("0.00mL");
        label3.setBounds(380,500,200,25);
        add(label3);
        //pH measure
        currentpH=new JLabel("Current pH is "+pH+".");
        currentpH.setBounds(285,420,200,25);
        add(currentpH);
    }
    //timer shows how slow it goes so thats the slider
    //how much it needs to go down by
    public void paint(Graphics g){
       Graphics2D g2D=(Graphics2D)g;
        super.paint(g);
        //buret reading
        timer3.setDelay((int)speedDrop);

        currentVolume=(newRectangleHeight-62)/8.00;
        if (currentVolume>50.00)
        currentVolume=50.00;
        currentVolumeString=df.format(currentVolume);
        label3.setText(currentVolumeString+"mL");
        
        //pH
            int value=UserInput.choice;
        switch(value){
                case 1: 
                    WeakAcidStrongBase lab1=new WeakAcidStrongBase(UserInput.dissConst, UserInput.Macid, UserInput.Mbase, UserInput.startingVol); 
                    pH=df.format(lab1.getpH(Double.parseDouble(currentVolumeString)/1000));//oh it needs to be in liters
                    break;
                case 2:
                   WeakBaseStrongAcid lab2=new WeakBaseStrongAcid(UserInput.dissConst, UserInput.Macid, UserInput.Mbase, UserInput.startingVol); 
                    pH=df.format(lab2.getpH(Double.parseDouble(currentVolumeString)/1000));
                   break;
                case 3:
                    StrongAcidStrongBase lab3=new StrongAcidStrongBase(UserInput.Macid, UserInput.Mbase, UserInput.startingVol); 
                     pH=df.format(lab3.getpH(Double.parseDouble(currentVolumeString)/1000));  
                    break;
                case 4:
                    StrongBaseStrongAcid lab4=new StrongBaseStrongAcid(UserInput.Macid, UserInput.Mbase, UserInput.startingVol); 
                    pH=df.format(lab4.getpH(Double.parseDouble(currentVolumeString)/1000));
                    break;
            }  
        currentpH.setText("Current pH is "+pH+".");
        
        
        
        //test
        //g2D.setColor(Color.black);
        //g2D.setStroke(new BasicStroke(2));
        //g2D.drawLine(500,461,48,461);
        
        //rectangle
        Color myGrey = new Color(204, 204, 204);
        
        
        g2D.setColor(myGrey);
        g2D.fillRect(111,(int)newRectangleHeight,27,420-(int)heightCutOff);//(111,62)(138,62)
        //the miniscus
        g2D.drawArc(111,(int)newRectangleHeight-15,28,15,180,180);//(111,47,28,15,180,180)
        int[] x3Points={111,111,121};
        int[] y3Points={(int)newRectangleHeight-3,(int)newRectangleHeight,(int)newRectangleHeight};
        g2D.drawPolygon(x3Points,y3Points, 3);
        g2D.fillPolygon(x3Points,y3Points, 3);
        int[] x4Points={138,138,128};
        int[] y4Points={(int)newRectangleHeight-3,(int)newRectangleHeight,(int)newRectangleHeight};
        g2D.drawPolygon(x4Points,y4Points, 3);
        g2D.fillPolygon(x4Points,y4Points, 3);
    
        //flask
        g2D.setColor(Color.black);
        g2D.setStroke(new BasicStroke(2));
        g2D.drawOval(101,573,48,28);
        g2D.drawLine(106,598,106,622);
        g2D.drawLine(144,598,144,622);
        g2D.drawLine(106,622,60,730);
        g2D.drawLine(144,622,190,730);
        
        //soln in flask
        g2D.setColor(shade);
        g2D.fillOval(60,690,130,80);
        g2D.setColor(fillSoln);
        g2D.fillOval((int)(ox+solnCutx),(int)(oy-solnCuty),(int)(owidth-solnCuty),(int)(oheight-solnCuty));
        g2D.setColor(outline);
        g2D.drawOval((int)(ox+solnCutx),(int)(oy-solnCuty),(int)(owidth-solnCuty),(int)(oheight-solnCuty));
        g2D.setColor(Color.black);
        g2D.drawOval(60,690,130,80);//bottom of the flask
        //buret
        g2D.setColor(Color.black);
        g2D.drawOval(107,10,36,24);
        g2D.drawLine(111,30,111,475);
        g2D.drawLine(139,30,139,475);
        g2D.drawLine(111,475,116,500);
        g2D.drawLine(139,475,134,495);
        //the stopper
        g2D.drawOval(95,502,10,18);
        g2D.drawOval(149,492,18,35);
        g2D.drawLine(100,502,158,492);
        g2D.drawLine(100,520,158,527);
        //the end
        g2D.drawLine(119,525,123,608);
        g2D.drawLine(131,525,127,608);
        //the 50 ticks
        g.setFont(new Font("TimesRoman", Font.PLAIN, 6));
        int y=62;
        for (int i=0;i<51;i++)
        {
            g2D.drawLine(133,y,139,y);
            String s=Integer.toString(i);
            if (i<10)
            {
                g2D.drawString(s,127,(y+1));
            }
            else
                g2D.drawString(s,123,(y+1));
            y+=8;
        }
        g.setFont(new Font("TimesRoman", Font.PLAIN, 13));
        g2D.drawString("50",119,47);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 10));
        g2D.drawString("1mL",117,56);
        
        //shade in the bottom
        g2D.setColor(myGrey);
        int[] xPoints={119+toNotOverlap,123+toNotOverlap,127-toNotOverlap,131-toNotOverlap};
        int[] yPoints={525,608-toNotOverlap,608-toNotOverlap,525};
        g2D.drawPolygon(xPoints,yPoints, 4);
        g2D.fillPolygon(xPoints,yPoints, 4);
        
        
        int[] x1Points={111+toNotOverlap,116+toNotOverlap,134-toNotOverlap,139-toNotOverlap};
        int[] y1Points={475,500-toNotOverlap,495,475};
        g2D.drawPolygon(x1Points,y1Points, 4);
        g2D.fillPolygon(x1Points,y1Points, 4);
    
        //droplet
        if (check){
        Color darkishGrey=new Color (130,130,129);
        g2D.setColor(darkishGrey);
        g2D.drawLine(125,droplety1,125,droplety2);//no droplet
    }
    }
    public void actionPerformed(ActionEvent e){
        //to prevent object from moving outside the bounds
        //the circle is going down because the rectangle height cut off is like that and 
        //462 is before adding the howfast
        if (newRectangleHeight<=462){
            if (newRectangleHeight+howFast>462){
            newRectangleHeight=462;
            heightCutOff=heightCutOff;
            timer1.stop();
            timer2.stop();
            timer3.stop();//have to stop timers when its all 50mL dispensed
        }
            else{
        newRectangleHeight=newRectangleHeight+howFast;
        heightCutOff=heightCutOff+howFast;
        }
        repaint();//calls paint
    }
    }
    private class ListenerTwo implements ActionListener{
    public void actionPerformed(ActionEvent e){
            if (newRectangleHeight<=462.0){
        //solution up
        if (((int)solnCutx<(int)(solnCutx+0.06*count))&&((int)solnCuty<(int)(solnCuty+0.1*count)))
        {solnCuty=solnCuty+solnUpy;//0.125
        solnCutx=solnCutx+solnUpx;//0.0625
        count=0;}
        else 
        count++;
        
        repaint();//calls paint
    }
        }
    }
    public class ListenerOne implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if (open){
            timer1.stop();
            timer2.stop();
            droplety1=620;//make sure droplet isn't visible
            droplety2=620;
            timer3.stop();
            check=false;
            open=false;
        }
            else{
            timer1.start();
            timer2.start();
            timer3.start();
            check=true;
            open=true;
        }
        }    
    }
    public class ListenerThree implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if (droplety1!=680&&droplety2==620){
            droplety1=droplety1+dropletPlus;
            droplety2=droplety2+3+dropletPlus;
        }    
        else if (droplety1!=680&&droplety2!=620){
            droplety1=droplety1+dropletPlus;
            droplety2=droplety2+dropletPlus;
        }    
        else 
            {
                droplety1=620;
                droplety2=620;
            }
    }
    }
    
    public class ListenerFour implements ActionListener{
        public void actionPerformed(ActionEvent e){
            int value=UserInput.choice;
        switch(value){
                case 1: 
                    new pHGraph((int)(Double.parseDouble(currentVolumeString)*100),(int)(Double.parseDouble(pH)*100));
                    break;
                case 2:
                   new pHGraph((int)(Double.parseDouble(currentVolumeString)*100),(int)(Double.parseDouble(pH)*100));
                   break;
                case 3:
                    new pHGraph((int)(Double.parseDouble(currentVolumeString)*100),(int)(Double.parseDouble(pH)*100));
                    break;
                case 4:
                    new pHGraph((int)(Double.parseDouble(currentVolumeString)*100),(int)(Double.parseDouble(pH)*100));
                    break;
            }   
        }
    }
    public class ListenerFive implements ActionListener{
        public void actionPerformed(ActionEvent e){
            pHGraph.main();
    }
    }
}
