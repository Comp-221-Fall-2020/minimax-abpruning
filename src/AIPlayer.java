import edu.macalester.graphics.CanvasWindow;

public class AIPlayer extends Player {
    int[] bestMove = null;
    private static int[] scores;

    public AIPlayer(int playerNumber, SinglePlayerGame game) {
        super(playerNumber, game);
        scores = new int[] { 0, -10, 10 };
        bestMove = new int[2];
    }

    public boolean addMark(CanvasWindow canvas) {
        int row = bestMove[0];
        int column = bestMove[1];
        Mark newMark = new Mark(imagePath, row, column);
        newMark.setMaxSize(game.squareSize * 0.8);
        newMark.setCenter(game.MARGIN + game.squareSize * column + game.squareSize / 2,
            game.MARGIN + game.squareSize * row + game.squareSize / 2);
        canvas.add(newMark.getSymbol());
        numOfMoves++;
        return true;
    }

    public int[] takeBestMove(int[][] gameBoard) {
        int depth = 0;
        bestMove = new int[2];
        Minimax(gameBoard, depth, true);
        return bestMove;
    }

    public int Minimax(int[][] board, int depth, boolean isMaximizer) {
        Integer winner = checkWinner(board);
        if (winner != null) {
            return scores[winner];
        }
        int bestVal;
        if (isMaximizer) {
            bestVal = Integer.MIN_VALUE;
            for (int r = 0; r < board.length; r++) {
                for (int c = 0; c < board.length; c++) {
                    if (board[r][c] == 0) {
                        board[r][c] = 2;
                        int value = Minimax(board, depth + 1, false);
                        board[r][c] = 0;
                        if (value > bestVal) {
                            bestVal = value;
                            if (depth == 0) {
                                bestMove[0] = r;
                                bestMove[1] = c;
                            }
                        }
                    }
                }
            }
            return bestVal;
        } else {
            bestVal = Integer.MAX_VALUE;
            for (int r = 0; r < board.length; r++) {
                for (int c = 0; c < board.length; c++) {
                    if (board[r][c] == 0) {
                        board[r][c] = 1;
                        int value = Minimax(board, depth + 1, true);
                        board[r][c] = 0;
                        if (value < bestVal) {
                            bestVal = value;
                        }
                    }
                }
            }
            return bestVal;
        }
    }

    private Integer checkWinner(int[][] board) {
        Integer winner = null;
        for (int i = 0; i < board.length; i++) {
            if (equals3(board[i][0], board[i][1], board[i][2])) {
                winner = board[i][1];
            }
            if (equals3(board[0][i], board[1][i], board[2][i])) {
                winner = board[0][i];
            }
        }
        if (equals3(board[0][0], board[1][1], board[2][2])) {
            winner = board[0][0];
        }
        if (equals3(board[0][2], board[1][1], board[2][0])) {
            winner = board[0][2];
        }
        if (winner == null && isBoardFilled(board)) {
            return 0;
        } else {
            return winner;
        }
    }

    private boolean isBoardFilled(int[][] board) {
        int availSpot = 9;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] != 0)
                    availSpot--;
            }
        }
        return (availSpot == 0);
    }

    private boolean equals3(int a, int b, int c) {
        return (a == b && b == c && a == c && a != 0);
    }

    @Override
    public String notifyWin(){
        return "AI neva dies B) Hehehe";
    }

    class Move {
        public int score;
        public int depth;
        public Move(int score, int depth) {
            this.score = score;
            this.depth = depth;
        }
    }
}
