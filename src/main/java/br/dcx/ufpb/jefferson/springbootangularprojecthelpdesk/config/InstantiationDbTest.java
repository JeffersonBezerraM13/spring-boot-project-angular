package br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.config;

import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.domain.entities.Call;
import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.domain.entities.Client;
import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.domain.entities.Technical;
import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.domain.entities.enums.Priority;
import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.domain.entities.enums.Profile;
import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.domain.entities.enums.Status;
import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.repositories.CallRepository;
import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.repositories.ClientRepository;
import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.repositories.PersonRepository;
import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.repositories.TechnicalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;

/**
 * Classe responsável por popular o banco de dados com dados de teste.
 * Essa configuração é executada automaticamente apenas quando o profile ativo é "test".
 */
@Configuration
@org.springframework.context.annotation.Profile("test") // Evita conflito com o profile principal da aplicação
public class InstantiationDbTest implements CommandLineRunner {

    @Autowired
    private CallRepository callRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private TechnicalRepository technicalRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    /**
     * Método executado automaticamente ao iniciar a aplicação, populando o banco com dados fictícios.
     *
     * @param args argumentos de inicialização (não utilizados aqui)
     */
    @Override
    public void run(String... args) throws Exception {
        clearDb(); // Remove dados anteriores para evitar duplicidade

        // Criação de técnicos
        Technical tech1 = new Technical(null, "Ana Green", "906.812.820-53", "ana@gmail.com", encoder.encode("+2Y}+uQqfH$aWin%"));
        tech1.addProfile(Profile.ADMIN);

        Technical tech2 = new Technical(null, "Bob Yellow", "917.489.110-31", "bob@gmail.com", encoder.encode("l)vg@!uB@K!:#QUW"));
        Technical tech3 = new Technical(null, "Carl Pink", "859.288.630-98", "carl@gmail.com", encoder.encode("scZ2qYmo;@m%ENQ7"));

        // Criação de clientes
        Client cli1 = new Client(null, "Dan Pink", "630.691.410-21", "dan@gmail.com", encoder.encode("^&OR#4fhaa5gXYJG"));
        cli1.addProfile(Profile.ADMIN); // Cliente com perfil de admin também

        Client cli2 = new Client(null, "Emma Black", "757.722.020-57", "emma@gmail.com", encoder.encode("C:~`F_|4t({__FG:"));
        Client cli3 = new Client(null, "Finn Grey", "983.680.740-37", "finn@gmail.com", encoder.encode("1j]g_;.v944+N(g|"));

        // Criação de chamados
        Call c1 = new Call(null, Priority.LOW, Status.OPEN, "Chamado 01", "Primeiro chamado", tech1, cli1);
        Call c2 = new Call(null, Priority.HIGH, Status.IN_PROGRESS, "Chamado 02", "Segundo chamado", tech1, cli2);

        // Persistência no banco
        technicalRepository.saveAll(Arrays.asList(tech1, tech2, tech3));
        clientRepository.saveAll(Arrays.asList(cli1, cli2, cli3));
        callRepository.saveAll(Arrays.asList(c1, c2));
    }

    /**
     * Limpa completamente o banco de dados antes de popular com novos dados.
     */
    private void clearDb() {
        callRepository.deleteAll();
        clientRepository.deleteAll();
        personRepository.deleteAll();
        technicalRepository.deleteAll();
    }
}
