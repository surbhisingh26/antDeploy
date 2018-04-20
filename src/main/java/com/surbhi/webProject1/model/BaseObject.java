package com.surbhi.webProject1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.mongojack.ObjectId;

public class BaseObject {
	private String id;

	@ObjectId
	@JsonProperty("_id")
	public String getId() {
		return this.id;
	}

	@ObjectId
	@JsonProperty("_id")
	public void setId(String id) {
		this.id = id;
	}
}