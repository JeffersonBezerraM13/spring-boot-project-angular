package br.dcx.ufpb.jefferson.springbootprojecthelpbridge.domain.entities;

import br.dcx.ufpb.jefferson.springbootprojecthelpbridge.domain.entities.dtos.ClientDTO;
import br.dcx.ufpb.jefferson.springbootprojecthelpbridge.domain.entities.enums.Profile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Client extends Person implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private List<Call> calls = new ArrayList<Call>();

    public Client() {
        addProfile(Profile.CLIENT);
    }

    public Client(Integer id, String name, String cpf, String email, String password) {
        super(id, name, cpf, email, password);
        addProfile(Profile.CLIENT);
    }

    public Client(ClientDTO ClientDTO) {
        this.id = ClientDTO.getId();
        this.name = ClientDTO.getName();
        this.cpf = ClientDTO.getCpf();
        this.email = ClientDTO.getEmail();
        this.password = ClientDTO.getPassword();
        this.profiles = ClientDTO.getProfiles().stream().map(Profile::getCode).collect(Collectors.toSet());
        this.created = ClientDTO.getCreated();
    }

    public List<Call> getCalls() {
        return calls;
    }

    public void setCalls(List<Call> calls) {
        this.calls = calls;
    }
}
