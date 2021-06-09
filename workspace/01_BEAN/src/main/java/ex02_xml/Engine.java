package ex02_xml;

public class Engine {

	
	private String type;
	private int cc;
	private int hp;
	private double efficiency;
	
	// constructor
	public Engine() {}

	// field (property)
	public Engine(String type, int cc, int hp, double efficiency) {
		super();
		this.type = type;
		this.cc = cc;
		this.hp = hp;
		this.efficiency = efficiency;
	}
	
	// method
	public void info() {
		System.out.println("엔진타입: " + type);
		System.out.println("배기량: " + cc + "cc");
		System.out.println("마력: " + hp + "hp");
		System.out.println("연비: " + efficiency + "km/l");
	}
	
	
	
	// Getter and Setter
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getCc() {
		return cc;
	}

	public void setCc(int cc) {
		this.cc = cc;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public double getEfficiency() {
		return efficiency;
	}

	public void setEfficiency(double efficiency) {
		this.efficiency = efficiency;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
