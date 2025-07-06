package br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.entities.enums;

public enum Priorit {
    LOW(0,"LOW"),
    MEDIUM(1,"MEDIUM"),
    HIGH(2,"HIGH");

    private Integer code;
    private String description;

    Priorit(Integer valor, String role) {
        this.code = valor;
        this.description = role;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static Priorit toEnum(Integer code){
        if(code == null){
            return null;
        }
        for(Priorit p : Priorit.values()){
            if(p.getCode().equals(code)){
                return p;
            }
        }
        throw new IllegalArgumentException("No enum constant with value: " + code);
    }
}
