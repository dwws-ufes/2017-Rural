package br.ufes.inf.produtorrural.application;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.ufes.inf.produtorrural.domain.NotaFiscal;

@Stateless
@LocalBean
public class NotaFiscalService implements Serializable 
{
	//ADICIONADO PELO ECLIPSE 
		private static final long serialVersionUID = 1L;
		
		@PersistenceContext
		private EntityManager entityManager;
		
		public void salvar(NotaFiscal notaFiscal) 
		{
			entityManager.merge(notaFiscal);
		}
		
		public void excluir(NotaFiscal notaFiscal) 
		{
			entityManager.remove(entityManager.merge(notaFiscal));
		}
		
		public void atualizar(NotaFiscal notaFiscal) 
		{
			entityManager.merge(notaFiscal);
		}
		
		@SuppressWarnings("unchecked")
		public List<NotaFiscal> listar()
		{	
			Query q = entityManager.createQuery("select nf from NotaFiscal nf",NotaFiscal.class);
				
			return q.getResultList();			
		}
		
		public Integer quantidadeNotasFiscaisCadastradas()
		{	
			Query q = entityManager.createQuery("select nf from NotaFiscal nf",NotaFiscal.class);
				
			return (Integer) q.getResultList().size();			
		}
		
}