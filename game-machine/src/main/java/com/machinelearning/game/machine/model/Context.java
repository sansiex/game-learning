package com.machinelearning.game.machine.model;

/**
 * Created by zuhai.jiang on 2016/10/2.
 */
public abstract class Context {
    abstract public Record toRecord();
    abstract public void update(Record record);
}
