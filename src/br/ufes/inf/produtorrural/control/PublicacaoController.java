package br.ufes.inf.produtorrural.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.ResourceFactory;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;

import br.ufes.inf.produtorrural.application.LocalidadeService;
import br.ufes.inf.produtorrural.application.NotaFiscalService;
import br.ufes.inf.produtorrural.domain.Localidade;

@WebServlet(urlPatterns = { "/data/localidades" })
public class PublicacaoController extends HttpServlet
{	
	private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	@EJB
	private LocalidadeService localidadeService;

	@EJB
	private NotaFiscalService notaFiscalService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		resp.setContentType("text/xml");
		
		// Lista de todas as localidades cadastradas
		List<Localidade> localidades = localidadeService.listar();
		
		Model model = ModelFactory.createDefaultModel();
		
		String myNS = "http://localhost:8080/ProdutorRural-0.0.1-SNAPSHOT/data/localidades/";
		
		// Yago
		String yagoNS = "http://dbpedia.org/class/yago/";
		model.setNsPrefix("yago", yagoNS);
		Resource yagoPopulatedPlacesInEspíritoSanto = ResourceFactory.createResource(yagoNS + "WikicatPopulatedPlacesInEspíritoSanto");
		
		// Pos
		String posNS = "http://www.w3.org/2003/01/geo/wgs84_pos#";
		model.setNsPrefix("pos", posNS);
		Property posLat = ResourceFactory.createProperty(posNS + "lat");
		Property posLong = ResourceFactory.createProperty(posNS + "long");
		
		// Currency amount
		String caNS = "http://www.omg.org/spec/EDMC-FIBO/FND/Accounting/CurrencyAmount/";
		model.setNsPrefix("ca", caNS);
		Property caHasAmount = ResourceFactory.createProperty(caNS + "hasAmount");
		
		for (Localidade localidade : localidades) 
		{
			model.createResource(myNS + localidade.getId())
			.addProperty(RDF.type, yagoPopulatedPlacesInEspíritoSanto)
			.addProperty(RDFS.label, localidade.getNome())
			.addProperty(posLat, ""+localidade.getLatitude())
			.addProperty(posLong, ""+localidade.getLongitude())
			.addProperty(caHasAmount, ""+notaFiscalService.valorTotalPorLocalidade(localidade.getId()));
			
			//.addProperty(RDFS.comment, pack.getDescription())
			//.addLiteral(gravailabilityStarts, ResourceFactory
			//.createTypedLiteral(df.format(pack.getBegin()), XSDDatatype.XSDdateTime))
			//.addLiteral(gravailabilityEnds, 
			//ResourceFactory.createTypedLiteral(df.format(pack.getEnd()), 
			//XSDDatatype.XSDdateTime))
			//.addProperty(grhasPriceSpecification, model.createResource()
			//.addProperty(RDF.type, grPriceSpecification)
			//.addLiteral(grhasCurrencyValue, pack.getPrice().floatValue()));
		}
		
		try (PrintWriter out = resp.getWriter()) {
			model.write(out, "RDF/XML");
		}
	}
}