/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.proUi.cmd.ServerUi.Panel.Groups;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.proUi.cmd.ServerUi.Panel.PerPanel;
import org.yaml.snakeyaml.Yaml;

/**
 *
 * @author Administrator
 */
public class Group {

    /**
     *
     * @param path 保存的文件路径，例如取运行目录（）+“\YAME.yaml”
     * @param name 本权限组名字，标准字体文字
     * @param inheritance 本组继承了哪些组
     * @param permissions 本组扩展了哪些权限
     * @param prefix 本组的名字前戳
     * @param suffix 本组的名字后缀
     * @param defaults 本组是否为一个默认组？只能有一个组是属于默认组。
     * @param builder 本组是否允许建筑/破坏地形？
     */
    public void SaveGroups(String path, String name, List<String> inheritance, List<String> permissions,
            String prefix, String suffix, boolean defaults, boolean builder) {
        FileConfiguration config;
        File fsssss = new File(path);
        config = YamlConfiguration.loadConfiguration(fsssss);//实例化 配置类
        String gname = "groups." + name + ".";
        //config.set("groups", name);
        config.set(gname + "default", defaults);
        config.set(gname + "permissions", permissions);
        config.set(gname + "inheritance", inheritance);
        config.set(gname + "info.prefix", prefix);
        config.set(gname + "info.build", builder);
        config.set(gname + "info.suffix", suffix);

        try {
            config.save(fsssss);   //保存！

            System.out.println("保存 " + name + " 组设置成功√");
           
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,
                    "保存时出错！ 异常: " + ex.toString(), "Warning", JOptionPane.INFORMATION_MESSAGE);
            System.err.println("=====================保存出错=错误代码:49=====================");
            ex.printStackTrace();
        }
    }

    public void saveGroups(String path, MyGroup g) {
        FileConfiguration config;
        File fsssss = new File(path);
        config = YamlConfiguration.loadConfiguration(fsssss);//实例化 配置类
        String gname = "groups." + g.name + ".";
        //config.set("groups", name);
        config.set(gname + "default", g.defaults);
        config.set(gname + "permissions", g.permissions);
        config.set(gname + "inheritance", g.inheritance);
        config.set(gname + "info.prefix", g.prefix);
        config.set(gname + "info.build", g.build);
        config.set(gname + "info.suffix", g.suffix);

        try {
            config.save(fsssss);   //保存！

            System.out.println("保存 " + g.name + " 组设置成功√");

            //  JOptionPane.showMessageDialog(null,
            //       "权限组 " + g.name + " 在保存成功！ \n路径: " + path, "Warning", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,
                    "保存时出错！ 异常: " + ex.toString(), "Warning", JOptionPane.INFORMATION_MESSAGE);
            System.err.println("=====================保存出错=错误代码:49=====================");
            ex.printStackTrace();
        }
    }

    /**
     *
     * @param path 配置文件的路径
     * @param name 配置文件的名字 删除一个组。
     * @param group 目前所有的组名
     */
    public void removeGroups(String path, String name, List<String> group) {
        List<MyGroup> list = new ArrayList<>();

        for (String s : group) {
            MyGroup g = new MyGroup();
            g = readGroups(path, s);
            list.add(g);
        }
        FileWriter fw = null;
        try {
            fw = new FileWriter(path);
            fw.write("");
            fw.flush();
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(Group.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,
                    "保存配置时出错。可能文件被占用。\n 异常: " + ex.toString(), "Warning", JOptionPane.INFORMATION_MESSAGE);
        }
        for (MyGroup g : list) {
            saveGroups(path, g);
        }

    }

    public MyGroup readGroups(String path, String name) {
        // System.out.println("=====================开始操作===================");
        MyGroup g = new MyGroup();
        FileConfiguration config;
        File fssssss = new File(path);
        config = YamlConfiguration.loadConfiguration(fssssss);//实例化 配置类
        g.inheritance = config.getStringList("groups." + name + ".inheritance");
        g.permissions = config.getStringList("groups." + name + ".permissions");
        g.build = config.getBoolean("groups." + name + ".info.build");
        g.defaults = config.getBoolean("groups." + name + ".default");
        g.name = name;
        InputStream fi = null;
        try {
            fi = new FileInputStream(fssssss.getAbsolutePath());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PerPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        Yaml ya = new Yaml();
        HashMap data;
        data = (HashMap) ya.load(fi);
        data = (HashMap) data.get("groups");
        data = (HashMap) data.get(name);
        HashMap data2 = (HashMap) data.get("info");
        g.prefix = data2.get("prefix") + "";
        g.suffix = data2.get("suffix") + "";
        return g;
    }

}
