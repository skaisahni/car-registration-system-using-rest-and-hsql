package question1;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.hsqldb.lib.Set;

@Path("/cars")
public class carResource {

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public List<car> getcars() throws ClassNotFoundException, SQLException{
		return carDao.INSTANCE.getAllcars();
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	@Path("{carId}")
	public car getcar(@PathParam("carId") String id) throws Exception{
		return carDao.INSTANCE.getcar(Integer.parseInt(id));
	}
//	@GET
//	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
//	@Path("{carName}")
//	public car getcarbyname(@PathParam("carName") String name) throws ClassNotFoundException, SQLException {
//		return carDao.INSTANCE.getcarbyname(name);
//	}
	
	@POST
	@Produces({ MediaType.TEXT_HTML })
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	public void postcar(@FormParam("id") String id,
			@FormParam("name") String name,
			@FormParam("model") String model,
			@FormParam("regarea") String regarea,
			@Context HttpServletResponse servletResponse) throws IOException, ClassNotFoundException, SQLException {
		System.out.println("Inside POST id = " + id);
		System.out.println("Name = " + name);
		
		car car = new car();
		car.setId(Integer.parseInt(id));
		car.setName(name);
		car.setModel(model);
		car.setRegarea(regarea);
		
		carDao.INSTANCE.insertcar(car);
		servletResponse.sendRedirect("../createcar.html");
		
	}
	
	@DELETE
	@Produces({ MediaType.TEXT_HTML })
	@Path("{carId}")
	public void deletecar(@PathParam("carId") String id) throws IOException, NumberFormatException, ClassNotFoundException, SQLException {
		System.out.println("Delete id: " + id);
		carDao.INSTANCE.deletecar(Integer.parseInt(id));
	}
	
	@PUT
	@Produces({ MediaType.TEXT_HTML })
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Path("{carId}")
	public static void putcar(@PathParam("carId") String id,
			@FormParam("name") String name,
			@FormParam("model") String model,
			@FormParam("regarea") String regarea,
			@Context HttpServletResponse servletResponse) throws IOException, ClassNotFoundException, SQLException {
		System.out.println("PUT id = " + id);
		
		car car = new car();
		car.setId(Integer.parseInt(id));
		car.setName(name);
		car.setModel(model);
        car.setRegarea(regarea);		
		carDao.INSTANCE.updatecar(car);
	}
}

