package br.com.appetitegourmet.api.spring.login.models;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "link_hash")
public class AlterPassword {

	@Id
	@Column(length = 300, nullable = false)
	private String email;
	
	@Column(length = 300, nullable = false)
	private String hash;
}
