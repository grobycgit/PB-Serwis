package PBServiceProgram;

public class NotificationsList {

	private int notifId;
	private String startDate;
	private String finishDate;
	private String owner;
	private String contact;
	private String address;
	private String product;
	private String serial;
	private String accessories;
	private String sellDate;
	private String price;
	private String description;
	private String service;
	private String damages;
	private String recommendations;
	private String warranty;
	private String adder;
	private String employee;
	private String status;

	public NotificationsList(int notifId, String finishDate){
		this.notifId = notifId;
		this.finishDate = finishDate;
	}
	
	public NotificationsList(String startDate, String finishDate, String owner, String contact, String address, String product,
			String serial, String accessories, String sellDate, String price, String description, String service, String damages, 
			String recommendations, String warranty, String adder, String employee, String status) {
		this(0, startDate, finishDate, owner, contact, address, product, serial, accessories, sellDate, price, description, 
				service, damages, recommendations,warranty, adder, employee, status);
	}

	public NotificationsList(int notifId, String startDate, String finishDate, String owner, String contact, String address, String product,
			String serial, String accessories, String sellDate, String price, String description, String service, String damages, 
			String recommendations,String warranty, String adder, String employee, String status) {

		super();
		this.notifId = notifId;
		this.startDate = startDate;
		this.finishDate = finishDate;
		this.owner = owner;
		this.contact = contact;
		this.address = address;
		this.product = product;
		this.serial = serial;
		this.accessories = accessories;
		this.sellDate = sellDate;
		this.price = price;
		this.description = description;
		this.service = service;
		this.damages = damages;
		this.recommendations = recommendations;
		this.warranty = warranty;
		this.adder = adder;
		this.employee = employee;
		this.status = status;
	}
	
	public int getNotifId() {
		return notifId;
	}

	public void setNotifId(int notifId) {
		this.notifId = notifId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getAccessories() {
		return accessories;
	}

	public void setAccessories(String accessories) {
		this.accessories = accessories;
	}

	public String getSellDate() {
		return sellDate;
	}

	public void setSellDate(String sellDate) {
		this.sellDate = sellDate;
	}
	
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}
	
	public String getDamages() {
		return damages;
	}

	public void setDamages(String damages) {
		this.damages = damages;
	}

	public String getRecommendations() {
		return recommendations;
	}

	public void setRecommendations(String recommendations) {
		this.recommendations = recommendations;
	}
	
	public String getAdder() {
		return adder;
	}

	public void setAdder(String adder) {
		this.adder = adder;
	}
	
	public String getEmployee() {
		return employee;
	}

	public void setEmployee(String employee) {
		this.employee = employee;
	}

	public String getWarranty() {
		return warranty;
	}

	public void setWarranty(String warranty) {
		this.warranty = warranty;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "NotificationsList [notifId=" + notifId + ", startDate=" + startDate + ", finishDate=" + finishDate
				+ ", owner=" + owner + ", contact=" + contact + ", address=" + address + "product=" + product + ", serial=" + serial
				+ ", accessories=" + accessories + ", sellDate=" + sellDate + ", price=" + price + ", description=" + description 
				+ ", service=" + service + ", damages=" + damages + ", recommendations=" + recommendations + ", warranty=" + warranty 
				+ ", adder=" + adder + ", employee=" + employee +  ", status=" + status + "]";
	}	
}
