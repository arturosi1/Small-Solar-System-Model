//Arturo Salazar
//CPSC-Java Final

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.Timer;
import java.text.DecimalFormat;

public class FinalFrame extends JFrame
{
  private final int UI_width = 1000;
  private final int UI_height = 800;

  private JPanel titlepanel;
  private final int titlepanel_width = UI_width;
  private final int titlepanel_height = 50;
  private JLabel titlelabel;

  private FinalGraphicPanel Quadpanel;

  private JPanel buttonpanel;
  private JPanel buttonpanel_1;
  private JPanel buttonpanel_2;
  private final int buttonpanel_width = UI_width;
  private final int buttonpanel_height = 100;
  private JButton pause_button;
  private JButton start_button;
  private JButton quit_button;

  private JLabel earth_speedLabel;
  private JLabel xcoordinatelabel;
  private JLabel ycoordinatelabel;
  private JTextField earthinput;
  private JTextField xCoordinate;
  private JTextField yCoordinate;
  private double earthrate;
  private double xlocation;
  private double ylocation;

  // setting up Graphic Panel
  private final int straightpanel_width = UI_width;
  private final int straightpanel_height = UI_height - titlepanel_height - buttonpanel_height;

  private double earth_ball_speed_pix_per_second;
  private double earth_ball_speed_pix_per_tic;

  private double mars_ball_speed_pix_per_tic;

  private double moon_ball_speed_pix_per_tic;

  private Timer refreshClock;
  private Timer earth_motionClock;
  private Timer mars_motionClock;
  private Timer moon_motionClock;

  private Clockhandlerclass clockhandler;
  private final double refresh_clock_rate = 120.47;
  private int refresh_clock_delay_interval;
  private final double earth_motion_clock_rate = 100.00;
  private int earth_motion_clock_delay_interval;
  private final double mars_motion_clock_rate = 100.00;
  private int mars_motion_clock_delay_interval;
  private final double moon_motion_clock_rate = 100.00;
  private int moon_motion_clock_delay_interval;

  private final double millisecondpersecond = 1000.0;
  private boolean active = false;
  private int counter = 1;
  private double earthxlocation = 666;
  private double earthylocation = 321;

  public FinalFrame()
  {
    super ("FinalFrame");
    setTitle("Final Program");
    setLayout(new BorderLayout());
    setSize(UI_width,UI_height);
    setLocationRelativeTo(null);

    //Setting up title panel
    titlepanel = new JPanel();
    titlelabel = new JLabel("Solar System by Arturo Salazar");
    titlepanel.setBackground(Color.pink);
    titlepanel.setSize(titlepanel_width,titlepanel_height);
    add(titlepanel, BorderLayout.NORTH);
    titlepanel.add(titlelabel);

    //Setting up Quadpanel
    Quadpanel = new FinalGraphicPanel();
    Quadpanel.setBackground(Color.black);
    Quadpanel.setSize(straightpanel_width, straightpanel_height);
    add(Quadpanel, BorderLayout.CENTER);

    buttonpanel = new JPanel();
    buttonpanel.setLayout(new GridLayout(0,2));
    buttonpanel_1 = new JPanel();
    buttonpanel_1.setLayout(new GridLayout(2,3));
    buttonpanel_1.setBackground(Color.yellow);
    buttonpanel_2 = new JPanel();
    buttonpanel_2.setLayout(new GridLayout(2,2));
    buttonpanel_2.setBackground(Color.yellow);
    buttonpanel.setBackground(Color.yellow);
    add(buttonpanel, BorderLayout.SOUTH);
    buttonpanel.add(buttonpanel_1);
    buttonpanel.add(buttonpanel_2);

    start_button = new JButton("Start");
    pause_button = new JButton("Pause");
    quit_button = new JButton("Quit");
    quit_button.setBackground(Color.red);
    start_button.setBackground(new Color(0,252,17));
    buttonpanel_1.add(start_button);
    buttonpanel_1.add(pause_button);
    buttonpanel_1.add(quit_button);

    earth_speedLabel = new JLabel("Earth speed");
    earthinput = new JTextField();
    buttonpanel_1.add(earth_speedLabel);
    buttonpanel_1.add(earthinput);

    xcoordinatelabel = new JLabel("Earth X Coordinate");
    ycoordinatelabel = new JLabel("Earth Y Coordinate");
    xCoordinate = new JTextField("000.00");
    yCoordinate = new JTextField("000.00");
    buttonpanel_2.add(xcoordinatelabel);
    buttonpanel_2.add(ycoordinatelabel);
    buttonpanel_2.add(xCoordinate);
    buttonpanel_2.add(yCoordinate);

    clockhandler = new Clockhandlerclass();

    buttonhandler myhandler = new buttonhandler();
    start_button.addActionListener(myhandler);
    quit_button.addActionListener(myhandler);
    pause_button.addActionListener(myhandler);

    refresh_clock_delay_interval = (int)Math.round(millisecondpersecond/refresh_clock_rate);
    refreshClock = new Timer(refresh_clock_delay_interval,clockhandler);

    earth_motion_clock_delay_interval = (int)Math.round(millisecondpersecond/earth_motion_clock_rate);
    earth_motionClock = new Timer(earth_motion_clock_delay_interval,clockhandler);

    mars_motion_clock_delay_interval = (int)Math.round(millisecondpersecond/mars_motion_clock_rate);
    mars_motionClock = new Timer(mars_motion_clock_delay_interval,clockhandler);

    moon_motion_clock_delay_interval = (int)Math.round(millisecondpersecond/moon_motion_clock_rate);
    moon_motionClock = new Timer(moon_motion_clock_delay_interval,clockhandler);

    Quadpanel.initializeObjectsInPanel(0,earthxlocation,earthylocation, 0,0);
  }

