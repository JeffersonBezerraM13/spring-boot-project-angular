package br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.domain.entities;

import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.domain.entities.enums.Profile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

    public List<Call> getCalls() {
        return calls;
    }

    public void setCalls(List<Call> calls) {
        this.calls = calls;
    }
}
