package br.ufes.inf.produtorrural.control;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import br.ufes.inf.produtorrural.application.NotaFiscalService;
import br.ufes.inf.produtorrural.application.ProdutoNotaFiscalService;
import br.ufes.inf.produtorrural.application.ProdutoService;
import br.ufes.inf.produtorrural.application.PropriedadeService;
import br.ufes.inf.produtorrural.domain.NotaFiscal;
import br.ufes.inf.produtorrural.domain.Produto;
import br.ufes.inf.produtorrural.domain.ProdutoNotaFiscal;
import br.ufes.inf.produtorrural.domain.Propriedade;

@Named
@SessionScoped
public class NotaFiscalController implements Serializable 
{
	//ADICIONADO PELO ECLIPSE
		private static final long serialVersionUID = 1L;
		
		@EJB
		private NotaFiscalService notaFiscalService;

		@EJB
		private PropriedadeService propriedadeService;

		@EJB
		private ProdutoService produtoService;
		
		@EJB
		private ProdutoNotaFiscalService produtoNotaFiscalService;
		
		private String estadoTela = "buscar";
		private NotaFiscal notaFiscal;
		private List<NotaFiscal> notasFiscais;

		private Long idPropriedadeSelecionada;
		private List<Propriedade> propriedades = new ArrayList<Propriedade>();
		
		private Long idProdutoSelecionado;
		private List<Produto> produtos = new ArrayList<Produto>();
		private ProdutoNotaFiscal produtoNotaFiscal;
		private List<ProdutoNotaFiscal> produtosNotaFiscal;
		
		@PostConstruct
		private void carregarPropriedades(){
			this.propriedades = propriedadeService.listar();
			this.produtos = produtoService.listar();
			listar();
		}
		
		public Long getIdPropriedadeSelecionada() {
			return idPropriedadeSelecionada;
		}

		public void setIdPropriedadeSelecionada(Long idPropriedadeSelecionada) {
			this.idPropriedadeSelecionada = idPropriedadeSelecionada;
		}

		public List<Propriedade> getPropriedades(){
			return propriedades;
		}
		
		public Long getIdProdutoSelecionado() {
			return idProdutoSelecionado;
		}

		public void setIdProdutoSelecionado(Long idProdutoSelecionado) {
			this.idProdutoSelecionado = idProdutoSelecionado;
		}

		public List<Produto> getProdutos(){
			return produtos;
		}

		private void popularNotaFiscalPropriedade(){
			this.notaFiscal.atualizarPropriedade(new Propriedade(idPropriedadeSelecionada)); 
		}
		
		private void popularProdutoNotaFiscalProduto(){
			this.produtoNotaFiscal.atualizarProduto(new Produto(idProdutoSelecionado)); 
		}
		
		private void popularProdutoNotaFiscalNotaFiscal(){
			this.produtoNotaFiscal.setNotaFiscal(notaFiscal); 
		}
		
		public String gerenciarProdutos(NotaFiscal notaFiscal){
			this.notaFiscal = notaFiscal;
			listarProdutoNotaFiscal();
			return "/notafiscal/produtoNotaFiscal.xhtml";
		}
				
		public void novo()
		{
			notaFiscal = new NotaFiscal();
			idPropriedadeSelecionada = null;
			mudarParaInserir();
		}
		
		public void novoProdutoNotaFiscal()
		{
			produtoNotaFiscal = new ProdutoNotaFiscal();
			mudarParaInserir();
		}
		
		public void salvar() 
		{		
			popularNotaFiscalPropriedade();
			notaFiscalService.salvar(this.notaFiscal);
			this.notaFiscal = new NotaFiscal();
			adicionarMensagem("Salvo com sucesso!", FacesMessage.SEVERITY_INFO);
			mudarParaBuscar();
		}
		
		public void salvarProdutoNotaFiscal() 
		{		
			popularProdutoNotaFiscalProduto();
			popularProdutoNotaFiscalNotaFiscal();
			produtoNotaFiscalService.salvar(this.produtoNotaFiscal);
			this.produtoNotaFiscal = new ProdutoNotaFiscal();
			adicionarMensagem("Salvo com sucesso!", FacesMessage.SEVERITY_INFO);
			mudarParaBuscar();
		}
		
