package PBServiceProgram;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class NotificationsTableModel extends AbstractTableModel{

	private static final long serialVersionUID = -5191014151670930696L;
	
	public static final int OBJECT_COL = -1;
	private static final int NOTIF_ID = 0;
	private static final int START_DATE = 1;
	private static final int FINISH_DATE = 2;
	private static final int OWNER = 3;
	private static final int CONTACT = 4;
	private static final int ADDRESS = 5; 
	private static final int PRODUCT = 6;
	private static final int SERIAL = 7;
	private static final int ACCESSORIES = 8;
	private static final int SELL_DATE = 9;
	private static final int PRICE = 10;
	private static final int DESCRIPTION = 11;
	private static final int ADDER = 12;
	private static final int EMPLOYEE = 13;
	private static final int WARRANTY = 14;
	private static final int STATUS = 15;

	private String[] columnNames = {"Nr", "Rozpoczêcie", "Koniec", "Zg³aszaj¹cy", "Kontakt", "Adres", "Dotyczy", "Serial", 
			"Wyposa¿enie", "Sprzeda¿", "Wycena", "Opis usterki","Dodaj¹cy", "Wykonawca", "Gwarancja", "Status"};
	
	private ArrayList<NotificationsList> notifications;
	
	public NotificationsTableModel(ArrayList<NotificationsList> theNotificationsList){
		notifications = theNotificationsList;
	}
	@Override
	public int getColumnCount(){
		return columnNames.length;
	}
	@Override
	public int getRowCount(){
		return notifications.size();
	}
	@Override
	public String getColumnName(int col){
		return columnNames[col];
	}
	@Override
	public Object getValueAt(int row, int col){
		
		NotificationsList tempNotifications = notifications.get(row);
		
		switch(col){
		case NOTIF_ID:
			return tempNotifications.getNotifId();
		case START_DATE:
			return tempNotifications.getStartDate();
		case FINISH_DATE:
			return tempNotifications.getFinishDate();
		case OWNER:
			return tempNotifications.getOwner();
		case CONTACT:
			return tempNotifications.getContact();
		case ADDRESS:	
			return tempNotifications.getAddress();
		case PRODUCT:
			return tempNotifications.getProduct();
		case SERIAL:
			return tempNotifications.getSerial();
		case ACCESSORIES:
			return tempNotifications.getAccessories();
		case SELL_DATE:
			return tempNotifications.getSellDate();	
		case PRICE:
			return tempNotifications.getPrice();
		case DESCRIPTION:
			return tempNotifications.getDescription();
		case ADDER:
			return tempNotifications.getAdder();
		case EMPLOYEE:
			return tempNotifications.getEmployee();
		case WARRANTY:
			return tempNotifications.getWarranty();
		case STATUS:
			return tempNotifications.getStatus();
		case OBJECT_COL:
			return tempNotifications;
		default:
			return tempNotifications.getNotifId();
		}
	}
	
	@Override
	public Class<?> getColumnClass(int c){
		return getValueAt(0, c).getClass();
	}
}