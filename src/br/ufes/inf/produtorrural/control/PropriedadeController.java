package br.ufes.inf.produtorrural.control;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import br.ufes.inf.produtorrural.application.PropriedadeService;
import br.ufes.inf.produtorrural.domain.Propriedade;

@Named
@SessionScoped
public class PropriedadeController implements Serializable 
{
	//ADICIONADO PELO ECLIPSE
		private static final long serialVersionUID = 1L;
		
		@EJB
		private PropriedadeService propriedadeService;
		
		private String estadoTela = "buscar";
		private Propriedade propriedade;
		private List<Propriedade> propriedades;
			
				
		public void novo()
		{
			propriedade = new Propriedade();
			mudarParaInserir();
		}
		
		public void salvar() 
		{			
			propriedadeService.salvar(this.propriedade);
			this.propriedade = new Propriedade();
			adicionarMensagem("Salvo com sucesso!", FacesMessage.SEVERITY_INFO);
			mudarParaBuscar();		
		}
		
		public void excluir(Propriedade propriedade) 
		{
			propriedadeService.excluir(propriedade);
			propriedades.remove(propriedade);
			adicionarMensagem("Excluido com sucesso!", FacesMessage.SEVERITY_INFO);
		}
		
		public void atualizar(Propriedade propriedade) 
		{
			this.propriedade = propriedade;			
			mudarParaEditar();
		}
		
		public void listar()
		{
			if(isBuscar() == false)
			{
				mudarParaBuscar();
				return;
			}
			
			this.propriedades = propriedadeService.listar();
			
			if(propriedades == null || propriedades.size() < 1)
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
		public Propriedade getPropriedade() 
		{ 
			return propriedade; 
		}
		
		public List<Propriedade> getPropriedades() 
		{
			return propriedades;
		}

		public void setPropriedades(List<Propriedade> propriedades) 
		{
			this.propriedades = propriedades;
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
}
