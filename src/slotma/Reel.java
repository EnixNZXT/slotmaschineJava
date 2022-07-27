package slotma;

import javax.swing.*;
import java.util.*;

public class Reel {
    private ArrayList<Symbol> symbol= new ArrayList<Symbol>();

    public Reel(){
        Symbol pepe = new Symbol();
        pepe.setImage(new ImageIcon("src/slotma/pepe.png","pepe"));
        pepe.setValue(2);
        symbol.add(pepe);

        Symbol jack = new Symbol();
        jack.setImage(new ImageIcon("src/slotma/jack.png","jack"));
        jack.setValue(3);
        symbol.add(jack);

        Symbol ox = new Symbol();
        ox.setImage(new ImageIcon("src/slotma/ox.png","ox"));
        ox.setValue(4);
        symbol.add(ox);
/*
        Symbol ox = new Symbol();
        ox.setImage(new ImageIcon("src/slotma/.png",""));
        ox.setValue(5);
        symbol.add(DEFAULT);

        Symbol ox = new Symbol();
        ox.setImage(new ImageIcon("src/slotma/.png",""));
        ox.setValue(6);
        symbol.add(DEFAULT);

        Symbol ox = new Symbol();
        ox.setImage(new ImageIcon("src/slotma/.png",""));
        ox.setValue(7);
        symbol.add(DEFAULT);
*/
        Collections.shuffle(symbol);
    }

    public void setSymbol(Symbol symbol) {
        if(symbol !=null){
         this.symbol.add(symbol);
        }
    }
    public ArrayList<Symbol> spin(){
        return symbol;
    }


}
