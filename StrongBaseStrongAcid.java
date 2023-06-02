import java.awt.Color;
import java.text.DecimalFormat;

//This class represents the titration experiment where a strong base solution 
//is being titrated with a strong acid solution.
public class StrongBaseStrongAcid implements TitrationSolutions{
    private double molesAcid,molesBase,acidAdded,baseVolume,baseMolarity,acidMolarity,evol;
        private static final DecimalFormat df=new DecimalFormat("0.000");
        static boolean check=true;
    public StrongBaseStrongAcid (double acidMolarity, double baseMolarity, double baseVolume)
  { 
      try{
          if (baseVolume<0)
              throw new NegativeVolumeException();
        this.baseVolume=baseVolume;
          if (acidMolarity<0||baseMolarity<0)
              throw new NegativeMolarityException();
        this.acidMolarity=acidMolarity;
        this.baseMolarity=baseMolarity;
        molesBase=baseMolarity*baseVolume;
        acidAdded=0;
        molesAcid=0;
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
            return (14.0-(-Math.log10(concentrationBase)));
          }
          //before equivalence point
          if (molesAcid<molesBase)
          {
            concentrationBase=(molesBase-molesAcid)/totalVolume;
            return (14.0-(-Math.log10(concentrationBase)));
          }
          if (check){
          String molesBaseString=df.format(molesBase);
          String molesAcidString=df.format(molesAcid);
          //at equivalence point where moles of acid and moles of base are equal
          if (molesBaseString.equals(molesAcidString))
          {
            MyPanel.shade= new Color(247,228,247);//light
            MyPanel.fillSoln= new Color(247,208,245);//dark
            check=false;
              return 7.00;
          }
        }
          if (molesAcid>molesBase)
          { 
            concentrationAcid=(molesAcid-molesBase)/totalVolume;
            return (-Math.log10(concentrationAcid));
          }
          
          //after equivalence point
          
          return -1;
    }
}