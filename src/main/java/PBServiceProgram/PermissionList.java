package PBServiceProgram;

public class PermissionList {

	private String permission;
	
	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public PermissionList(String permission){
		super();
		
		this.permission = permission;
	}
	
	public String toString(){
		
		return permission;
	}
}
