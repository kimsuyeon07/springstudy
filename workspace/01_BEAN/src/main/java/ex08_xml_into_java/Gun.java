package ex08_xml_into_java;

public class Gun {

	// field
	private String model;
	private int bullet;
	
	// constructor
	public Gun() {}
	public Gun(String model, int bullet) {
		super();
		this.model = model;
		this.bullet = bullet;
	}
	
	
	// Getter and Setter
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getBullet() {
		return bullet;
	}
	public void setBullet(int bullet) {
		this.bullet = bullet;
	}
	
	
	
	
	
}
