//Acid or Base dissociation constants (Ka/Kb) cannot be negative or 0. 
public class AcidorBaseDissociationConstantException extends Exception
{
    public AcidorBaseDissociationConstantException()
    {
        super("Acid or Base dissociation constants (Ka/Kb) cannot be negative or 0.");
    }
    public AcidorBaseDissociationConstantException(String message)
    {
        super(message);
    }
}
