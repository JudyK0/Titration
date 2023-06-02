import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.*;
import java.awt.Point;
import javax.swing.JLabel;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.util.ArrayList;


public class pHGraph {
    static ArrayList<Integer> data= new ArrayList<Integer>();
    static ArrayList<Integer> pointsCoord = new ArrayList<Integer>();
    private Integer someA, someB;
    pHGraph (){};
    pHGraph (int someA, int someB)
    {
        this.someA= Integer.valueOf(someA);
        this.someB= Integer.valueOf(someB);
        data.add(someA);
        data.add(someB);
    }
 public static void main() {
  SwingUtilities.invokeLater(new Runnable() {

   @Override
   public void run() {
    CartesianFrame frame = new CartesianFrame();
    frame.showUI();
    for (int i=0; i<data.size()-1;i=i+2)
    {
    frame.panel.drawPoint(new Point(data.get(i), data.get(i+1)));
   }
}
  });
 }

}

class CartesianFrame extends JFrame {
 CartesianPanel panel;
    private  JLabel label1,label2;

 public CartesianFrame() {
  panel = new CartesianPanel();
  add(panel);
  panel.setLayout(null);
  label2=new JLabel("pH");
    label2.setBounds(3,300,150,25);
    panel.add(label2);
  label2=new JLabel("mL of titrant");
    label2.setBounds(710,750,150,25);
    panel.add(label2);
 }

 public void showUI() {
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  setTitle("pH graph");
  setSize(1420, 810);
  setVisible(true);
 }
}

class CartesianPanel extends JPanel {
 
    // x-axis coord constants
 public static final int X_AXIS_FIRST_X_COORD = 50;
 public static final int X_AXIS_SECOND_X_COORD = 1400;
 public static final int X_AXIS_Y_COORD = 720;

 // y-axis coord constants
 public static final int Y_AXIS_FIRST_Y_COORD = 15;
 public static final int Y_AXIS_SECOND_Y_COORD = 720;
 public static final int Y_AXIS_X_COORD = 50;

 //arrows of axis are represented with "hipotenuse" of 
 //triangle
 // now we are define length of cathetas of that triangle
 public static final int FIRST_LENGHT = 5;
 public static final int SECOND_LENGHT= 5;//the tick mark heights

 // size of start coordinate lenght
 public static final int ORIGIN_COORDINATE_LENGHT = 6;

 // distance of coordinate strings from axis
 public static final int AXIS_STRING_DISTANCE = 25;

    private List<Point> points = new ArrayList<>();
     //coordinates
    int xCoordNumbers = 50;
  int yCoordNumbers = 15;
  //probably happening bc of int not double
  int xLength= (X_AXIS_SECOND_X_COORD - X_AXIS_FIRST_X_COORD)/ xCoordNumbers;
  double xLength1= (double)(X_AXIS_SECOND_X_COORD - X_AXIS_FIRST_X_COORD)/ (xCoordNumbers*100);//distance b/n lines
  double xLength2= (double)(X_AXIS_SECOND_X_COORD - X_AXIS_FIRST_X_COORD)/ (xCoordNumbers*10);
  int yLength = (Y_AXIS_SECOND_Y_COORD - Y_AXIS_FIRST_Y_COORD)/ yCoordNumbers;
  double yLength1=(double)(Y_AXIS_SECOND_Y_COORD - Y_AXIS_FIRST_Y_COORD)/ (yCoordNumbers*100);//bc its 0.1 and its just 0 in int
  double yLength2=(double)(Y_AXIS_SECOND_Y_COORD - Y_AXIS_FIRST_Y_COORD)/ (yCoordNumbers*10);
    public void drawPoint(Point point) {
    points.add(point);
    repaint();
}
 
private void drawPointOnPanel(Point point, Graphics g) {
    final int pointDiameter = 5;
    final int x = (int)((X_AXIS_FIRST_X_COORD + (point.x * xLength1) - pointDiameter / 2));//controls where the point is drawn
    final int y = (int)((Y_AXIS_SECOND_Y_COORD - (point.y * yLength1) - pointDiameter / 2));
    pHGraph.pointsCoord.add(x);
    pHGraph.pointsCoord.add(y);
    //g.fillOval(x, y, pointDiameter, pointDiameter);
}


 public void paintComponent(Graphics g) {
  super.paintComponent(g);

  Graphics2D g2 = (Graphics2D) g;

  g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
    RenderingHints.VALUE_ANTIALIAS_ON);

  // x-axis
  g2.drawLine(X_AXIS_FIRST_X_COORD, X_AXIS_Y_COORD,
     X_AXIS_SECOND_X_COORD, X_AXIS_Y_COORD);
  // y-axis
  g2.drawLine(Y_AXIS_X_COORD, Y_AXIS_FIRST_Y_COORD,
     Y_AXIS_X_COORD, Y_AXIS_SECOND_Y_COORD);

  // draw origin Point
  g2.fillOval(
    X_AXIS_FIRST_X_COORD - (ORIGIN_COORDINATE_LENGHT / 2), 
    Y_AXIS_SECOND_Y_COORD - (ORIGIN_COORDINATE_LENGHT / 2),
    ORIGIN_COORDINATE_LENGHT, ORIGIN_COORDINATE_LENGHT);
    //draw lines
    
  // numerate axis
  // draw x-axis numbers//adding 50mL total 2 decimals 50*10*10=5000//dividn by ten makes 20 tick but closer together
  for(int i = 1; i <=(xCoordNumbers*100); i++) {
        if (i%10==0)
      g2.drawLine((int)(X_AXIS_FIRST_X_COORD + (i * (xLength1))), X_AXIS_Y_COORD - SECOND_LENGHT, (int)(X_AXIS_FIRST_X_COORD + (i * (xLength1))), X_AXIS_Y_COORD + SECOND_LENGHT);
  }
  for(int i = 1; i <= (xCoordNumbers*100); i++) {
        if (i%100==0)
      g2.drawString(Integer.toString(i/100), (int)(X_AXIS_FIRST_X_COORD + (i * xLength1) - 3), X_AXIS_Y_COORD + AXIS_STRING_DISTANCE);//dont divide this one
  }

  //draw y-axis numbers
    for(int i = 1; i <= (yCoordNumbers*100); i++) {
       if (i%10==0)
      g2.drawLine(Y_AXIS_X_COORD - SECOND_LENGHT,(int)(Y_AXIS_SECOND_Y_COORD - (i * yLength1)), Y_AXIS_X_COORD + SECOND_LENGHT,(int)(Y_AXIS_SECOND_Y_COORD - (i * yLength1)));
    }
    for(int i = 1; i < (yCoordNumbers*100); i++) {
        if (i%100==0)
        g2.drawString(Integer.toString(i/100), Y_AXIS_X_COORD - AXIS_STRING_DISTANCE, (int)(Y_AXIS_SECOND_Y_COORD - (i * yLength1)));
      }
  
  points.forEach(p -> drawPointOnPanel(p, g));
  if (pHGraph.pointsCoord.size()>2)
    {
      
        for (int i=0; i<pHGraph.pointsCoord.size()-3;i=i+2)
        {
            g2.drawLine(pHGraph.pointsCoord.get(i)+2,pHGraph.pointsCoord.get(i+1)+2,pHGraph.pointsCoord.get(i+2)+2,pHGraph.pointsCoord.get(i+3)+2);
        }
    }
 }
}