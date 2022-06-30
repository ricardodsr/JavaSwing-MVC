/**
 * Write a description of class Cronometro here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Cronometro
{
    // instance variables - replace the example below with your own
    private long start; 
    private static final double nano = 1.0E-9;
    /**
     * Constructor for objects of class Cronometro
     */
    public Cronometro()
    {
        start = 0 ;
    }

    public void start(){
        start = System.nanoTime(); 
    }
    public double getTime(){
        return nano * (System.nanoTime() - this.start); 
    }
    
    

}
