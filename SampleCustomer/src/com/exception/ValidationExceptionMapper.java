package com.exception;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ValidationException> {

	@Override
	public Response toResponse(ValidationException e) {

		return Response.status(Response.Status.BAD_REQUEST).entity(prepareMessage(e)).type("text/plain").build();

	}

	private String prepareMessage(ValidationException exception) {
		if (exception instanceof ConstraintViolationException) {
			final StringBuilder strBuilder = new StringBuilder();
			for (ConstraintViolation<?> cv : ((ConstraintViolationException) exception).getConstraintViolations()) {
				strBuilder.append(cv.getPropertyPath().toString() + " " + cv.getMessage());
			}
			return strBuilder.toString();
		}
		return null;
	}
}