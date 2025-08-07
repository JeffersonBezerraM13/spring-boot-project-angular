package br.dcx.ufpb.jefferson.springbootprojecthelpbridge.config;

import br.dcx.ufpb.jefferson.springbootprojecthelpbridge.services.DatabaseSeederService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

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
