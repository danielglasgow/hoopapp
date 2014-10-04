package com.example.helloworld;

import java.io.Serializable;
import java.util.List;

public class CourtsTransferObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final List<Courts> courts;
	
	public CourtsTransferObject(List<Courts> courts) {
		this.courts = courts;
	}
	
	
}
