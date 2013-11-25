package pattern;

public class User{

	private int id;

	private String address;

	private String email;

	private String name;

	private String phone;

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

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String telephone) {
		this.phone = telephone;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("id:" + id + "\n")
		.append("name:" + name + "\n")
		.append("address:" + address + "\n")
		.append("tel:" + phone + "\n")
		.append("email:" + email + "\n")
		.append("type:" + type + "\n");
		return sb.toString();
	}

}