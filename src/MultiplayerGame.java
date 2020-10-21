import edu.macalester.graphics.*;

public class MultiplayerGame extends Game {
    public int movesCount;

    public MultiplayerGame(CanvasWindow canvas) {
        super(canvas);
    }

    @Override
    public void play() {
        for (int i = 0; i < numberOfPlayers; i++) {
            players.add(new HumanPlayer(i + 1, this));
        }
        canvas.onClick(e -> {
            if (!players.isEmpty()) {
                currentPlayer = players.get(turn % numberOfPlayers);
                turnDisplay.setText("Next turn: player " + ((turn + 1)% numberOfPlayers + 1));
            }
            Point pos = e.getPosition();
            if (playMode && currentPlayer != null && clickInRange(pos)) {
                int column = (int) Math.floor((pos.getX() - MARGIN)/squareSize);
                int row = (int) Math.floor((pos.getY() - MARGIN)/squareSize);
                if (currentPlayer.addMark(canvas, row, column)) {
                    playMode = false;
                    askToPlayAgain(true);
                } else if (movesCount == boardSize*boardSize) {
                    playMode = false;
                    askToPlayAgain(false);
                }
                turn += 1;
            }
        });
    }


    // private BoardSquare isRectangle(Point location) {
    //     GraphicsObject obj = playBoard.getElementAt(location);
    //     if (obj instanceof Rectangle) {
    //         return (BoardSquare) obj;
    //     }
    //     return null;
    // }

    // private void drawRectangle() {
    //     int x = MARGIN;

    //     for (int i = 0; i < boardSize; i++) {
    //         int y = MARGIN;
    //         for (int j = 0; j < boardSize; j++) {
    //             BoardSquare square = new BoardSquare(x, y, squareSize, squareSize, j, i);
    //             square.setFillColor(Color.WHITE);
    //             playBoard.add(square);
    //             y += squareSize;
    //             rectangleCount++;
    //         }
    //         x += squareSize;
    //     }
    // }

}
