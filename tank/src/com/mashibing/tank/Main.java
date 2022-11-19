package com.mashibing.tank;

import com.mashibing.tank.model.Dir;
import com.mashibing.tank.model.Group;
import com.mashibing.tank.model.Tank;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TestFrame frame = TestFrame.testFrame;
        for (int i = 0; i < 5; i++) {
            frame.tanks.add(new Tank(100 + i * 50, 100, Dir.DOWN, frame, Group.BAD));
        }
        while (true) {
            Thread.sleep(50);
            frame.repaint();
        }
    }
}
