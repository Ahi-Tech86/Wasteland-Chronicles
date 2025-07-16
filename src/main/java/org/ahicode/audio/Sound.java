package org.ahicode.audio;

public interface Sound {
    void play(String soundId);
    void loop(String soundId);
    void stop();
}
