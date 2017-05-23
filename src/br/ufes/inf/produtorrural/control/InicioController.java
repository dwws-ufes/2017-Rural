package br.ufes.inf.produtorrural.control;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.ufes.inf.produtorrural.application.NotaFiscalService;
import br.ufes.inf.produtorrural.application.ProdutoService;
import br.ufes.inf.produtorrural.application.ProdutorService;
import br.ufes.inf.produtorrural.application.PropriedadeService;

@Named
@RequestScoped
public class InicioController implements Serializable 
{
	//ADICIONADO PELO ECLIPSE
		private static final long serialVersionUID = 1L;
		
		@EJB
		private ProdutorService produtorService;
		
		@EJB
		private PropriedadeService propriedadeService;
		
		@EJB
		private ProdutoService produtoService;
		
		@EJB
		private NotaFiscalService notaFiscalService;
		
		private Integer quantidadeProdutoresCadastrados;
		private Integer quantidadePropriedadesCadastradas;
		private Integer quantidadeProdutosCadastrados;
		private Integer quantidadeNotasFiscaisCadastradas;
		private Double valorTotalNotasEmitidas;

		@PostConstruct
		public void carregarDados(){
			this.quantidadeProdutoresCadastrados = new Integer(produtorService.quantidadeProdutoresCadastrados());
			this.quantidadePropriedadesCadastradas = new Integer(propriedadeService.quantidadePropriedadesCadastradas());
			this.quantidadeProdutosCadastrados = new Integer(produtoService.quantidadeProdutosCadastrados());
			this.quantidadeNotasFiscaisCadastradas = new Integer(notaFiscalService.quantidadeNotasFiscaisCadastradas());
			this.valorTotalNotasEmitidas = new Double(notaFiscalService.valorTotalNotasEmitidas());
		}
		
		public Integer getQuantidadeProdutoresCadastrados() {
			return quantidadeProdutoresCadastrados;
		}

		public void setQuantidadeProdutoresCadastrados(Integer quantidadeProdutoresCadastrados) {
			this.quantidadeProdutoresCadastrados = quantidadeProdutoresCadastrados;
		}

		public Integer getQuantidadePropriedadesCadastradas() {
			return quantidadePropriedadesCadastradas;
		}

		public void setQuantidadePropriedadesCadastradas(Integer quantidadePropriedadesCadastradas) {
			this.quantidadePropriedadesCadastradas = quantidadePropriedadesCadastradas;
		}

		public Integer getQuantidadeProdutosCadastrados() {
			return quantidadeProdutosCadastrados;
		}

		public void setQuantidadeProdutosCadastrados(Integer quantidadeProdutosCadastrados) {
			this.quantidadeProdutosCadastrados = quantidadeProdutosCadastrados;
		}

		public Integer getQuantidadeNotasFiscaisCadastradas() {
			return quantidadeNotasFiscaisCadastradas;
		}

		public void setQuantidadeNotasFiscaisCadastradas(Integer quantidadeNotasFiscaisCadastradas) {
			this.quantidadeNotasFiscaisCadastradas = quantidadeNotasFiscaisCadastradas;
		}

		public double getValorTotalNotasEmitidas() {
			return valorTotalNotasEmitidas;
		}

		public void setValorTotalNotasEmitidas(Double valorTotalNotasEmitidas) {
			this.valorTotalNotasEmitidas = valorTotalNotasEmitidas;
		}

}
