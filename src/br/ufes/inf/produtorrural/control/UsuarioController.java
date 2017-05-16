package br.ufes.inf.produtorrural.control;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import br.ufes.inf.produtorrural.application.UsuarioService;
import br.ufes.inf.produtorrural.domain.Usuario;

@Named
@SessionScoped
public class UsuarioController implements Serializable 
{
	//ADICIONADO PELO ECLIPSE
		private static final long serialVersionUID = 1L;
		
		@EJB
		private UsuarioService usuarioService;
		
		private String estadoTela = "buscar";
		private Usuario usuario;
		private List<Usuario> usuarios;
			
				
		public void novo()
		{
			usuario = new Usuario();
			mudarParaInserir();
		}
		
		public void salvar() 
		{			
			usuarioService.salvar(this.usuario);
			this.usuario = new Usuario();
			adicionarMensagem("Salvo com sucesso!", FacesMessage.SEVERITY_INFO);
			mudarParaBuscar();		
		}
		
		public void excluir(Usuario usuario) 
		{
			usuarioService.excluir(usuario);
			usuarios.remove(usuario);
			adicionarMensagem("Excluido com sucesso!", FacesMessage.SEVERITY_INFO);
		}
		
		public void atualizar(Usuario usuario) 
		{
			this.usuario = usuario;			
			mudarParaEditar();
		}
		
		public void listar()
		{
			if(isBuscar() == false)
			{
				mudarParaBuscar();
				return;
			}
			
			this.usuarios = usuarioService.listar();
			
			if(usuarios == null || usuarios.size() < 1)
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
		public Usuario getUsuario() 
		{ 
			return usuario; 
		}
		
		public List<Usuario> getUsuarios() 
		{
			return usuarios;
		}

		public void setUsuarios(List<Usuario> usuarios) 
		{
			this.usuarios = usuarios;
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
