package slotma;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

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
    public static boolean flag=true, reelClickable=true;
    private ArrayList<ArrayList<Symbol>> reelImages;
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
        contentPane=getContentPane();
        contentPane.add(mainPanel(),BorderLayout.CENTER);
        contentPane.add(titlePanel(),BorderLayout.NORTH);

        startup();
    }
    private void startup() {

        reelImages = new ArrayList<ArrayList<Symbol>>();
        for (int x = 0; x < 3; x++) {
            reelImages.add(slotMaschine.getReel(x));
        }
        reel1.setIcon(reelImages.get(0).get(index).getImage());
        reel2.setIcon(reelImages.get(1).get(index).getImage());
        reel3.setIcon(reelImages.get(2).get(index).getImage());
        spinButtonVisibility();
        reelClickable = false;
    }
    private void updateLabels(){
        if (slotMaschine.getRemCredit()<10){
            creditArea.setText("0"+slotMaschine.getRemCredit()+" ");
        }else{
            creditArea.setText(" "+slotMaschine.getRemCredit()+" ");
        }
        if (slotMaschine.getCurrentBet()<10){
            betArea.setText("0"+slotMaschine.getCurrentBet()+" ");
        }else{
            betArea.setText(" "+slotMaschine.getCurrentBet()+" ");
        }

    }

    private void addCoinBtnPressed(){
        slotMaschine.addCoin();
        updateLabels();
    }

    private void betOneBtnPressed(){
        try {
            slotMaschine.betOne();
            updateLabels();
            spinButtonVisibility();
        }catch(RuntimeException e){
            JOptionPane.showMessageDialog(null, "Not Enough Credits Available!!!");
        }
    }
    private void betMaxBtnPressed(){
        try {
            slotMaschine.betMax();
            updateLabels();
            spinButtonVisibility();
            betMaxBtn.setEnabled(false);
        }catch(RuntimeException e){
            JOptionPane.showMessageDialog(null, "Not Enough Credits Available!!!");
        }
    }

    private void resetBtnPressed(){
        slotMaschine.setRemCredit(slotMaschine.getRemCredit()+ slotMaschine.getCurrentBet());
        slotMaschine.setCurrentBet(0);
        updateLabels();
        spinButtonVisibility();
    }
    private void spinBtnPressed(){
        spinBtn.setEnabled(false);
        new ThreadController().threadSpinMethod();
        flag=true;
    }
    private void spinButtonVisibility(){
        if (slotMaschine.getCurrentBet()==0){
            spinBtn.setEnabled(false);
        }else{
            spinBtn.setEnabled(true);
        }
    }
    private void turnOffOtherButtonVisibility(){
        addCoinBtn.setEnabled(false);
        betOneBtn.setEnabled(false);
        betMaxBtn.setEnabled(false);
        resetBtn.setEnabled(false);
        statBtn.setEnabled(false);
    }
    private void turnOnOtherButtonsVisibility(){
        addCoinBtn.setEnabled(true);
        betOneBtn.setEnabled(true);
        betMaxBtn.setEnabled(true);
        resetBtn.setEnabled(true);
        statBtn.setEnabled(true);
    }

    private void calculateResult() {
        if(reelClickable) {
            if (reel1.getIcon().toString() == reel2.getIcon().toString()
                    && reel2.getIcon().toString() == reel3.getIcon().toString()
                    && reel1.getIcon().toString() == reel3.getIcon().toString()) {
                profit = ThreadController.reelImages.get(0).get(ThreadController.getIndex()).getValue() * slotMaschine.getCurrentBet();
                slotMaschine.setRemCredit(slotMaschine.getRemCredit() + ThreadController.reelImages.get(0).get(ThreadController.getIndex()).getValue() * slotMaschine.getCurrentBet());
                JOptionPane.showMessageDialog(null, "You've Earned: " + ThreadController.reelImages.get(0).get(ThreadController.getIndex()).getValue() * slotMaschine.getCurrentBet() + " Credits");
                slotMaschine.setCurrentBet(0);
                spinButtonVisibility();
                updateLabels();
                noOfGames++;
                wins++;
            } else if (reel1.getIcon().toString() == reel2.getIcon().toString()
                    || reel2.getIcon().toString() == reel3.getIcon().toString()
                    || reel1.getIcon().toString() == reel3.getIcon().toString()) {
                JOptionPane.showMessageDialog(null, "You've Earned a Free Spin!!!!!");

            } else {
                JOptionPane.showMessageDialog(null, "You've Lost!!!!!");
                loss = slotMaschine.getCurrentBet();
                slotMaschine.setCurrentBet(0);
                spinButtonVisibility();
                updateLabels();
                losses++;
                noOfGames++;
            }

        }
        statValue+=profit-loss;
        average=statValue/noOfGames;
        if(noOfGames==0){
            average=0;
        }
        profit=0;
        loss=0;
        reelClickable = false;
    }

    private JComponent titlePanel(){
        JPanel titlePanel= new JPanel();
        title= new JLabel();
        title.setText("Slotma Oxo");
        titlePanel.setFont(new Font("Arial", Font.PLAIN,72));
        setForeground(cyan);
        titlePanel.add(title);
        titlePanel.setBackground(Color.black);
        return  titlePanel;
    }

    private JComponent mainPanel(){
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(lila);
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints= new GridBagConstraints();
        constraints.insets=new Insets(5,5,20,20);

        creditAreaTitle=new JLabel();
        creditAreaTitle.setText("Credit Area");
        constraints.gridx=0;
        constraints.gridy=0;
        mainPanel.add(creditAreaTitle,constraints);

        creditArea=new JLabel();
        if(slotMaschine.getRemCredit()<10){
            creditArea.setText(" 0" + slotMaschine.getRemCredit() + " ");
        } else {
            creditArea.setText(" " + slotMaschine.getRemCredit() + " ");
        }
        creditArea.setForeground(Color.red);
        creditArea.setBackground(Color.black);
        creditArea.setBorder(BorderFactory.createLineBorder(Color.RED));
        creditArea.getPreferredSize().setSize(20,20);
        creditArea.setOpaque(true);
        constraints.gridx = 0;
        constraints.gridy = 1;
        mainPanel.add(creditArea, constraints);

        betAreaTitle = new JLabel();
        betAreaTitle.setText("Bet Area");
        constraints.gridx = 0;
        constraints.gridy = 2;
        mainPanel.add(betAreaTitle, constraints);

        betArea = new JLabel();
        if(slotMaschine.getCurrentBet()<10){
            betArea.setText(" 0" + slotMaschine.getCurrentBet() + " ");
        } else {
            betArea.setText(" " + slotMaschine.getCurrentBet() + " ");
        }
        betArea.setForeground(Color.red);
        betArea.setBackground(Color.black);
        betArea.setBorder(BorderFactory.createLineBorder(Color.RED));
        betArea.getPreferredSize().setSize(20,20);
        betArea.setOpaque(true);
        constraints.gridx = 0;
        constraints.gridy = 3;
        mainPanel.add(betArea, constraints);

        reel1 = new JLabel();
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridheight = 7;
        constraints.fill = GridBagConstraints.VERTICAL;
        mainPanel.add(reel1, constraints);

        reel2 = new JLabel();
        constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.gridheight = 7;
        constraints.fill = GridBagConstraints.VERTICAL;
        mainPanel.add(reel2, constraints);

        reel3 = new JLabel();
        constraints.gridx = 3;
        constraints.gridy =0;
        constraints.gridheight = 7;
        constraints.fill = GridBagConstraints.VERTICAL;
        mainPanel.add(reel3, constraints);

        constraints.gridheight=1;
        spinBtn=new JButton("Spin");
        spinBtn.setBackground(cyan);
        constraints.gridx=1;
        constraints.gridy=7;
        constraints.gridwidth=3;
        constraints.fill=GridBagConstraints.HORIZONTAL;
        mainPanel.add(spinBtn,constraints);

        constraints.gridwidth = 1;
        addCoinBtn = new JButton("Add Coin");
        addCoinBtn.setBackground(cyan);
        constraints.gridx = 4;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(addCoinBtn, constraints);

        betOneBtn = new JButton("Bet One");
        betOneBtn.setBackground(cyan);
        constraints.gridx = 4;
        constraints.gridy = 1;
        mainPanel.add(betOneBtn, constraints);

        betMaxBtn = new JButton("Bet Max");
        betMaxBtn.setBackground(cyan);
        constraints.gridx = 4;
        constraints.gridy = 2;
        mainPanel.add(betMaxBtn, constraints);

        resetBtn = new JButton("Reset");
        resetBtn.setBackground(cyan);
        constraints.gridx = 4;
        constraints.gridy = 3;
        mainPanel.add(resetBtn, constraints);

        statBtn = new JButton("Statistics");
        statBtn.setBackground(cyan);
        constraints.gridx = 4;
        constraints.gridy = 4;
        mainPanel.add(statBtn, constraints);

        reel1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                flag=false;
                spinButtonVisibility();
                turnOnOtherButtonsVisibility();
                calculateResult();
            }
        });

        reel2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                flag=false;
                spinButtonVisibility();
                turnOnOtherButtonsVisibility();
                calculateResult();
            }
        });

        reel3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                flag=false;
                spinButtonVisibility();
                turnOnOtherButtonsVisibility();
                calculateResult();
            }
        });

        addCoinBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCoinBtnPressed();
            }
        });

        betOneBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                betOneBtnPressed();
            }
        });
        betMaxBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                betMaxBtnPressed();
            }
        });

        resetBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                slotMaschine.setRemCredit(10);
                noOfGames=0;
                wins=0;
                loss=0;
                resetBtnPressed();
            }
        });

        spinBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                spinButtonVisibility();
                turnOffOtherButtonVisibility();
                spinBtnPressed();
                reelClickable=true;
            }
        });

        return mainPanel;
    }
    public static void main(String[] args) {
      SlotMaschine slotMaschine=new SlotMaschine();
      GUI gui=new GUI(slotMaschine);
      gui.setTitle("Slotma Oxo");
      gui.setSize(1024,768);
      gui.setDefaultCloseOperation(EXIT_ON_CLOSE);
      gui.setLocationRelativeTo(null);
      gui.setResizable(true);
      gui.setVisible(true);
    }
}

