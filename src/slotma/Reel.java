package slotma;

import javax.swing.*;
import java.util.*;

public class Reel {
    private ArrayList<Symbol> symbol= new ArrayList<Symbol>();

    public Reel(){
        Symbol pepe = new Symbol();
        pepe.setImage(new ImageIcon("src/slotma/cherry.png","pepe"));
        pepe.setValue(2);
        symbol.add(pepe);

        Symbol jack = new Symbol();
        jack.setImage(new ImageIcon("src/slotma/lemon.png","jack"));
        jack.setValue(3);
        symbol.add(jack);

        Symbol ox = new Symbol();
        ox.setImage(new ImageIcon("src/slotma/watermelon.png","ox"));
        ox.setValue(4);
        symbol.add(ox);

        Symbol pengu = new Symbol();
        pengu.setImage(new ImageIcon("src/slotma/bell.png","pengu"));
        pengu.setValue(5);
        symbol.add(pengu);

        Symbol bo = new Symbol();
        bo.setImage(new ImageIcon("src/slotma/redseven.png",""));
        bo.setValue(6);
        symbol.add(bo);

        Symbol lappentopf = new Symbol();
        lappentopf.setImage(new ImageIcon("src/slotma/plum.png","lappentopf"));
        lappentopf.setValue(7);
        symbol.add(lappentopf);

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
