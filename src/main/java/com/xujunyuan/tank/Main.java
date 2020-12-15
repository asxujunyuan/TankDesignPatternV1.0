package com.xujunyuan.tank;


import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tankFrame = new TankFrame();
        int iniTankCount = Integer.parseInt((String)PropertyMgr.get("initTankCount"));
        for (int i =0;i<iniTankCount;i++){
            tankFrame.tanks.add(new Tank(100+i*80,200,Dir.DOWN,Group.BAD,tankFrame));
    }
        while (true){
            Thread.sleep(50);
            tankFrame.repaint();
        }
    }
}
