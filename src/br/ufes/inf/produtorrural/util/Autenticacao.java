package br.ufes.inf.produtorrural.util;

import javax.ejb.Remote;

@Remote
public interface Autenticacao 
{
	public boolean autenticar(String login, String senha);
}
