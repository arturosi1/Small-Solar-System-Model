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

public class FinalTest
{
  public static void main(String[] args)
  {
    JFrame myframe = new FinalFrame();
    myframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    myframe.setSize(1000,800);
    myframe.setVisible(true);
    myframe.setResizable(false);
  }
}
