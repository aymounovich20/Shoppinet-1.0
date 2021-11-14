package com.example.demo.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
public class Commande implements Serializable {
	@Id
	@GeneratedValue
	private Long id;
	private Date date;
	private long cout;
	@ManyToOne
	@JoinColumn(name="id_client")
	private Client client;
	
	@OneToMany(mappedBy = "commandes")
	public Collection<Commande_Produit> listCommandeProduit;
	
	
}
