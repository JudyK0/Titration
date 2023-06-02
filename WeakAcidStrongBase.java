import java.text.DecimalFormat;
import java.awt.Color;

//This class represents the titration experiment where a weak acid solution 
//is being titrated with a strong base solution.
public class WeakAcidStrongBase implements TitrationSolutions{
    private double molesAcid,molesBase,Kb,baseAdded,acidVolume,Ka,baseMolarity,acidMolarity;
    private static final DecimalFormat df=new DecimalFormat("0.000");
    static boolean check=true;
    public WeakAcidStrongBase (double Ka, double acidMolarity, double baseMolarity, double acidVolume)
      {
        if (Ka<=0||acidMolarity<0||baseMolarity<0||acidVolume<0)
        System.exit(0);
        this.acidMolarity=acidMolarity;
        this.baseMolarity=baseMolarity;
        molesBase=0;  
        molesAcid=acidMolarity*acidVolume;
        this.Ka=Ka;
        this.acidVolume=acidVolume;
        baseAdded=0;
        Kb=(1.0*Math.pow(10,-14))/Ka;
      }
    
    //getpH returns the pH of the solution in the flask
    public double getpH(double baseVolume)
    {
        double totalVolume=baseVolume+acidVolume;
        baseAdded=baseVolume;
        molesBase=baseMolarity*baseAdded;
        
        double concentrationAcid=0.0,concentrationBase;
        
        //when no base is added
      if (molesBase==0)
      {
        concentrationAcid=molesAcid/totalVolume;
        double HConc1=((Ka+Math.sqrt(Math.pow(Ka,2)-(4*(-1)*concentrationAcid*Ka)))/(2*-1));
        double HConc2=((Ka-Math.sqrt(Math.pow(Ka,2)-(4*(-1)*concentrationAcid*Ka)))/(2*-1));
        if (HConc1>0)
        {
          return (-Math.log10(HConc1));//return 
        }
        else if (HConc2>0)
        {
          return (-Math.log10(HConc2));//return 
        }
      }
      //before equivalence point
      if (molesBase<molesAcid)
      {
        concentrationBase=molesBase/totalVolume;
        concentrationAcid=(molesAcid-molesBase)/totalVolume;
        return (-(Math.log10(Ka))+Math.log10(concentrationBase/concentrationAcid));
      }
      if (check){ 
          String molesBaseString=df.format(molesBase);
          String molesAcidString=df.format(molesAcid);
      //at equivalence point where moles of acid and moles of base are equal
      if (molesBaseString.equals(molesAcidString))
      {
        concentrationBase=molesBase/totalVolume;
        double OHConc1=((Kb+Math.sqrt(Math.pow(Kb,2)-(4*(-1)*concentrationBase*Kb)))/(2*-1));
        double OHConc2=((Kb-Math.sqrt(Math.pow(Kb,2)-(4*(-1)*concentrationBase*Kb)))/(2*-1));
        MyPanel.shade= new Color(247,228,247);//light
            MyPanel.fillSoln= new Color(247,208,245);//dark
            check=false;
        if (OHConc1>0)
        {
           return (14.0-(-Math.log10(OHConc1)));
        }
        else if (OHConc2>0)
        {
            return (14.0-(-Math.log10(OHConc2)));
        }
      }
    }
      //after equivalence point
      if (molesBase>molesAcid)
      {
        concentrationBase=(molesBase-molesAcid)/totalVolume;
        return (14.0-(-Math.log10(concentrationBase)));
      }
      return -1;
    }
}