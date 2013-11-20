package pattern;

public class User{

	private int id;

	private String address;

	private String email;

	private String name;

	private int telphone;

	private String type;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTelphone() {
		return this.telphone;
	}

	public void setTelphone(int telphone) {
		this.telphone = telphone;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("id:" + id + "\n");
		sb.append("name:" + name + "\n");
		sb.append("address:" + address + "\n");
		sb.append("tel:" + telphone + "\n");
		sb.append("email:" + email + "\n");
		sb.append("type:" + type + "\n");
		return sb.toString();
	}

}