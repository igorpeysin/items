package com.igor.springitemsinstock.rest;

@SuppressWarnings("serial")
public class NotEnoughItemsException extends Exception {
	public NotEnoughItemsException(String msg) {
		super(msg);
	}
}
