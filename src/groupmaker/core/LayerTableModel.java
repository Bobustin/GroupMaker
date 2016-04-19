package groupmaker.core;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class LayerTableModel extends AbstractTableModel {

	private ArrayList<Object[]> data;
	private String[] columnNames;

	public LayerTableModel(Object[][] oData, String[] columns){
		data = new ArrayList<Object[]>();
		for(int i=0; i<oData.length; i++){
			data.add(oData[i]);
		}
		columnNames = columns;
	}
	
	public int getColumnCount() {
	        return columnNames.length;
	}

	public int getRowCount() {
	    return data.size();
	}

	public String getColumnName(int col) {
	    return columnNames[col];
	}

	public Object getValueAt(int row, int col) {
	    return data.get(row)[col];
	}

	public Class getColumnClass(int c) {
	    return getValueAt(0, c).getClass();
	}
	public boolean isCellEditable(int row, int col) {
	    return true;
	}
	public void setValueAt(Object value, int row, int col) {
	    data.get(row)[col] = value;
	    fireTableCellUpdated(row, col);
	}
	public void addRow(Object[] row){
		int index = data.size();
		data.add(row);
		fireTableRowsInserted(index, index);
	}
	public void insertRow(Object[] row, int index){
		data.add(index,row);
		fireTableRowsInserted(index,index);
	}
	public void removeRow(int row){
		data.remove(row);
		fireTableRowsDeleted(row, row);
	}
	public void shiftRowUp(int row){
		if(row>0){
			Object[] prev = data.get(row-1);
			data.set(row-1, data.get(row));
			data.set(row,prev);
			fireTableChanged(new RowShiftTableModelEvent(this,row,row-1));
		}
	}
	public void shiftRowDown(int row){
		if(row<data.size()-1){
			Object[] prev = data.get(row+1);
			data.set(row+1, data.get(row));
			data.set(row,prev);
			fireTableChanged(new RowShiftTableModelEvent(this,row,row+1));
		}
	}
}
