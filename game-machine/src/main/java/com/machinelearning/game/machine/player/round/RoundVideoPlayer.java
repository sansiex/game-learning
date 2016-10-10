package com.machinelearning.game.machine.player.round;

import com.machinelearning.game.machine.controller.GameController;
import com.machinelearning.game.machine.controller.Status;
import com.machinelearning.game.machine.controller.VideoReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by zuhai.jiang on 2016/10/4.
 */
public abstract class RoundVideoPlayer extends RoundPlayer {

    protected VideoReader reader;

    public RoundVideoPlayer(GameController controller) {
        super(controller);
        reader = new VideoReader();
    }

    public String readLine(){
        return reader.readLine();
    }

    public void playFile(File file) throws FileNotFoundException {
        reader.play(file);
    }

    @Override
    public String getName() {
        return "Video";
    }

    @Override
    protected void move() {
        String line = readLine();
        if (line == null) {
            if (controller.getStatus() == Status.PAUSED) {
                controller.end();
            }
        } else {
            moveByVideo(line);
        }
    }

    abstract protected void moveByVideo(String line);

    @Override
    public void close() throws IOException {
        reader.close();
    }
}
