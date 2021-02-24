import java.util.*;

class OXOModel
{
    private ArrayList<List<OXOPlayer>> cells;
    private ArrayList<OXOPlayer> players;
    private OXOPlayer currentPlayer;
    private OXOPlayer winner;
    private boolean gameDrawn;
    private int winThreshold;

    public OXOModel(int numberOfRows, int numberOfColumns, int winThresh)
    {
        winThreshold = winThresh;
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
        return players.size();
    }

    public void addPlayer(OXOPlayer player)
    {
        players.add(player);
    }

    public OXOPlayer getPlayerByNumber(int number)
    {
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
        return cells.size();
    }

    public int getNumberOfColumns()
    {
        return cells.get(0).size();
    }

    public OXOPlayer getCellOwner(int rowNumber, int colNumber)
    {
        return cells.get(rowNumber).get(colNumber);
    }

    public void setCellOwner(int rowNumber, int colNumber, OXOPlayer player)
    {
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

    public boolean winDetection()
    {
        int occupation = 0;
        for(int i = 0; i < getNumberOfRows() ; i++) {
            for(int j = 0; j < getNumberOfColumns(); j++) {
                if(getCellOwner(i, j) != null) {
                    // Check horizontal
                    occupation = 0;
                    int x = j, y = i;
                    while (x + 1 < getNumberOfColumns() && (getCellOwner(y, x) == getCellOwner(y, x + 1))) {
                        occupation++;
                        x++;
                    }
                    if (occupation == getWinThreshold() - 1) {
                        return true;
                    }

                    // Check vertical
                    occupation = 0;
                    x = j;
                    y = i;
                    while (y + 1 < getNumberOfRows() && (getCellOwner(y, x) == getCellOwner(y + 1, x))) {
                        occupation++;
                        y++;
                    }
                    if (occupation == getWinThreshold() - 1) {
                        return true;
                    }

                    // Check diagonal (Upper left to lower right)
                    occupation = 0;
                    x = j;
                    y = i;
                    while (x + 1 < getNumberOfColumns() && y + 1 < getNumberOfRows() && getCellOwner(y, x) == getCellOwner(y + 1, x + 1)) {
                        occupation++;
                        x++;
                        y++;
                    }
                    if (occupation == getWinThreshold() - 1) {
                        return true;
                    }

                    // Check diagonal (Upper right to lower left)
                    occupation = 0;
                    x = j;
                    y = i;
                    while (x - 1 >= 0 && y + 1 < getNumberOfRows() && getCellOwner(y, x) == getCellOwner(y + 1, x - 1)) {
                        occupation++;
                        x--;
                        y++;
                    }
                    if (occupation == getWinThreshold() - 1) {
                        return true;
                    }
                }
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
