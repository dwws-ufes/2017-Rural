package br.ufes.inf.produtorrural.application;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.ufes.inf.produtorrural.domain.Produto;

@Stateless
@LocalBean
public class ProdutoService implements Serializable 
{
	//ADICIONADO PELO ECLIPSE 
		private static final long serialVersionUID = 1L;
		
		@PersistenceContext
		private EntityManager entityManager;
		
		public void salvar(Produto produto) 
		{
			entityManager.merge(produto);
		}
		
		public void excluir(Produto produto) 
		{
			entityManager.remove(entityManager.merge(produto));
		}
		
		public void atualizar(Produto produto) 
		{
			entityManager.merge(produto);
		}
		
		@SuppressWarnings("unchecked")
		public List<Produto> listar()
		{	
			Query q = entityManager.createQuery("select produto from Produto produto",Produto.class);
				
			return q.getResultList();			
		}
		
		public Integer quantidadeProdutosCadastrados(){

			Query q = entityManager.createQuery("select produto from Produto produto",Produto.class);
				
			return (Integer) q.getResultList().size();
		}
		
}
