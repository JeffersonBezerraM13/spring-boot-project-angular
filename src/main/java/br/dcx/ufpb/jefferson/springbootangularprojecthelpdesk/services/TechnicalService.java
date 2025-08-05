package br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.services;

import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.domain.entities.Person;
import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.domain.entities.Technical;
import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.domain.entities.dtos.TechnicalDTO;
import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.repositories.PersonRepository;
import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.repositories.TechnicalRepository;
import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.services.exceptions.DataIntegrityViolationException;
import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.services.exceptions.ObjectNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TechnicalService {

    @Autowired
    private TechnicalRepository technicalRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;

    public List<Technical> findAll() {
        return technicalRepository.findAll();
    }

    public Technical findById(Integer id) {
        return technicalRepository
                .findById(id)
                .orElseThrow(
                        () -> new ObjectNotFoundException("Technical not found, id: " + id)
                );
    }

    public Technical create(TechnicalDTO obj){
        obj.setId(null); //nullando o id para que o repository nao entenda que o id possa ser uma atualização
        validatedByCpfAndEmail(obj);

        //encryptando a senha
        obj.setPassword(encoder.encode(obj.getPassword()));

        Technical newObj = new Technical(obj);
        //.save() é uma chamada assincrona, ele vai lá no banco primeiro
        return technicalRepository.save(newObj);
    }

    public Technical update(Integer id, @Valid TechnicalDTO objDTO) {
        objDTO.setId(id);
        Technical oldObj = findById(id);
        validatedByCpfAndEmail(objDTO); //passou dessa linha, não lançou nenhuma exception
        oldObj = new Technical(objDTO);
        return technicalRepository.save(oldObj);
    }

    public void delete(Integer id) {
        Technical obj = findById(id);
        if(obj.getCalls().size() > 0){
            throw new DataIntegrityViolationException("Técnico possui ordens de serviço e NÃO PODE SER DELETADO");
        }
        technicalRepository.deleteById(id);
    }

    private void validatedByCpfAndEmail(TechnicalDTO objDto) {
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
