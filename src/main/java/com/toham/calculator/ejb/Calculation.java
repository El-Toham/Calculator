package com.toham.calculator.ejb;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Calculation {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int numberO; // The first number.
    private int numberT; // The second number.
    private String operation;
    private int result;
    
    // *******Getters*******
    /**
	 * @return the numberO
	 */
	public int getNumberO() {
		return numberO;
	}
	
	/**
	 * @return the numberT
	 */
	public int getNumberT() {
		return numberT;
	}
	
	/**
	 * @return the operation
	 */
	public String getOperation() {
		return operation;
	}
	
	/**
	 * @return the result
	 */
	public int getResult() {
		return result;
	}
	
	
    // *******Setters*******
	/**
	 * @param numberO the numberO to set
	 */
	public void setNumberO(int numberO) {
		this.numberO = numberO;
	}
	
	/**
	 * @param numberT the numberT to set
	 */
	public void setNumberT(int numberT) {
		this.numberT = numberT;
	}
	
	/**
	 * @param operation the operation to set
	 */
	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	/**
	 * @param result the result to set
	 */
	public void setResult(int result) {
		this.result = result;
	}


}
