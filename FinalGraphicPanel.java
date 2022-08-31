//Arturo Salazar
//CPSC-Java Final

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.*;
import java.awt.geom.Point2D;

public class FinalGraphicPanel extends JPanel
{
  private int widthPanel = 1000;
  private int heightPanel = 800 - 50 - 100;

  private final double earth_ballradius = 10.0;
  private final double earth_balldiameter = 2.0 * earth_ballradius;

  private final double sun_ballradius = 20.0;
  private final double sun_balldiameter = 2.0 * sun_ballradius;

  private final double mars_ballradius = 7.0;
  private final double mars_balldiameter = 2.0 * mars_ballradius;

  private final double moon_ballradius = 4.0;
  private final double moon_balldiameter = 2.0 * moon_ballradius;

  private double earth_xBallCenter;
  private double earth_yBallCenter;
  private double earth_xBallUpperCorner;
  private double earth_yBallUpperCorner;
  private int earth_intXBallUpperCorner;
  private int earth_intYBallUpperCorner;

  private double sun_xBallCenter;
  private double sun_yBallCenter;
  private double sun_xBallUpperCorner;
  private double sun_yBallUpperCorner;
  private int sun_intXBallUpperCorner;
  private int sun_intYBallUpperCorner;

  private double mars_xBallCenter;
  private double mars_yBallCenter;
  private double mars_xBallUpperCorner;
  private double mars_yBallUpperCorner;
  private int mars_intXBallUpperCorner;
  private int mars_intYBallUpperCorner;

  private double moon_xBallCenter;
  private double moon_yBallCenter;
  private double moon_xBallUpperCorner;
  private double moon_yBallUpperCorner;
  private int moon_intXBallUpperCorner;
  private int moon_intYBallUpperCorner;

  private double xmin,xmax;
  private double ymin,ymax;
  private double earth_sigmaX;
  private double earth_sigmaY;
  private double mars_sigmaX;
  private double mars_sigmaY;
  private double moon_sigmaY;
  private double moon_sigmaX;

  private double earth_speed;
  private double mars_speed;
  private double moon_speed;

  private boolean successfulMove = true;
  private double xtemporary;
  private double ytemporary;
  private double deltaT;
  private double marsdeltaT;
  private double moondeltaT;
  private double distance;
  private double marsdistance;
  private double moondistance;
  private double t = 0;
  private double marst = 0;
  private double moont = 0;


  public FinalGraphicPanel()
  {
    setVisible(true);
  }

  public void paintComponent(Graphics graphicArea)
  {
    super.paintComponent(graphicArea);
    setBackground(Color.black);
    graphicArea.setColor(Color.YELLOW);
    graphicArea.fillOval(sun_intXBallUpperCorner, sun_intYBallUpperCorner,(int)Math.round(sun_balldiameter), (int)Math.round(sun_balldiameter));

    graphicArea.setColor(Color.BLUE);
    graphicArea.fillOval(earth_intXBallUpperCorner, earth_intYBallUpperCorner,(int)Math.round(earth_balldiameter), (int)Math.round(earth_balldiameter));

    graphicArea.setColor(Color.RED);
    graphicArea.fillOval(mars_intXBallUpperCorner, mars_intYBallUpperCorner, (int)Math.round(mars_balldiameter), (int)Math.round(mars_balldiameter));

    graphicArea.setColor(Color.GRAY);
    graphicArea.fillOval(moon_intXBallUpperCorner, moon_intYBallUpperCorner, (int)Math.round(moon_balldiameter), (int)Math.round(moon_balldiameter));
  }

