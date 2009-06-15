/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Vista;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Francisca
 */
 class TArtPost extends DefaultTableModel {
@Override
    public boolean isCellEditable (int row, int column) {
       return false;
   }
}
