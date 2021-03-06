package br.ufes.inf.produtorrural.control;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;

import br.ufes.inf.produtorrural.application.LocalidadeService;
import br.ufes.inf.produtorrural.domain.Localidade;

@Named
@SessionScoped
public class LocalidadeController implements Serializable 
{
	//ADICIONADO PELO ECLIPSE
		private static final long serialVersionUID = 1L;
		
		@EJB
		private LocalidadeService localidadeService;
		
		private String estadoTela = "buscar";
		private Localidade localidade;
		private List<Localidade> localidades;
		
		@PostConstruct
		private void carregarLocalidades(){
			listar();
		}
				
		public void novo()
		{
			localidade = new Localidade();
			mudarParaInserir();
		}
		
		public void salvar() 
		{			
			localidadeService.salvar(this.localidade);
			this.localidade = new Localidade();
			adicionarMensagem("Salvo com sucesso!", FacesMessage.SEVERITY_INFO);
			mudarParaBuscar();		
		}
		
		public void excluir(Localidade localidade) 
		{
			localidadeService.excluir(localidade);
			localidades.remove(localidade);
			adicionarMensagem("Excluido com sucesso!", FacesMessage.SEVERITY_INFO);
		}
		
		public void atualizar(Localidade localidade) 
		{
			this.localidade = localidade;			
			mudarParaEditar();
		}
		
		public void listar()
		{
			if(isBuscar() == false)
			{
				mudarParaBuscar();
				return;
			}
			
			this.localidades = localidadeService.listar();
			
			if(localidades== null || localidades.size() < 1)
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
		public Localidade getLocalidade() 
		{ 
			return localidade; 
		}
		
		public List<Localidade> getLocalidades() 
		{
			return localidades;
		}

		public void setLocalidades(List<Localidade> localidades) 
		{
			this.localidades = localidades;
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
		
		public int getQuantidadeLocalidades()
		{
			return this.localidades.size();
		}
		
		public void buscarLatLong() {
			String name = localidade.getNome();
				if (name != null && name.length() > 3) {
					String query2 = 
									"PREFIX type: <http://www.w3.org/1999/02/22-rdf-syntax-ns#type>" +
									"PREFIX comment: <http://www.w3.org/2000/01/rdf-schema#comment>" +
									"PREFIX label: <http://www.w3.org/2000/01/rdf-schema#label>" +
									"PREFIX yago: <http://dbpedia.org/class/yago/>" + 
									"PREFIX lat: <http://www.w3.org/2003/01/geo/wgs84_pos#lat>" +
									"PREFIX long: <http://www.w3.org/2003/01/geo/wgs84_pos#long>" +
									"SELECT ?citylabel ?lat ?long " +
									"WHERE {" +
									   "?city type: yago:WikicatPopulatedPlacesInEspíritoSanto." +
									   "?city label: ?citylabel." +
									   "?city lat: ?lat ." +
									   "?city long: ?long ." +
									   "?city comment: ?comment ." +
									   "FILTER (lcase(str(?citylabel)) = \"" + name.toLowerCase() + "\") " +
									   "FILTER (lang(?citylabel) = 'pt')" +
									   "FILTER (lang(?comment) = 'pt')" +
									"} ORDER BY DESC(?citylabel) LIMIT 1";
					QueryExecution queryExecution = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query2);
					ResultSet results = queryExecution.execSelect();
					if (results.hasNext()) {
						QuerySolution querySolution = results.next();
						Literal latitude = querySolution.getLiteral("lat");
						Literal longitude = querySolution.getLiteral("long");
						localidade.setLatitude(latitude.getDouble());
						localidade.setLongitude(longitude.getDouble());
					}
					else
					{
						localidade.setLatitude(new Double(0));
						localidade.setLongitude(new Double(0));
					}
			}
		}
}
