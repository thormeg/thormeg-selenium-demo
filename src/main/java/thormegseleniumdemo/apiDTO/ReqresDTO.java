package thormegseleniumdemo.apiDTO;

/**
 * Basic DTO to represent (de)serialisation using a REST API.
 * 
 * Used arbitrarily here with some standard fields.
 */
public class ReqresDTO {
	private String job;
	private String name;
	private String id;
	private String createdAt;
	private String updatedAt;
	
	public void setJob(String job) {
		this.job = job;
	}
	public String getJob() {
		return job;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public String getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}
}
