package com.machinelearning.game.machine.exception;

/**
 * Created by zuhai.jiang on 2016/10/2.
 */
public class GameException extends Exception {
    public GameException(String msg){
        super(msg);
    }

    public GameException(Throwable th){
        super(th);
    }

    public GameException(String msg, Throwable th){
        super(msg, th);
    }
}
