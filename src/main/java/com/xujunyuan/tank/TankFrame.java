package com.xujunyuan.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;


public class TankFrame extends Frame {

    Tank myTank = new Tank(200,400,Dir.DOWN,Group.GOOD,this);
    List<Bullet> bullets = new ArrayList<>();
    List<Tank> tanks = new ArrayList<>();
    List<Exploed> explodes = new ArrayList<>();

    static final int GAME_WIDTH = 1080;
    static final int GAME_HEIGHT = 960;

    public TankFrame(){
        setSize(GAME_WIDTH,GAME_HEIGHT);
        setResizable(false);
        setTitle("tank war");
        setVisible(true);

        this.addKeyListener(new MyKeyListener());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if(offScreenImage ==null){
            offScreenImage = this.createImage(GAME_WIDTH,GAME_HEIGHT);
        }
        Graphics goffScreen = offScreenImage.getGraphics();
        Color c = goffScreen.getColor();
        goffScreen.setColor(Color.black);
        goffScreen.fillRect(0,0,GAME_WIDTH,GAME_HEIGHT);
        goffScreen.setColor(c);
        paint(goffScreen);
        g.drawImage(offScreenImage,0,0,null);
    }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.white);
        g.drawString("子弹的数量:" + bullets.size(),10,60);
        g.drawString("地方坦克的数量:" + tanks.size(),10,80);
        g.drawString("爆炸的数量:" + explodes.size(),10,100);
        g.setColor(c);
        //画自己的坦克
        myTank.paint(g);
        //画坦克
        for (int i = 0; i < tanks.size(); i++) {
            tanks.get(i).paint(g);
        }
        //画子弹
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(g);
        }
        //爆炸
        for (int i = 0; i < explodes.size(); i++) {
            explodes.get(i).paint(g);
        }
        //碰撞
        for(int i =0;i<bullets.size();i++){
            for(int j =0;j<tanks.size();j++){
                bullets.get(i).collideWidth(tanks.get(j));
            }

        }

    }

    class MyKeyListener extends KeyAdapter{

        boolean bL = false;
        boolean bU = false;
        boolean bR = false;
        boolean bD = false;

        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode){
                case KeyEvent.VK_LEFT:
                    bL = true;
                break;
                case KeyEvent.VK_UP:
                    bU = true;
                break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                default:
                    break;
            }
            setMainTanDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode){
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                case KeyEvent.VK_CONTROL:
                    myTank.fire();
                default:
                    break;
            }
            setMainTanDir();
        }

        private void setMainTanDir(){
            if(!bL && !bU && !bD && !bR){
                myTank.setMoving(false);
            }
            else {
                myTank.setMoving(true);
                if(bL) myTank.setDir(Dir.LEFT);
                if(bU) myTank.setDir(Dir.UP);
                if(bR) myTank.setDir(Dir.RIGHT);
                if(bD) myTank.setDir(Dir.DOWN);
            }
        }

    }


}
