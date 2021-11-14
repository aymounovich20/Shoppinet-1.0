package com.example.demo.entities;

import java.io.Serializable;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor

public class Client implements Serializable  {

	@Id
	@GeneratedValue
	private Long id;
	private String nom;
	private String prenom;
	private String mdp;
	private String mail;
	private String numTel;
	private String adresse;
	private String ville;
	
	
	@OneToMany(mappedBy = "client")
	private java.util.Collection<Commande> listeCommande= new HashSet<Commande>();
	
	public Client(String nom,String prenom,String mail,String mdp,String adresse,String ville,String telf)
	{
		this.nom=nom;
		this.prenom=prenom;
		this.mail=mail;
		this.mdp=mdp;
		this.adresse=adresse;
		this.ville=ville;
		this.numTel=telf;
	}
	
	
}
