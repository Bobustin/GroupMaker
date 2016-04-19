package groupmaker.core;
import javax.swing.event.TableModelEvent;
import javax.swing.table.TableModel;


public class RowShiftTableModelEvent extends TableModelEvent {

	public static final int SHIFT_UP = 2;
	public static final int SHIFT_DOWN = 3;
	
	private int row1;
	private int row2;
	private int shift;
	
	public RowShiftTableModelEvent(TableModel source, int r1, int r2){
		super(source);
		if(r1==r2){throw new IllegalArgumentException("Rows must not be equal!");}
		row1 = r1;
		row2 = r2;
		shift = r1>r2?SHIFT_DOWN:SHIFT_UP;
	}
	
	public int getFirstRow(){
		return row1;
	}

	public int getSecondRow(){
		return row2;
	}
	
	public int getShiftType(){
		return shift;
	}
	@Override
	public int getType(){
		return getShiftType();
	}
	
}
