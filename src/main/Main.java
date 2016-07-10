/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class Main {

    public final static String yunStr = "" + System.getProperty("user.dir");

    public static void main(String[] args) throws IOException {
        System.out.println("当前运行目录： " + System.getProperty("user.dir"));
        File file = new File(System.getProperty("user.dir") + "/" + "lib");
        if (file.exists() == false) {
            file.mkdirs();
        }
        
        file = new File(System.getProperty("user.dir") + "/" + "lib/BukkitProphetNull.jar");
        if (!file.exists()) {
            {
                InputStream input = Main.class.getResourceAsStream("BukkitProphetNull.jar");
                File toFile = new File(System.getProperty("user.dir") + "/" + "lib/BukkitProphetNull.jar");
                try (DataOutputStream output = new DataOutputStream(new FileOutputStream(toFile))) {
                    byte buffer[] = new byte[input.available()];
                    int size = 0;
                    while ((size = input.read(buffer)) != -1) {
                        output.write(buffer, 0, size);
                        output.flush();
                    }
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null,
                            "错误: 输出辅助文件错误！请删除 " + System.getProperty("user.dir") + "/" + "lib/BukkitProphetNull.jar" + 
                                    "文件并且重新启动本程序！并且检测文件是否被占用！\n "
                            + "  异常输出: " + ex.toString(), "系统信息", JOptionPane.ERROR_MESSAGE);
                    System.exit(0);
                }
            }
        }

        new MainWindow().setVisible(true);
    }
}
/*

 JOptionPane.showMessageDialog(newFrame.getContentPane(),
 "弹出的是消息提示框!", "系统信息", JOptionPane.INFORMATION_MESSAGE);
 JOptionPane.showMessageDialog(newFrame.getContentPane(),
 "弹出的是警告提示框!", "系统信息", JOptionPane.WARNING_MESSAGE);
 JOptionPane.showMessageDialog(newFrame.getContentPane(),
 "弹出的是错误提示框!", "系统信息", JOptionPane.ERROR_MESSAGE);
 JOptionPane.showMessageDialog(newFrame.getContentPane(),
 "弹出的是询问提示框!", "系统信息", JOptionPane.QUESTION_MESSAGE);
 */
