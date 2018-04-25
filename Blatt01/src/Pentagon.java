
public class Pentagon implements GeomCalculation {
    private double seitenlaenge;

    Pentagon(double seitenlaenge){
        this.seitenlaenge = seitenlaenge;
    }

    public double getPerimeter(){
        return ((seitenlaenge/10)*Math.sqrt(50+10*Math.sqrt(5)));
    }

    public double getArea(){
        return ((Math.pow(seitenlaenge,2)/4)*Math.sqrt(25+10*Math.sqrt(5)));
    }


}
