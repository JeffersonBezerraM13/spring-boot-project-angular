package br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.config;

import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.services.DatabaseSeederService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@org.springframework.context.annotation.Profile("dev") // Evita conflito com o profile principal da aplicação
public class InstantiationDbDev implements CommandLineRunner {

    private final DatabaseSeederService databaseSeederService;

    public InstantiationDbDev(DatabaseSeederService databaseSeederService) {
        this.databaseSeederService = databaseSeederService;
    }

    @Override
    public void run(String... args) throws Exception {
        databaseSeederService.populateDatabase();
    }
}
