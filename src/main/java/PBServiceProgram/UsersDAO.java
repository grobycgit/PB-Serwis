package PBServiceProgram;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



public class UsersDAO {
	
	
	private ConnectionManager sqlConnection;
	private Connection sqlConnect;
	
	public UsersDAO() throws Exception {
		
		sqlConnection = new ConnectionManager();
		sqlConnect = sqlConnection.getSqlConnection();
	}

	public ArrayList<UsersList> getAllUsers() throws Exception {
		ArrayList<UsersList> list = new ArrayList<>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try{
			myStmt = sqlConnect.createStatement();
			myRs = myStmt.executeQuery("select * from user order by last_name");
			
			while (myRs.next()){
				UsersList tempUser = convertRowToUser(myRs);
				list.add(tempUser);
			}
			
			return list;
		}
		finally{
			close(myStmt, myRs);
		}
	}
	
	public ArrayList<UsersList> getLoginName() throws Exception {
		ArrayList<UsersList> loginList = new ArrayList<UsersList>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try{
			myStmt = sqlConnect.createStatement();
			myRs = myStmt.executeQuery("select * from user order by last_name");
			
			while (myRs.next()){
				UsersList tempLoginList = convertRowToUserLogin(myRs);
				if(tempLoginList.getUserId()!=0){
					loginList.add(tempLoginList);
				}
			}
			return loginList;
		}
		finally{
			close(myStmt, myRs);
		}
	}
	
	public ArrayList<NotificationsList> getAllNotifications() throws Exception {
		ArrayList<NotificationsList> list = new ArrayList<>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try{
			myStmt = sqlConnect.createStatement();
			myRs = myStmt.executeQuery("select * from notifications order by notif_id DESC");
			
			while (myRs.next()){
				NotificationsList tempNotificationsList = convertRowToNotification(myRs);
				list.add(tempNotificationsList);
			}
			
			return list;
		}
		finally{
			close(myStmt, myRs);
		}
	}
	
	public void addUser (UsersList theUser) throws Exception{
		PreparedStatement myStmt = null;
		
		try{
			myStmt = sqlConnect.prepareStatement("insert into user" + 
					"(first_name, last_name, permission, font_name, font_size, email) values (?, ?, ?, ?, ?, ?)");
			
			myStmt.setString(1, theUser.getFirstName());
			myStmt.setString(2, theUser.getLastName());
			myStmt.setString(3, theUser.getPermission());
			myStmt.setString(4, "Arial");
			myStmt.setInt(5, 10);
			myStmt.setString(6, theUser.getEmail());
			
			myStmt.executeUpdate();
			
		}
		finally {
			close(myStmt);
		}
	}
	
	public void editUser (UsersList theUser) throws Exception{
		PreparedStatement myStmt = null;
		
		try{
			myStmt = sqlConnect.prepareStatement("update user set first_name=?, last_name=?, permission=?, email=? where user_id=?");
			myStmt.setString(1, theUser.getFirstName());
			myStmt.setString(2, theUser.getLastName());
			myStmt.setString(3, theUser.getPermission());
			myStmt.setString(4, theUser.getEmail());
			myStmt.setInt(5, theUser.getUserId());
			
			myStmt.executeUpdate();
		}
		finally {
			close(myStmt);
		}
	}
	
