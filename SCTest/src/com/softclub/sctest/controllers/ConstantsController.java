package com.softclub.sctest.controllers;

import java.util.Locale;
import java.util.ResourceBundle;

public class ConstantsController {
	private static ResourceBundle constants;
	private static ConstantsController instance = null; 
	
	private ConstantsController() {
		constants = ResourceBundle.getBundle("constants", Locale.getDefault());
	}
	
	public static ResourceBundle getBundle() {
		if(instance==null) {
			instance = new ConstantsController();
		}
		return constants;
	}
}
