package br.dcx.ufpb.jefferson.springbootprojecthelpbridge.services;

import br.dcx.ufpb.jefferson.springbootprojecthelpbridge.domain.entities.Person;
import br.dcx.ufpb.jefferson.springbootprojecthelpbridge.domain.entities.Client;
import br.dcx.ufpb.jefferson.springbootprojecthelpbridge.domain.entities.dtos.ClientDTO;
import br.dcx.ufpb.jefferson.springbootprojecthelpbridge.repositories.PersonRepository;
import br.dcx.ufpb.jefferson.springbootprojecthelpbridge.repositories.ClientRepository;
import br.dcx.ufpb.jefferson.springbootprojecthelpbridge.services.exceptions.DataIntegrityViolationException;
import br.dcx.ufpb.jefferson.springbootprojecthelpbridge.services.exceptions.ObjectNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository ClientRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;

    public List<Client> findAll() {
        return ClientRepository.findAll();
    }

    public Client findById(Integer id) {
        return ClientRepository
                .findById(id)
                .orElseThrow(
                        () -> new ObjectNotFoundException("Client not found, id: " + id)
                );
    }

    public Client create(ClientDTO obj){
        obj.setId(null); //nullando o id para que o repository nao entenda que o id possa ser uma atualização

        //encryptando a senha
        obj.setPassword(encoder.encode(obj.getPassword()));

        validatedByCpfAndEmail(obj);
        Client newObj = new Client(obj);
        //.save() é uma chamada assincrona, ele vai lá no banco primeiro
        return ClientRepository.save(newObj);
    }

    public Client update(Integer id, @Valid ClientDTO objDTO) {
        objDTO.setId(id);
        Client oldObj = findById(id);
        validatedByCpfAndEmail(objDTO); //passou dessa linha, não lançou nenhuma exception
        oldObj = new Client(objDTO);
        return ClientRepository.save(oldObj);
    }

    public void delete(Integer id) {
        Client obj = findById(id);
        if(obj.getCalls().size() > 0){
            throw new DataIntegrityViolationException("Cliente possui ordens de serviço e NÃO PODE SER DELETADO");
        }
        ClientRepository.deleteById(id);
    }

    private void validatedByCpfAndEmail(ClientDTO objDto) {
        Optional<Person> person = personRepository.findByCpf(objDto.getCpf());
        if(person.isPresent() && person.get().getId() != objDto.getId()) {
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema");
        }
        Optional<Person> person2 = personRepository.findByEmail(objDto.getEmail());
        if(person2.isPresent() && person2.get().getId() != objDto.getId()) {
            throw new DataIntegrityViolationException("Email já cadastrado no sistema");
        }
    }
}
