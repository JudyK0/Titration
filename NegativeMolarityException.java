public class NegativeMolarityException extends Exception
{
    public NegativeMolarityException()
    {
        super("Molarity is negative. Not possible");
    }
    public NegativeMolarityException(String message)
    {
        super(message);
    }
}