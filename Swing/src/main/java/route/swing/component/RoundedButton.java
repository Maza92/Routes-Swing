/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package route.swing.component;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import javax.swing.BorderFactory;
import javax.swing.JButton;

/**
 *
 * @author Luis
 */
public class RoundedButton extends JButton {
    private static final int ARC_WIDTH = 30;
    private static final int ARC_HEIGHT = 30;

    // No-argument constructor
    public RoundedButton() {
        this(null);
    }

    // Constructor with text
    public RoundedButton(String text) {
        super(text);
        setContentAreaFilled(false); // Eliminar la área de contenido predeterminada
        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Adjust for the border insets
        int width = getWidth() - 1;
        int height = getHeight() - 1;
        
        // Dibujar el fondo del botón
        g2.setColor(getBackground());
        g2.fill(new RoundRectangle2D.Float(0, 0, width, height, ARC_WIDTH, ARC_HEIGHT));
        
        // Dibujar el borde del botón
        g2.setColor(getForeground());
        g2.draw(new RoundRectangle2D.Float(0, 0, width, height, ARC_WIDTH, ARC_HEIGHT));
        
        g2.dispose();
        
        super.paintComponent(g); // Dibujar el texto del botón
    }

    @Override
    public void updateUI() {
        super.updateUI();
        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    }
}
