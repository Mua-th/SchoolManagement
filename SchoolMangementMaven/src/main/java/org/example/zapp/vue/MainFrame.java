package org.example.zapp.vue;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
  private static MainFrame instance;

  private MainFrame() {
    setTitle("Application");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(800, 600);
    setLayout(new BorderLayout());
  }

  public static MainFrame getInstance() {
    if (instance == null) {
      instance = new MainFrame();
    }
    return instance;
  }

  public void setPanel(JPanel panel) {
    getContentPane().removeAll();
    getContentPane().add(panel, BorderLayout.CENTER);
    revalidate();
    repaint();
  }
}