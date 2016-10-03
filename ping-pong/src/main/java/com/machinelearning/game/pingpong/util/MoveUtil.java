package com.machinelearning.game.pingpong.util;

import com.machinelearning.game.pingpong.controller.PingPongDrawer;
import com.machinelearning.game.pingpong.model.PingPongContext;

/**
 * Created by zuhai.jiang on 2016/9/30.
 */
public class MoveUtil {

    public static double[] getRandomDirection(){
        double angle = Math.random()*120+30;
        double r = Math.toRadians(angle);
        double y=-Math.sin(r);
        double x=Math.cos(r);
        return new double[]{x, y};
    }

    public static int[] getNewPosition(PingPongContext ctx){
        int x= (int) (ctx.getSpeed()*ctx.getDirx()+ctx.getBallx());
        int y= (int) (ctx.getSpeed()*ctx.getDiry()+ctx.getBally());
        return new int[]{x, y};
    }

    public static int getIntersectX(int xb, int yb, int xa, int ya){
        int y= PingPongDrawer.RACKET_Y-PingPongDrawer.BALL_RADIUS;
        int xl= ((y-yb)*(xa-xb))/(ya-yb);
        return xb+xl;
    }
}
