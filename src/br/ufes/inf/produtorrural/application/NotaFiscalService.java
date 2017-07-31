package br.ufes.inf.produtorrural.application;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.ufes.inf.produtorrural.domain.Localidade;
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
		
		public double valorTotalNotasEmitidas(){
			TypedQuery<Double> q = entityManager.createQuery("select sum(nf.valorTotal) from NotaFiscal nf",Double.class);
			
			return q.getSingleResult();	
		}
		
		public double valorTotalPorLocalidade(Long localidade_id){
			TypedQuery<Double> q = entityManager
					.createQuery(
							"select "
								+ "sum(nf.valorTotal) "
							+ "from "
								+ "NotaFiscal nf "
								+ "join nf.propriedade p "
								+ "join p.localidade l "
							+ "where l.id = "+localidade_id
					,Double.class);
			
			if(q == null)
			{
				return 0;
			}
			
			return q.getSingleResult();	
		}
		
}
