import OXOExceptions.*;

class OXOController
{
    OXOModel gameModel;
    int count = 0;
    int row = 0;
    int col = 0;
    char[] cmd;
    OXOPlayer currentPlayer;

    public OXOController(OXOModel model)
    {
        gameModel = model;
    }

    public void handleIncomingCommand(String command) throws OXOMoveException
    {
        currentPlayer = gameModel.getCurrentPlayer();
        cmd = command.toCharArray();
        row = cmd[0] - 'a';
        col = cmd[1] - '1';

        if(gameModel.getCellOwner(row, col) == null) {
            gameModel.setCellOwner(row, col, currentPlayer);
            if(gameModel.checkWinner(currentPlayer)) {
                gameModel.setWinner(currentPlayer);
            }
            else if(gameModel.checkDrawn()) {
                gameModel.setGameDrawn();
            }
            else {
                gameModel.setCurrentPlayer(gameModel.getPlayerByNumber(++count % gameModel.getNumberOfPlayers()));
            }
        }

        if(gameModel.isGameDrawn()) {
            gameModel.extendBoard();
            gameModel.setGameNotDrawn();
            gameModel.setCurrentPlayer(gameModel.getPlayerByNumber(++count % gameModel.getNumberOfPlayers()));
        }

    }

}
