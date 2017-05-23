package br.ufes.inf.produtorrural.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Propriedade 
{
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String nome;
	private double tamanho;
	private double latitude;
	private double longitude;
	
	@ManyToOne 
	private Produtor produtor;
	
	@ManyToOne 
	private Localidade localidade;
	
	public Propriedade(){
		
	}
	
	public Propriedade(Long id){
		this.id = id;
	}
	
	public Long getId() 
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
	public Localidade getLocalidade(){
		return this.localidade;
	}
	public void setLocalidade(Localidade localidade){
		this.localidade = localidade;
	}
	public void atualizarProdutor(Produtor produtor){
		this.produtor  = produtor;
	}
	public void atualizarLocalidade(Localidade localidade){
		this.localidade  = localidade;
	}
}
