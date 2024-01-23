package br.com.appetitegourmet.api.spring.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.appetitegourmet.api.spring.login.models.AlterPassword;

@Repository
public interface AlterPasswordRepository extends JpaRepository<AlterPassword, Long> {
	Boolean existsByEmailAndHash(String email, String hash);
	void deleteByEmailAndHash(String email, String hash);
	void deleteByEmail(String email);
}
