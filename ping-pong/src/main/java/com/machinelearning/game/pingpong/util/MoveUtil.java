package com.machinelearning.game.pingpong.util;

/**
 * Created by zuhai.jiang on 2016/9/30.
 */
public class MoveUtil {

    public static double[] getRandomDirection(){
        double angle = Math.random()*180;
        double y=-Math.sin(angle);
        double x=Math.cos(angle);
        return new double[]{x, y};
    }
}
