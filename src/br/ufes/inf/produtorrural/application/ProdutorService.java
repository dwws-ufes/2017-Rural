package br.ufes.inf.produtorrural.application;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.ufes.inf.produtorrural.domain.Produtor;

@Stateless
@LocalBean
public class ProdutorService implements Serializable 
{
	//ADICIONADO PELO ECLIPSE 
		private static final long serialVersionUID = 1L;
		
		@PersistenceContext
		private EntityManager entityManager;
		
		public void salvar(Produtor produtor) 
		{
			entityManager.merge(produtor);
		}
		
		public void excluir(Produtor produtor) 
		{
			entityManager.remove(entityManager.merge(produtor));
		}
		
		public void atualizar(Produtor produtor) 
		{
			entityManager.merge(produtor);
		}
		
		@SuppressWarnings("unchecked")
		public List<Produtor> listar()
		{	
			Query q = entityManager.createQuery("select produtor from Produtor produtor",Produtor.class);
				
			return q.getResultList();			
		}
		
}
