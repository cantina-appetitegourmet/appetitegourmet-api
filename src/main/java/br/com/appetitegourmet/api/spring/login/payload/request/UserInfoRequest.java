package br.com.appetitegourmet.api.spring.login.payload.request;

public class UserInfoRequest {
	private Long id;
	private String role;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
}