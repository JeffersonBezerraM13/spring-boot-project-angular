package br.dcx.ufpb.jefferson.springbootprojecthelpbridge.services;

import br.dcx.ufpb.jefferson.springbootprojecthelpbridge.domain.entities.Call;
import br.dcx.ufpb.jefferson.springbootprojecthelpbridge.domain.entities.Client;
import br.dcx.ufpb.jefferson.springbootprojecthelpbridge.domain.entities.Technical;
import br.dcx.ufpb.jefferson.springbootprojecthelpbridge.domain.entities.enums.Priority;
import br.dcx.ufpb.jefferson.springbootprojecthelpbridge.domain.entities.enums.Profile;
import br.dcx.ufpb.jefferson.springbootprojecthelpbridge.domain.entities.enums.Status;
import br.dcx.ufpb.jefferson.springbootprojecthelpbridge.repositories.CallRepository;
import br.dcx.ufpb.jefferson.springbootprojecthelpbridge.repositories.ClientRepository;
import br.dcx.ufpb.jefferson.springbootprojecthelpbridge.repositories.PersonRepository;
import br.dcx.ufpb.jefferson.springbootprojecthelpbridge.repositories.TechnicalRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DatabaseSeederService {

    private final CallRepository callRepository;
    private final ClientRepository clientRepository;
    private final PersonRepository personRepository;
    private final TechnicalRepository technicalRepository;
    private final BCryptPasswordEncoder encoder;

    public DatabaseSeederService(
            CallRepository callRepository,
            ClientRepository clientRepository,
            PersonRepository personRepository,
            TechnicalRepository technicalRepository,
            BCryptPasswordEncoder encoder
    ) {
        this.callRepository = callRepository;
        this.clientRepository = clientRepository;
        this.personRepository = personRepository;
        this.technicalRepository = technicalRepository;
        this.encoder = encoder;
    }

    public void populateDatabase() {
        clearDb();

        // Técnicos
        Technical tech1 = new Technical(null, "Ana Green", "906.812.820-53", "ana@gmail.com", encoder.encode("+2Y}+uQqfH$aWin%"));
        tech1.addProfile(Profile.ADMIN);

        Technical tech2 = new Technical(null, "Bob Yellow", "917.489.110-31", "bob@gmail.com", encoder.encode("l)vg@!uB@K!:#QUW"));
        Technical tech3 = new Technical(null, "Carl Pink", "859.288.630-98", "carl@gmail.com", encoder.encode("scZ2qYmo;@m%ENQ7"));

        // Clientes
        Client cli1 = new Client(null, "Dan Pink", "630.691.410-21", "dan@gmail.com", encoder.encode("^&OR#4fhaa5gXYJG"));
        Client cli2 = new Client(null, "Emma Black", "757.722.020-57", "emma@gmail.com", encoder.encode("C:~`F_|4t({__FG:"));
        Client cli3 = new Client(null, "Finn Grey", "983.680.740-37", "finn@gmail.com", encoder.encode("1j]g_;.v944+N(g|"));

        // Chamados
        Call c1 = new Call(null, Priority.LOW, Status.OPEN, "Chamado 01", "Primeiro chamado", tech1, cli1);
        Call c2 = new Call(null, Priority.HIGH, Status.IN_PROGRESS, "Chamado 02", "Segundo chamado", tech1, cli2);

        // Persistência
        technicalRepository.saveAll(Arrays.asList(tech1, tech2, tech3));
        clientRepository.saveAll(Arrays.asList(cli1, cli2, cli3));
        callRepository.saveAll(Arrays.asList(c1, c2));
    }

    private void clearDb() {
        callRepository.deleteAll();
        clientRepository.deleteAll();
        personRepository.deleteAll();
        technicalRepository.deleteAll();
    }
}
