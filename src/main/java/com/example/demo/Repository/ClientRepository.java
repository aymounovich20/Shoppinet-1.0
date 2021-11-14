package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entities.Client;
import java.lang.String;
import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long>{
	
	List<Client> findByMail(String mail);
	
//	@Modifying
//	@Query("update Client c set c.nom = ?1, c.prenom = ?2 where c.id = ?3")
//	void setUserInfoById(String nom, String prenom);

}
