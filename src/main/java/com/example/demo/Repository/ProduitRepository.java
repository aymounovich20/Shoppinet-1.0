package com.example.demo.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.Produit;

public interface ProduitRepository extends JpaRepository<Produit, Long>{
	
	Optional<Produit> findById(Long id);
	@Query("select p from Produit p where p.id = :x")
	List<Produit> rechercheParId(@Param("x")Long id);
	@Query("SELECT DISTINCT marque from Produit")
	List<String> findAllByMarque();

}
