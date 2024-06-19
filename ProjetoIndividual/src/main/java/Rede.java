import java.time.LocalDateTime;

public class Rede {
    private Integer id;
    private Long velocidade;
    private LocalDateTime dataHora;

    public Rede(){

    }

    public Rede(Integer id, Long velocidade, LocalDateTime dataHora) {
        this.id = id;
        this.velocidade = velocidade;
        this.dataHora = dataHora;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(Long velocidade) {
        this.velocidade = velocidade;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    @Override
    public String toString() {
        return "Rede{" +
                "id=" + id +
                ", velocidade=" + velocidade +
                ", dataHora=" + dataHora +
                '}';
    }
}
