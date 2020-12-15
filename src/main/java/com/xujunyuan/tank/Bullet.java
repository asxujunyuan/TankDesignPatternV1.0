package com.xujunyuan.tank;

import java.awt.*;

public class Bullet {
    private static final int SPEED = 10;
    public static final int WIDTH = ResourceMgr.bulletD.getWidth();
    public  static final int HEIGHT = ResourceMgr.bulletD.getHeight();

    Rectangle rect = new Rectangle();

    int x,y;
    private Dir dir;
    private boolean living = true;
    TankFrame tf = null;
    private Group group = Group.BAD;

    public Bullet(int x, int y, Dir dir,Group group,TankFrame tf) {
        this.x = x;
        this.y = y;
        this.group = group;
        this.dir = dir;
        this.tf = tf;
        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
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

    public void paint(Graphics g) {
        if(!living){
            tf.bullets.remove(this);
        }

        switch (dir){
            case LEFT: g.drawImage(ResourceMgr.bulletL,x,y,null);
                break;
            case UP: g.drawImage(ResourceMgr.bulletU,x,y,null);
                break;
            case DOWN: g.drawImage(ResourceMgr.bulletD,x,y,null);
                break;
            case RIGHT: g.drawImage(ResourceMgr.bulletR,x,y,null);
                break;
        }

        move();

    }

    private void move() {
        switch (dir){
            case LEFT:
                x-=SPEED;
                break;
            case UP:
                y-=SPEED;
                break;
            case DOWN:
                y+=SPEED;
                break;
            case RIGHT:
                x+=SPEED;
                break;
        }
        //UPDATE rect
        rect.x = this.x;
        rect.y = this.y;
        if(x<0 || y<0 || x>TankFrame.GAME_WIDTH || y> TankFrame.GAME_HEIGHT) living =false;
    }

    public void collideWidth(Tank tank) {
        if(this.group == tank.getGroup()) return;

        if(rect.intersects(tank.rect)){
            tank.die();
            this.die();
            int eX = tank.getX() + Tank.WIDTH/2 - Exploed.WIDTH/2;
            int eY = tank.getY() + Tank.HEIGHT/2 - Exploed.HEIGHT/2;
            tf.explodes.add(new Exploed(eX,eY,tf));
        }
    }

    private void die() {
        this.living = false;
    }
}
