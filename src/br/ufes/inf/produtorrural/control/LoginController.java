package br.ufes.inf.produtorrural.control;

import java.io.Serializable;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.ufes.inf.produtorrural.application.UsuarioService;

@Named
@ViewScoped
public class LoginController implements Serializable
{
	private static final long serialVersionUID = 1L;

	public void verificarAutenticacao()
	{
		try
		{
			FacesContext context = FacesContext.getCurrentInstance();
			UsuarioService us = (UsuarioService) context.getExternalContext().getSessionMap().get("usuario");
		
			if(us == null)
			{

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
	
}
