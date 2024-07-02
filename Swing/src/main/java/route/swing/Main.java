/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package route.swing;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.UIManager;
import route.swing.view.Login;

/**
 *
 * @author Luis
 */
public class Main {
    public static void main(String[] args) {
        FlatLightLaf.setup();
        UIManager.put("Button.arc", 12);
        UIManager.put("Component.arc", 12);
        UIManager.put("ProgressBar.arc", 12);
        UIManager.put("TextComponent.arc", 12);
        UIManager.put("Table.arc", 12);
        Login login = new Login();
        login.setVisible(true);
    }
}
