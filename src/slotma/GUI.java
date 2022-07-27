package slotma;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {

    Color lila =new Color(43,3,72);
    Color rot = new Color(233,13,33);
    Color cyan= new Color(13,255,255);
    Color underline = new Color(133,49,125);
    Color offblue = new Color(8,168,255);
    private JButton spinBtn, addCoinBtn, betOneBtn, betMaxBtn, resetBtn, statBtn;
    private static JLabel title, creditArea, betArea,  creditAreaTitle, betAreaTitle;
    public static JLabel reel1, reel2,reel3;
    private Container contentPane;
    private SlotMaschine slotMaschine;
    private static int index, wins, losses, noOfGames;
    private int profit, loss;
    private static double statValue, average;


    public static int getWins() {
        return wins;
    }
    public static int getLosses() {
        return losses;
    }
    public static double getAverage() {
        return average;
    }

    public GUI(SlotMaschine slotMaschine){
        this.slotMaschine=slotMaschine;
        setTitle("Slotma Oxo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        contentPane=getContentPane();
        contentPane.add(mainPanel(),BorderLayout.CENTER);
    }

    private JComponent mainPanel(){
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(lila);
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints= new GridBagConstraints();
        constraints.insets=new Insets(5,5,20,20);



        return mainPanel;




    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                SlotMaschine slotMaschine=new SlotMaschine();
                GUI gui=new GUI(slotMaschine);
                gui.setVisible(true);
            }
        });

    }
}

