package com.resource;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;
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
import com.utils.ValidationMessages;

/**
 * @author Deepali This class acts as a controller that accepts requests from
 *         the outside world to fetch the customers, to create new customers to
 *         update existing customers to delete customers
 */
@Path("/customers")
public class CustomerResource {

	/**
	 * @return List<Customer> containing all the customer rows from the database
	 */
	@SuppressWarnings("unchecked")
	@GET
	@Produces("application/json")
	public List<Customer> getCustomers() {
		CustomerDao dao = new CustomerDao();
		@SuppressWarnings("rawtypes")
		List customers = dao.getCustomers();
		return (List<Customer>) customers;
	}

	/**
	 * @param cust
	 * @return Response OK for creation successful, Response BadRequest for creation
	 *         failed
	 */
	@POST
	@Path("/create")
	@Consumes("application/json")
	public Response addCustomer(@Valid Customer cust) {
		cust.setName(cust.getName());
		cust.setEmail(cust.getEmail());
		cust.setPhone(cust.getPhone());
		cust.setPincode(cust.getPincode());

		CustomerDao dao = new CustomerDao();
		int id = dao.addCustomer(cust);

		if (id > 0)
			return Response.ok().entity(ValidationMessages.MESSAGE_CUSTOMER_ADDED + id).type("text/plain").build();
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
	}

	/**
	 * @param id
	 * @param cust
	 * @return Response OK for updation successful, Response BadRequest for updation
	 *         failed
	 */
	@PUT
	@Path("/update/{id}")
	@Consumes("application/json")
	public Response updateCustomer(
			@Min(value = 1, message = ValidationMessages.MESSAGE_ID_VALID) @PathParam("id") int id,
			@Valid Customer cust) {
		CustomerDao dao = new CustomerDao();
		int count = dao.updateCustomer(id, cust);
		if (count == 0) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		return Response.ok().entity(ValidationMessages.MESSAGE_CUSTOMER_UPDATED + id).type("text/plain").build();
	}

	/**
	 * @param id
	 * @return Response OK for deletion successful, Response BadRequest for deletion
	 *         failed
	 */
	@DELETE
	@Path("/delete/{id}")
	@Consumes("application/json")
	public Response deleteCustomer(
			@Min(value = 1, message = ValidationMessages.MESSAGE_ID_VALID) @PathParam("id") int id) {
		CustomerDao dao = new CustomerDao();
		int count = dao.deleteCustomer(id);
		if (count == 0) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		return Response.ok().entity(ValidationMessages.MESSAGE_CUSTOMER_DELETED + id).type("text/plain").build();
	}
}
