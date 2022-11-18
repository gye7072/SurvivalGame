public class SurvivalGame {
    private int numDays;
    public int dayHelpArrives;
    private int daysWithoutFood;
    public String location;
    private int daysWithoutWater;
    private int HP;
    private int mentalState;
    private int maxHP;
    private int maxMentalState;
    private int attackPower;
    private int beastAttackPower;


    public SurvivalGame(int HP, int mentalState, int daysWithoutFood, int daysWithoutWater) {
        this.mentalState = mentalState;
        numDays = 1;
        this.maxHP = HP;
        maxMentalState = mentalState;
        this.daysWithoutFood = daysWithoutFood;
        this.daysWithoutWater = daysWithoutWater;
        this.HP = HP;
    }
    public SurvivalGame(int beastAttackPower){
        this.beastAttackPower = beastAttackPower;
    }

    public String beastAttack(){
        if(isEven()) {
            HP -= beastAttackPower;
            return "You lost " + beastAttackPower + " HP.";
        }
        else{
            return "You managed to defend yourself and run away successfully.";
        }
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
        if (mentalState == maxMentalState) {
            return mentalState;
        } else {
            return mentalState += 1;
        }
    }

    public int getHP() {
        if (HP == maxHP) {
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
        String gameOver = " ";
        if (!isAlive()) {
            if (HP <= 0) {
                gameOver += "\nYou died from wild beasts!";
            } else if (daysWithoutWater == 3) {
                gameOver += "\nYou died from dehydration!";
            } else if (daysWithoutFood == 6) {
                gameOver += "\nYou died from starvation!";
            } else if (mentalState == 0) {
                gameOver += "\nYou couldn't take it anymore!";
            }
            return "\nGame Over!" + gameOver + "\nYou survived for " + (numDays - 1) + " day(s)";
        }
        return "";
    }

    public String status() {
        String status = "\nEnd of Day " + (numDays - 1) + ": \nHP (Max " + maxHP + "): " + HP + "\nDays without food (Max 6): "
                + daysWithoutFood + "\nDays without water (Max 3): " + daysWithoutWater +
                "\nMental State (Max " + maxMentalState + "): " + mentalState + "\n";
            if (daysWithoutWater == 0) {
                status += "\nYou are hydrated.";
            }
            if(daysWithoutWater == 1){
                status += "\nYou are thirsty";
            }
            if(daysWithoutWater == 2){
                status += "\nYOU NEED WATER RIGHT NOW!";
            }
            if(daysWithoutFood == 1 || daysWithoutFood == 2){
                status += "\nYou are hungry";
            }
            if(daysWithoutFood == 3){
                status += "\nYou are really hungry";
            }
            if(daysWithoutFood == 4){
                status += "\nYou are extremely hungry";
            }
            if (daysWithoutFood == 5) {
                status += "\nYOU NEED FOOD RIGHT NOW";
            }
            if(mentalState == 5){
                status += "\nYou have a healthy mind";
            }
            if(mentalState == 4 || mentalState == 3){
                status += "\nYour mind is deteriorating";
            }
            if (mentalState == 2) {
                status += "\nYou are losing it.";
            }
            if(mentalState == 1){
                status += "\nYOU ARE GOING TO LOSE IT.";
            if (HP <= 2 && HP != 0) {
                status += "\nYou are an inch close to DEATH.";
            }
        }
        return status;
    }

    public String getInstructionManual(){
        String instruction = "";
        instruction += "Your goal is survive on a stranded island until help arrives which can take 7 to 14 days."
                + "\nTo survive you need to take care of your health, shelter, food, water, and mental state."
                + "\nIf your health (HP) or your mental state reaches 0 its GAME OVER."
                + "\nIf the number of days without food reaches 6 or the number of days without water reaches 3 its GAME OVER."
                + "\nTo start you are given " + getHP() + " HP" + ", " + getRest() + " \"mental state\"" + ", "
                + getFood() + " hunger" + " and " + getWater() + " thirst.\n";

        instruction += "\nThe hut can recover 1 HP and 1 mental state per visit.\nThe sea can alleviate two days without water per visit." +
                "\nThe trees can alleviate one day without water and food per visit." +
                "\nThe signs of wild beasts can alleviate two days without food per visit.";

        return instruction;
    }

    public String choiceResults(String playerChoice) {
        location = "";
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
            } else {
                int tenProbability = (int) ((Math.random() * 10) + 1);
                if (tenProbability <= 9) {
                    choiceS = "You decided to go to the sea and try to filter the seawater to drinkable water. " +
                            "\nYou unfortunately met some wild sea monsters so you attempt to defend yourself.";
                    daysWithoutFood++;
                    getWater();
                    getWater();
                    mentalState--;
                    location = "sea";
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
                location = "beasts";
            }
            numDays++;
            return choiceW;
        } else if(playerChoice.equals("instruction manual")){
            return getInstructionManual();
        }
        return "This is an invalid response. Please try again.";
    }
}