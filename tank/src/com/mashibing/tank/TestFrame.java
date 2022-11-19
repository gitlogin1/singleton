package com.mashibing.tank;

import com.mashibing.tank.model.Buillter;
import com.mashibing.tank.model.Dir;
import com.mashibing.tank.model.Group;
import com.mashibing.tank.model.Tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class TestFrame extends Frame{
    public static final  TestFrame testFrame = new TestFrame();
    public final static int width = 800;
    public final static int height = 800;
    private Tank tank = new Tank(200, 200, Dir.DOWN, this, Group.GOOD);
    public List<Buillter> buillters = new ArrayList<>();

    public List<Tank> tanks = new ArrayList<>();
    public TestFrame(){
        setVisible(true);
        setSize(width, height);
        setResizable(false);
        setTitle("坦克大战");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        addKeyListener(new MyKeyListener());
    }

    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(width, height);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, width, height);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics graphics) {
//        List<Buillter> buillterList = tank.getBuillters();
        // 查看当前坦克数量和子弹数量
        Color c = graphics.getColor();
        graphics.setColor(Color.WHITE);
        graphics.drawString("bullets:" + buillters.size(), 10, 60);
        graphics.drawString("tanks:" + tanks.size(), 10, 80);
        graphics.setColor(c);

        tank.paint(graphics, this);
        for (int i = 0; i < buillters.size(); i++) {
            buillters.get(i).paint(graphics, testFrame);
        }
        for (int i = 0; i < tanks.size(); i++) {
            tanks.get(i).paint(graphics, testFrame);
        }
    }

    class MyKeyListener extends KeyAdapter{

        boolean up = false;
        boolean down = false;
        boolean left = false;
        boolean right = false;

        @Override
        public void keyPressed(KeyEvent e) {
            int code = e.getKeyCode();
            switch (code) {
                case KeyEvent.VK_UP:
                    up = true;
                    break;
                case KeyEvent.VK_DOWN:
                    down = true;
                    break;
                case KeyEvent.VK_LEFT:
                    left = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    right = true;
                    break;
                default:
                    break;
            }
            setDir();
        }

        private void setDir() {
            if (!up && !down && !left && !right) {
                tank.setMoving(false);
                return;
            }
            if (up) {
                tank.setDir(Dir.UP);
            }
            if (down) {
                tank.setDir(Dir.DOWN);
            }
            if (left) {
                tank.setDir(Dir.LEFT);
            }
            if (right) {
                tank.setDir(Dir.RIGHT);
            }
            tank.setMoving(true);
        }

        /**
         * Invoked when a key has been released.
         */
        @Override
        public void keyReleased(KeyEvent e) {
            int code = e.getKeyCode();
            switch (code) {
                case KeyEvent.VK_UP:
                    up = false;
                    break;
                case KeyEvent.VK_DOWN:
                    down = false;
                    break;
                case KeyEvent.VK_LEFT:
                    left = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    right = false;
                    break;
                case KeyEvent.VK_CONTROL:
                    tank.fire();
                default:
                    break;
            }
            setDir();
        }
    }
}
