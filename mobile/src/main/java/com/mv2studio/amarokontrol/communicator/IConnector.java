package com.mv2studio.amarokontrol.communicator;

import com.mv2studio.amarokontrol.model.CurrentState;
import com.mv2studio.amarokontrol.model.PlayingState;
import com.mv2studio.amarokontrol.model.Playlist;

/**
 * Created by matej on 22/10/2016.
 */

public interface IConnector {

    /**
     * Returns {@link com.mv2studio.amarokontrol.model.CurrentState}.
     */
    void getPlayNowState(IResult<CurrentState> callback);

    /**
     * Returns current playlist represented by {@link com.mv2studio.amarokontrol.model.Playlist}
     */
    void getCurrentPlaylist(IResult<Playlist> callback);

    /**
     * Returns album cover in given width by given song id.
     * @param width
     * @param songId
     */
    void getAlbumCoverBySongId(int width, String songId);

    void playPause(IResult callback);

    void next(IResult callback);

    void prev(IResult callback);

    void stop(IResult callback);

    void volDown(IResult callback);

    void volUp(IResult callback);

    void mute(IResult callback);

    /**
     * Starts playing song by given {@link com.mv2studio.amarokontrol.model.Song#id}.
     * @param id Song ID
     */
    void playById(String id, IResult callback);

    /**
     * Removes song from current playlist by given {@link com.mv2studio.amarokontrol.model.Song#id}.
     * @param id Song ID
     */
    void removeById(String id, IResult callback);

    /**
     * Seeks to specific second in currently playing song
     * @param second
     */
    void seekTo(int second, IResult callback);

    /**
     * Adds song specified by id into current playlist.
     * @param id
     */
    void enqueueSong(String id, IResult callback);

    /**
     * Clears current playlist.
     */
    void clearCurrentPlaylist(IResult callback);

    PlayingState resolvePlayingState(String state);
}
