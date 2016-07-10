/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;

/**
 *
 * @author Administrator
 */
public class Ui {

    public static int M_c = 0;

    public void addClassUi(javax.swing.JFrame frame) {
        int windowWidth = frame.getWidth();                     //获得窗口宽   
        int windowHeight = frame.getHeight();                   //获得窗口高   
        Toolkit kit = Toolkit.getDefaultToolkit();              //定义工具包     
        Dimension screenSize = kit.getScreenSize();             //获取屏幕的尺寸   
        int screenWidth = screenSize.width;                     //获取屏幕的宽     
        int screenHeight = screenSize.height;                   //获取屏幕的高     
        frame.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);//设置窗口居中显示  
    }

    public void setClassJPanel(javax.swing.JPanel p) {
        p.setBackground(new Color(255, 255, 255));
    }

    public void setClassContainer(Container c) {
        c.setBackground(new Color(255, 255, 255));
    }
}
