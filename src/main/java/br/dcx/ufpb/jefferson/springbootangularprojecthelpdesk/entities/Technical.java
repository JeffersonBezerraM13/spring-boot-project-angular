package br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Technical extends Person implements Serializable {
    private List<Call> calls = new ArrayList<Call>();

    public Technical() {
    }

    public Technical(Integer id, String name, String cpf, String email, String password) {
        super(id, name, cpf, email, password);
    }

    public List<Call> getCalls() {
        return calls;
    }

    public void setCalls(List<Call> calls) {
        this.calls = calls;
    }
}
