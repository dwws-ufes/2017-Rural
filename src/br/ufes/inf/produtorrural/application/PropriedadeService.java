package br.ufes.inf.produtorrural.application;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.ufes.inf.produtorrural.domain.Propriedade;

@Stateless
@LocalBean
public class PropriedadeService implements Serializable 
{
	//ADICIONADO PELO ECLIPSE 
		private static final long serialVersionUID = 1L;
		
		@PersistenceContext
		private EntityManager entityManager;
		
		public void salvar(Propriedade propriedade) 
		{
			entityManager.merge(propriedade);
		}
		
		public void excluir(Propriedade propriedade) 
		{
			entityManager.remove(entityManager.merge(propriedade));
		}
		
		public void atualizar(Propriedade propriedade) 
		{
			entityManager.merge(propriedade);
		}
		
		@SuppressWarnings("unchecked")
		public List<Propriedade> listar()
		{	
			Query q = entityManager.createQuery("select propriedade from Propriedade propriedade",Propriedade.class);
				
			return q.getResultList();			
		}
		
		public Integer quantidadePropriedadesCadastradas()
		{	
			Query q = entityManager.createQuery("select propriedade from Propriedade propriedade",Propriedade.class);
				
			if(q == null) return 0;
			
			return (Integer) q.getResultList().size();			
		}
		
}
