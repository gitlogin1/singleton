package com.mashibing.tank.model;

import com.mashibing.tank.TestFrame;
import com.mashibing.tank.util.ResourceMgr;

import java.awt.*;
import java.util.Random;

public class Tank {
    private int width = 50;
    private int height = 50;
    private int x;
    private int y;
    private Dir dir;
    private int speed = 8;
    private boolean moving = true;
    private TestFrame testFrame;
    private Group group;
    private boolean isLiving = true;

    public boolean isLiving() {
        return isLiving;
    }

    public void setLiving(boolean living) {
        isLiving = living;
    }

    private Random random = new Random();
    // 坦克范围
    Rectangle rectangle = new Rectangle();

//    private List<Buillter> buillter = new ArrayList<>();
    public void setMoving(boolean moving) {
        this.moving = moving;
    }
    public void setDir(Dir dir) {
        this.dir = dir;
    }
    private int id = random.nextInt();

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Tank (int x, int y , Dir d, TestFrame testFrame, Group group){
        this.x = x;
        this.y = y;
        this.dir = d;
        this.testFrame = testFrame;
        this.group = group;
        if (group == Group.GOOD) {
            this.moving = false;
        }

        rectangle.y = this.y;
        rectangle.x = this.x;
        rectangle.width = ResourceMgr.tankuImg.getWidth();
        rectangle.height = ResourceMgr.tankuImg.getHeight();
    }

    public void paint(Graphics graphics, TestFrame testFrame) {
        if(!isLiving) this.testFrame.tanks.remove(this);
//        graphics.setColor(Color.yellow);
//        graphics.fillRect(this.x, this.y, width, height);
        switch (dir) {
            case UP:
//                graphics.drawImage(this.group == Group.GOOD ? ResourceMgr.myTank : ResourceMgr.tankuImg, x, y, null);
                graphics.drawImage(ResourceMgr.tankuImg, x, y, null);
                break;
            case DOWN:
                graphics.drawImage(ResourceMgr.tankdImg, x, y, null);
                break;
            case LEFT:
                graphics.drawImage(ResourceMgr.tanklImg, x, y, null);
                break;
            case RIGHT:
                graphics.drawImage(ResourceMgr.tankrImg, x, y, null);
                break;
            default:
                break;
        }
        move();
//        removeBuillter();
    }

    public void fire() {
        testFrame.buillters.add(new Buillter(this.x, this.y, this.dir, this.group));
    }

    private void move() {

        if (!moving) {
            return;
        }
        switch (dir) {
            case UP:
                y -= speed;
                break;
            case DOWN:
                y += speed;
                break;
            case LEFT:
                x -= speed;
                break;
            case RIGHT:
                x += speed;
                break;
            default:
                break;
        }
        this.rectangle.x = x;
        this.rectangle.y = y;
        if(this.group == Group.BAD && random.nextInt(100) > 95)
            this.fire();

        if(this.group == Group.BAD && random.nextInt(100) > 95)
            randomDir();
        checkBounds();
    }

    private void checkBounds() {
        if (this.x < 2) x = 2;
        if (this.y < 28) y = 28;
        if (this.x > (800 - 50 - 2)) x = 800 - 50 -2;
        if (this.y > (800 - 50 - 2)) y = 800 - 50 -2;
    }

    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }

    /*public void removeBuillter() {
        Iterator<Buillter> iterator = buillter.iterator();
        while (iterator.hasNext()){
            Buillter next = iterator.next();
            if (!next.isLiving()){
                iterator.remove();
            }
        }
    }*/

    public Dir getDir() {
        return dir;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void die() {
        this.isLiving = false;
    }

    /*public List<Buillter> getBuillters() {
        return buillter;
    }

    public void setBuillters(List<Buillter> buillters) {
        this.buillter = buillters;
    }*/
}
