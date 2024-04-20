package Model;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.github.britooo.looca.api.group.discos.Volume;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.group.sistema.Sistema;

import java.util.*;

public class Totem {
    private String codigoTotem;

    Looca looca = new Looca();

    private final Sistema sistema = looca.getSistema();
    private final Memoria memoria = looca.getMemoria();
    private final Processador processador = looca.getProcessador();
    private final DiscoGrupo grupoDisco = looca.getGrupoDeDiscos();

    public Totem(String codigoTotem) {
        this.codigoTotem = codigoTotem;
    }

    public String getCodigoTotem() {
        return codigoTotem;
    }

    public void setCodigoTotem(String codigoTotem) {
        this.codigoTotem = codigoTotem;
    }

    //getters Sistema

    public String getSistemaOperacional() {
        return sistema.getSistemaOperacional();
    }

    public Long getSistemaTempoDeAtividade() {
        return sistema.getTempoDeAtividade();
    }

    public String getSistemaFabricante() {
        return sistema.getFabricante();
    }

    //getters memoria

    public Long getMemoriaTotal() {
        return  memoria.getTotal();
    }

    public Long getMemoriaDisponivel() {
        return memoria.getDisponivel();
    }

    public Long getMemoriaEmUso() {
        return  memoria.getEmUso();
    }

    //getters processador

    public String getProcessadorFabricante() {
        return processador.getFabricante();
    }

    public Integer getProcessadorCpusFisicas() {
        return  processador.getNumeroCpusFisicas();
    }

    public Integer getProcessadorCpusLogicas() {
        return processador.getNumeroCpusLogicas();
    }

    public Long getProcessadorFrequencia() {
        return processador.getFrequencia();
    }

    public Double getProcessadorUso() {
        return processador.getUso();
    }

    //getters discos e volumes

    public List<String> getModelosDiscos() {
        List<String> modelosDiscos = new ArrayList<>();
        for (Disco disco : grupoDisco.getDiscos()) {
            modelosDiscos.add(disco.getModelo());
        }
        return modelosDiscos;
    }

    public Integer getQuantidadeDeDiscos() {
        return grupoDisco.getQuantidadeDeDiscos();
    }

    public Map<String,Long> getPorcentagemUsoVolumes() {
        Map<String, Long> mapaPorcentagemUsoVolumes = new TreeMap<>();
        for (Volume volume : grupoDisco.getVolumes()) {
            Long emUso = volume.getTotal() - volume.getDisponivel();
            Long porcentagemVolumeUso = emUso * 100/ volume.getTotal();
            mapaPorcentagemUsoVolumes.put(volume.getNome(),porcentagemVolumeUso);
        }
        return mapaPorcentagemUsoVolumes;
    }

    public Map<String, Long> getLeituraDosDiscos() {
        Map<String, Long> mapaLeituraDosDiscos = new HashMap<>();
        for (Disco disco : grupoDisco.getDiscos()) {
            mapaLeituraDosDiscos.put(disco.getNome(),disco.getLeituras());
        }
        return mapaLeituraDosDiscos;
    }

    public Map<String, Long> getEscritaDosDiscos() {
        Map<String, Long> mapaLeituraDosDiscos = new HashMap<>();
        for (Disco disco : grupoDisco.getDiscos()) {
            mapaLeituraDosDiscos.put(disco.getNome(),disco.getEscritas());
        }
        return mapaLeituraDosDiscos;
    }
}
