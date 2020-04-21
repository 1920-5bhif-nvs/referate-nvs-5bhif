package at.htl;

import at.htl.TestContainersPostgresqlTestResource.Initializer;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.HashMap;
import java.util.Map;

@QuarkusTestResource(Initializer.class)
public class TestContainersPostgresqlTestResource {

    public static class Initializer implements QuarkusTestResourceLifecycleManager {

        private PostgreSQLContainer postgreSQLContainer;

        @Override
        public Map<String, String> start() {
            this.postgreSQLContainer = new PostgreSQLContainer<>("postgres");
            this.postgreSQLContainer.start();

            return configurationParameters();
        }

        private Map<String, String> configurationParameters() {
            final Map<String,String> conf = new HashMap<>();
            conf.put("quarkus.datasource.url",this.postgreSQLContainer.getJdbcUrl());
            System.out.println(this.postgreSQLContainer.getJdbcUrl());
            conf.put("quarkus.datasource.username",this.postgreSQLContainer.getUsername());
            conf.put("quarkus.datasource.password", this.postgreSQLContainer.getPassword());
            System.out.println(this.postgreSQLContainer.getUsername());
            return conf;
        }

        @Override
        public void stop() {
                if (this.postgreSQLContainer != null){
                    this.postgreSQLContainer.close();
                }
        }

    }
}
