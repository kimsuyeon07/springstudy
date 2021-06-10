package ex05_java;

public class Song {

	
	// field (property)
	private String title;
	private String genre;
	
	// constructor
	public Song() {}

	public Song(String title, String genre) {
		super();
		this.title = title;
		this.genre = genre;
	}

	
	// method
	public void info() {
		
	}
	
	
	// Getter and Setter
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
