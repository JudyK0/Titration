import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.*;
public class Slider implements ChangeListener
{
    // instance variables - replace the example below with your own
    public JSlider slider;
    public Slider()
    {
        //slider
         Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
        
        slider = new JSlider(5,1000,5); //min max start 5 1000 500/x=0.125
        labelTable.put(new Integer( 5 ), new JLabel("Slow") );
        labelTable.put(new Integer( 1000 ),new JLabel("Fast"));
        slider.setLabelTable(labelTable);
        slider.setPaintLabels(true);
        
        slider.addChangeListener(this);
    }
    public void stateChanged(ChangeEvent e){
        MyPanel.howFast=slider.getValue()*0.01;
        MyPanel.solnUpy=slider.getValue()/32000.0;
        MyPanel.solnUpx=slider.getValue()/64000.0;
        if (slider.getValue()>20)
        MyPanel.speedDrop=10+(1000.0/slider.getValue());//3 is the min
        else
        MyPanel.speedDrop=165-slider.getValue();//165-
    }
}