	public ArrayList<NotificationsList> getAllUserNotifications(UsersList theUser) throws Exception{
		ArrayList<NotificationsList> list = new ArrayList<>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		String choosenUser = theUser.getFirstName() + " " + theUser.getLastName();
		
		try{
			myStmt = sqlConnect.createStatement();
			myRs = myStmt.executeQuery("select * from notifications where employee=" + "'" + choosenUser + "' OR employee='' order by status, notif_id DESC");
			
			while (myRs.next()){
				NotificationsList tempNotificationsList = convertRowToNotification(myRs);
				if(tempNotificationsList.getNotifId()!=0){
					list.add(tempNotificationsList);
				}
				
			}
			if(list.isEmpty()){
				myRs = myStmt.executeQuery("select * from notifications where notif_id=0");
				while (myRs.next()){
					NotificationsList tempNotificationsList = convertRowToNotification(myRs);
					list.add(tempNotificationsList);
				}
			}
			
		} catch (Exception w){
			w.printStackTrace();
		}
		finally{
			close(myStmt, myRs);
		}return list;
	}
	
	
	public NotificationsList getLastNotif() throws Exception {
		NotificationsList tempNotification = null;
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = sqlConnect.createStatement();
			myRs = myStmt.executeQuery("select * from notifications order by notif_id desc limit 1");
			
			while(myRs.next()){
				tempNotification = convertRowToNotification(myRs);
			}
			
		} catch (Exception e) {
			
		} return tempNotification;
	}
	public ArrayList<NotificationsList> searchNotifications(String word) throws Exception {
		ArrayList<NotificationsList> list = new ArrayList<>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try{
			myStmt = sqlConnect.createStatement();
			myRs = myStmt.executeQuery("select * from notifications where start_date LIKE '%" + word + "%' OR finish_date LIKE '%" + word 
					+ "%' OR owner LIKE '%" + word + "%' OR contact LIKE '%" + word + "%' OR product LIKE '%" + word + "%' OR  serial LIKE '%" + word 
					+ "%' OR accessories LIKE '%" + word + "%' OR sell_date LIKE '%" + word + "%' OR price LIKE '%" + word 
					+ "%' OR description LIKE '%" + word+ "%' order by notif_id DESC");

			
			while (myRs.next()){
				NotificationsList tempNotificationsList = convertRowToNotification(myRs);
				list.add(tempNotificationsList);
			}
			if(list.isEmpty()){
				myRs = myStmt.executeQuery("select * from notifications where notif_id=0");
				while (myRs.next()){
					NotificationsList tempNotificationsList = convertRowToNotification(myRs);
					list.add(tempNotificationsList);
				}
			}
			return list;
		}
		finally{
			close(myStmt, myRs);
		}
	}
	public void editNotification (NotificationsList theNotification) throws Exception{
		PreparedStatement myStmt = null;
		
		try{
			myStmt = sqlConnect.prepareStatement("update notifications set start_date=?, finish_date=?, owner=?, "
					+ "contact=?, address=?, product=?, serial=?, accessories=?, sell_date=?, price=?, description=?, service=?, "
					+ "damages=?, recommendations=?, adder=?, employee=?, warranty=?, status=? where notif_id=?");

			myStmt.setString(1, theNotification.getStartDate());
			myStmt.setString(2, theNotification.getFinishDate());
			myStmt.setString(3, theNotification.getOwner());
			myStmt.setString(4, theNotification.getContact());
			myStmt.setString(5, theNotification.getAddress());
			myStmt.setString(6, theNotification.getProduct());
			myStmt.setString(7, theNotification.getSerial());
			myStmt.setString(8, theNotification.getAccessories());
			myStmt.setString(9, theNotification.getSellDate());
			myStmt.setString(10, theNotification.getPrice());
			myStmt.setString(11, theNotification.getDescription());
			myStmt.setString(12, theNotification.getService());
			myStmt.setString(13, theNotification.getDamages());
			myStmt.setString(14, theNotification.getRecommendations());
			myStmt.setString(15, theNotification.getAdder());
			myStmt.setString(16, theNotification.getEmployee());
			myStmt.setString(17, theNotification.getWarranty());
			myStmt.setString(18, theNotification.getStatus());
			myStmt.setInt(19, theNotification.getNotifId());
			
			myStmt.executeUpdate();
		}
		finally {
			close(myStmt);
		}
	}
	public void deleteUser(int userId) throws SQLException {
		PreparedStatement myStmt = null;
		
		try{
			myStmt = sqlConnect.prepareStatement("delete from user where user_id=?");
			myStmt.setInt(1, userId);
			myStmt.executeUpdate();
		}
		finally{
			close(myStmt);
		}
	}
	public void deleteNotification (int notifId) throws SQLException {
		PreparedStatement myStmt = null;
		
		try{
			myStmt = sqlConnect.prepareStatement("delete from notifications where notif_id=?");
			myStmt.setInt(1, notifId);
			myStmt.executeUpdate();
		}
		finally{
			close(myStmt);
		}
	}
	public void addNotification (NotificationsList theNotificationList) throws Exception{
		PreparedStatement myStmt = null;
		
		try{
			
			myStmt = sqlConnect.prepareStatement("insert into notifications (start_date, finish_date, owner, "
					+ "contact, address, product, serial, accessories, sell_date, price, description, service, damages, "
					+ "recommendations, adder, employee, warranty, status) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			
			myStmt.setString(1, theNotificationList.getStartDate());
			myStmt.setString(2, theNotificationList.getFinishDate());
			myStmt.setString(3, theNotificationList.getOwner());
			myStmt.setString(4, theNotificationList.getContact());
			myStmt.setString(5, theNotificationList.getAddress());
			myStmt.setString(6, theNotificationList.getProduct());
			myStmt.setString(7, theNotificationList.getSerial());
			myStmt.setString(8, theNotificationList.getAccessories());
			myStmt.setString(9, theNotificationList.getSellDate());
			myStmt.setString(10, theNotificationList.getPrice());
			myStmt.setString(11, theNotificationList.getDescription());
			myStmt.setString(12, theNotificationList.getService());
			myStmt.setString(13, theNotificationList.getDamages());
			myStmt.setString(14, theNotificationList.getRecommendations());
			myStmt.setString(15, theNotificationList.getAdder());
			myStmt.setString(16, theNotificationList.getEmployee());
			myStmt.setString(17, theNotificationList.getWarranty());
			myStmt.setString(18, theNotificationList.getStatus());
			
			myStmt.executeUpdate();
			
		}
		finally {
			close(myStmt);
		}
	}
	
	public void setDone(int row, String date) throws SQLException{
		PreparedStatement myStmt = null;
		
		try{
			myStmt = sqlConnect.prepareStatement("update notifications set status='Zakoñczony', finish_date ='" + date + "' where notif_id=?");

			myStmt.setInt(1, row);
			
			myStmt.executeUpdate();
		}
		finally {
			close(myStmt);
		}
	}
	
	public void setUndone(int row) throws SQLException{
		PreparedStatement myStmt = null;
		
		try{
			myStmt = sqlConnect.prepareStatement("update notifications set status='Realizowany', finish_date='' where notif_id=?");

			myStmt.setInt(1, row);
			
			myStmt.executeUpdate();
		}
		finally {
			close(myStmt);
		}
	}
	
	private UsersList convertRowToUser(ResultSet myRs) throws SQLException {
		
		int userId = myRs.getInt("user_id");
		String firstName = myRs.getString("first_name");
		String lastName = myRs.getString("last_name");
		String permission = myRs.getString("permission");
		String email = myRs.getString("email");

		
		UsersList tempUser = new UsersList(userId, firstName, lastName, permission, email);
		
		return tempUser;
	}
	private UsersList convertRowToUserLogin(ResultSet myRs) throws SQLException {
		
		int userId = myRs.getInt("user_id");
		String firstName = myRs.getString("first_name");
		String lastName = myRs.getString("last_name");
		
		UsersList tempUser = new UsersList(userId, firstName, lastName);
		
		return tempUser;
	}
	private NotificationsList convertRowToNotification(ResultSet myRs) throws SQLException {
		
		int notifId = myRs.getInt("notif_id");
		String startDate = myRs.getString("start_date");
		String finishDate = myRs.getString("finish_date");
		String owner = myRs.getString("owner");
		String contact = myRs.getString("contact");
		String address = myRs.getString("address");
		String product = myRs.getString("product");
		String serial = myRs.getString("serial");
		String accessories = myRs.getString("accessories");
		String sellDate = myRs.getString("sell_date");
		String price = myRs.getString("price");
		String description = myRs.getString("description");
		String service = myRs.getString("service");
		String damages = myRs.getString("damages");
		String recommendations = myRs.getString("recommendations");
		String warranty = myRs.getString("warranty");
		String adder = myRs.getString("adder");
		String employee = myRs.getString("employee");
		String status = myRs.getString("status");
		
		NotificationsList tempNotification = new NotificationsList(notifId, startDate, finishDate, owner, contact, address, product, serial,
				accessories, sellDate, price, description, service, damages, recommendations, warranty, adder, employee, status);
		
		return tempNotification;
	}
	
//	private NotificationsList convertRowToFinishDate(ResultSet myRs) throws SQLException {
//		
//		int notifId = myRs.getInt("notif_id");
//		String finishDate = myRs.getString("finish_date");
//		
//		NotificationsList tempNotification = new NotificationsList(notifId, finishDate);
//		
//		return tempNotification;
//	}
//	private PermissionList convertRowToPermission(ResultSet myRs) throws SQLException {
//		
//		String permission = myRs.getString("status");
//		
//		PermissionList tempUser = new PermissionList(permission);
//		
//		return tempUser;
//	}
	
	public boolean authenticate(UsersList theUsersList) throws Exception {
		boolean result = false;
		
		String plainTextPassword = theUsersList.getPassword();
		
		// get the encrypted password from database for this user
		String encryptedPasswordFromDatabase = getEncrpytedPassword(theUsersList.getUserId());
		
		// compare the passwords
		result = PasswordUtils.checkPassword(plainTextPassword, encryptedPasswordFromDatabase);
//		result = true;
		return result;
	}
	
	public void changePassword(UsersList usersList) throws Exception {
		
		String plainTextPassword = usersList.getPassword();
		String encryptedPassword = PasswordUtils.encryptPassword(plainTextPassword);
		PreparedStatement myStmt = null;
		
		try{
			myStmt = sqlConnect.prepareStatement("update user set password=? where user_id=?");
			myStmt.setString(1, encryptedPassword);
			myStmt.setInt(2, usersList.getUserId());
			myStmt.executeUpdate();
		}
		finally{
			close(myStmt);
		}
	}
	
	private String getEncrpytedPassword(int id) throws Exception {
		String encryptedPassword = null;
			
		Statement myStmt = null;
		ResultSet myRs = null;
			
		try {
			myStmt = sqlConnect.createStatement();
			myRs = myStmt.executeQuery("select password from user where user_id=" + id);
				
			if (myRs.next()) {
				encryptedPassword = myRs.getString("password");
			}
			else {
				throw new Exception("User id not found: " + id);
			}

			return encryptedPassword;		
		}
		finally {
				close(myStmt, myRs);
		}		
	}	
	
	private static void close(Connection sqlConnection, Statement myStmt, ResultSet myRs)
			throws SQLException {

		if (myRs != null) {
			myRs.close();
		}

		if (myStmt != null) {
			
		}
		
		if (sqlConnection != null) {
			sqlConnection.close();
		}
	}

	private void close(Statement myStmt, ResultSet myRs) throws SQLException {
		close(null, myStmt, myRs);		
	}
	
	private void close(Statement myStmt) throws SQLException{
		close(null, myStmt, null);
	}
	
	public static void main(String[] args){
		try{
		ConnectionManager jaja = new ConnectionManager();
		String masi=jaja.toString();
		System.out.println(masi);
		UsersDAO test = new UsersDAO();
		test.getLoginName();
		System.out.println(test);
		
		} catch (Exception ow){
			ow.printStackTrace();
		}
	}
}
