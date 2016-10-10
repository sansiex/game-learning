package com.machinelearning.game.machine.model.round;

import com.machinelearning.game.machine.model.Context;

/**
 * Created by zuhai.jiang on 2016/10/4.
 */
public abstract class RoundContext extends Context {

    protected int roundCount;

    public int getRoundCount() {
        return roundCount;
    }

    public void setRoundCount(int roundCount) {
        this.roundCount = roundCount;
    }
}
