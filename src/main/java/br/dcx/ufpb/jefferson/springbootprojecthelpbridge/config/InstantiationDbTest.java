package br.dcx.ufpb.jefferson.springbootprojecthelpbridge.config;

import br.dcx.ufpb.jefferson.springbootprojecthelpbridge.services.DatabaseSeederService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

/**
 * Classe responsável por popular o banco de dados com dados de teste.
 * Essa configuração é executada automaticamente apenas quando o profile ativo é "test".
 */
@Configuration
@org.springframework.context.annotation.Profile("test") // Evita conflito com o profile principal da aplicação
public class InstantiationDbTest implements CommandLineRunner {

    private final DatabaseSeederService databaseSeederService;

    public InstantiationDbTest(DatabaseSeederService databaseSeederService) {
        this.databaseSeederService = databaseSeederService;
    }

    @Override
    public void run(String... args) throws Exception {
        databaseSeederService.populateDatabase();
    }
}