package OXOExceptions;

public class OutsideCellRangeException extends CellDoesNotExistException
{
    private int position;
    private RowOrColumn type;

    private int inputrow;
    private int inputcolumn;
    private RowOrColumn row;
    private RowOrColumn column;

    public OutsideCellRangeException(int position, RowOrColumn type) {
        super();
        this.position = position;
        this.type = type;
    }

    public OutsideCellRangeException(int inputrow, int inputcolumn, RowOrColumn row, RowOrColumn column) {
        super();
        this.inputrow = inputrow;
        this.inputcolumn = inputcolumn;
        this.row = row;
        this.column = column;
    }

    public String toString() {
        if(type != null) {
            return type + " " + (char) (position) + " is outside cell range.";
        }
        else {
            return row + " " + (char)(inputrow) + " and " + column + " " + (char)(inputcolumn) + " are outside cell range.";
        }
    }

}
