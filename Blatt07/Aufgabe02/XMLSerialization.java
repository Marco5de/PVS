package Blatt07;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Manuelles schreiben und auslesen in XML Dateien
 */
public class XMLSerialization {
    public static void main(String[]args){
        //Obejkte zum Schreiben/Lesen aus XML Files
        XMLOutputter out = new XMLOutputter();
        SAXBuilder sax = new SAXBuilder();

        //Objekte die gepseichert werden sollen
        Car car = new Car("CR-MD-5","16.05.1998",4,4,4);
        Car car2 = new Car("UL-M-5","11.03.2002",10,2,5);


        Element rootEle = new Element("cars");
        Document doc = new Document(rootEle);

        //hinzufuegen des ersten autos
        Element carEle = new Element("car");
        carEle.setAttribute(new Attribute("car","1"));
        carEle.addContent(new Element("licenseplate").setText(car.getLicensePlate()));
        carEle.addContent(new Element("productiondate").setText(car.getProductionDate()));
        carEle.addContent(new Element("numberpassengers").setText(Integer.toString(car.getNumberPassengers())));
        carEle.addContent(new Element("numberwheels").setText(Integer.toString(car.getNumberWheels())));
        carEle.addContent(new Element("numberdoors").setText(Integer.toString(car.getNumberDoors())));

        //hinzufuegen des zweiten autos
        Element carEle2 = new Element("car2");
        carEle2.setAttribute(new Attribute("car2","2"));
        carEle2.addContent(new Element("licenseplate").setText(car2.getLicensePlate()));
        carEle2.addContent(new Element("productiondate").setText(car2.getProductionDate()));
        carEle2.addContent(new Element("numberpassengers").setText(Integer.toString(car2.getNumberPassengers())));
        carEle2.addContent(new Element("numberwheels").setText(Integer.toString(car2.getNumberWheels())));
        carEle2.addContent(new Element("numberdoors").setText(Integer.toString(car2.getNumberDoors())));


        doc.getRootElement().addContent(carEle);
        doc.getRootElement().addContent(carEle2);

        //Einstellen des Formates auf gut lesbares, eingeruecktes Format
        out.setFormat(Format.getPrettyFormat());
        //Versuche in xml Datei zu schreiben
        try {
            out.output(doc, new FileWriter("SaveCar.xml"));
        } catch (IOException e) {
            System.out.println("Error opening File");
        }


        //Einlesen

        File input = new File("SaveCar.xml");
        Document inputDoc = null;
        //Versuche aus xml Datei zu lesen und Document zu instanziieren
        try {
            inputDoc = (Document) sax.build(input);
        } catch (JDOMException e) {
            System.out.println("An Error occured");
        } catch (IOException e) {
            System.out.print("Error opening File");
        }

        //Liste von Elementen der jeweiligen Autos
        List<Element> listCar = inputDoc.getRootElement().getChildren("car");
        List<Element> listCar2 = inputDoc.getRootElement().getChildren("car2");

        //Ausgabe der Objekte auf der Konsole (manuell)
        printXML(listCar);
        System.out.println();
        printXML(listCar2);

        //Erstellen der abgespeicherten Objekte
        Car savedCar1 = createObj(listCar);
        Car savedCar2 = createObj(listCar2);

        System.out.println();
        System.out.println(savedCar1);
        System.out.println();
        System.out.println(savedCar2);

}


static void printXML(List<Element> list) {
    for (Element x : list) {
        System.out.println(x.getChildText("licenseplate"));
        System.out.println(x.getChildText("productiondate"));
        System.out.println(x.getChildText("numberpassengers"));
        System.out.println(x.getChildText("numberdoors"));
        System.out.println(x.getChildText("numberwheels"));
    }
}

static Car createObj(List<Element> list){
        try {
            return new Car(list.get(0).getChildText("licenseplate"),
                    list.get(0).getChildText("productiondate"),
                    Integer.parseInt(list.get(0).getChildText("numberpassengers")),
                    Integer.parseInt(list.get(0).getChildText("numberdoors")),
                    Integer.parseInt(list.get(0).getChildText("numberwheels")));
        }
        catch (Exception e){
            System.out.println("Error creating Car");
            return null;
        }


}

}