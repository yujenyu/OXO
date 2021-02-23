package OXOExceptions;

public class OutsideCellRangeException extends CellDoesNotExistException
{
    private int position;
    private RowOrColumn type;

//    private RowOrColumn row;
//    private RowOrColumn column;
//    private int inputrow;
//    private int inputcolumn;

    public OutsideCellRangeException(int position, RowOrColumn type) {
        this.position = position;
        this.type = type;
    }

//    public OutsideCellRangeException(int inputrow, int inputcolumn, RowOrColumn row, RowOrColumn column) {
//        this.inputrow = inputrow;
//        this.inputcolumn = inputcolumn;
//        this.row = row;
//        this.column = column;
//    }

    public String toString() {
        return type + " " + (char)(position) + " is outside cell range.";
        // return row + (char)(inputrow) + " & " + column + (char)(inputcolumn) + " is outside cell range.";
    }

}
