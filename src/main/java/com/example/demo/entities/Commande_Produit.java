package com.example.demo.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;

@IdClass(Commande_Produit.class)

@Data
@Entity
@AllArgsConstructor
public class Commande_Produit implements Serializable{

	private int qte;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "idCommande")
	private Commande commandes;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "idProduit")
	private Produit produit;
	
	
}
