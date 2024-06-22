/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package route.swing.component;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import java.awt.*;

/**
 *
 * @author Luis
 */
public class TableCustom extends JTable {
    
    public TableCustom(TableModel model) {  
        super(model);
        initialize();
    }

    private void initialize() {
        // Eliminar bordes del encabezado y las celdas
        this.setShowGrid(false);
        this.setIntercellSpacing(new Dimension(0, 0));
        this.setTableHeader(null);

        // Renderizador de celdas personalizado
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                           boolean hasFocus, int row, int column) {
                Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                // Alternar colores de filas
                if (row % 2 == 0) {
                    cell.setBackground(new Color(220, 220, 220)); // Color gris claro
                } else {
                    cell.setBackground(Color.WHITE);
                }

                // Cambiar fuente
                cell.setFont(new Font("Arial", Font.PLAIN, 14));

                // Eliminar bordes de la celda
                ((JComponent) cell).setBorder(null);

                return cell;
            }
        };

        // Aplicar el renderizador personalizado a todas las columnas
        for (int i = 0; i < this.getColumnCount(); i++) {
            this.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
    }
}
