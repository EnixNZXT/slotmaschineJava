package slotma;

import javax.swing.*;

public class Symbol implements ISymbol {

    private ImageIcon image;
    private int value;


    @Override
    public void setImage(ImageIcon image) {
        this.image=image;
    }

    @Override
    public ImageIcon getImage() {
        return image;
    }

    @Override
    public void setValue(int value) {
        this.value=value;
    }

    @Override
    public int getValue() {
        return value;
    }
}
