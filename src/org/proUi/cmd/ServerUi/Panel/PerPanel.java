/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.proUi.cmd.ServerUi.Panel;

import org.proUi.cmd.ServerUi.Panel.Groups.Gjf;
import org.proUi.cmd.ServerUi.Panel.Groups.NewG;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import main.Ui;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.proUi.cmd.ServerUi.Panel.Groups.Group;
import org.yaml.snakeyaml.Yaml;

/**
 *
 * @author Administrator
 */
public class PerPanel extends javax.swing.JPanel {

    private final DefaultListModel listModel1 = new DefaultListModel();
    private final DefaultListModel listModel2 = new DefaultListModel();
    private final DefaultListModel listModel3 = new DefaultListModel();

    private FileConfiguration config;

    /**
     * Creates new form PerPanel
     */
    public PerPanel() {
        initComponents();

        tishi.setLineWrap(true);
        flushList2();
    }

    /**
     *
     * @param name 本权限组名字，标准字体文字
     * @param inheritance 本组继承了哪些组
     * @param permissions 本组扩展了哪些权限
     * @param prefix 本组的名字前戳
     * @param suffix 本组的名字后缀
     * @param defaults 本组是否为一个默认组？只能有一个组是属于默认组。
     * @param builder 本组是否允许建筑/破坏地形？
     * @param worldName 本组将放置在哪个世界内，不可重复名，但应该已处于检查完毕。 使用务必小心。 本方法并没有成功实现，暂时无效！
     * @deprecated
     */
    public void addGroups(String name, List<String> inheritance, List<String> permissions,
            String prefix, String suffix, boolean defaults, boolean builder, String worldName) {

    }

    /**
     *
     * @param name 本权限组名字，标准字体文字
     * @param inheritance 本组继承了哪些组
     * @param permissions 本组扩展了哪些权限
     * @param prefix 本组的名字前戳
     * @param suffix 本组的名字后缀
     * @param defaults 本组是否为一个默认组？只能有一个组是属于默认组。
     * @param builder 本组是否允许建筑/破坏地形？
     * @param worldName 本组将放置在哪个世界内，不可重复名，但应该已处于检查完毕。 使用务必小心。
     * @deprecated
     */
    public void addGroups(String name, String[] inheritance, String[] permissions,
            String prefix, String suffix, boolean defaults, boolean builder, String worldName) {
        System.out.println("添加的组是: " + name + "\n");

    }

    public DefaultListModel getDefaultListModel1() {
        return this.listModel1;

    }

