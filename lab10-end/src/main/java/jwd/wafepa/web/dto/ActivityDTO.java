package jwd.wafepa.web.dto;

import jwd.wafepa.model.Activity;

public class ActivityDTO {
	private Long id;
	private String name;
	private Integer logCount;
	
	public ActivityDTO() {
		super();
	}

	public ActivityDTO(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
		this.logCount = 0;
	}
	
	public ActivityDTO(Activity activity){
		super();
		this.id = activity.getId();
		this.name = activity.getName();
		this.logCount = 0;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getLogCount() {
		return logCount;
	}
	public void setLogCount(Integer logCount) {
		this.logCount = logCount;
	}
}
