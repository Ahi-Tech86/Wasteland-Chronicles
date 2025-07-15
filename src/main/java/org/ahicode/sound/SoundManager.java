package org.ahicode.sound;

import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class SoundManager implements Sound {

    private final Map<String, Clip> soundClips = new HashMap<>();
    private Clip currentClip;

    public SoundManager() {
        loadSounds();
    }

    @Override
    public void play(String soundId) {
        currentClip = soundClips.get(soundId);

        if (currentClip != null) {
            currentClip.setFramePosition(0);
            currentClip.start();
        }
    }

    @Override
    public void loop(String soundId) {
        stopCurrentSound();
        currentClip = soundClips.get(soundId);

        if (currentClip != null) {
            currentClip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    @Override
    public void stop() {
        stopCurrentSound();
    }

    private void stopCurrentSound() {
        if (currentClip != null && currentClip.isRunning()) {
            currentClip.stop();
        }
    }

    private void loadSounds() {
        Map<String, String> soundResources = Map.of(
                "bush1", "/sound/shaken_bush_type1.wav",
                "bush2", "/sound/shaken_bush_type2.wav"
        );

        for (Map.Entry<String, String> entry : soundResources.entrySet()) {
            String soundId = entry.getKey();
            String soundResourcePath = entry.getValue();

            try (
                    InputStream inputStream = getClass().getResourceAsStream(soundResourcePath);
                    AudioInputStream audioStream = AudioSystem.getAudioInputStream(inputStream)
            ) {
                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);
                soundClips.put(soundId, clip);
            } catch (UnsupportedAudioFileException | IOException e) {
                throw new RuntimeException("Failed to load sound: " + soundResourcePath, e);
            } catch (LineUnavailableException e) {
                throw new RuntimeException("Audio line unavailable for sound: " + soundResourcePath, e);
            }
        }
    }

    private void unloadSound(String soundId) {
        Clip clip = soundClips.get(soundId);

        if (clip != null) {
            clip.close();
            soundClips.remove(soundId);
        }
    }
}
