package br.ufes.inf.produtorrural.control;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import br.ufes.inf.produtorrural.application.ProdutorService;
import br.ufes.inf.produtorrural.domain.Produtor;

@Named
@SessionScoped
public class ProdutorController implements Serializable 
{
	//ADICIONADO PELO ECLIPSE
		private static final long serialVersionUID = 1L;
		
		@EJB
		private ProdutorService produtorService;
		
		private String estadoTela = "buscar";
		private Produtor produtor;
		private List<Produtor> produtores;
		
		@PostConstruct
		private void carregarProdutores(){
			listar();
		}	
				
		public void novo()
		{
			produtor = new Produtor();
			mudarParaInserir();
		}
		
		public void salvar() 
		{			
			produtorService.salvar(this.produtor);
			this.produtor = new Produtor();
			adicionarMensagem("Salvo com sucesso!", FacesMessage.SEVERITY_INFO);
			mudarParaBuscar();		
		}
		
		public void excluir(Produtor produtor) 
		{
			produtorService.excluir(produtor);
			produtores.remove(produtor);
			adicionarMensagem("Excluido com sucesso!", FacesMessage.SEVERITY_INFO);
		}
		
		public void atualizar(Produtor produtor) 
		{
			this.produtor = produtor;			
			mudarParaEditar();
		}
		
		public void listar()
		{
			if(isBuscar() == false)
			{
				mudarParaBuscar();
				return;
			}
			
			this.produtores = produtorService.listar();
			
			if(produtores == null || produtores.size() < 1)
			{
				adicionarMensagem("N�o temos nada cadastrado!", FacesMessage.SEVERITY_WARN);
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
		public Produtor getProdutor() 
		{ 
			return produtor; 
		}
		
		public List<Produtor> getProdutores() 
		{
			return produtores;
		}

		public void setProdutores(List<Produtor> produtores) 
		{
			this.produtores = produtores;
		}
		
		//M�todos para controlar a tela
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
			listar();
		}
}
