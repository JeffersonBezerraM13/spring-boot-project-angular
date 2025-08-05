package br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.domain.entities.dtos;


//da pra virar um record
public class CredentialsDTO {
    private String email;
    private String password;

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
}
