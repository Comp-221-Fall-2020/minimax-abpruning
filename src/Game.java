import edu.macalester.graphics.*;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.ui.Button;
import edu.macalester.graphics.Point;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class Game {
    protected CanvasWindow canvas;
    public int winCondition;
    public int numberOfPlayers;
    public int boardSize;
    public double squareSize;
    protected final double CANVAS_SIZE;
    protected final double MARGIN = 50;
    protected GraphicsText turnDisplay;
    protected int turn;
    protected boolean playMode; // true: game in play
    protected Player currentPlayer;
    protected List<Player> players;

    public Game (CanvasWindow canvas) {
        this.canvas = canvas;
        this.CANVAS_SIZE = canvas.getHeight();
        players = new ArrayList<>();
    }

    public void resetGame() {
        turn = 0;
        drawLines();
        // canvas.draw();
        turnDisplay = new GraphicsText();
        turnDisplay.setText("Next turn: player 1");
        turnDisplay.setPosition(50, 35);
        turnDisplay.setFontSize(25);
        canvas.add(turnDisplay);
        playMode = true;
        play();
    }

    protected void play() {
        // implemented in the subclasses
    }

    protected void askToPlayAgain(boolean someoneWins) {
        Rectangle rectangle = new Rectangle(0, 0, 250, 150);
        rectangle.setCenter(300, 300);
        rectangle.setFillColor(new Color(255, 246, 249, 236));
        GraphicsText ask = new GraphicsText();
        GraphicsText notifier = new GraphicsText();
        if (!someoneWins) {
            notifier.setText("It was a draw game.");
        } else {
            notifier.setText(currentPlayer.notifyWin());
        }
        ask.setText("Do you want to play again?");
        notifier.setCenter(290, 255);
        notifier.setFont(FontStyle.BOLD, 15);
        notifier.setFillColor(Color.RED);
        ask.setCenter(300, 285);
        canvas.add(rectangle);
        canvas.add(ask);
        canvas.add(notifier);
        Button yes = new Button("Yes");
        yes.setCenter(250, 345);
        Button no = new Button("No");
        no.setCenter(350, 345);
        canvas.add(yes);
        canvas.add(no);
        yes.onClick(() -> {
            canvas.removeAll();
            players = new ArrayList<>();
            currentPlayer = null;
            new WelcomeWindow(canvas);
            canvas.draw();
        });
        no.onClick(() -> {
            canvas.closeWindow();
        });
    }

    protected boolean clickInRange(Point pos) {
        return (pos.getX() <= CANVAS_SIZE-MARGIN && pos.getX() >= MARGIN && pos.getY() <= CANVAS_SIZE-MARGIN && pos.getY() >= MARGIN);
    }

    // ---------------------------- Setters and Getters ---------------------------------
    public void setNumOfPlayers(int num) {
        this.numberOfPlayers = num;
    }

    public void setBoardSize(int num) {
        this.boardSize = num;
    }

    public void setWinCondition(int num) {
        this.winCondition = num;
    }

    public int getNumOfPlayers() {
        return numberOfPlayers;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public int getWinCondition() {
        return winCondition;
    }

    protected void drawLines() {
        squareSize = ((CANVAS_SIZE - 2 * MARGIN) / boardSize);
        double x = MARGIN;
        double y = MARGIN;
        // Creates rows
        for (int j = 0; j < boardSize + 1; j++) {
            Line line = new Line(x, y, MARGIN + boardSize * squareSize, y);
            line.setStrokeColor(Color.BLACK);
            canvas.add(line);
            y = y + squareSize;
        }

        // Creates columns
        x = MARGIN;
        y = MARGIN;

        for (int j = 0; j < boardSize + 1; j++) {
            Line line = new Line(x, y, x, MARGIN + boardSize * squareSize);
            line.setStrokeColor(Color.BLACK);
            canvas.add(line);
            x += squareSize;
        }
    }

}
