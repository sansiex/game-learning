package com.machinelearning.game.pingpong.controller;

import com.machinelearning.game.machine.controller.round.RoundGameController;
import com.machinelearning.game.machine.action.PlayingAction;
import com.machinelearning.game.machine.model.Context;
import com.machinelearning.game.pingpong.model.PingPongContext;
import com.machinelearning.game.pingpong.util.MoveUtil;

import java.awt.event.KeyEvent;

/**
 * Created by zuhai.jiang on 2016/10/2.
 */
public class PingPongController extends RoundGameController {
    public static final int RACKET_SPEED=30;
    public static final int RESULT_UNKNOWN=0;
    public static final int RESULT_SUCCEED=1;
    public static final int RESULT_FAIL=2;
    public static final int MOVE_INTERVAL = 100;
    public static final int DEFAULT_SPEED=20;

    public PingPongController(PingPongCore core){
        super(core);
        actionMap.put(KeyEvent.VK_LEFT, new PlayingAction() {
            @Override
            public void doAction() {
                PingPongContext ctx = (PingPongContext) context;
                int rx=ctx.getRacketX();
                if (rx<RACKET_SPEED) {
                    ctx.setRacketX(0);
                } else {
                    ctx.setRacketX(rx-RACKET_SPEED);
                }
            }
        });
        actionMap.put(KeyEvent.VK_RIGHT, new PlayingAction() {
            @Override
            public void doAction() {
                PingPongContext ctx = (PingPongContext) context;
                int rx=ctx.getRacketX();
                if (rx>PingPongDrawer.WIDTH-PingPongDrawer.RACKET_LENGTH-RACKET_SPEED) {
                    ctx.setRacketX(PingPongDrawer.WIDTH-PingPongDrawer.RACKET_LENGTH);
                } else {
                    ctx.setRacketX(rx+RACKET_SPEED);
                }
            }
        });
    }


    @Override
    public long getDefaultMoveInterval() {
        return MOVE_INTERVAL;
    }

    @Override
    protected Context initContext() {
        PingPongContext context=new PingPongContext();
        context.setBallx(PingPongDrawer.WIDTH / 2);
        context.setBally(PingPongDrawer.RACKET_Y - PingPongDrawer.BALL_RADIUS);
        double[] direction= MoveUtil.getRandomDirection();
        context.setDirx(direction[0]);
        context.setDiry(direction[1]);
        context.setSpeed(DEFAULT_SPEED);
        context.setRacketLen(PingPongDrawer.RACKET_LENGTH);
        context.setRacketX(PingPongDrawer.WIDTH / 2 - PingPongDrawer.RACKET_LENGTH / 2);
        return context;
    }

    @Override
    public void pause() {

    }

    @Override
    public void fail(){
        end();
    }

    @Override
    public void beforeRoundStart() {
        PingPongContext ctx = (PingPongContext) context;
        int[] newP = MoveUtil.getNewPosition(ctx);
        double[] newD = new double[]{ctx.getDirx(), ctx.getDiry()};
        int r=PingPongDrawer.BALL_RADIUS;
        if (newP[0]<r) {
            newP[0]= 2*r-newP[0];
            newD[0]= - newD[0];
        }

        if (newP[0]>PingPongDrawer.WIDTH-r) {
            newP[0]=2*(PingPongDrawer.WIDTH-r)-newP[0];
            newD[0]= - newD[0];
        }

        if (newP[1]<r) {
            newP[1]=2*r-newP[1];
            newD[1]= - newD[1];
        }

        boolean fail=false;
        if (newP[1]>PingPongDrawer.RACKET_Y-r) {
            int ix = MoveUtil.getIntersectX(ctx.getBallx(), ctx.getBally(), newP[0], newP[1]);
            if (ix>=ctx.getRacketX() && ix<=ctx.getRacketX()+ctx.getRacketLen()) {
                newP[1]=2*(PingPongDrawer.RACKET_Y-PingPongDrawer.BALL_RADIUS)-newP[1];
                newD[1]= - newD[1];
            } else {
                fail = true;
            }

        }
        ctx.setBallx(newP[0]);
        ctx.setBally(newP[1]);
        ctx.setDirx(newD[0]);
        ctx.setDiry(newD[1]);
        if (fail) {
            ctx.setResult(RESULT_FAIL);
            fail();
        }
    }
}
