package com.toham.calculator.api;

import java.util.List;
import com.toham.calculator.ejb.Calculation;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Stateless
@Path("/calc")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CalculatorService {
	
	@PersistenceContext(unitName = "calculation")
	private EntityManager entityManager;
	
	
	@POST
	@Path("/createcalculation")
	public Response createCalculation(Calculation calc) {
		try {
            int result;
            switch (calc.getOperation()) {
                case "+": result = calc.getNumberO() + calc.getNumberT(); break;
                case "-": result = calc.getNumberO() - calc.getNumberT(); break;
                case "*": result = calc.getNumberO() * calc.getNumberT(); break;
                case "/": 
                    if (calc.getNumberT() == 0) {
                        return Response.status(400).entity("{\"error\": \"Division by zero\"}").build();
                    }
                    result = calc.getNumberO() / calc.getNumberT(); 
                    break;
                default: return Response.status(400).entity("{\"error\": \"Invalid operation\"}").build();
            }
            calc.setResult(result);
            entityManager.persist(calc); // Persist to the database
            return Response.ok("{\"Result\": " + result + "}").build();
        } catch (Exception e) {
            return Response.serverError().entity("{\"error\": \"" + e.getMessage() + "\"}").build();
        }
    }

	@GET
    @Path("/calculations") 
    public Response getAllCalculations() {
        try {
            List<Calculation> calculations = entityManager.createQuery("SELECT c FROM Calculation c", Calculation.class).getResultList();
            return Response.ok(calculations).build();
        } catch (Exception e) {
            return Response.serverError().entity("{\"error\": \"" + e.getMessage() + "\"}").build();
        }
    }
}
