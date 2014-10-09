package com.example.helloworld;

import java.io.Serializable;
import java.util.List;

import com.google.android.gms.maps.model.Marker;

public class DetailTransfer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final String mark;
	
	public DetailTransfer(String mark) {
		this.mark = mark;
	}
	
	
}