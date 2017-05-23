package br.ufes.inf.produtorrural.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class NotaFiscal {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Long numero;
	private Date dataEmissao;
	private double valorTotal;
	
	@ManyToOne
	private Propriedade propriedade;
	
	public Long getId(){
		return this.id;
	}
	
	public Long getNumero() {
		return numero;
	}
	public void setNumero(Long numero) {
		this.numero = numero;
	}
	public Date getDataEmissao() {
		return dataEmissao;
	}
	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}
	public double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	public Propriedade getPropriedade(){
		return this.propriedade;
	}
	public void setPropriedade(Propriedade propriedade){
		this.propriedade = propriedade;
	}
	public void atualizarPropriedade(Propriedade propriedade){
		this.propriedade  = propriedade;
	}
}
