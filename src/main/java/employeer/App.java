package employeer;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import employeer.model.Funcionario;
import employeer.projedataRequest.Prints;
import employeer.restAPI.RestApi;
import employeer.util.MapAndIncRemunaration;
import employeer.util.ToPrint;

/**
 *  main class
 */
public class App {
	/** Para acessar o banco. */
	RestApi crud = new RestApi();
	
	/** método main.*/ 
    public static void main( String[] args )
    {
    	Prints prints = new Prints();
        App app = new App();
        new Seed();
        Scanner scan = new Scanner(System.in);
        String options = "p";
        while (!options.equals("e")) {
            app.printOptions();
            options = scan.nextLine();
            app.optionsValue(options, scan, prints);
        }
        scan.close();
        System.out.print("bye bye!!");
    }
    
    /** espera uma entrada com enter. */
    private void delay(Scanner scan) {
    	System.out.println("\n pressione enter para continuar");
        scan.nextLine();
        
    }
    
    /** pula 10 linhas. */
    private void jumpTenLines() {
    	for(int i = 0 ; i < 10; i++) {
    	    System.out.print("\n");
    	}
    }
    
    /** chama o metodo delay e jumpTenLines. */
    private void next(Scanner scan) {
        this.delay(scan);
        this.jumpTenLines();
    }
    
    /** faz os requisitos do teste. */
    private void requestProjedata(Scanner scan, Prints prints) {
        prints.table();
        this.next(scan);
        prints.deleteJoao();
        this.next(scan);
        prints.jobAndNames();
        this.next(scan);
        prints.monthAndNames();
        this.next(scan);
        prints.oldestPerson();
        this.next(scan);
        prints.sortPeople();
        this.next(scan);
        prints.total();
        this.next(scan);
        prints.remunerations();
    }
    
    /** printa as opções do painel de controle. */
    private void printOptions() {
        System.out.print("digite 0 para seguir os requisitos propostos\n");
        System.out.print("digite 1 para adicionar novos funcionários\n");
        System.out.print("digite 2 para deletar algum funcionário pelo nome\n");
        System.out.print("digite 3 para imprimir a tabela\n");
        System.out.print("digite 4 para aumentar o salário de todos os funcionarios\n");
        System.out.print("digite 5 para imprimir todos os funcionarios agrupados por função\n");
        System.out.print("digite 6 para imprimir o funcionário mais velho\n");
        System.out.print("digite 7 para imprimir a lista de funcionários por ordem alfabética\n");
        System.out.print("digite 8 para imprimir o total dos salários dos funcionários\n");
        System.out.print("digite 9 para imprimir quantos salários mínimos ganha cada funcionário\n");
        System.out.print("digite r para reininciar o banco\n");
        System.out.print("digite e para encerrar\n");
    }
    
    /** com a opção escolhida faz o que foi pedido e chama o método next. */
    private void optionsValue(String option, Scanner scan, Prints prints) {
        switch (option) {
        case "0" :
            this.requestProjedata(scan, prints);
            this.next(scan);
            break;
        case "1":
        	this.addNew(scan);
        	this.next(scan);
        	break;
        case "2":
            this.delete(scan);
            this.next(scan);
            break;
        case "3":
            prints.table();
            this.next(scan);
            break;
        case "4":
            this.incRemuneration(scan);
            this.next(scan);
            break;
        case "5":
            this.mapJob();
            this.next(scan);
            break;
        case "6":
            this.oldest();
            this.next(scan);
            break;
        case "7":
            this.sort();
            this.next(scan);
            break;
        case "8":
            this.allRemuneration();
            this.next(scan);
            break;
        case "9":
            this.minRemuneration(scan);
            this.next(scan);
            break;
        case "r":
            new Seed();
            this.next(scan);
            break;
        }
    }
    
    /** adiciona um Funcionario no banco. */
    private void addNew(Scanner scan) {
        Funcionario newEmployeer = new Funcionario();
        System.out.print("digite o nome: \n");
        String name = scan.nextLine();
        System.out.print("digite o salário no formato com \".\" na decimal: \n");
        double remuneration = scan.nextDouble();
        BigDecimal toRemuneration = new BigDecimal(remuneration);
        System.out.print("digite o ano que ele nasceu: \n");
        int year = scan.nextInt();
        System.out.print("digite o número do mês que ele nasceu: \n");
        int month = scan.nextInt();
        System.out.print("digite o dia que ele nasceu: \n");
        int day = scan.nextInt();
        LocalDate birthdate = LocalDate.of(year, month, day);
        System.out.print("digite a função do funcionário: \n");
        scan.nextLine(); // para zerar o buffer
        String job = scan.nextLine();
        
        newEmployeer.setName(name);
        newEmployeer.setBirthDate(birthdate);
        newEmployeer.setJob(job);
        newEmployeer.setRemuneration(toRemuneration);
        
        this.crud.addOneEmployeer(newEmployeer);
    }
    
    /** deleta Funcionario do banco. */
    private void delete(Scanner scan) {
    	System.out.print("digite o nome para excluir o funcionário: \n");
        String name = scan.nextLine();
        
        Long id = this.crud.getByName(name).getId();
        this.crud.delete(id);
    }
    
    /** muda o saário de todos os funcionários. */
    private void incRemuneration(Scanner scan) {
    	System.out.print("digite a porcentagem do incremento/decremento: \n");
        int inc = scan.nextInt();
        
    	MapAndIncRemunaration util = new MapAndIncRemunaration();
        util.incRemuneration(inc);
    }
    
    /** mostra o map de job. */
    private void mapJob() {
    	MapAndIncRemunaration util = new MapAndIncRemunaration();
    	
    	System.out.println(util.getMapJobAndPeople());
    }
    
    /** mostra o mais velho. */
    private void oldest() {
        ToPrint util = new ToPrint();
        
        System.out.println(util.findOlder());
    }
    
    /** mostra ordenado. */
    private void sort() {
        ToPrint util = new ToPrint();
        
        List<String> employeers = util.sort();
        
        employeers.stream()
            .forEach(e -> System.out.println(e));
    }
    
    /** soma os salários. */
    private void allRemuneration() {
    	ToPrint util = new ToPrint();
    	
    	BigDecimal total = util.allRemuneration();
    	System.out.println(util.remunerationToString(total));
    }
    
    /** mostra quantos salários cada um recebe. */
    private void minRemuneration(Scanner scan) {
        ToPrint util = new ToPrint();
        List<Funcionario> employeers = this.crud.getAllFunc();
        
        System.out.print("digite o salário mínimo: \n");
        double min = scan.nextDouble();
        
        List<String> nameAndRemunerations = employeers.stream()
        		.map(employeer -> util.numberRemuneration(employeer, min))
        		.collect(Collectors.toList());
        
       nameAndRemunerations.stream().forEach(e -> System.out.println(e));
    }
}
