package Vista;

import java.util.LinkedList;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class TModeloLogin implements TableModel{
    
    public void addArea (String area){
        datos.add (area);
    }
    
    public int getRowCount() {
        return datos.size();
    }

    public int getColumnCount() {
        return 1;
    }

    public String getColumnName(int columnIndex) {
        switch (columnIndex){
            case 0:
                return "Area";
            default:
                return null;
        }
    }

    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex){
            case 0:              
                return String.class;
            default:
                return Object.class;
        }
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {                
        switch (columnIndex){
            case 0:
                return datos.get(rowIndex);
           default:
                return null;
        }
    }

    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        switch (columnIndex){
            case 0:
                datos.get(rowIndex);
                break;            
            default:
                break;
        }
   }

    public void addTableModelListener(TableModelListener l) {
        
        listeners.add (l);
    }

    public void removeTableModelListener(TableModelListener l) {
        
        listeners.remove(l);
    }
    
    private LinkedList datos = new LinkedList();
    
    private LinkedList listeners = new LinkedList();

}