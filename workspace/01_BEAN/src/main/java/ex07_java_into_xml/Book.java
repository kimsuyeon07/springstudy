package ex07_java_into_xml;

public class Book {

	// field
	private String title;
	private int price;
	private Publisher publisher;

	
	// constructor
	public Book() {}
	public Book(String title, int price, Publisher publisher) {
		super();
		this.title = title;
		this.price = price;
		this.publisher = publisher;
	}
	
	
	// method
	public void info() {
		System.out.println("책제목: " + title);
		System.out.println("가격: " + price);
		System.out.println("출판사명: " + publisher.getName());
		System.out.println("출판사위치: " + publisher.getLocation());
	}
	
	
	
	// Getter and Setter
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Publisher getPublisher() {
		return publisher;
	}
	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
