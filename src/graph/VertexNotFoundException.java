package graph;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Andrew
 */
public class VertexNotFoundException extends Exception{
    
    public VertexNotFoundException() {
	}

	public VertexNotFoundException(String message) {
		super(message);
	}

	public VertexNotFoundException(Throwable cause) {
		super(cause);
	}

	public VertexNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}

