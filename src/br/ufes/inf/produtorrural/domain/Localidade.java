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
	private double latitude;
	private double longitude;
	
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
	public double getLatitude() 
	{
		return latitude;
	}
	public void setLatitude(double latitude) 
	{
		this.latitude = latitude;
	}
	public double getLongitude() 
	{
		return longitude;
	}
	public void setLongitude(double longitude) 
	{
		this.longitude = longitude;
	}
}
