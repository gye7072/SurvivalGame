import java.util.Scanner;

public class SurvivalGameRunner {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        SurvivalGame player = new SurvivalGame(5,5,0,0);
        player.generateDayHelpArrives();
        System.out.println();
        System.out.print("Welcome to a Survival Game! Your goal is to survive on a stranded island until help arrives which can take 7 to 14 days."
                + "\nTo survive you need to take care of your health, shelter, food, water, and mental state."
                + "\nIf your health (HP) or your mental state reaches 0 you lose."
                + "\nIf the number of days without food reaches 6 you lose."
                + "\nIf the number of days without water reaches 3 you lose."
                + "\nTo start you are given " + player.getHP() + " HP" + ", " + player.getRest() + " \"mental state\"" + ", "
                + player.getFood() + " hunger" + " and " + player.getWater() + " thirst.\n");

        System.out.println("\nYou look around and notice that there is an abandoned hut to the north, the sea to the south, " +
                "coconut trees to the east, and signs of wild beasts to the west."
                + "\nThe hut can recover 1 HP and 1 mental state per visit." + "\nThe sea can alleviate two days without water per visit."
                + "\nThe trees can alleviate one day without water and food per visit." + "\nThe signs of wild beasts can alleviate two days without food per visit.\n");
        while ((player.getNumDays() <= player.dayHelpArrives) && player.isAlive()) {
            System.out.println("It's Day " + player.getNumDays() + "\nWhere do you wish to go? " + "Only hut, sea, trees, or beasts are appropriate responses.");
            String playerChoice = s.nextLine();
            System.out.println(player.everyday(playerChoice));
            System.out.println(player.status());
            System.out.println(player.gameOver());
        }
        if(player.isAlive()){
            System.out.println("The rescue team has arrived! You are free to leave!");
        }

    }
}
