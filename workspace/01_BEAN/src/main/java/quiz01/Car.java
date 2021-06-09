package quiz01;

public class Car {

	private String model;
	private int price;
	
	public Car() {}
	public Car(String model, int price) {
		super();
		this.model = model;
		this.price = price;
	}
	
	// Getter and Setter
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	
	
	
}
