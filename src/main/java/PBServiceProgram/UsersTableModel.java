package PBServiceProgram;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class UsersTableModel extends AbstractTableModel{


	private static final long serialVersionUID = -2932801242552956172L;
	
	public static final int OBJECT_COL = -1;
	private static final int FIRST_NAME_COL = 0;
	private static final int LAST_NAME_COL = 1;
	private static final int PERMISSION_COL = 2;
	private static final int EMAIL_COL = 3;
	
	private String[] columnNames = { "Imiê", "Nazwisko", "Uprawnienia", "E-mail"};
	private ArrayList<UsersList> users;
	
	public UsersTableModel(ArrayList<UsersList> theUsers) {
		users = theUsers;
	}
	@Override
	public int getColumnCount(){
		return columnNames.length;
	}
	@Override
	public int getRowCount(){
		return users.size();
	}
	@Override
	public String getColumnName(int col){
		return columnNames[col];
	}
	@Override
	public Object getValueAt(int row, int col){
		
		UsersList tempUsers = users.get(row);
		
		switch(col){
		case FIRST_NAME_COL:
			return tempUsers.getFirstName();
		case LAST_NAME_COL:
			return tempUsers.getLastName();
		case PERMISSION_COL:
			return tempUsers.getPermission();
		case EMAIL_COL:
			return tempUsers.getEmail();
		case OBJECT_COL:
			return tempUsers;
		default:
			return tempUsers.getFirstName();
		}
	}
	@Override
	public Class<?> getColumnClass(int c){
		return getValueAt(0, c).getClass();
	}
}
