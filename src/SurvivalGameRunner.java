import java.util.Scanner;

public class SurvivalGameRunner {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        SurvivalGame player = new SurvivalGame(5,5,0,0,2);
        SurvivalGame landBeast = new SurvivalGame(5,2);
        SurvivalGame seaBeast = new SurvivalGame(5,2);
        player.generateDayHelpArrives();
        System.out.println();
        System.out.print("Welcome to a Survival Game! Your goal is to survive on a stranded island until help arrives which can take 7 to 14 days."
                + "\nTo survive you need to take care of your health, shelter, food, water, and mental state."
                + "\nIf your health (HP) or your mental state reaches 0 its GAME OVER."
                + "\nIf the number of days without food reaches 6 or the number of days without water reaches 3 its GAME OVER."
                + "\nTo start you are given " + player.getHP() + " HP" + ", " + player.getRest() + " \"mental state\"" + ", "
                + player.getFood() + " hunger" + " and " + player.getWater() + " thirst.\n");

        System.out.println("\nGOOD LUCK AND GAME START! \nYou wake up to find yourself on a desert island. " +
                "\nYou look around and notice that there is an abandoned hut to the north, the sea to the south, " +
                "coconut trees to the east, and signs of wild beasts to the west."
                + "\nYou gather information on each location and found out that: \n" +
                "\nThe hut can recover 1 HP and 1 mental state per visit." + "\nThe sea can alleviate two days without water per visit."
                + "\nThe trees can alleviate one day without water and food per visit." +
                "\nThe signs of wild beasts can alleviate two days without food per visit.\n");

        while ((player.getNumDays() <= player.dayHelpArrives) && player.isAlive()) {
            System.out.println("It's Day " + player.getNumDays() + "\nWhere do you wish to go? " + "Only hut, sea, trees, or beasts are appropriate responses."
            + "\nIf you wish to see the instruction manual again, please enter \"instruction manual\"");
            String playerChoice = s.nextLine();
            System.out.println(player.everyday(playerChoice));
            if(player.beastLocation.equals("beasts")){
               System.out.println(landBeast.beastAttack());
            }
            if (player.beastLocation.equals("sea")) {
                System.out.println(seaBeast.beastAttack());
            }
            if((player.getNumDays()-1 != 0)){
                System.out.println(player.toString());
            }
            System.out.println(player.gameOver());
        }
        if(player.isAlive()){
            System.out.println("\nCongratulations! You WIN! \nThe rescue team has arrived! You are free to leave!");
        }

    }
}
