package br.dcx.ufpb.jefferson.springbootprojecthelpbridge.domain.entities.dtos;

import br.dcx.ufpb.jefferson.springbootprojecthelpbridge.domain.entities.Client;
import br.dcx.ufpb.jefferson.springbootprojecthelpbridge.domain.entities.enums.Profile;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Schema(name = "ClientDTO", description = "Data transfer object para Clientes")
public class ClientDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "ID do cliente", example = "1")
    private Integer id;

    @Schema(description = "Nome completo do cliente", example = "Albert Einstein", required = true)
    @NotNull(message = "O campo NOME é requirido")
    @NotBlank(message = "O campo NOME não pode ser vazio")
    private String name;

    @Schema(description = "CPF do cliente", example = "123.456.789-00", required = true)
    @NotNull(message = "O campo CPF é requirido")
    @NotBlank(message = "O campo CPF não pode ser vazio")
    private String cpf;

    @Schema(description = "Email do cliente", example = "albert.einstein@email.com", required = true)
    @NotNull(message = "O campo EMAIL é requirido")
    @NotBlank(message = "O campo EMAIL não pode ser vazio")
    private String email;

    @Schema(description = "Senha do cliente", example = "senhaSegura123", required = true)
    @NotNull(message = "O campo SENHA é requirido")
    @NotBlank(message = "O campo SENHA não pode ser vazio")
    private String password;

    @Schema(description = "Conjunto de perfis do cliente, representados por códigos inteiros", example = "[1, 2]")
    private Set<Integer> profiles = new HashSet<>();

    @Schema(description = "Data de criação do registro", example = "25/07/2025", type = "string", format = "date")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate created = LocalDate.now();

    public ClientDTO() {
        addProfile(Profile.CLIENT);
    }

    public ClientDTO(Client Client) {
        this.id = Client.getId();
        this.name = Client.getName();
        this.cpf = Client.getCpf();
        this.email = Client.getEmail();
        this.password = Client.getPassword();
        this.profiles = Client.getProfiles().stream().map(Profile::getCode).collect(Collectors.toSet());
        this.created = Client.getCreated();
        addProfile(Profile.CLIENT);
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

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ClientDTO that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getName(), that.getName()) && Objects.equals(getCpf(), that.getCpf()) && Objects.equals(getEmail(), that.getEmail()) && Objects.equals(getPassword(), that.getPassword()) && Objects.equals(getProfiles(), that.getProfiles()) && Objects.equals(getCreated(), that.getCreated());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getCpf(), getEmail(), getPassword(), getProfiles(), getCreated());
    }
}
