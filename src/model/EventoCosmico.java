package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import model.enums.TipoEventoCosmico;

public class EventoCosmico {

    private Integer idEventoCosmico;
    private String nome;
    private TipoEventoCosmico tipo;
    private LocalDate dataEvento;
    private LocalTime oraEvento;
    private Integer idStella;

    /**
     * Object constructor.
     *
     * @param idEventoCosmico
     * @param nome
     * @param tipo
     * @param dataEvento
     * @param oraEvento
     * @param idStella
     */
    public EventoCosmico(Integer idEventoCosmico, String nome, TipoEventoCosmico tipo,
            LocalDate dataEvento, LocalTime oraEvento, Integer idStella) {
        this.idEventoCosmico = idEventoCosmico;
        this.nome = nome;
        this.tipo = tipo;
        this.dataEvento = dataEvento;
        this.oraEvento = oraEvento;
        this.idStella = idStella;
    }

    public Integer getIdEventoCosmico() {
        return idEventoCosmico;
    }

    public void setIdEventoCosmico(Integer idEventoCosmico) {
        this.idEventoCosmico = idEventoCosmico;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoEventoCosmico getTipo() {
        return tipo;
    }

    public void setTipo(TipoEventoCosmico tipo) {
        this.tipo = tipo;
    }

    public LocalDate getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(LocalDate dataEvento) {
        this.dataEvento = dataEvento;
    }

    public LocalTime getOraEvento() {
        return oraEvento;
    }

    public void setOraEvento(LocalTime oraEvento) {
        this.oraEvento = oraEvento;
    }

    public Integer getIdStella() {
        return idStella;
    }

    public void setIdStella(Integer idStella) {
        this.idStella = idStella;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.idEventoCosmico);
        hash = 17 * hash + Objects.hashCode(this.nome);
        hash = 17 * hash + Objects.hashCode(this.tipo);
        hash = 17 * hash + Objects.hashCode(this.dataEvento);
        hash = 17 * hash + Objects.hashCode(this.oraEvento);
        hash = 17 * hash + Objects.hashCode(this.idStella);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EventoCosmico other = (EventoCosmico) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.idEventoCosmico, other.idEventoCosmico)) {
            return false;
        }
        if (this.tipo != other.tipo) {
            return false;
        }
        if (!Objects.equals(this.dataEvento, other.dataEvento)) {
            return false;
        }
        if (!Objects.equals(this.oraEvento, other.oraEvento)) {
            return false;
        }
        return Objects.equals(this.idStella, other.idStella);
    }

    @Override
    public String toString() {
        return "EventoCosmico{" + "idEventoCosmico=" + idEventoCosmico + ", nome=" + nome + ", tipo=" + tipo + ", dataEvento=" + dataEvento + ", oraEvento=" + oraEvento + ", idStella=" + idStella + '}';
    }
}
