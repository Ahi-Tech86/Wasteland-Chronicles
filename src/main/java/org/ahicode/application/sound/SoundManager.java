package org.ahicode.application.sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class SoundManager {
    Clip clip;
    URL[] soundUrl = new URL[30];

    public SoundManager() {
        soundUrl[0] = getClass().getResource("/sound/shaken_bush_type1.wav");
        soundUrl[1] = getClass().getResource("/sound/shaken_bush_type2.wav");
    }

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundUrl[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void play() {
        clip.start();
    }

    public void stop() {
        clip.stop();
    }
}
