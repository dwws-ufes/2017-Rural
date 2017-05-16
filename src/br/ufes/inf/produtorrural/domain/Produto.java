package br.ufes.inf.produtorrural.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Produto 
{
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String nome;
	private double valor;
	
	protected Long getId() 
	{
		return id;
	}
	protected void setId(Long id) 
	{
		this.id = id;
	}
	public String getNome() 
	{
		return nome;
	}
	public void setNome(String nome) 
	{
		this.nome = nome;
	}
	public double getValor() 
	{
		return valor;
	}
	public void setValor(double valor) 
	{
		this.valor = valor;
	}
	
	
}
