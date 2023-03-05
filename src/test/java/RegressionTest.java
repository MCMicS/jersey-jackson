import de.mcmics.ToDo;
import de.mcmics.ToDoService;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class RegressionTest {

    private static final Logger logger = Logger.getLogger(RegressionTest.class.getName());

    @Test
    void getToDoFromJsonPlaceholderWihtoutJackson() {
        ResteasyClientBuilder builder = new ResteasyClientBuilder();
        final ResteasyClient resteasyClient = builder
                .connectionPoolSize(60)
                .build();
        final ResteasyWebTarget target = resteasyClient.target("https://jsonplaceholder.typicode.com/");
        final ToDoService toDoService = target.proxy(ToDoService.class);
        final String toDo = toDoService.getAtAsString(1);
        logger.info(toDo.toString());
        final String expected = """
                {
                  "userId": 1,
                  "id": 1,
                  "title": "delectus aut autem",
                  "completed": false
                }""";
        assertEquals(expected, toDo);
    }

    @Test
    void getToDoFromJsonPlaceholder() {
        ResteasyClientBuilder builder = new ResteasyClientBuilder();
        final ResteasyClient resteasyClient = builder
                .register(new JacksonFeature())
                .connectionPoolSize(60)
                .build();
        final ResteasyWebTarget target = resteasyClient.target("https://jsonplaceholder.typicode.com/");
        final ToDoService toDoService = target.proxy(ToDoService.class);
        final ToDo toDo = toDoService.getAt(1);
        logger.info(toDo.toString());
        assertEquals(1, toDo.getId());
        assertEquals(1, toDo.getUserId());
        assertEquals("delectus aut autem", toDo.getTitle());
    }
}
