package slotma;
import java.util.*;

public class SlotMaschine {
    private int  remCredit = 10;
    private int currentBet  = 0;
    private Reel[] reel = new Reel[3];


    public SlotMaschine(){
        for (int i=0; i<3;i++){
            reel[i]=new Reel();
        }
    }
    public int getRemCredit() {
        return remCredit;
    }
    public void setRemCredit(int remCredit) {
        this.remCredit = remCredit;
    }
    public int getCurrentBet() {
        return currentBet;
    }
    public void setCurrentBet(int currentBet) {
        this.currentBet = currentBet;
    }
    public ArrayList<Symbol> getReel(int reelNo){
        return reel[reelNo].spin();
    }
    public void addCoin(){
        remCredit+=1;
    }
    public void betOne(){
        if (remCredit>0){
            currentBet+=1;
            remCredit-=1;
        }else{
            throw new RuntimeException("Not enough Credits");
        }
    }
    public void betMax(){
        if (remCredit>2){
            currentBet+=3;
            remCredit-=3;
        }else{
            throw new RuntimeException("Not enough Credits");
        }
    }
}