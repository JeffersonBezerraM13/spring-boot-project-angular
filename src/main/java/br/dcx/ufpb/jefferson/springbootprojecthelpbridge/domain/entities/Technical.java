package br.dcx.ufpb.jefferson.springbootprojecthelpbridge.domain.entities;

import br.dcx.ufpb.jefferson.springbootprojecthelpbridge.domain.entities.dtos.TechnicalDTO;
import br.dcx.ufpb.jefferson.springbootprojecthelpbridge.domain.entities.enums.Profile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Technical extends Person implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @OneToMany(mappedBy = "technical")
    private List<Call> calls = new ArrayList<Call>();

    public Technical() {
        addProfile(Profile.CLIENT);
    }

    public Technical(Integer id, String name, String cpf, String email, String password) {
        super(id, name, cpf, email, password);
        addProfile(Profile.CLIENT);
    }

    public Technical(TechnicalDTO technicalDTO) {
        this.id = technicalDTO.getId();
        this.name = technicalDTO.getName();
        this.cpf = technicalDTO.getCpf();
        this.email = technicalDTO.getEmail();
        this.password = technicalDTO.getPassword();
        this.profiles = technicalDTO.getProfiles().stream().map(Profile::getCode).collect(Collectors.toSet());
        this.created = technicalDTO.getCreated();
    }

    public List<Call> getCalls() {
        return calls;
    }

    public void setCalls(List<Call> calls) {
        this.calls = calls;
    }
}
