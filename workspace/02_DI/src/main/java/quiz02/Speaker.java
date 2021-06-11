package quiz02;

public class Speaker {

	// field
	private int volume;  // 0~100
	
	
	// method
	public void volumeUp() {
		if (volume < 100) {
			volume ++;
			System.out.println("현재 볼륨: " + volume);
		} else {
			System.out.println("현재 볼륨: " + volume +" (소리를 최대로 키웠습니다.)");
		}
		
	}
	
	public void volumeDown() {
		if (volume > 0) {
			volume --;
			if (volume == 0) {
				System.out.println("현재 볼륨: " + volume +" (음소거되었습니다.)");
			} else {
				System.out.println("현재 볼륨: " + volume);
			}
		} else {
			System.out.println("현재 볼륨: " + volume +" (음소거되었습니다.)");
		}
	}

	
	// Getter and Setter
	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}
			
	
	
	
	
	
}
