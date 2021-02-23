package OXOExceptions;

public class InvalidIdentifierLengthException extends InvalidIdentifierException
{
    private int length;

    public InvalidIdentifierLengthException(int length) {
        super();
        this.length = length;
    }

    public String toString()
    {
        return "Length of " + length + " is invalid.";
    }
}
