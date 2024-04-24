package Model;

public class HardWare {
    private Integer id;
    private String tipo;
    private Integer fkTotem;

    public HardWare(Integer id, String tipo, Integer fkTotem) {
        this.id = id;
        this.tipo = tipo;
        this.fkTotem = fkTotem;
    }

    public HardWare() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getFkTotem() {
        return fkTotem;
    }

    public void setFkTotem(Integer fkTotem) {
        this.fkTotem = fkTotem;
    }

    @Override
    public String toString() {
        return """
                ID do hardware: %d
                tipo: %s
                FK do totem: %d
                """.formatted(this.id, this.tipo, this.fkTotem);
    }
}
