import java.util.concurrent.ThreadLocalRandom;

class Dice {
    int numberOfDice = 0;

    Dice(int diceCount) {
        this.numberOfDice = diceCount;
    }

    public int throwDice() {
        int value = 0;
        int diceCount = 0;
        while (diceCount < numberOfDice) {
            value += ThreadLocalRandom.current().nextInt(1, 6);
            diceCount++;
        }
        return value;
    }
}