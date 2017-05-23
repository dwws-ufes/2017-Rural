package br.ufes.inf.produtorrural.application;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.ufes.inf.produtorrural.domain.ProdutoNotaFiscal;

@Stateless
@LocalBean
public class ProdutoNotaFiscalService implements Serializable 
{
	//ADICIONADO PELO ECLIPSE 
		private static final long serialVersionUID = 1L;
		
		@PersistenceContext
		private EntityManager entityManager;
		
		public void salvar(ProdutoNotaFiscal produtoNotaFiscal) 
		{
			entityManager.merge(produtoNotaFiscal);
		}
		
		public void excluir(ProdutoNotaFiscal produtoNotaFiscal) 
		{
			entityManager.remove(entityManager.merge(produtoNotaFiscal));
		}
		
		public void atualizar(ProdutoNotaFiscal produtoNotaFiscal) 
		{
			entityManager.merge(produtoNotaFiscal);
		}
		
		@SuppressWarnings("unchecked")
		public List<ProdutoNotaFiscal> listar()
		{	
			Query q = entityManager.createQuery("select pnf from ProdutoNotaFiscal pnf",ProdutoNotaFiscal.class);
				
			return q.getResultList();			
		}
		
		@SuppressWarnings("unchecked")
		public List<ProdutoNotaFiscal> listar(Long notaFiscalId)
		{	
			Query q = entityManager.createQuery("select pnf from ProdutoNotaFiscal pnf where pnf.notaFiscal.id = :notaFiscalId",ProdutoNotaFiscal.class);
			q.setParameter("notaFiscalId", notaFiscalId);	
			
			return q.getResultList();			
		}
		
}
