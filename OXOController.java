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
        gameModel.setCurrentPlayer(gameModel.getPlayerByNumber(0));
    }

    public void handleIncomingCommand(String command) throws OXOMoveException
    {
        if(gameModel.getWinner() != null) {
            return;
        }

        if(command.length() != 2 )
        {
            throw new InvalidIdentifierLengthException(command.length());
        }

        currentPlayer = gameModel.getCurrentPlayer();
        cmd = command.toLowerCase().toCharArray();
        row = cmd[0] - 'a';
        col = cmd[1] - '1';

        if((!Character.isLetter(cmd[0])) && (!Character.isDigit(cmd[1]))) {
            throw new InvalidIdentifierCharacterException(cmd[0], cmd[1], RowOrColumn.ROW, RowOrColumn.COLUMN);
        }
        else if(!Character.isLetter(cmd[0])) {
            throw new InvalidIdentifierCharacterException(cmd[0], RowOrColumn.ROW);
        }
        else if(!Character.isDigit(cmd[1])) {
            throw new InvalidIdentifierCharacterException(cmd[1], RowOrColumn.COLUMN);
        }

        if(row >= gameModel.getNumberOfRows() && col >= gameModel.getNumberOfColumns()) {
            throw new OutsideCellRangeException(cmd[0], cmd[1], RowOrColumn.ROW, RowOrColumn.COLUMN);
        }
        else if(row >= gameModel.getNumberOfRows() || row < 0) {
            throw new OutsideCellRangeException(cmd[0], RowOrColumn.ROW);
        }
        else if(col >= gameModel.getNumberOfColumns() || col < 0) {
            throw new OutsideCellRangeException(cmd[1], RowOrColumn.COLUMN);
        }

        if(gameModel.getCellOwner(row, col) == null) {
            gameModel.setCellOwner(row, col, currentPlayer);
            if(gameModel.winDetection()) {
                gameModel.setWinner(currentPlayer);
            }
            else if(gameModel.drawnDetection()) {
                gameModel.setGameDrawn();
            }
            else {
                gameModel.setCurrentPlayer(gameModel.getPlayerByNumber(++count % gameModel.getNumberOfPlayers()));
            }
        }
        else {
            throw new CellAlreadyTakenException(row, col);
        }

//        // Extend board if game is drawn
//        if(gameModel.isGameDrawn()) {
//            gameModel.extendBoard();
//            gameModel.setGameNotDrawn();
//            gameModel.setCurrentPlayer(gameModel.getPlayerByNumber(++count % gameModel.getNumberOfPlayers()));
//        }
    }
}
