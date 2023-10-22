package com.miu.flightmanagement.airlinebookingservice.exception;

public class ScheduledFlightAlreadyBookedException extends RuntimeException {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public ScheduledFlightAlreadyBookedException(String str) {
		super(str);
	}

}
