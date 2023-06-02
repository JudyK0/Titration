import java.text.DecimalFormat;
import java.awt.Color;

//This class represents the titration experiment where a weak base solution 
//is being titrated with a strong acid solution.
public class WeakBaseStrongAcid implements TitrationSolutions 
  {
    private static final DecimalFormat df=new DecimalFormat("0.000");
    static boolean check=true;
      private double molesAcid,molesBase,Kb, acidAdded,baseVolume,Ka,acidMolarity,baseMolarity;
    public WeakBaseStrongAcid(double Kb, double acidMolarity, double baseMolarity, double baseVolume)
    {
        try{
            if (Kb<=0)
                throw new AcidorBaseDissociationConstantException();
            this.Kb=Kb;
            Ka=(1.0*Math.pow(10,-14))/Kb;
            if (acidMolarity<0||baseMolarity<0)
                throw new NegativeMolarityException();
            this.acidMolarity=acidMolarity;
            this.baseMolarity=baseMolarity;
            if (baseVolume<0)
            throw new NegativeVolumeException();
            this.baseVolume=baseVolume;
            acidAdded=0;
            molesAcid=0;
            molesBase=baseMolarity*baseVolume;
        }
        catch(AcidorBaseDissociationConstantException e)
        {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        catch (NegativeMolarityException e)
        {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        catch (NegativeVolumeException e)
        {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }
    //getpH returns the pH of the solution in the flask
    public double getpH(double acidVolume)
    {
        acidAdded=acidVolume;
            molesAcid=acidMolarity*acidAdded;
           double totalVolume=baseVolume+acidVolume;
        double concentrationBase,concentrationAcid;
      //when no acid is added
      if (molesAcid==0)
      {
        concentrationBase=molesBase/totalVolume;
        double OHConc1=((Kb+Math.sqrt(Math.pow(Kb,2)-(4*(-1)*concentrationBase*Kb)))/(2*-1));
        double OHConc2=((Kb-Math.sqrt(Math.pow(Kb,2)-(4*(-1)*concentrationBase*Kb)))/(2*-1));
        if (OHConc1>0)
        {
          return (14.0-(-Math.log10(OHConc1)));
        }
        else if (OHConc2>0)
        {
          return (14.0-(-Math.log10(OHConc2)));
        }
      }
      //before equivalence point
      if (molesAcid<molesBase)
      {
        concentrationBase=(molesBase-molesAcid)/totalVolume;
        concentrationAcid=molesAcid/totalVolume;
        return (14.0-(-(Math.log10(Kb))+Math.log10(concentrationAcid/concentrationBase)));
      }
      if (check){ 
          String molesBaseString=df.format(molesBase);
          String molesAcidString=df.format(molesAcid);
      if (molesBaseString.equals(molesAcidString))
      {
        concentrationAcid=molesAcid/totalVolume;
        double HConc1=((Ka+Math.sqrt(Math.pow(Ka,2)-(4*(-1)*concentrationAcid*Ka)))/(2*-1));
        double HConc2=((Ka-Math.sqrt(Math.pow(Ka,2)-(4*(-1)*concentrationAcid*Ka)))/(2*-1));
        MyPanel.shade= new Color(247,228,247);//light
            MyPanel.fillSoln= new Color(247,208,245);//dark
            check=false;
        if (HConc1>0)
        {
          return (-Math.log10(HConc1));
        }
        else if (HConc2>0)
        {
          return (-Math.log10(HConc2));
        }
      }
    }
      //after equivalence point
      if (molesAcid>molesBase)
      {
        concentrationAcid=(molesAcid-molesBase)/totalVolume;
        return (-Math.log10(concentrationAcid));
      }
      return -1;
    }
  }