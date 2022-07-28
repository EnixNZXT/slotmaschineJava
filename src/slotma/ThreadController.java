package slotma;

import javax.swing.*;
import java.util.ArrayList;

public class ThreadController extends Thread{

    public static ArrayList<ArrayList<Symbol>> reelImages;
    private SlotMaschine slotMaschine;
    private static int index;

    public static int getIndex() {
        return index;
    }

    public void threadSpinMethod(){
        this.slotMaschine = new SlotMaschine();
        reelImages = new ArrayList<ArrayList<Symbol>>();

        for(int x = 0 ; x < 3 ; x++) {
            reelImages.add(slotMaschine.getReel(x));
        }

        ThreadController thread = new ThreadController();
        thread.start();


    }
    public void run() {
        while (GUI.flag) {
            GUI.reel1.setIcon(reelImages.get(0).get(index).getImage());
            GUI.reel2.setIcon(reelImages.get(1).get(index).getImage());
            GUI.reel3.setIcon(reelImages.get(2).get(index).getImage());

            try {
                Thread.sleep(60);
            } catch (InterruptedException e) {
                JOptionPane.showMessageDialog(null, e);
            }

            index++;
            if (index > 5) {
                index = 0;
            }
        }
    }
}
