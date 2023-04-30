package employeer.projedataRequest;

/** class para imprimir as requisições. */
public class Prints {
    /** conexão com requests. */
    final Requests req;
    
    /** constructor. */
    public Prints() {
        this.req = new Requests();
    }

    /** método para imprimir a tabela. */
    public void table() {
        Table table = new Table();
        System.out.print(table.divTitleAndItens());
        System.out.print(table.titleTable());
        System.out.print(table.divTitleAndItens());
        table.tableRows().stream()
             .forEach(row -> System.out.print(row));
    }
    
    /** método para imprimir a tabela sem João. */
    public void deleteJoao() {
        this.req.deleteJoao();
        this.table();
    }
    
    /** método para imprimir o map com função e lista de nomes. */
    public void jobAndNames() {
        System.out.print(this.req.jobAndPeople());
    }
    
    /** método para imprimir as pessoas que nasceram no mês 10 e 12. */
    public void monthAndNames() {
        System.out.print(this.req.monthAndPeople());
    }
    
    /** método para imprimir a pessoa mais velha. */
    public void oldestPerson() {
        System.out.print(this.req.oldest());
    }
    
    /** método para imprimir a lista em ordem alfabética. */
    public void sortPeople() {
    	this.req.sort().stream()
    	    .forEach(employeer -> System.out.println(employeer));
    }
    
    /** método para imprimir o valor total dos salários. */
    public void total() {
        System.out.print(this.req.total());
    }
    
    /** método para imprimir a quantos salários minimos. */
    public void remunerations() {
        this.req.numberOfRemunerations().stream()
            .forEach(employeer -> System.out.println(employeer));
    }
}