  private class buttonhandler implements ActionListener
  {
    public void actionPerformed(ActionEvent event)
    {
      if(event.getSource() == start_button)
      {
          earth_ball_speed_pix_per_second = Double.parseDouble(earthinput.getText());
          earth_ball_speed_pix_per_tic = earth_ball_speed_pix_per_second/earth_motion_clock_rate;

          mars_ball_speed_pix_per_tic = (earth_ball_speed_pix_per_second)/earth_motion_clock_rate;

          moon_ball_speed_pix_per_tic = (earth_ball_speed_pix_per_second)/earth_motion_clock_rate;

          Quadpanel.initializeObjectsInPanel(earth_ball_speed_pix_per_tic, earthxlocation, earthylocation, mars_ball_speed_pix_per_tic, moon_ball_speed_pix_per_tic);

          earth_motionClock.start();
          refreshClock.start();
          mars_motionClock.start();
          moon_motionClock.start();
      }
      else if(event.getSource() == pause_button)
      {

        earth_motionClock.stop();
        refreshClock.stop();
        mars_motionClock.stop();
        moon_motionClock.stop();

      }
      else if(event.getSource() == quit_button)
      {
        System.exit(0);
      }
    }
  }
  private class Clockhandlerclass implements ActionListener
  {
    public void actionPerformed(ActionEvent event)
    {
      boolean animation_continues = false;
      if(event.getSource() == refreshClock)
      {
        Quadpanel.repaint();
      }
      else if(event.getSource() == earth_motionClock)
      {
        animation_continues = Quadpanel.earth_moveBall();
        xlocation = Quadpanel.XgetDistanceBetween();
        xCoordinate.setText(String.format("%.2f", xlocation));
        ylocation = Quadpanel.YgetDistanceBetween();
        yCoordinate.setText(String.format("%.2f", ylocation));
        if(!animation_continues)
        {
          earth_motionClock.stop();
          refreshClock.stop();
          mars_motionClock.stop();
          moon_motionClock.stop();
        }
      }
      else if(event.getSource() == mars_motionClock)
      {
        animation_continues = Quadpanel.mars_moveBall();
        if(!animation_continues)
        {
          earth_motionClock.stop();
          refreshClock.stop();
          mars_motionClock.stop();
          moon_motionClock.stop();
        }
      }
      else if(event.getSource() == moon_motionClock)
      {
        animation_continues = Quadpanel.moon_moveBall();
        if(!animation_continues)
        {
          earth_motionClock.stop();
          refreshClock.stop();
          mars_motionClock.stop();
          moon_motionClock.stop();
        }
      }
    }
  }

}
