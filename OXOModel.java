import java.util.*;

class OXOModel
{
    // private OXOPlayer cells[][];
    private ArrayList<List<OXOPlayer>> cells;
    private OXOPlayer players[];
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
        players = new OXOPlayer[2];
    }

    public int getNumberOfPlayers()
    {
        return players.length;
    }

    public void addPlayer(OXOPlayer player)
    {
        for(int i=0; i<players.length ;i++) {
            if(players[i] == null) {
                players[i] = player;
                return;
            }
        }
    }

    public OXOPlayer getPlayerByNumber(int number)
    {
        return players[number];
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

    public boolean checkWinner(OXOPlayer currentPlayer)
    {
        int occupation = 0;

        // Check horizontal
        for(int i = 0; i < getNumberOfRows(); i++) {
            occupation = 0;
            for(int j = 0; j < getNumberOfColumns(); j++){
                if(getCellOwner(i, j) == currentPlayer){
                    occupation++;
                }
            }
            if(occupation == getWinThreshold()){
                return true;
            }
        }

        // Check vertical
        for(int i = 0; i < getNumberOfColumns(); i++) {
            occupation = 0;
            for(int j = 0; j < getNumberOfRows(); j++){
                if(getCellOwner(j, i) == currentPlayer){
                    occupation++;
                }
            }
            if(occupation == getWinThreshold()){
                return true;
            }
        }

        // Check diagonals [0][0], [1][1], [2][2]
        occupation = 0;
        for(int i = 0; i < getWinThreshold(); i++){
            if(getCellOwner(i, i) == currentPlayer){
                occupation++;
            }
            if(occupation == getWinThreshold()){
                return true;
            }
        }

        // Check diagonals [0][2], [1][1], [2][0]
        occupation = 0;
        for(int i = 0; i < getWinThreshold(); i++){
            if(getCellOwner(i, getWinThreshold() - i - 1) == currentPlayer){
                occupation++;
            }
            if(occupation == getWinThreshold()){
                return true;
            }
        }
        return false;
    }

    public boolean checkDrawn()
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

    public void extendBoard() {
        cells.add(new ArrayList<>());
        for(int row = 0; row < cells.size(); row++) {
            winThreshold++;
            cells.get(row).add(null);
            cells.get(cells.size() - 1).add(null);
        }
    }
}
