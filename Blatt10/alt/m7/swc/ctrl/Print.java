package swc.ctrl;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.print.*;

public class Print implements Printable{
    private Component _objComponent;
    /** 
     * Constructor - sets the component to print.
     */
    public Print(Component objComponent){
        this._objComponent = objComponent;
    }

 
    /**
     * The actual printing method.
     */
    public int print(Graphics objGraphics, PageFormat objPageFormat, int intPageNumber){

        if (intPageNumber > maxPageNumber(objPageFormat)) return NO_SUCH_PAGE;
        Graphics2D objGraphics2 = (Graphics2D) objGraphics;
        AffineTransform objAffineTransform = new AffineTransform(); 
        // move printout dending on pagenumber
        
        objAffineTransform.translate(objPageFormat.getImageableX(), objPageFormat.getImageableY() - intPageNumber * objPageFormat.getImageableHeight());
        objAffineTransform.scale(0.55, 0.55);        
       
        objGraphics2.transform(objAffineTransform);
        //c.paint(g);
        _objComponent.printAll(objGraphics);
        return PAGE_EXISTS;
    }
    /**
     * Calculates the PageNumber of the print job.
     */
    private int maxPageNumber(PageFormat f){
        return (int) Math.floor(_objComponent.getPreferredSize().getHeight() / f.getImageableHeight());
    }
    
}