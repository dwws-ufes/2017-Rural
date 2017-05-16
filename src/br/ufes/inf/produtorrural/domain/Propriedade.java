package br.ufes.inf.produtorrural.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Propriedade 
{
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String nome;
	private String endereco;
	private double tamanho;
	private double latitude;
	private double longitude;
	
	@ManyToOne 
	private Produtor produtor;
		
	@OneToMany 
	private Set<Produto> produtos;
	
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
	public String getEndereco() 
	{
		return endereco;
	}
	public void setEndereco(String endereco) 
	{
		this.endereco = endereco;
	}
	public double getTamanho() 
	{
		return tamanho;
	}
	public void setTamanho(double tamanho) 
	{
		this.tamanho = tamanho;
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
	public Produtor getProdutor() 
	{
		return produtor;
	}
	public void setProdutor(Produtor produtor) 
	{
		this.produtor = produtor;
	}
	public Set<Produto> getProdutos() 
	{
		return produtos;
	}
	public void setProdutos(Set<Produto> produtos) 
	{
		this.produtos = produtos;
	}
	
}
