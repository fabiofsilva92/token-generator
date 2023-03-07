package gft.estudo.apitemalivre.enums;

public enum RoleEnum {
    ADMIN(1, "ROLE_ADMIN"),
    USUARIO(2, "ROLE_USUARIO");

    private int cod;
    private String descricao;

    RoleEnum(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


    public static RoleEnum toEnum(Integer cod){
        if(cod == null) return null;
        for (RoleEnum x : RoleEnum.values()){
            if (cod.equals(x.getCod())) return x;
        }
        throw new IllegalArgumentException("Id inválido: "+cod);
    }
}
