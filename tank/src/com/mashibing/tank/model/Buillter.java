package com.mashibing.tank.model;

import com.mashibing.tank.TestFrame;
import com.mashibing.tank.util.ResourceMgr;

import java.awt.*;

public class Buillter {
    private int x;
    private int y;
    private int SPEED = 15;
    private Dir dir;

    public boolean isLiving() {
        return living;
    }

    public void setLiving(boolean living) {
        this.living = living;
    }

    private boolean living = true;

    public Buillter(int x, int y, Dir dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public void paint(Graphics graphics, TestFrame testFrame) {
        if (!isLiving()) {
            testFrame.buillters.remove(this);
        }
//        graphics.setColor(Color.red);
//        graphics.drawOval(x, y, 10, 10);
//        graphics.drawImage(ResourceMgr.buillterdImg, x + 20, y + 20, null);
        switch(dir) {
            case LEFT:
                graphics.drawImage(ResourceMgr.buillterlImg, x, y + 20, null);
                break;
            case UP:
                graphics.drawImage(ResourceMgr.buillteruImg, x + 20, y, null);
                break;
            case RIGHT:
                graphics.drawImage(ResourceMgr.buillterrImg, x, y + 20, null);
                break;
            case DOWN:
                graphics.drawImage(ResourceMgr.buillterdImg, x + 20, y, null);
                break;
        }
        move(testFrame);
    }

    private void move(TestFrame frame) {
        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
        }
        if (x < 0 || y < 0 || x > frame.getWidth() || y > frame.getHeight()) {
            living = false;
        }
    }

    @Override
    public String toString() {
        return "Buillter{" +
                "x=" + x +
                ", y=" + y +
                ", SPEED=" + SPEED +
                ", dir=" + dir +
                ", living=" + living +
                '}';
    }
}
