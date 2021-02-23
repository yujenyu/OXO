package OXOExceptions;

public class InvalidIdentifierCharacterException extends InvalidIdentifierException
{
    char character;
    private RowOrColumn type;

    public InvalidIdentifierCharacterException(char character, RowOrColumn type) {
        super();
        this.character = character;
        this.type = type;
    }

    public String toString() {
        return type + " " + character + " character are not valid ";
    }
 }
