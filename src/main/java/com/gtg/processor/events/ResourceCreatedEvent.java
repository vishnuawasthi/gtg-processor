package com.gtg.processor.events;

public class ResourceCreatedEvent<T> {

	private Object dto;

	private String event;

	public ResourceCreatedEvent(Object dto) {
		this.dto = dto;
	}

	public Object getDto() {
		return dto;
	}

	public void setDto(Object dto) {
		this.dto = dto;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	
}
