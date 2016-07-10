/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.proUi.cmd.ServerUi.Panel.Groups;

import java.util.List;

/**
 *
 * @author Administrator
 */
public class MyGroup {

    public String name;
    public boolean defaults;
    public List<String> permissions;
    public List<String> inheritance;
    public String prefix;
    public String suffix;
    public boolean build;
}
/*
 default: false
 permissions: []
 inheritance:
 - moderator
 - g:groupmanager_admin
 - g:bukkit_admin
 - g:essentials_admin
 - g:towny_admin
 - g:vanish_admin
 info:
 prefix: '&c'
 build: true
 suffix: ''
 */
