package br.ufes.inf.produtorrural.application;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.ufes.inf.produtorrural.domain.Localidade;

@Stateless
@LocalBean
public class LocalidadeService implements Serializable 
{
	//ADICIONADO PELO ECLIPSE 
		private static final long serialVersionUID = 1L;
		
		@PersistenceContext
		private EntityManager entityManager;
		
		public void salvar(Localidade localidade) 
		{
			entityManager.merge(localidade);
		}
		
		public void excluir(Localidade localidade) 
		{
			entityManager.remove(entityManager.merge(localidade));
		}
		
		public void atualizar(Localidade localidade) 
		{
			entityManager.merge(localidade);
		}
		
		@SuppressWarnings("unchecked")
		public List<Localidade> listar()
		{	
			Query q = entityManager.createQuery("select localidade from Localidade localidade",Localidade.class);
				
			return q.getResultList();			
		}
		
}