  public void initializeObjectsInPanel(double earth_rate, double earthX, double earthY, double mars_rate, double moon_rate)
  {
    xmin = 0;
    xmax = widthPanel;
    ymin = 0;
    ymax = heightPanel;

    sun_xBallCenter = (xmin + xmax) / 2;
    sun_yBallCenter = (ymin + ymax) / 2;


    earth_speed = earth_rate;
    mars_speed = mars_rate;
    moon_speed = moon_rate;

    earth_yBallCenter = 321;
    earth_xBallCenter = 666;

    moon_yBallCenter = 321;
    moon_xBallCenter = 690;

    mars_yBallCenter = 321;
    mars_xBallCenter = 800;

    distance = Math.sqrt(Math.pow(sun_xBallCenter - 666, 2) + Math.pow(sun_yBallCenter - 321, 2));
    marsdistance = Math.sqrt(Math.pow(sun_xBallCenter - 800, 2) + Math.pow(sun_yBallCenter - 321,2));
    moondistance = Math.sqrt(Math.pow(earth_xBallCenter - 690, 2) + Math.pow(earth_yBallCenter - 321,2));

    earth_xBallUpperCorner = earth_xBallCenter - earth_ballradius;
    earth_yBallUpperCorner = earth_yBallCenter - earth_ballradius;
    earth_intXBallUpperCorner = (int)Math.round(earth_xBallUpperCorner);
    earth_intYBallUpperCorner = (int)Math.round(earth_yBallUpperCorner);

    sun_xBallUpperCorner = sun_xBallCenter - sun_ballradius;
    sun_yBallUpperCorner = sun_yBallCenter - sun_ballradius;
    sun_intXBallUpperCorner = (int)Math.round(sun_xBallUpperCorner);
    sun_intYBallUpperCorner = (int)Math.round(sun_yBallUpperCorner);

    mars_xBallUpperCorner = mars_xBallCenter - mars_ballradius;
    mars_yBallUpperCorner = mars_yBallCenter - mars_ballradius;
    mars_intXBallUpperCorner = (int)Math.round(mars_xBallUpperCorner);
    mars_intYBallUpperCorner = (int)Math.round(mars_yBallUpperCorner);

    moon_xBallUpperCorner = moon_xBallCenter - moon_ballradius;
    moon_yBallUpperCorner = moon_yBallCenter - moon_ballradius;
    moon_intXBallUpperCorner = (int)Math.round(moon_xBallUpperCorner);
    moon_intYBallUpperCorner = (int)Math.round(moon_yBallUpperCorner);
  }

  public boolean earth_moveBall()
  {
    successfulMove = true;
    deltaT = (earth_speed*.5)/distance;
    t = t + deltaT;

    earth_sigmaX = sun_xBallCenter + distance*Math.cos(t);
    earth_sigmaY = sun_yBallCenter + distance*Math.sin(-t);

    earth_xBallCenter = earth_sigmaX;
    earth_yBallCenter = earth_sigmaY;

    earth_xBallUpperCorner = earth_xBallCenter - earth_ballradius;
    earth_yBallUpperCorner = earth_yBallCenter - earth_ballradius;
    earth_intXBallUpperCorner = (int)Math.round(earth_xBallUpperCorner);
    earth_intYBallUpperCorner = (int)Math.round(earth_yBallUpperCorner);


    return successfulMove;
  }

  public boolean mars_moveBall()
  {
    successfulMove = true;
    marsdeltaT = (mars_speed*(.25))/marsdistance;
    marst = marst + marsdeltaT;

    mars_sigmaX = sun_xBallCenter + marsdistance*Math.cos(marst);
    mars_sigmaY = sun_yBallCenter + marsdistance*Math.sin(-marst);

    mars_xBallCenter = mars_sigmaX;
    mars_yBallCenter = mars_sigmaY;

    mars_xBallUpperCorner = mars_xBallCenter - mars_ballradius;
    mars_yBallUpperCorner = mars_yBallCenter - mars_ballradius;
    mars_intXBallUpperCorner = (int)Math.round(mars_xBallUpperCorner);
    mars_intYBallUpperCorner = (int)Math.round(mars_yBallUpperCorner);

    return successfulMove;
  }

  public boolean moon_moveBall()
  {
    successfulMove = true;
    moondeltaT = (moon_speed*(.1))/moondistance;
    moont = moont + moondeltaT;

    moon_sigmaX = earth_xBallCenter + moondistance*Math.cos(moont);
    moon_sigmaY = earth_yBallCenter + moondistance*Math.sin(-moont);

    moon_xBallCenter = moon_sigmaX;
    moon_yBallCenter = moon_sigmaY;

    moon_xBallUpperCorner = moon_xBallCenter - moon_ballradius;
    moon_yBallUpperCorner = moon_yBallCenter - moon_ballradius;
    moon_intXBallUpperCorner = (int)Math.round(moon_xBallUpperCorner);
    moon_intYBallUpperCorner = (int)Math.round(moon_yBallUpperCorner);

    return successfulMove;
  }

  public double XgetDistanceBetween()
  {
    xtemporary = earth_xBallCenter;
    return xtemporary;
  }

  public double YgetDistanceBetween()
  {
    ytemporary = earth_yBallCenter;
    return ytemporary;
  }
}
