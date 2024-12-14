package main;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {
	
	Clip clip;
	URL soundURL[] = new URL[30];
	FloatControl fc;
	int volumeScale = 3;
	float volume;
	
	public Sound() {
		
		soundURL[0] = getClass().getResource("/sound/Pirate.wav");
		soundURL[1] = getClass().getResource("/sound/coin.wav");
		soundURL[2] = getClass().getResource("/sound/powerup.wav");
		soundURL[3] = getClass().getResource("/sound/unlock.wav");
		soundURL[4] = getClass().getResource("/sound/fanfare.wav");
		soundURL[5] = getClass().getResource("/sound/hitmonster.wav");
		soundURL[6] = getClass().getResource("/sound/receivedamage.wav");
		soundURL[7] = getClass().getResource("/sound/swingweapon.wav");
		soundURL[8] = getClass().getResource("/sound/levelup.wav");
		soundURL[9] = getClass().getResource("/sound/cursor.wav");
		soundURL[10] = getClass().getResource("/sound/burning.wav");
		soundURL[11] = getClass().getResource("/sound/cuttree.wav");
		soundURL[12] = getClass().getResource("/sound/gameover.wav");
		soundURL[13] = getClass().getResource("/sound/stairs.wav");
		soundURL[14] = getClass().getResource("/sound/sleep.wav");
		soundURL[15] = getClass().getResource("/sound/blocked.wav");
		soundURL[16] = getClass().getResource("/sound/parry.wav");
		soundURL[17] = getClass().getResource("/sound/speak.wav");
		soundURL[18] = getClass().getResource("/sound/Merchant.wav");
		soundURL[19] = getClass().getResource("/sound/Dungeon.wav");
		soundURL[20] = getClass().getResource("/sound/chipwall.wav");
		soundURL[21] = getClass().getResource("/sound/dooropen.wav");
		soundURL[22] = getClass().getResource("/sound/FinalBattle.wav");


	}
	
	public void setFile(int i) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
			fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
			checkVolume();
		}catch(Exception e) {
		}
	}
	
	public void play() {
		clip.start();
	}
	
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void stop() {
		clip.stop();
	}
	public void checkVolume() {
		volume = switch(volumeScale) {
			case 0 -> -80f;
			case 1 -> -20f;
			case 2 -> -12f;
			case 3 -> -5f;
			case 4 -> 1f;
			case 5 -> 6f;
			default -> 0f;
		};
		fc.setValue(volume);
	}
}
