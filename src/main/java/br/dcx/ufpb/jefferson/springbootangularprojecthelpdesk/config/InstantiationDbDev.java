package br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.config;

import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.domain.entities.Call;
import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.domain.entities.Client;
import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.domain.entities.Technical;
import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.domain.entities.enums.Priorit;
import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.domain.entities.enums.Status;
import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.repositories.CallRepository;
import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.repositories.ClientRepository;
import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.repositories.PersonRepository;
import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.repositories.TechnicalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class InstantiationDbDev implements CommandLineRunner {
    @Autowired
    private CallRepository callRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private TechnicalRepository technicalRepository;

    @Override
    public void run(String... args) throws Exception {
        cleanDb();

        Technical tech1 = new Technical(null,"Bob Green","906.812.820-53","bob@gmail.com","123");
        tech1.addProfile(br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.domain.entities.enums.Profile.ADMIN);

        Client cli1 = new Client(null,"Ana Pink", "859.288.630-98","ana@gmail.com,","123");

        Call c1 = new Call(null, Priorit.MEDIUM, Status.IN_PROGRESS,"Chamado 01","Primeiro chamado",tech1,cli1);

        technicalRepository.save(tech1);
        clientRepository.save(cli1);
        callRepository.save(c1);
    }

    private void cleanDb() {
        callRepository.deleteAll();
        clientRepository.deleteAll();
        personRepository.deleteAll();
        technicalRepository.deleteAll();
    }
}