    private void getGroups() {
        g1.setText("");
        g2.setText("");
        if (jList2.getSelectedIndex() >= 0 && jList3.getSelectedIndex() >= 0) {
            String worldName = listModel2.get(jList2.getSelectedIndex()).toString();
            String name = listModel3.get(jList3.getSelectedIndex()).toString();

            File file = new File(main.Main.yunStr + "/plugins/GroupManager/worlds/" + worldName + "/groups.yml");
            if (!file.exists()) {
                return;
            }
            gname.setText(name);
            config = YamlConfiguration.loadConfiguration(file);//实例化 配置类
            if (config == null) {
                System.err.println("Config Object is Null");
            }
            List<String> s = config.getStringList("groups." + name + ".inheritance");
            for (String tmep : s) {
                g1.append(tmep + "\n");
                System.out.println("listModel4:  " + tmep);
            }
            List<String> s2 = config.getStringList("groups." + name + ".permissions");
            for (String tmp : s2) {
                g2.append(tmp + "\n");
                System.out.println("listModel5: " + tmp);
            }

            //new String(config.getString("groups." + name + ".info.prefix").getBytes("utf-8"), "GBK")
            InputStream fi = null;
            try {
                fi = new FileInputStream(file.getAbsolutePath());
            } catch (FileNotFoundException ex) {
                Logger.getLogger(PerPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            Yaml ya = new Yaml();
            HashMap data;
            data = (HashMap) ya.load(fi);
            data = (HashMap) data.get("groups");
            data = (HashMap) data.get(name);
            HashMap data2 = (HashMap) data.get("info");
            q1.setText(data2.get("prefix") + "");
            q2.setText(data2.get("suffix") + "");

            System.out.println("取到的Data2是：" + data2.toString());
            jCheckBox2.setSelected(config.getBoolean("groups." + name + ".info.build"));
            jCheckBox1.setSelected(config.getBoolean("groups." + name + ".default"));

        } else {
            System.err.println("无法取到获取的权限组。");
            //send("权限组读取失败！请刷新重试！");
        }

    }

    private void send(String str) {

        JOptionPane.showMessageDialog(null,
                str, "信息", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     *
     * @param path Yaml文件路径
     * @param zijie 欲读取的子节点，不可扩充到其他子节
     * @return 返回当前节下的所有子节，已String[]的形式
     */
    private String[] getYaml(String path, String zijie) {
        InputStream fi = null;
        String str = "";
        try {
            File fssssss = new File(path);
            if (!fssssss.exists()) {
                String[] s = {""};
                return s;
            }
            fi = new FileInputStream(fssssss.getAbsolutePath());

            Yaml ya = new Yaml();
            HashMap data;
            data = (HashMap) ya.load(fi);
            data = (HashMap) data.get(zijie);
            str = data + "";
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PerPanel.class.getName()).log(Level.SEVERE, null, ex);
            String[] s = {""};
            return s;
        }

        str = str.replaceFirst("\\" + str.charAt(0), "");
        str = str.replaceFirst("\\" + str.charAt(str.length() - 1), "");
        str = str.replaceAll("\\(.*?\\)|\\{.*?}|\\[.*?]|（.*?）", "");
        str = str.replaceAll("}", "");
        String[] strs = str.split("=, ");
        strs[strs.length - 1] = strs[strs.length - 1].replaceAll("=", "");
        if (strs[0] == null) {
            String[] s = {""};
            return s;
        }

        return strs;

    }

    private ArrayList<String> findDir(String filepath) {
        ArrayList<String> arr = new ArrayList<>();
        File file = new File(filepath);
        if (file == null) {
            return arr;
        }
        if (file.isDirectory()) {
            String[] filelist = file.list();
            for (String filelist1 : filelist) {
                File readfile = new File(filepath + "/" + filelist1);
                if (readfile.isDirectory()) {
                    System.out.println("找到文件夹内文件：" + readfile.getName());
                    arr.add(readfile.getName());
                }
            }
            return arr;
        }
        return arr;
    }

    private void flushList2() {

        {
            String strs = main.Main.yunStr
                    + "/plugins/GroupManager/globalgroups.yml";
            String[] qwert = this.getYaml(strs, "groups");
            listModel1.removeAllElements();
            for (String strerer : qwert) {
                listModel1.addElement(strerer);
                System.out.println("添加：listModel1" + strerer);
            }
        }

        {
            String str2 = main.Main.yunStr
                    + "/plugins/GroupManager/worlds";
            ArrayList<String> arrs = findDir(str2);

            listModel2.removeAllElements();
            for (String strerer : arrs) {
                listModel2.addElement(strerer);
                System.out.println("添加：listModel2: " + strerer);
            }
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        /*String strs = main.Main.yunStr+
        "/plugins/GroupManager/globalgroups.yml";
        String[] qwert = this.getYaml(strs, "groups");

        for(String strerer : qwert){
            listModel1.addElement(strerer);
        }*/
        ListG = new javax.swing.JList(listModel1);
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList(listModel2);
        jScrollPane3 = new javax.swing.JScrollPane();
        jList3 = new javax.swing.JList(listModel3);
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        q1 = new javax.swing.JTextField();
        q2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        gname = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        g1 = new javax.swing.JTextArea();
        jScrollPane8 = new javax.swing.JScrollPane();
        g2 = new javax.swing.JTextArea();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tishi = new javax.swing.JTextArea();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        jMenu1.setText("jMenu1");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "全局权限组", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("微软雅黑", 0, 14), java.awt.Color.black)); // NOI18N
        jPanel1.setToolTipText("全局权限组设置");
        jPanel1.setOpaque(false);

        ListG.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jScrollPane1.setViewportView(ListG);

        jLabel4.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 102, 255));
        jLabel4.setText("帮助");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel4MouseEntered(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jLabel6.setText("可进行编辑(注意格式)");

        jButton6.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jButton6.setText("编辑选定的全局权限组");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("楷体", 0, 10)); // NOI18N
        jLabel18.setText("一般情况下不需要对此进行编辑");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton6))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel18)))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "实际权限组", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("微软雅黑", 0, 14), java.awt.Color.black)); // NOI18N
        jPanel2.setToolTipText("您可以在这里设置各个世界的各个权限组");
        jPanel2.setOpaque(false);

        jList2.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jList2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList2MouseClicked(evt);
            }
        });
        jList2.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList2ValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(jList2);

        jList3.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jList3.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList3ValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(jList3);

        jLabel1.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jLabel1.setText("<html>本世界内的权限组： <br> - 选择一个即可进行配置。</html>");
        jLabel1.setToolTipText("");

        jLabel2.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jLabel2.setText("<html>权限组所在世界： <br> - 配置这个世界中的权限组。</html>");
        jLabel2.setToolTipText("");

        jLabel3.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 102, 255));
        jLabel3.setText("帮助");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel3MouseEntered(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35))
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel3))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane3)
                            .addComponent(jScrollPane2))
                        .addContainerGap())))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "权限组编辑", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("微软雅黑", 0, 14), java.awt.Color.black)); // NOI18N
        jPanel3.setToolTipText("权限组编辑");
        jPanel3.setOpaque(false);

        jLabel5.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 255));
        jLabel5.setText("这是什么？");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel5MouseEntered(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("微软雅黑", 1, 12)); // NOI18N
        jLabel7.setText("本权限组名字：");

        jLabel8.setFont(new java.awt.Font("微软雅黑", 1, 12)); // NOI18N
        jLabel8.setText("本组继承的权限组(inheritance): ");

        jLabel9.setFont(new java.awt.Font("微软雅黑", 1, 12)); // NOI18N
        jLabel9.setText("本组其他额外指定的单个权限(permissions):");

        jLabel10.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 102, 255));
        jLabel10.setText("这是什么？");
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel10MouseEntered(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("微软雅黑", 1, 12)); // NOI18N
        jLabel11.setText("<html>是否将权限组设为玩家默认组 <br> - 所有权限组只能有一个为默认组,这是玩家的初始组</html>");

        jLabel12.setFont(new java.awt.Font("微软雅黑", 1, 12)); // NOI18N
        jLabel12.setText("本组玩家是否可建筑/破坏？");

        jLabel13.setFont(new java.awt.Font("微软雅黑", 1, 12)); // NOI18N
        jLabel13.setText("本组内所有玩家称号名称设置：");

        jLabel14.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jLabel14.setText("前缀：");

        jLabel15.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jLabel15.setText("后缀：");

        q1.setFont(new java.awt.Font("新宋体", 0, 12)); // NOI18N

        q2.setFont(new java.awt.Font("楷体", 0, 12)); // NOI18N

        jButton1.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jButton1.setText("保存本组设置");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jButton4.setText("删除本组(谨慎操作)");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jCheckBox1.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jCheckBox1.setText("是(谨慎修改)");
        jCheckBox1.setOpaque(false);
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jCheckBox2.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jCheckBox2.setText("是(谨慎修改)");
        jCheckBox2.setOpaque(false);

        gname.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        gname.setText("<暂未选择任何实际权限组>");

        g1.setColumns(20);
        g1.setRows(5);
        jScrollPane7.setViewportView(g1);

        g2.setColumns(20);
        g2.setRows(5);
        jScrollPane8.setViewportView(g2);

        jLabel17.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 102, 255));
        jLabel17.setText("颜色代码表");
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel17MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel17MouseEntered(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("微软雅黑", 0, 10)); // NOI18N
        jLabel19.setText("提示：由于编码问题，如果出现乱码则重新写一遍中文保存即可");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gname))
            .addComponent(jScrollPane7)
            .addComponent(jScrollPane8)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel17))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel14)
                        .addGap(18, 18, 18)
                        .addComponent(q1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(q2, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jCheckBox2)))
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(gname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(q1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(q2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19)
                .addGap(24, 24, 24)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jCheckBox1)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jCheckBox2)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton1)))
        );

        jLabel16.setFont(new java.awt.Font("微软雅黑", 1, 12)); // NOI18N
        jLabel16.setText("提示与帮助：");

        tishi.setColumns(20);
        tishi.setFont(new java.awt.Font("黑体", 0, 13)); // NOI18N
        tishi.setRows(5);
        tishi.setText("欢迎使用，权限组编辑器。\n 权限组编辑器可以方便您管理您的服务器内大部分权限，并且降低出错率。如果您会权限组设置本界面如有不佳之处欢迎反馈。\n 如果您未曾配置过权限组，您得有权限组的概念。从而才可以进行自由的配置。如果您未曾使用过类似的软件或编辑过权限，请仔细查看下列文字：\n1.全局权限组: globalgroups.yml文件，定义了所有权限，是一个无玩家只有权限的权限组,一般只用于被继承。\n2.实际权限组: \\worlds\\世界名字\\groups.yml文件，定义了本世界内所有的组，可继承全局权限组，玩家可分类在这些组拥有不同权限（如VIP等）。");
        jScrollPane6.setViewportView(tishi);

        jButton3.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jButton3.setText("新建权限组在选定的世界内");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jButton5.setText("刷新");
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton5MouseClicked(evt);
            }
        });
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6)
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton5))
                .addContainerGap())
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jList2ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList2ValueChanged
        /* {
         String strs = main.Main.yunStr = main.Main.yunStr//\plugins\GroupManager\worlds\world
         + "/plugins/GroupManager/" + jList2.getSelectedValue().toString() + "/groups.yml";
         String[] qwert = this.getYaml(strs, "groups");
         listModel3.removeAllElements();
         for (String strerer : qwert) {
         System.out.println("添加：listModel3: " + strerer);
         listModel3.addElement(strerer);
         }
         }
         */
        try {
            if (jList2.getSelectedIndex() >= 0) {
                System.out.println("[事件] jList2ValueChanged : " + jList2.getSelectedIndex());
                System.out.println(listModel2.get(jList2.getSelectedIndex()).toString());
                String strs = main.Main.yunStr//\plugins\GroupManager\worlds\world
                        + "/plugins/GroupManager/worlds/" + listModel2.get(jList2.getSelectedIndex()).toString() + "/groups.yml";
                String[] qwert = this.getYaml(strs, "groups");
                System.out.println("读取的路径是：" + strs);
                listModel3.removeAllElements();
                for (String strerer : qwert) {
                    System.out.println("添加：listModel3: " + strerer);
                    listModel3.addElement(strerer);
                }
            }
        } catch (Exception e) {
            send("读取失败！请检查文件是否为正确格式或者重新配置。\n异常: " + e.toString());
        }

    }//GEN-LAST:event_jList2ValueChanged

    private void jButton5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseClicked

        //  flushList();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5MouseClicked

    private void jList2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList2MouseClicked
        //  flushList2();        // TODO add your handling code here:
    }//GEN-LAST:event_jList2MouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        if (ListG.getSelectedIndex() >= 0) {
            System.out.println("[事件] ListGValueChanged : " + ListG.getSelectedIndex());
            System.out.println(listModel1.get(ListG.getSelectedIndex()).toString());
            new Gjf(listModel1.get(ListG.getSelectedIndex()).toString()).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null,
                    "您无法编辑全局权限组，可能的原因是: \n 权限组配置读取出错，或者您未选择任何权限组！\n建议: 您重启本编辑器。", "提示", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jLabel4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseEntered
        // TODO add your handling code here:】】
        tishi.setText(""
                + "全局权限组：\n"
                + "定义了一些不可加入玩家的权限集合，这些组里面有很多权限，一般只用于被继承。\n"
                + "假设Builder组继承了这里面的某个组，那么Builder组就拥有这个组里面的所有权限。\n"
                + "可自行删除或添加这些全局权限组里面的权限。"
        );
    }//GEN-LAST:event_jLabel4MouseEntered

    private void jLabel3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseEntered
        // TODO add your handling code here:
        tishi.setText(""
                + "实际权限组：\n"
                + "首先你需要选择一个你打算修改权限组的世界。如有多世界则需要选择，否则一般就world。\n"
                + "选择世界之后便可 修改/新建 这世界内的所有权限组，一般Builder为玩家默认组(注意不一定)，这是根据default属性决定的\n"
                + "你可以选择一个权限组进行操作了。"
        );
    }//GEN-LAST:event_jLabel3MouseEntered

    private void jLabel5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseEntered
        // TODO add your handling code here:
        //本组继承的权限组(inheritance): 
        tishi.setText(""
                + "本组继承的权限组(inheritance): \n"
                + "在这里可以继承全局权限组或者其他的权限组，一旦继承了他们，就会拥有这些组里面的所有权限。\n"
                + "可自行 编辑/新建 你想要继承的组。\n"
                + "一般不建议【删除】默认的继承的组，除非你有特殊要求，否则可能造成这个组没有最基础的权限。"
        );
    }//GEN-LAST:event_jLabel5MouseEntered

    private void jLabel10MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseEntered
        // TODO add your handling code here:
        //本组其他指定权限(permissions):

        tishi.setText(""
                + "本组其他指定权限(permissions):\n"
                + "这些权限组除了被继承之外，还可以另外单独的添加多余的权限给这个组。\n"
                + "列如可以添加 groupmanager.noofflineperms 到这里。\n"
                + "添加之后这个组将拥有 groupmanager.noofflineperms这个权限，并且只限于这个组，独立的。"
        );
    }//GEN-LAST:event_jLabel10MouseEntered

    private void jList3ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList3ValueChanged
        // TODO add your handling code here:
        getGroups();
    }//GEN-LAST:event_jList3ValueChanged

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        flushList2();
        listModel3.removeAllElements();
        g1.setText("");
        g2.setText("");
        gname.setText("<暂未选择任何实际权限组>");
        q1.setText("");
        q2.setText("");

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        /**
         *
         * pram group 这个组将在哪个世界内创造。 param grouped 这个世界内有其他哪些组的名字 param p
         * 权限窗口传递过来，我要使用你的方法在等下。 务必仔细查看本方法使用参数，否则将一去不复返。！
         */
        if (jList2.getSelectedIndex() >= 0) {//listModel3
            List<String> list = new ArrayList<>();
            for (int i = 0; i < listModel2.size(); i++) {
                list.add(listModel2.getElementAt(i).toString());
            }
            for (String s : list) {
                System.out.println("List：" + s);
            }

            new NewG(listModel2.get(jList2.getSelectedIndex()).toString(), list, this).setVisible(true);
        } else {
            send("请先选择一个世界\n权限组必须要建立在一个世界里面。\n您必须选择先选择一个世界，再进行编辑。");
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        String[] str = g1.getText().split("\n");
        List<String> list = new ArrayList<>();
        for (String tmp : str) {
            list.add(tmp);
        }
        String[] str2 = g2.getText().split("\n");
        List<String> list2 = new ArrayList<>();
        for (String tmp : str2) {
            list2.add(tmp);
        }
        /*
         path - 保存的文件路径，例如取运行目录（）+“\YAME.yaml”
         name - 本权限组名字，标准字体文字 
         inheritance - 本组继承了哪些组 
         permissions - 本组扩展了哪些权限 
         prefix - 本组的名字前戳 
         suffix - 本组的名字后缀 
         defaults - 本组是否为一个默认组？只能有一个组是属于默认组。 
         builder - 本组是否允许建筑/破坏地形？ 
         */
        //   this.disable();
        if (jList3.getSelectedIndex() < 0) {
            send("您必须要先选择一个世界中的权限组才可以保存。权限组必须要先保存到世界中。");
        }
        Group group = new Group();
        String path = main.Main.yunStr + "/plugins/GroupManager/worlds/" + listModel2.get(jList2.getSelectedIndex()).toString()
                + "/groups.yml";
        group.SaveGroups(path, gname.getText().toString(), list, list2, q1.getText().toString(), q2.getText().toString(),
                jCheckBox1.isSelected(), jCheckBox2.isSelected());

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        // path - 配置文件的路径 
        // name - 配置文件的名字 删除一个组。
        // group - 目前所有的组名 
        String path = main.Main.yunStr + "/plugins/GroupManager/worlds/" + listModel2.get(jList2.getSelectedIndex()).toString()
                + "/groups.yml";
        List<String> list = new ArrayList<>();
        for (int i = 0; i < listModel3.size(); i++) {
            if (((String) listModel3.get(i)).equalsIgnoreCase(gname.getText())) {

            } else {
                list.add((String) listModel3.get(i));
            }

        }

        new Group().removeGroups(path, null, list);
        JOptionPane.showMessageDialog(null, "已删除本组，程序将进行自动刷新已实时读取信息。", "信息", JOptionPane.INFORMATION_MESSAGE);
        listModel3.removeAllElements();
        flushList2();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jLabel17MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel17MouseEntered

    private void jLabel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseClicked
        // TODO add your handling code here:
        new ColorLook().setVisible(true);
    }//GEN-LAST:event_jLabel17MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList ListG;
    private javax.swing.JTextArea g1;
    private javax.swing.JTextArea g2;
    private javax.swing.JTextField gname;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList jList2;
    private javax.swing.JList jList3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTextField q1;
    private javax.swing.JTextField q2;
    private javax.swing.JTextArea tishi;
    // End of variables declaration//GEN-END:variables
}
