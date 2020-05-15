package net.javaguides.springboot.springsecurity;

import static org.junit.Assert.assertEquals;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import io.specto.hoverfly.junit.core.SimulationSource;
import io.specto.hoverfly.junit.dsl.HoverflyDsl;
import io.specto.hoverfly.junit.dsl.ResponseCreators;
import io.specto.hoverfly.junit.rule.HoverflyRule;


//@RunWith(SpringRunner.class)
//@EnableAutoConfiguration
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,properties = "MOVIE_ENDPOINT=films")
//@ContextConfiguration(classes = UserServiceBoundaryTest.class)
public class UserServiceBoundaryTest {
    @Autowired
    private TestRestTemplate restTemplate;
        
    @ClassRule
  //  public static HoverflyRule rule = HoverflyRule
    //.inCaptureOrSimulationMode("movies.json", HoverflyConfig.localConfigs().proxyLocalHost()).printSimulationData();
    public static HoverflyRule rule = HoverflyRule.inSimulationMode(SimulationSource.dsl(HoverflyDsl.service("http://localhost:8081").get("/films").willReturn(ResponseCreators.success().body("[{\"id\":1,\"name\":\"Gori vatra\",\"description\":\"neki opis\",\"rating\":5.0,\"length\":123},{\"id\":2,\"name\":\"Nebo iznad krajolika\",\"description\":\"neki opis filma\",\"rating\":4.8,\"length\":136},{\"id\":3,\"name\":\"Gori vatra\",\"description\":\"neki opis\",\"rating\":5.0,\"length\":123},{\"id\":4,\"name\":\"Nebo iznad krajolika\",\"description\":\"neki opis filma\",\"rating\":4.8,\"length\":136},{\"id\":5,\"name\":\"Gori vatra\",\"description\":\"neki opis\",\"rating\":5.0,\"length\":123},{\"id\":6,\"name\":\"Nebo iznad krajolika\",\"description\":\"neki opis filma\",\"rating\":4.8,\"length\":136}]"))));
   // @Test
    public void test() {
    	//rule.simulate(SimulationSource.dsl(HoverflyDsl.service("http://localhost:8081").get("/films").willReturn(ResponseCreators.success().body("{\"id\":1,\"name\":\"Gori vatra\",\"description\":\"neki opis\",\"rating\":5.0,\"length\":123},{\"id\":2,\"name\":\"Nebo iznad krajolika\",\"description\":\"neki opis filma\",\"rating\":4.8,\"length\":136},{\"id\":3,\"name\":\"Gori vatra\",\"description\":\"neki opis\",\"rating\":5.0,\"length\":123},{\"id\":4,\"name\":\"Nebo iznad krajolika\",\"description\":\"neki opis filma\",\"rating\":4.8,\"length\":136},{\"id\":5,\"name\":\"Gori vatra\",\"description\":\"neki opis\",\"rating\":5.0,\"length\":123},{\"id\":6,\"name\":\"Nebo iznad krajolika\",\"description\":\"neki opis filma\",\"rating\":4.8,\"length\":136}{\"id\":1,\"name\":\"Gori vatra\",\"description\":\"neki opis\",\"rating\":5.0,\"length\":123},{\"id\":2,\"name\":\"Nebo iznad krajolika\",\"description\":\"neki opis filma\",\"rating\":4.8,\"length\":136},{\"id\":3,\"name\":\"Gori vatra\",\"description\":\"neki opis\",\"rating\":5.0,\"length\":123},{\"id\":4,\"name\":\"Nebo iznad krajolika\",\"description\":\"neki opis filma\",\"rating\":4.8,\"length\":136},{\"id\":5,\"name\":\"Gori vatra\",\"description\":\"neki opis\",\"rating\":5.0,\"length\":123},{\"id\":6,\"name\":\"Nebo iznad krajolika\",\"description\":\"neki opis filma\",\"rating\":4.8,\"length\":136}"))));
    	ResponseEntity<String> lista = restTemplate.getForEntity("http://localhost:8081/films", String.class);
    	ResponseEntity<String> lista2 = restTemplate.getForEntity("http://localhost:8082/films", String.class);
    	System.out.println("LISTA OBJEKATA " + lista);
    	assertEquals(HttpStatus.OK, lista.getStatusCode());
    	assertEquals(lista.getBody(),lista2.getBody());
    	
    }
}
