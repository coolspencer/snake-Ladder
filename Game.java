import java.util.*;;

class Game {
    Deque<Player> playersList = new LinkedList<>();
    int size, numberOfSnake, numberOfLadder;
    Dice dice;
    Board board;
    Player winner;

    public Game(int size, int numberOfSnake, int numberOfLadder) {
        this.size = size;
        this.numberOfLadder = numberOfLadder;
        this.numberOfSnake = numberOfSnake;
        initializeGame(size, numberOfSnake, numberOfLadder);
    }

    public void initializeGame(int size, int numberOfSnake, int numberOfLadder) {

        board = new Board(size, numberOfSnake, numberOfLadder);
        dice = new Dice(1);
        winner = null;
        addPlayers();
    }

    private void addPlayers() {
        Player player1 = new Player("p1");
        Player player2 = new Player("p2");
        playersList.add(player1);
        playersList.add(player2);

    }

    public void startGame() {
        while (winner == null) {
            Player playerTurn = findPlayerTurn();
            System.out.println(
                    playerTurn.name + " is playing and is at current Position : " + playerTurn.currentPosition);
            int diceNumber = dice.throwDice();
            int currentPlayerPosition = playerTurn.currentPosition + diceNumber;
            int position = jumpCheck(currentPlayerPosition);
            playerTurn.currentPosition = position;
            System.out.println("player turn is:" + playerTurn.name + " new Position is: " + position);
            if (position > size * size - 1) {
                System.out.println(playerTurn.name + "win win ");
                winner = playerTurn;
            }
        }
    }

    public int jumpCheck(int position) {
        if (position > size * size - 1)
            return position;
        Cell currentCell = board.getCell(position);
        if (currentCell.jump != null && currentCell.jump.start == position) {
            String move = currentCell.jump.end > currentCell.jump.start ? "ladder" : "snake";
            System.out.println("Player has taken " + move);
            return currentCell.jump.end;
        }
        return position;

    }

    public Player findPlayerTurn() {
        Player current = playersList.pollFirst();
        playersList.addLast(current);
        return current;

    }
}