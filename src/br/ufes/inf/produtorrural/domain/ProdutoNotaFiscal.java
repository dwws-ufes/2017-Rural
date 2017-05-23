package br.ufes.inf.produtorrural.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ProdutoNotaFiscal {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private double valorTotal;
	private double aliquota;
	
	@ManyToOne
	private NotaFiscal notaFiscal;
	
	@ManyToOne
	private Produto produto;
	
	public double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	public double getAliquota() {
		return aliquota;
	}
	public void setAliquota(double aliquota) {
		this.aliquota = aliquota;
	}
	public void setNotaFiscal(NotaFiscal notaFiscal){
		this.notaFiscal = notaFiscal;
	}
	public void atualizarProduto(Produto produto){
		this.produto  = produto;
	}
	public Produto getProduto(){
		return this.produto;
	}
	public void setProduto(Produto produto){
		this.produto = produto;
	}	
	public NotaFiscal getNotaFiscal(){
		return this.notaFiscal;
	}
	
}
