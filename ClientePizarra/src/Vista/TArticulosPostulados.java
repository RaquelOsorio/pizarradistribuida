/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Vista;
import VO.VOPostulacion;
import java.util.LinkedList;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Francisca
 */
class TArticulosPostulados extends DefaultTableModel{

    private LinkedList datosAP = new LinkedList();
    private LinkedList listeners = new LinkedList();

    public void addArticuloPostulado (String us){
        datosAP.add(us);
    }

    @Override
    public int getRowCount() {
        return datosAP.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex){
            case 0:
                return "Articulo";
            case 1:
                return "Autor";
            case 2:
                return "Estado";
            default:
                return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

 @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex){
            case 0:
                return ((VOPostulacion)datosAP.get(rowIndex)).getTitulo();
            case 1:
                return ((VOPostulacion)datosAP.get(rowIndex)).getUser();
            case 2:
                return ((VOPostulacion)datosAP.get(rowIndex)).getEstado();
           default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
        this.listeners.add(l);
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        this.listeners.remove(l);
    }
}
