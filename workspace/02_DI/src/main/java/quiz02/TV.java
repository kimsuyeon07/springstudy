package quiz02;

import org.springframework.beans.factory.annotation.Autowired;

public class TV {

	
	// field
	private int ch;  // 채널 ( 1 ~ 100번)
	private Speaker speaker;
	
	// Getter
	public Speaker getSpeaker() {
		return speaker;
	}
	// Setter
	@Autowired
	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}
	
	public int getCh() {
		return ch;
	}
	public void setCh(int ch) {
		this.ch = ch;
	}
	
	
	
	// method
	public void chUp() {
		if (ch < 100) {
			ch++;
		} else {
			ch = 1;
		}
		System.out.println("채널: " + ch);
	}
	public void chDown() {
		if (ch > 0) {
			ch--;
		} else {
			ch = 100;
		}
		System.out.println("채널: " + ch);
	}
		
	public void volumeUp() {
		speaker.volumeUp();
	}
	public void volumeDown() {
		speaker.volumeDown();
	}
	
	
	
	
	
	
}
