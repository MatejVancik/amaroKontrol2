package com.mv2studio.amarokontrol.model;

/**
 * Created by matej on 22/10/2016.
 */

public class CurrentState {

    private Song song;

    /**
     * Playing state is represented as string in response so it can be any value. Mapping is done
     * via specific {@link com.mv2studio.amarokontrol.communicator.IConnector}
     */
    private String playingState;

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public String getPlayingState() {
        return playingState;
    }

    public void setPlayingState(String playingState) {
        this.playingState = playingState;
    }
}
