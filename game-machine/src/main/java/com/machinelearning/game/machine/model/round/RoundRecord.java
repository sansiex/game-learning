package com.machinelearning.game.machine.model.round;

import com.machinelearning.game.machine.model.Record;

/**
 * Created by zuhai.jiang on 2016/10/4.
 */
public abstract class RoundRecord extends Record {

    protected int roundCount;

    public int getRoundCount() {
        return roundCount;
    }

    public void setRoundCount(int roundCount) {
        this.roundCount = roundCount;
    }

}
