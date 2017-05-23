package br.ufes.inf.produtorrural.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Localidade {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String nome;
	
	public Localidade(){
		
	}
	
	public Localidade(Long id){
		this.id = id;
	}
	
	public Long getId(){
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
