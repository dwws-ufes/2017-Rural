package br.ufes.inf.produtorrural.util;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;

import br.ufes.inf.produtorrural.application.UsuarioService;

@Stateless
public class AutenticacaoBean implements Autenticacao 
{
	@EJB
	private UsuarioService usuarioService;
	
	@Override
	public boolean autenticar(String login, String senha) 
	{
		try{
			if(usuarioService.autenticar(login, senha) != null)
			{
				//Coloca usuario na sess�o
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usuarioService);
				
				return true;
			}
		}
		catch (Exception e) {
			return false;
		}
		
		return false;
	}
	
	
}
