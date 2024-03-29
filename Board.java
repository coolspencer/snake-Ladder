import java.util.concurrent.ThreadLocalRandom;

class Board {
    Cell[][] board;
    int size,numberOfSnake, numberOfLadder;

    Board(int size, int snakeCount, int ladderCount) {
        this.size = size;
        board = new Cell[size][size];
        this.numberOfSnake = snakeCount;
        this.numberOfLadder = ladderCount;
        initializeBoard();
        addSnakeAndLadder();
    }

    public void initializeBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Cell c = new Cell();
                board[i][j] = c;
            }
        }
    }

    public void addSnakeAndLadder() {
        int snakeCount = 0, ladderCount = 0;
        while (snakeCount < numberOfSnake) {
            int snakeHead = ThreadLocalRandom.current().nextInt(1, size * size - 1);
            int snakeTail = ThreadLocalRandom.current().nextInt(1, size * size - 1);
            if (snakeTail >= snakeHead)
                continue;
            Jump jump = new Jump();
            jump.start = snakeHead;
            jump.end = snakeTail;

            Cell cell = getCell(snakeHead);
            cell.jump = jump;

            snakeCount++;
        }
        while (ladderCount < numberOfLadder) {
            int ladderStart = ThreadLocalRandom.current().nextInt(1, size * size - 1);
            int ladderTail = ThreadLocalRandom.current().nextInt(1, size * size - 1);
            if (ladderTail <= ladderStart)
                continue;
            Jump jump = new Jump();
            jump.start = ladderStart;
            jump.end = ladderTail;

            Cell cell = getCell(ladderStart);
            cell.jump = jump;
            ladderCount++;
        }
    }

    public Cell getCell(int position) {
        return board[position / size][position % size];
    }

}