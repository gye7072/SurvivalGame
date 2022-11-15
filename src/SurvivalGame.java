public class SurvivalGame {
    private int numDays;
    public int dayHelpArrives;
    private int daysWithoutFood;
    public String location;
    private int daysWithoutWater;
    private int HP;
    private int mentalState;


    public SurvivalGame() {
        mentalState = 5;
        numDays = 1;
        daysWithoutFood = 0;
        daysWithoutWater = 0;
        HP = 5;
    }

    public int getFood() {
        if (daysWithoutFood == 0) {
            return daysWithoutFood;
        } else {
            return daysWithoutFood -= 1;
        }
    }

    public int getWater() {
        if (daysWithoutWater == 0) {
            return daysWithoutWater;
        } else {
            return daysWithoutWater -= 1;
        }
    }

    public int getRest() {
        if (mentalState == 5) {
            return mentalState;
        } else {
            return mentalState += 1;
        }
    }

    public int getHP() {
        if (HP == 5) {
            return HP;
        } else {
            return HP += 1;
        }
    }

    public boolean isAlive() {
        return (HP > 0) && (daysWithoutWater != 3) && (daysWithoutFood != 6) && (mentalState != 0);
    }

    public int getNumDays() {
        return numDays;
    }

    public boolean isEven() {
        return ((int) (Math.random() * 6) + 1) % 2 == 0;
    }

    public void generateDayHelpArrives() {
        dayHelpArrives = (int) ((Math.random() * 8) + 7);
    }


    public String everyday(String playerChoice) {
        if (numDays <= 14) {
            return choiceResults(playerChoice);
        }
        return "";
    }

    public String gameOver() {
        if (!isAlive()) {
            if (HP <= 0) {
                return "\nGame Over!" + "\nYou died from wild beasts! \nYou survived for " + (numDays - 1) + " day(s)";
            } else if (daysWithoutWater == 3) {
                return "\nGame Over!" + "\nYou died from dehydration! \nYou survived for " + (numDays - 1) + " day(s)";
            } else if (daysWithoutFood == 6) {
                return "\nGame Over!" + "\nYou died from starvation! \nYou survived for " + (numDays - 1) + " day(s)";
            } else if (mentalState == 0) {
                return "\nGame Over!" + "\nYou couldn't take it anymore! \nYou survived for " + (numDays - 1) + " day(s)";
            } else if (daysWithoutFood >= 3 && daysWithoutWater >= 2) {
                return "\nGame Over!" + "\nYou died from both hunger and thirst! \nYou survived for " + (numDays - 1) + " day(s)";
            }
        }
        return "";
    }

    public String status() {
        String status = "\nEnd of Day " + (numDays-1) + ": \nHP (Max 5): " + HP + "\nDays without food (Max 6): " + daysWithoutFood + "\nDays without water (Max 3): " + daysWithoutWater +
                "\nMental State (Max 5): " + mentalState + "\n";
        if(daysWithoutWater >= 2){
            status += "\nYou are very thirsty.";
        }
        if(daysWithoutFood >3){
            status += "\nYou are very hungry.";
        }
        if(mentalState <= 2){
            status+= "\nYou are losing it.";
        }
        if(HP <= 2 && HP != 0){
            status+= "\nYou are very low.";
        }
        return status;
    }

    public String choiceResults(String playerChoice) {
        if (playerChoice.equals("hut")) {
            String choiceN;
            int maxTenProbability = (int) ((Math.random() * 10) + 1);
            if (maxTenProbability == 10) {
                choiceN = "You decided to go to the abandoned hut. \nUnfortunately you had some bad luck and your shelter was destroyed by some wild beasts." +
                        "\nYou spend the entire rest of the day rebuilding it.";
                mentalState--;
                daysWithoutFood ++;
                daysWithoutWater ++;
            }else {
                choiceN = "You decided to go to the abandoned hut. \nOverwhelmed by everything that happened so far, you take a nap. " +
                        "\nYour health and mental state improved a lot.";
                location.equals("hut");
                daysWithoutFood++;
                daysWithoutWater++;
                getRest();
                getHP();
            }
            numDays++;
            return choiceN;
        } else if (playerChoice.equals("trees")) {
            String choiceE;
            if (isEven()) {
                choiceE = """
                        You decided to go where the coconut trees and for the rest of the day grab some coconuts.
                        After tireless effort, at the end of the day, you manage to get some coconuts.
                        You ate some and managed to satiate your hunger and thirst by a bit.""";
                getFood();
                getWater();
                location.equals("trees");
            } else {
                choiceE = "You tried going to the trees to find some coconuts but despite a day filled with tireless effort you found none";
                daysWithoutFood++;
                daysWithoutWater++;
            }
            mentalState--;
            numDays++;
            return choiceE;
        } else if (playerChoice.equals("sea")) {
            String choiceS;
            if (isEven()) {
                choiceS = "You decided to go to the sea and managed to filter the seawater to drinkable water." +
                        "\nYou drank some and your thirst is alleviated for the day.";
                daysWithoutFood++;
                getWater();
                getWater();
                mentalState--;
                location.equals("sea");
            } else {
                int tenProbability = (int) ((Math.random() * 10) + 1);
                if (tenProbability <= 9) {
                    choiceS = "You decided to go to the sea and try to filter the seawater to drinkable water. " +
                            "\nYou unfortunately met some wild sea monsters however you were able to defend yourself" +
                            " although you received some major injuries.";
                    HP = HP - 2;
                    daysWithoutFood++;
                    getWater();
                    getWater();
                    mentalState--;
                    location.equals("sea");
                } else {
                    choiceS = "You decided to go to the sea and try to filter the seawater to drinkable water. " +
                            "\nUnfortunately you met some wild sea monsters and were quickly eaten.";
                    HP = 0;
                }
            }
            numDays++;
            return choiceS;
        } else if (playerChoice.equals("beasts")) {
            String choiceW;
            int count = numDays + 1;
            int maxTenProbability = (int) ((Math.random() * count) + 1);
            if (maxTenProbability == count) {
                choiceW = "You end up going to where there are signs of wild beasts and find yourself encountering a wild hungry beast." +
                        "\nYou try to defend yourself and run away but your inexperience made you an easy target and you were quickly eaten.";
                HP = 0;
            } else {
                choiceW = "You end up going to where there are signs of wild beasts and find yourself encountering a wild hungry beast." +
                        "\nThanks to your vast experience you managed to defeat the beast and satiate your hunger with only a few injuries.";
                HP = HP - 2;
                getFood();
                getFood();
                daysWithoutWater++;
                mentalState--;
                location.equals("beasts");
            }
            numDays++;
            return choiceW;
        }
        return "This is an invalid response. Please try again.";
    }
}