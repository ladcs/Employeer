package employeer;

import employeer.util.MapAndIncRemunaration;

/**
 *  main class
 */
public class App 
{
    public static void main( String[] args )
    {
        new Seed();
        MapAndIncRemunaration aux = new MapAndIncRemunaration();
        aux.incRemuneration(10);
    }
}
