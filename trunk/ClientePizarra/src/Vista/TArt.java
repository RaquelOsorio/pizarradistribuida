package Vista;

import javax.swing.table.DefaultTableModel;

class TArt extends DefaultTableModel {
    @Override
    public boolean isCellEditable (int row, int column) {
       return false;
   }
}
