package com.resource;

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

import com.dao.CustomerDao;
import com.pojo.Customer;

@Path("/customers")
public class CustomerResource {

	@GET
	@Produces("application/json")
	public List<Customer> getCustomers() {
		CustomerDao dao = new CustomerDao();
		List customers = dao.getCustomers();
		return customers;
	}

	@POST
	@Path("/create")
	@Consumes("application/json")
	public Response addCustomer(Customer cust) {
		cust.setName(cust.getName());
		cust.setEmail(cust.getEmail());
		cust.setPhone(cust.getPhone());
		cust.setPincode(cust.getPincode());
		
		CustomerDao dao = new CustomerDao();
		boolean status = dao.addCustomer(cust);
		
		//TODO -- call another service for status with above boolean value status
		
		return Response.ok().build();
	}

	@PUT
	@Path("/update/{id}")
	@Consumes("application/json")
	public Response updateCustomer(@PathParam("id") int id, Customer cust) {
		CustomerDao dao = new CustomerDao();
		int count = dao.updateCustomer(id, cust);
		if (count == 0) {
			//TODO -- call another service for status with boolean false
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		//TODO -- call another service for status with boolean true
		return Response.ok().build();
	}

	@DELETE
	@Path("/delete/{id}")
	@Consumes("application/json")
	public Response deleteCUstomer(@PathParam("id") int id) {
		CustomerDao dao = new CustomerDao();
		int count = dao.deleteCustomer(id);
		if (count == 0) {
			//TODO -- call another service for status with boolean false
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		//TODO -- call another service for status with boolean true
		return Response.ok().build();
	}
}
