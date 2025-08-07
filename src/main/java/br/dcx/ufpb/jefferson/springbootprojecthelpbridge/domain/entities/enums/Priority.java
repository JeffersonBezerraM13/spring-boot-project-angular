package br.dcx.ufpb.jefferson.springbootprojecthelpbridge.domain.entities.enums;

public enum Priority {
    LOW(0,"LOW"),
    MEDIUM(1,"MEDIUM"),
    HIGH(2,"HIGH");

    private Integer code;
    private String description;

    Priority(Integer valor, String role) {
        this.code = valor;
        this.description = role;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static Priority toEnum(Integer code){
        if(code == null){
            return null;
        }
        for(Priority p : Priority.values()){
            if(p.getCode().equals(code)){
                return p;
            }
        }
        throw new IllegalArgumentException("No enum constant with value: " + code);
    }
}
