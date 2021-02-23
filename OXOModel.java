import java.util.*;

class OXOModel
{
    // private OXOPlayer cells[][];
    private ArrayList<List<OXOPlayer>> cells;
    // private OXOPlayer players[];
    private ArrayList<OXOPlayer> players;
    private OXOPlayer currentPlayer;
    private OXOPlayer winner;
    private boolean gameDrawn;
    private int winThreshold;

    public OXOModel(int numberOfRows, int numberOfColumns, int winThresh)
    {
        winThreshold = winThresh;
        // cells = new OXOPlayer[numberOfRows][numberOfColumns];
        cells = new ArrayList<>();
        for(int row = 0; row < numberOfRows; row++){
            cells.add(row, new ArrayList<>());
            for(int col = 0; col <numberOfColumns; col++){
                cells.get(row).add(null);
            }
        }
        players = new ArrayList<>();
    }

    public int getNumberOfPlayers()
    {
        // return players.length;
        return players.size();
    }

    public void addPlayer(OXOPlayer player)
    {
//        for(int i=0; i<players.length ;i++) {
//            if(players[i] == null) {
//                players[i] = player;
//                return;
//            }
//        }
        players.add(player);
    }

    public OXOPlayer getPlayerByNumber(int number)
    {
        // return players[number];
        return players.get(number);
    }

    public OXOPlayer getWinner()
    {
        return winner;
    }

    public void setWinner(OXOPlayer player)
    {
        winner = player;
    }

    public OXOPlayer getCurrentPlayer()
    {
        return currentPlayer;
    }

    public void setCurrentPlayer(OXOPlayer player)
    {
        currentPlayer = player;
    }

    public int getNumberOfRows()
    {
        // return cells.length;
        return cells.size();
    }

    public int getNumberOfColumns()
    {
        // return cells[0].length;
        return cells.get(0).size();
    }

    public OXOPlayer getCellOwner(int rowNumber, int colNumber)
    {
        // return cells[rowNumber][colNumber];
        return cells.get(rowNumber).get(colNumber);
    }

    public void setCellOwner(int rowNumber, int colNumber, OXOPlayer player)
    {
        // cells[rowNumber][colNumber] = player;
        cells.get(rowNumber).set(colNumber, player);
    }

    public void setWinThreshold(int winThresh)
    {
        winThreshold = winThresh;
    }

    public int getWinThreshold()
    {
        return winThreshold;
    }

    public void setGameDrawn()
    {
        gameDrawn = true;
    }

    public void setGameNotDrawn()
    {
        gameDrawn = false;
    }

    public boolean isGameDrawn()
    {
        return gameDrawn;
    }

    public boolean winDetection(OXOPlayer currentPlayer)
    {
        int occupation = 0;

        // Check horizontal
        for(int i = 0; i < getNumberOfRows(); i++) {
            occupation = 0;
            for(int j = 0; j < getNumberOfColumns() - 1; j++) {
                if(getCellOwner(i, j) != null && getCellOwner(i, j+1) != null) {
                    if(getCellOwner(i, j) == getCellOwner(i, j + 1) && getCellOwner(i, j) == currentPlayer) {
                        occupation++;
                    }
                    else {
                        occupation = 0;
                    }
                }
            }
            if(occupation == getWinThreshold() - 1) {
                return true;
            }
        }

        // Check vertical
        for(int i = 0; i < getNumberOfColumns(); i++) {
            occupation = 0;
            for(int j = 0; j < getNumberOfRows() -1; j++) {
                if(getCellOwner(j, i) != null && getCellOwner(j + 1, i) != null) {
                    if(getCellOwner(j, i) == getCellOwner(j + 1, i) && getCellOwner(j, i) == currentPlayer) {
                        occupation++;
                    }
                    else {
                        occupation = 0;
                    }
                }
            }
            if(occupation == getWinThreshold() - 1) {
                return true;
            }
        }

        // Check diagonal (Upper left to lower right)
        occupation = 0;
        for(int i = 0; i < getNumberOfRows() - 1; i++) {
            if(getCellOwner(i, i) != null && getCellOwner(i+1, i+1) != null) {
                if(getCellOwner(i, i) == getCellOwner(i+1, i+1)) {
                    occupation++;
                }
                else {
                    occupation = 0;
                }
            }
            if(occupation == getWinThreshold() - 1) {
                return true;
            }
        }

        // Check diagonal (Upper right to lower left)
        occupation = 0;
        for(int i = 0; i < getNumberOfRows() - 1; i++) {
            if(getCellOwner(i, getNumberOfRows() - i - 1) != null && getCellOwner(i+1, getNumberOfRows() - i - 2) != null) {
                if(getCellOwner(i, getNumberOfRows() - i - 1) == getCellOwner(i + 1, getNumberOfRows() - i - 2)) {
                    occupation++;
                }
                else {
                    occupation = 0;
                }
            }
            if(occupation == getWinThreshold() - 1){
                return true;
            }
        }
        return false;
    }

    public boolean drawnDetection()
    {
        int count = 0;
        for(int i = 0; i < getNumberOfRows(); i++) {
            for(int j = 0; j < getNumberOfColumns(); j++) {
                if(getCellOwner(i, j) != null) {
                    count++;
                }
                else {
                    break;
                }
            }
        }
        if(count == getNumberOfRows() * getNumberOfColumns()) {
            return true;
        }
        else {
            return false;
        }
    }

    public void extendBoard()
    {
        cells.add(new ArrayList<>());
        for(int row = 0; row < cells.size(); row++) {
            cells.get(row).add(null);
            cells.get(cells.size() - 1).add(null);
        }
    }
}
