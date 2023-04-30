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

@Controller
@Path("/api/employeer")
@Produces("application/json")
public class RestApi {
	
	final EmployeerService service;
	
	public RestApi() {
		service = new EmployeerService();
	}

    @POST
    @Consumes("application/json")
    public Response addOneEmployeer(Funcionario employeer) {
        Funcionario emp = this.service.insertOne(employeer);
        URI uriToEmployeer = URI.create("/api/employeer/" + emp.getId());
        return Response.created(uriToEmployeer)
             .entity(emp)
             .build();
    }
    
    @GET
    public List<Funcionario> getAllFunc() {
        return this.service.findAll();
    }
    
    @PUT
    @Path("/{id}")
    @Consumes("application/json")
    public Funcionario updateRemunaration(@PathParam("id") Long id, BigDecimal newValue) {
    	Funcionario employeer = this.service.findById(id);
        return this.service.updateRemuneration(employeer, newValue);
    }
    
    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
    	Funcionario emp = this.service.findById(id);
        this.service.deletePerson(emp.getName());
    }
}
