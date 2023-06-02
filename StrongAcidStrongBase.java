import java.text.DecimalFormat;
import java.awt.Color;

//This class represents the titration experiment where a strong acid solution 
//is being titrated with a strong base solution.
public class StrongAcidStrongBase implements TitrationSolutions{
    private double molesAcid,molesBase,baseAdded,baseMolarity,acidMolarity,acidVolume,evol;
    private static final DecimalFormat df=new DecimalFormat("0.000");
    static boolean check=true;
    public StrongAcidStrongBase (double acidMolarity, double baseMolarity, double acidVolume)
      {
         try{
          if (acidVolume<0)
              throw new NegativeVolumeException();
            this.acidVolume=acidVolume;
          if (acidMolarity<0||baseMolarity<0)
              throw new NegativeMolarityException();
            this.acidMolarity=acidMolarity;
            this.baseMolarity=baseMolarity;  
            molesAcid=acidMolarity*acidVolume;
            baseAdded=0;
            molesBase=0;
    }
    catch(NegativeVolumeException e)
    {
        System.out.println(e.getMessage());
        System.exit(0);
    }
    catch(NegativeMolarityException e)
    {
        System.out.println(e.getMessage());
        System.exit(0);
    }
      }

      
    //getpH returns the pH of the solution in the flask
    public double getpH(double baseVolume)
        {
            double totalVolume=baseVolume+acidVolume;
        baseAdded=baseVolume;
        molesBase=baseMolarity*baseAdded;
            double concentrationAcid,concentrationBase;
          //when no base is added
          if (molesBase==0)
          {
            concentrationAcid=molesAcid/totalVolume;
            return (-Math.log10(concentrationAcid));
          }
          //before equivalence point
          if (molesBase<molesAcid)
          {
            concentrationAcid=(molesAcid-molesBase)/totalVolume;
            return (-Math.log10(concentrationAcid));
          }
          if (check){ 
          String molesBaseString=df.format(molesBase);
          String molesAcidString=df.format(molesAcid);
          if (molesBaseString.equals(molesAcidString))
          {
            MyPanel.shade= new Color(247,228,247);//light
            MyPanel.fillSoln= new Color(247,208,245);//dark
            check=false;
              return 7.00;//pH is 7 because both acid and base are strong
          }
        }
         if (molesBase>molesAcid)
          {
            concentrationBase=(molesBase-molesAcid)/totalVolume;
            return (14.0-(-Math.log10(concentrationBase)));
          }
          //at equivalence point where moles of base and moles of acid are equal
          
          //after equivalence point
          return -1;
    }
}