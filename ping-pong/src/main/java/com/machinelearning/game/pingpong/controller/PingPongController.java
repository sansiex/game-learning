package com.machinelearning.game.pingpong.controller;

import com.machinelearning.game.machine.controller.VideoPlayer;
import com.machinelearning.game.machine.controller.round.RoundGameController;
import com.machinelearning.game.machine.action.PlayingAction;
import com.machinelearning.game.machine.model.Context;
import com.machinelearning.game.pingpong.model.PingPongContext;
import com.machinelearning.game.pingpong.model.PingPongRecord;
import com.machinelearning.game.pingpong.util.MoveUtil;

import java.awt.event.KeyEvent;
import java.util.List;

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

    public static final int LABEL_FAIL=0;
    public static final int LABEL_SUC=1;

    public static final int MOVE_NO=0;

    public PingPongController(PingPongCore core){
        super(core);
        actionMap.put(KeyEvent.VK_LEFT, new PlayingAction() {
            @Override
            public void doAction() {
                moveLeft();
            }
        });
        actionMap.put(KeyEvent.VK_RIGHT, new PlayingAction() {
            @Override
            public void doAction() {
                moveRight();
            }
        });
    }

    private void moveLeft(){
        PingPongContext ctx = (PingPongContext) context;
        int rx=ctx.getRacketX();
        if (rx<RACKET_SPEED) {
            ctx.setRacketX(0);
        } else {
            ctx.setRacketX(rx-RACKET_SPEED);
        }
        ctx.setMove(KeyEvent.VK_LEFT);
    }

    private void moveRight(){
        PingPongContext ctx = (PingPongContext) context;
        int rx=ctx.getRacketX();
        if (rx>PingPongDrawer.WIDTH-PingPongDrawer.RACKET_LENGTH-RACKET_SPEED) {
            ctx.setRacketX(PingPongDrawer.WIDTH-PingPongDrawer.RACKET_LENGTH);
        } else {
            ctx.setRacketX(rx+RACKET_SPEED);
        }
        ctx.setMove(KeyEvent.VK_RIGHT);
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
        context.setMove(0);
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
    protected void moveByVideo(String moveLine) {
        String[] arr=moveLine.split("\t");
        int move=Integer.parseInt(arr[6]);
        if (move == KeyEvent.VK_LEFT) {
            moveLeft();
        } else if (move == KeyEvent.VK_RIGHT) {
            moveRight();
        }
    }

    @Override
    protected void initByVideo(VideoPlayer player) {
        String line = player.readLine();
        PingPongRecord record = (PingPongRecord) getCore().getStorage().deserialize(line);
        context.update(record);
    }

    @Override
    protected void moveByAI() {

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
        boolean touchRacket=false;
        if (newP[1]>PingPongDrawer.RACKET_Y-r) {
            int ix = MoveUtil.getIntersectX(ctx.getBallx(), ctx.getBally(), newP[0], newP[1]);
            if (ix>=ctx.getRacketX() && ix<=ctx.getRacketX()+ctx.getRacketLen()) {
                newP[1]=2*(PingPongDrawer.RACKET_Y-PingPongDrawer.BALL_RADIUS)-newP[1];
                newD[1]= - newD[1];
                touchRacket=true;
            } else {
                fail = true;
            }

        }
        ctx.setBallx(newP[0]);
        ctx.setBally(newP[1]);
        ctx.setDirx(newD[0]);
        ctx.setDiry(newD[1]);
        PingPongStorage storage = (PingPongStorage) getCore().getStorage();
        if (fail) {
            ctx.setResult(RESULT_FAIL);
            storage.appendRecord(ctx);
            List<PingPongRecord> list = storage.getRecords();
            updateLabel(list, LABEL_FAIL);
            storage.persist();
            storage.clear();
            fail();
            return;
        }

        if (touchRacket) {
            storage.appendRecord(ctx);
            List<PingPongRecord> list = storage.getRecords();
            updateLabel(list, LABEL_SUC);
            storage.persist();
            storage.clear();
            return;
        }
        storage.appendRecord(ctx);
        ctx.setMove(MOVE_NO);
    }

    private void updateLabel(List<PingPongRecord> records, int label){
        for (PingPongRecord rec:records) {
            rec.setLabel(label);
        }
    }
}
