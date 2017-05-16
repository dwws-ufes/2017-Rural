package br.ufes.inf.produtorrural.util;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

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
			FacesMessage fm = new FacesMessage("Login ou senha não conferem!");
			FacesContext.getCurrentInstance().addMessage("msg",fm);
			
			return null;
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
