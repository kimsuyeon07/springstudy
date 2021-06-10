package ex05_java;

public class Singer {

	// field
	private String name;
	private Song song;

	
	// constructor
	public Singer() {}
	
	public Singer(String name, Song song) {
		super();
		this.name = name;
		this.song = song;
	}

	
	
	// method
	public void info() {
		System.out.println("가수명: "+ name);
		System.out.println("대표곡명: " + song.getTitle());
		System.out.println("장르: " + song.getGenre());
	}
	
	
	// Getter and Setter
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Song getSong() {
		return song;
	}

	public void setSong(Song song) {
		this.song = song;
	}
	
	
	
	
	
	
	
}
