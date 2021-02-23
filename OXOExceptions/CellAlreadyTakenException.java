package OXOExceptions;

public class CellAlreadyTakenException extends OXOMoveException
{
    private int rowNumber;
    private int columnNumber;

    public CellAlreadyTakenException(int row, int column) {
        rowNumber = row;
        columnNumber = column;
    }

    public String toString() {
        return "Cell " + (char)(rowNumber + 'a') + (char)(columnNumber + '1') + " is already taken.";
    }
}
