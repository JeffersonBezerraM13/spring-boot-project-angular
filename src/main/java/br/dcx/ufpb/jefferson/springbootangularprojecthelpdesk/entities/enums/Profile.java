package br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.entities.enums;

public enum Profile {
    ADMIN(0,"ROLE_ADMIN"),
    CLIENT(1,"ROLE_CLIENT"),
    TECHNICAL(2,"ROLE_TECHNICAL");

    private Integer code;
    private String description;

    Profile(Integer valor, String role) {
        this.code = valor;
        this.description = role;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static Profile toEnum(Integer code){
        if(code == null){
            return null;
        }
        for(Profile p : Profile.values()){
            if(p.getCode().equals(code)){
                return p;
            }
        }
        throw new IllegalArgumentException("No enum constant with value: " + code);
    }
}
