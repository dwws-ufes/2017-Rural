package br.ufes.inf.produtorrural.util;

import javax.ejb.EJB;
import javax.ejb.Stateless;

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
				return true;
		}
		catch (Exception e) {
			return false;
		}
		
		return false;
	}
}
