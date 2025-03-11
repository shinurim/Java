import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

public class BGM {
	public  BGM() {
		try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File("C:\\BGM.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(ais);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
