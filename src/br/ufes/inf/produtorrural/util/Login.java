package br.ufes.inf.produtorrural.util;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import br.ufes.inf.produtorrural.application.UsuarioService;

@ManagedBean
public class Login 
{
	@EJB
	private Autenticacao ejb;
	
	private String login;
	private String senha;
	
	public String autenticar()
	{
		if(ejb.autenticar(login, senha))
		{
			return "/index2.xhtml";
		}
		else
		{
			FacesMessage fm = new FacesMessage("Email ou senha incorretos!");
			FacesContext.getCurrentInstance().addMessage("msg",fm);
			
			return null;
		}
	}
	
	public void sair() 
	{
		try
		{
			FacesContext context = FacesContext.getCurrentInstance();
			UsuarioService us = (UsuarioService) context.getExternalContext().getSessionMap().get("usuario");
		
			if(us != null)
			{
				context.getExternalContext().getSessionMap().remove("usuario");
				
				ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		    
		        externalContext.redirect(externalContext.getRequestContextPath()+ "/#{facesContext.externalContext.requestContextPath}");
				
				
//				context.getExternalContext().redirect("#{facesContext.externalContext.requestContextPath}");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public String getLogin() 
	{
		return login;
	}

	public void setLogin(String login) 
	{
		this.login = login;
	}

	public String getSenha() 
	{
		return senha;
	}

	public void setSenha(String senha) 
	{
		this.senha = senha;
	}
}
