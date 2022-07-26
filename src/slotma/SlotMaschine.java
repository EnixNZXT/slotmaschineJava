package slotma;
import java.util.*;

public class SlotMaschine {
    public static void main(String[] args) {
        Random generator=new Random();
        Scanner terminal= new Scanner(System.in);

        int input;
        int token=100;
        int slot1,slot2,slot3;

        System.out.println("Slot Maschine");
        System.out.println("Token: "+ token);
        System.out.println("press1 to play, 2 to quit");

        do{
            input = terminal.nextInt();

            slot1 = generator.nextInt(6);
            slot2 = generator.nextInt(6);
            slot3 = generator.nextInt(6);

            switch (slot1)
            {
                case 0:
                    System.out.print("Cherry ");
                    break;
                case 1:
                    System.out.print("Orange ");
                    break;
                case 2:
                    System.out.print("Plum ");
                    break;
                case 3:
                    System.out.print("Bell ");
                    break;
                case 4:
                    System.out.print("Melon ");
                    break;
                default:
                    System.out.print("Bar ");
            }

            switch (slot2)
            {
                case 0:
                    System.out.print("Cherry ");
                    break;
                case 1:
                    System.out.print("Orange ");
                    break;
                case 2:
                    System.out.print("Plum ");
                    break;
                case 3:
                    System.out.print("Bell ");
                    break;
                case 4:
                    System.out.print("Melon ");
                    break;
                default:
                    System.out.print("Bar ");
            }

            switch (slot3)
            {
                case 0:
                    System.out.println("Cherry");
                    break;
                case 1:
                    System.out.println("Orange");
                    break;
                case 2:
                    System.out.println("Plum");
                    break;
                case 3:
                    System.out.println("Bell");
                    break;
                case 4:
                    System.out.println("Melon");
                    break;
                default:
                    System.out.println("Bar");
            }

            if (slot1!= slot2 && slot1!=slot3 && slot2!=slot3) {
            System.out.println("You lost");
            token-=2;
            System.out.println("Token: "+ token);
            } else if (slot1==slot2|| slot1==slot3 || slot2==slot3){
                System.out.println("Congratulations, you have won");
                token+=2;
                System.out.println("Token: "+ token);
            } else if (slot1==slot2 && slot1==slot3 && slot1!=0) {
                System.out.println("Congratulations, you won a double");
                token+=4;
                System.out.println("Token: "+ token);
            } else if (slot1==0 && slot2==0 && slot3==0) {
                System.out.println("Congratulations! You have won the jackpot");
                token+=100;
            }
        }while (input==1);
    }

}
