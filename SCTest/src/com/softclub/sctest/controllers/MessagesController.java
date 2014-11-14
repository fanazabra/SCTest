package com.softclub.sctest.controllers;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessagesController {
	private static ResourceBundle messages;
	private static MessagesController instance = null; 
	
	private MessagesController() {
		messages = ResourceBundle.getBundle("messages", Locale.getDefault());
	}
	
	public static ResourceBundle getBundle() {
		if(instance==null) {
			instance = new MessagesController();
		}
		return messages;
	}
}
