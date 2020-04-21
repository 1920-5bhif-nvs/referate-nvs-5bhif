package airhacks;


import io.quarkus.test.junit.QuarkusTest;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class HelloResourceTest {

    @Inject
    @RestClient
    HelloResource helloResource;


    @Test
    public void Hello() {
        String content = helloResource.content();
        System.out.println(content);
        assertThat(content).isEqualTo("hello");
        //-- JUnit
       // assertEquals(content,"hello");
    }

}
