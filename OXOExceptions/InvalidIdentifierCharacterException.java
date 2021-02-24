package OXOExceptions;

public class InvalidIdentifierCharacterException extends InvalidIdentifierException
{
    private char character;
    private RowOrColumn type;
    private char inputrow;
    private char inputcolumn;
    private RowOrColumn row;
    private RowOrColumn column;

    public InvalidIdentifierCharacterException(char character, RowOrColumn type) {
        super();
        this.character = character;
        this.type = type;
    }

    public InvalidIdentifierCharacterException(char inputrow, char inputcolumn, RowOrColumn row, RowOrColumn column) {
        super();
        this.inputrow = inputrow;
        this.inputcolumn = inputcolumn;
        this.row = row;
        this.column = column;
    }

    public String toString() {
        if(type != null) {
            return type + " " + character + " character is not valid.";
        }
        else {
            return row + " " + inputrow + " and " + column + " " + inputcolumn + " character are not valid.";
        }
    }
 }
