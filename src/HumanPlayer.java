
public class HumanPlayer extends Player {

    public HumanPlayer(int playerNumber, Game game){
        super(playerNumber, game);
        switch (playerNumber) {
            case 1:
                imagePath = "black x.jpg";
                break;
            case 2:
                imagePath = "red o.png";
                break;
            case 3:
                imagePath = "black o.png";
                break;
            case 4:
                imagePath = "red x.png";
                break;
            case 5:
                imagePath = "red check.png";
                break;
            case 6:
                imagePath = "black check.png";
                break;
            default:
                imagePath = "black x.jpg";
        }
    }

    // public boolean checkWin(){
    //     if (checkWinInRow()) return true;
    //     if (checkWinInColumn()) return true;
    //     if (checkWinInDiagonalUpRight()) return true;
    //     if (checkWinInDiagonalUpLeft()){
    //         return true;
    //     }
    //     return false;
    // }

    // private boolean checkWinInRow(){
    //     for (Mark m: marks){
    //         int count = 1;
    //         // for (int i = 1; i < MultiplayerGame.winCondition; i ++) {
    //         //     if (isMarkInPosition(m.getColumn(), m.getRow() + i)) count += 1;
    //         //     else break;
    //         // }
    //         // if (count == MultiplayerGame.winCondition) return true;
    //     }
    //     return false;
    // }

    // private boolean checkWinInColumn(){
    //     for (Mark m: marks){
    //         int count = 1;
    //         // for (int i = 1; i < MultiplayerGame.winCondition; i ++) {
    //         //     if (isMarkInPosition(m.getColumn() + i, m.getRow())) count += 1;
    //         //     else break;
    //         // }
    //         // if (count == MultiplayerGame.winCondition) return true;
    //     }
    //     return false;
    // }

    // private boolean checkWinInDiagonalUpRight(){
    //     for (Mark m: marks){
    //         int count = 1;
    //     //    for (int i = 1; i < MultiplayerGame.winCondition; i ++) {
    //     //         if (isMarkInPosition(m.getColumn() + i, m.getRow() + i)) count += 1;
    //     //     }
    //     //     if (count == MultiplayerGame.winCondition) return true;
    //     }
    //     return false;
    // }

    // private boolean checkWinInDiagonalUpLeft(){
    //     for (Mark m: marks){
    //         int count = 1;
    //         // for (int i = 1; i < MultiplayerGame.winCondition; i ++) {
    //         //     if (isMarkInPosition(m.getColumn() + i, m.getRow() - i)) count += 1;
    //         // }
    //         // if (count == MultiplayerGame.winCondition) return true;
    //     }
    //     return false;
    // }




    // private boolean isMarkInPosition(int column, int row){
    //     for (Mark m: marks){
    //         if (m.getRow() == row && m.getColumn() == column){
    //             return true;
    //         }
    //     }
    //     return false;
    // }
}
