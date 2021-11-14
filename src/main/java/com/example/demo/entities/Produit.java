package com.example.demo.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Produit implements Serializable {
	
	@Id
	@GeneratedValue
	private Long id;
	private String nom;
	private String marque;
	private String description;
	private String image;
	private float prix;
	
	
	
	@OneToMany(mappedBy = "produit")
	public Collection<Commande_Produit> listeCommandeProduit;
	
	public String pubPrix()
	{
		return "prix : "+prix +" Dt";
	}

}