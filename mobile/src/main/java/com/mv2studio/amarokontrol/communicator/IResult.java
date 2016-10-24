package com.mv2studio.amarokontrol.communicator;

/**
 * Created by matej on 22/10/2016.
 */

public interface IResult<T> {

    /**
     * Callback to deliver result of connector job. This can be delivered
     * either synchronously or asynchronously.
     * @param result
     */
    void onDeliverResult(T result);

}
