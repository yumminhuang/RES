package model.producer;

import static org.odata4j.examples.JaxRsImplementation.JERSEY;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.odata4j.examples.ODataServerFactory;
import org.odata4j.examples.producer.jpa.DatabaseUtils;
import org.odata4j.producer.jpa.JPAProducer;
import org.odata4j.producer.resources.DefaultODataProducerProvider;

public class Main{
	
	public static void main(String[] args) {
	    Main example = new Main();
	    example.run(args);
	  }

	  private void run(String[] args) {

	    String endpointUri = "http://localhost:8886/res.svc/";
	    
	    String persistenceUnitName = "RES_SERVER";
	    String namespace = "Oh!Data";
	    EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnitName);

	    JPAProducer producer = new JPAProducer(emf, namespace, 50);
	    DatabaseUtils.fillDatabase(namespace.toLowerCase(), "/META-INF/persistence.xml");

	    // register the producer as the static instance, then launch the http server
	    DefaultODataProducerProvider.setInstance(producer);
	    new ODataServerFactory(JERSEY).hostODataServer(endpointUri);

	  }
}