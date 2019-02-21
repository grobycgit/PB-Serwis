package PBServiceProgram;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table (name = "Users")
public class UsersList {

	@Id
	@Column (name = "USER_ID")
	private int userId;
	@Column (name = "FIRST_NAME")
	private String firstName;
	@Column (name = "LAST_NAME")
	private String lastName;
	@Column (name = "PASSWORD")
	private String password;
	@Column (name = "PERMISSION")
	private String permission;
	@Column (name = "EMAIL")
	private String email;
	
	public UsersList(){
		super();
	}
	
	public UsersList(int userId){
		super();

		this.userId = userId;
	}
	
	public UsersList(String firstName, String lastName){
		this.firstName = firstName;
		this.lastName = lastName;
	}
	// constructor for login
	public UsersList(int userId, String firstName, String lastName){
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public UsersList(String firstName, String lastName, String permission, String email){
		this(0, firstName, lastName, permission, email);
	}
	// constructor for show in user settings
	public UsersList(int userId, String firstName, String lastName, String permission, String email){
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.permission = permission;
		this.email = email;
	}
	
	public void addToList(){
		
	}
	public int getUserId(){
		return userId;
	}
	public void setUserId(int userId){
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String toString(){
		
		return firstName + " " +lastName;
	}
}
