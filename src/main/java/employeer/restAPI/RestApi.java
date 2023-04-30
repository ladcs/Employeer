package employeer.restAPI;

import org.springframework.stereotype.Controller;

import employeer.model.Funcionario;
import employeer.service.EmployeerService;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/** class RestApi para acessar o banco. */
@Controller
@Path("/api/employeer")
@Produces("application/json")
public class RestApi {
	
	/** propriedade para criar um objeto da classe EmployeerService. */
	final EmployeerService service;
	
	/** constructor cria o objeto classe EmployeerService. */
	public RestApi() {
		service = new EmployeerService();
	}

	/** endpoint para adicionar um funcionario no banco. */
    @POST
    @Consumes("application/json")
    public Response addOneEmployeer(Funcionario employeer) {
        Funcionario emp = this.service.insertOne(employeer);
        URI uriToEmployeer = URI.create("/api/employeer/" + emp.getId());
        return Response.created(uriToEmployeer)
             .entity(emp)
             .build();
    }
    
    /** endpoint para buscar todos os funcionarios do banco. */
    @GET
    public List<Funcionario> getAllFunc() {
        return this.service.findAll();
    }
    
    /** endpoint para buscar um o funcionário do banco pelo nome. */
    @GET
    @Consumes("application/json")
    public Funcionario getByName(String name) {
        return this.service.findByName(name);
    }
    
    /** endpoint para buscar um o funcionário do banco pelo id. */
    @GET
    @Path("/{id}")
    public Funcionario getByName(@PathParam("id") Long id) {
        return this.service.findById(id);
    }
    
    /** endpoint para editar a remuneração de um funcionario no banco. */
    @PUT
    @Path("/{id}")
    @Consumes("application/json")
    public Funcionario updateRemunaration(@PathParam("id") Long id, BigDecimal newValue) {
    	Funcionario employeer = this.service.findById(id);
        return this.service.updateRemuneration(employeer, newValue);
    }
    
    /** endpoint para deletar um funcionario do bando. */
    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
    	Funcionario emp = this.service.findById(id);
        this.service.deletePerson(emp.getName());
    }
}