		public void excluir(NotaFiscal notaFiscal) 
		{
			notaFiscalService.excluir(notaFiscal);
			notasFiscais.remove(notaFiscal);
			adicionarMensagem("Excluido com sucesso!", FacesMessage.SEVERITY_INFO);
		}
		
		public void excluirProdutoNotaFiscal(ProdutoNotaFiscal produtoNotaFiscal) 
		{
			produtoNotaFiscalService.excluir(produtoNotaFiscal);
			produtosNotaFiscal.remove(produtoNotaFiscal);
			adicionarMensagem("Excluido com sucesso!", FacesMessage.SEVERITY_INFO);
		}
		
		public void atualizar(NotaFiscal notaFiscal) 
		{
			this.notaFiscal = notaFiscal;
			this.idPropriedadeSelecionada = notaFiscal.getPropriedade().getId();
			mudarParaEditar();
		}
		
		public void atualizarProdutoNotaFiscal(ProdutoNotaFiscal produtoNotaFiscal) 
		{
			this.produtoNotaFiscal = produtoNotaFiscal;
			this.idProdutoSelecionado = produtoNotaFiscal.getProduto().getId();
			mudarParaEditar();
		}
		
		public void listar()
		{
			if(isBuscar() == false)
			{
				mudarParaBuscar();
				return;
			}
			
			this.notasFiscais = notaFiscalService.listar();
			
			if(notasFiscais == null || notasFiscais.size() < 1)
			{
				adicionarMensagem("Não temos nada cadastrado!", FacesMessage.SEVERITY_WARN);
			}		
		}
		
		public void listarProdutoNotaFiscal()
		{
			if(isBuscar() == false)
			{
				mudarParaBuscar();
				return;
			}
			
			this.produtosNotaFiscal = produtoNotaFiscalService.listar(notaFiscal.getId());
			
			if(produtosNotaFiscal == null || produtosNotaFiscal.size() < 1)
			{
				adicionarMensagem("Não temos nada cadastrado!", FacesMessage.SEVERITY_WARN);
			}		
		}
			
		public void cancelar()
		{
			mudarParaBuscar();
		}
		
		public void adicionarMensagem(String mensagem, FacesMessage.Severity tipoErro)
		{		
			FacesMessage fm = new FacesMessage(tipoErro, mensagem, null);
			
			FacesContext.getCurrentInstance().addMessage(null, fm);
		}
		
		//Geters e Setters
		public NotaFiscal getNotaFiscal() 
		{ 
			return notaFiscal; 
		}
		
		public List<NotaFiscal> getNotasFiscais() 
		{
			return notasFiscais;
		}

		public void setNotasFiscais(List<NotaFiscal> notasFiscais) 
		{
			this.notasFiscais = notasFiscais;
		}
		
		
		//Métodos para controlar a tela
		public boolean isInserir()
		{
			return "inserir".equals(estadoTela);
		}
		public boolean isEditar()
		{
			return "editar".equals(estadoTela);
		}
		public boolean isBuscar()
		{
			return "buscar".equals(estadoTela);
		}
		public void mudarParaInserir()
		{
			estadoTela = "inserir";
		}
		public void mudarParaEditar()
		{
			estadoTela = "editar";
		}
		public void mudarParaBuscar()
		{
			estadoTela = "buscar";
			listar();
		}
		
		public int getQuantidadeNotasFiscais()
		{
			return this.notasFiscais.size();
		}

		public ProdutoNotaFiscal getProdutoNotaFiscal() {
			return produtoNotaFiscal;
		}

		public void setProdutoNotaFiscal(ProdutoNotaFiscal produtoNotaFiscal) {
			this.produtoNotaFiscal = produtoNotaFiscal;
		}

		public List<ProdutoNotaFiscal> getProdutosNotaFiscal() {
			return produtosNotaFiscal;
		}

		public void setProdutosNotaFiscal(List<ProdutoNotaFiscal> produtosNotaFiscal) {
			this.produtosNotaFiscal = produtosNotaFiscal;
		}
}
