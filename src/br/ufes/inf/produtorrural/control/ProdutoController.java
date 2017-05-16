package br.ufes.inf.produtorrural.control;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import br.ufes.inf.produtorrural.application.ProdutoService;
import br.ufes.inf.produtorrural.domain.Produto;

@Named
@SessionScoped
public class ProdutoController implements Serializable 
{
	//ADICIONADO PELO ECLIPSE
		private static final long serialVersionUID = 1L;
		
		@EJB
		private ProdutoService produtoService;
		
		private String estadoTela = "buscar";
		private Produto produto;
		private List<Produto> produtos;
			
				
		public void novo()
		{
			produto = new Produto();
			mudarParaInserir();
		}
		
		public void salvar() 
		{			
			produtoService.salvar(this.produto);
			this.produto = new Produto();
			adicionarMensagem("Salvo com sucesso!", FacesMessage.SEVERITY_INFO);
			mudarParaBuscar();		
		}
		
		public void excluir(Produto produto) 
		{
			produtoService.excluir(produto);
			produtos.remove(produto);
			adicionarMensagem("Excluido com sucesso!", FacesMessage.SEVERITY_INFO);
		}
		
		public void atualizar(Produto produto) 
		{
			this.produto = produto;			
			mudarParaEditar();
		}
		
		public void listar()
		{
			if(isBuscar() == false)
			{
				mudarParaBuscar();
				return;
			}
			
			this.produtos = produtoService.listar();
			
			if(produtos == null || produtos.size() < 1)
			{
				adicionarMensagem("Não temos nada cadastrado!", FacesMessage.SEVERITY_WARN);
			}		
		}
			
		public void cancelar()
		{
			mudarParaBuscar();
		}
		
		public void adicionarMensagem(String mensagem, FacesMessage.Severity tipoErro)
		{		
			FacesMessage fm = new FacesMessage(tipoErro, mensagem, null);
			
			FacesContext.getCurrentInstance().addMessage(null, fm);
		}
		
		//Geters e Setters
		public Produto getProduto() 
		{ 
			return produto; 
		}
		
		public List<Produto> getProdutos() 
		{
			return produtos;
		}

		public void setProdutos(List<Produto> produtos) 
		{
			this.produtos = produtos;
		}
		
		
		//Métodos para controlar a tela
		public boolean isInserir()
		{
			return "inserir".equals(estadoTela);
		}
		public boolean isEditar()
		{
			return "editar".equals(estadoTela);
		}
		public boolean isBuscar()
		{
			return "buscar".equals(estadoTela);
		}
		public void mudarParaInserir()
		{
			estadoTela = "inserir";
		}
		public void mudarParaEditar()
		{
			estadoTela = "editar";
		}
		public void mudarParaBuscar()
		{
			estadoTela = "buscar";
		}
		
		public int getQuantidadeProdutos()
		{
			return this.produtos.size();
		}
}
