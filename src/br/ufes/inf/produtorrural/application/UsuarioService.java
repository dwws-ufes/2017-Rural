package br.ufes.inf.produtorrural.application;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.ufes.inf.produtorrural.domain.Usuario;

@Stateless
@LocalBean
public class UsuarioService implements Serializable 
{
	//ADICIONADO PELO ECLIPSE 
		private static final long serialVersionUID = 1L;
		
		@PersistenceContext
		private EntityManager entityManager;
		
		public void salvar(Usuario usuario) 
		{
			entityManager.merge(usuario);
		}
		
		public void excluir(Usuario usuario) 
		{
			entityManager.remove(entityManager.merge(usuario));
		}
		
		public void atualizar(Usuario usuario) 
		{
			entityManager.merge(usuario);
		}
		
		@SuppressWarnings("unchecked")
		public List<Usuario> listar()
		{	
			Query q = entityManager.createQuery("select usuario from Usuario usuario",Usuario.class);
				
			return q.getResultList();			
		}
		
		public Usuario autenticar(String email, String senha)
		{
			Query q = entityManager.createQuery("select u from Usuario u where u.email = :email and u.senha = :senha",Usuario.class);
			q.setParameter("email", email);
			q.setParameter("senha", senha);
			
			Usuario usu = (Usuario) q.getSingleResult();
			
			return usu;
		}
}
