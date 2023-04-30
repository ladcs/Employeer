package employeer;

import java.util.Scanner;

/**
 *  main class
 */
public class App 
{
    public static void main( String[] args )
    {
        App app = new App();
        new Seed();
        app.delay();
    }
    
    private void delay() {
    	System.out.println("pressione enter para continuar");
    	Scanner scan = new Scanner(System.in);
        scan.nextLine();
        scan.close();
    }
}
