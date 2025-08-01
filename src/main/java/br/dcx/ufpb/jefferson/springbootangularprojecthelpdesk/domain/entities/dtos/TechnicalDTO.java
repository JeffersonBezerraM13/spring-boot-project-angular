package br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.domain.entities.dtos;

import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.domain.entities.Technical;
import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.domain.entities.enums.Profile;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class TechnicalDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private String cpf;
    private String email;
    private String password;
    private Set<Integer> profiles;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate created = LocalDate.now();

    public TechnicalDTO() {
    }

    public TechnicalDTO(Technical technical) {
        this.id = technical.getId();
        this.name = technical.getName();
        this.cpf = technical.getCpf();
        this.email = technical.getEmail();
        this.password = technical.getPassword();
        this.profiles = technical.getProfiles().stream().map(Profile::getCode).collect(Collectors.toSet());
        this.created = technical.getCreated();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Profile> getProfiles() {
        return this.profiles.stream().map(Profile::toEnum).collect(Collectors.toSet());
    }

    public void addProfile(Profile profile) {
        this.profiles.add(profile.getCode());
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }
}
