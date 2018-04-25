import java.util.LinkedList;

class Programm {
    public static void main(String[]args){

        GeomCalculation[] testArr = new GeomCalculation[5];

        testArr[0] = new Circle(2.45);
        testArr[1] = new Circle(3.0);
        testArr[2] = new Pentagon(2.43);
        testArr[3] = new Circle(1.43);
        testArr[4] = new Pentagon(5.12);

        //Im Folgenden wird die Summe der Umfaenge und Flaechen berechnet
        double totalPerimeter=0;
        double totalArea=0;
        for(int i=0; i < testArr.length ; i++){
            totalPerimeter += testArr[i].getPerimeter();
            totalArea += testArr[i].getArea();
        }
        System.out.println(totalPerimeter);
        System.out.println(totalArea);

        //Aufgabe 4
        LinkedList<GeomCalculation> list = new LinkedList<>();

        list.add(new Circle(2.45));
        list.add(new Circle(3.0));
        list.add(new Pentagon(2.43));
        list.add(new Circle(1.43));
        list.add(new Pentagon(5.12));

        double totalPerimeterList=0;
        double totalAreaList=0;
        for(GeomCalculation x : list){
            totalPerimeterList += x.getPerimeter();
            totalAreaList += x.getArea();
        }
        System.out.println(totalPerimeterList);
        System.out.println(totalAreaList)
        ;
    }
}