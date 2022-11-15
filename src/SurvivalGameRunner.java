import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class SurvivalGameRunner {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        SurvivalGame player = new SurvivalGame();
        player.generateDayHelpArrives();
        List<String> inv = new ArrayList<String>();
        System.out.println();
        System.out.print("""
                Welcome to a Survival Game! Your goal is to survive until help arrives which can take 7 to 14 days.
                To survive you need to take care of your health, shelter, food, water, and mental state.
                If you go days without these things it can result in GAME OVER so please be careful and good luck.
                """);

        System.out.println("\nYou are stranded on an island." +
                "\nYou look around and notice that there is an abandoned hut to the north, the sea to the south, coconut trees to the east, and signs of wild beasts to the west.\n");
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