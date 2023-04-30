package employeer;

import java.util.Scanner;

import employeer.projedataRequest.Prints;

/**
 *  main class
 */
public class App 
{
    public static void main( String[] args )
    {
    	Prints prints = new Prints();
    	
        App app = new App();
        new Seed();
        prints.table();
        Scanner scan = new Scanner(System.in);
        app.next(scan);
        prints.deleteJoao();
        app.next(scan);
        prints.jobAndNames();
        app.next(scan);
        prints.monthAndNames();
        app.next(scan);
        prints.oldestPerson();
        app.next(scan);
        prints.sortPeople();
        app.next(scan);
        prints.total();
        app.next(scan);
        scan.close();
        prints.remunerations();
    }
    
    private void delay(Scanner scan) {
    	System.out.println("\n pressione enter para continuar");
        scan.nextLine();
        
    }
    
    private void jumpTenLines() {
    	for(int i = 0 ; i < 10; i++) {
    	    System.out.print("\n");
    	}
    }
    
    private void next(Scanner scan) {
        this.delay(scan);
        this.jumpTenLines();
    }
}
