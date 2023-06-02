public class NegativeVolumeException extends Exception
{
    public NegativeVolumeException()
    {
        super("Volume is negative. Not possible");
    }
    public NegativeVolumeException(String message)
    {
        super(message);
    }
}