package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import model.enums.TipoEventoCosmico;

public class EventoCosmico {

    private int idEventoCosmico;
    private String nome;
    private TipoEventoCosmico tipo;
    private LocalDate dataEvento;
    private LocalTime oraEvento;
    private int idStella;

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
    public EventoCosmico(int idEventoCosmico, String nome, TipoEventoCosmico tipo,
            LocalDate dataEvento, LocalTime oraEvento, int idStella) {
        this.idEventoCosmico = idEventoCosmico;
        this.nome = nome;
        this.tipo = tipo;
        this.dataEvento = dataEvento;
        this.oraEvento = oraEvento;
        this.idStella = idStella;
    }

    public int getIdEventoCosmico() {
        return idEventoCosmico;
    }

    public void setIdEventoCosmico(int idEventoCosmico) {
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

    public int getIdStella() {
        return idStella;
    }

    public void setIdStella(int idStella) {
        this.idStella = idStella;
    }

    @Override
    public String toString() {
        return "EventoCosmico [idEventoCosmico=" + idEventoCosmico + ", nome=" + nome
                + ", tipo=" + tipo + ", dataEvento=" + dataEvento
                + ", oraEvento=" + oraEvento + ", idStella=" + idStella + "]";
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + this.idEventoCosmico;
        hash = 41 * hash + Objects.hashCode(this.nome);
        hash = 41 * hash + Objects.hashCode(this.tipo);
        hash = 41 * hash + Objects.hashCode(this.dataEvento);
        hash = 41 * hash + Objects.hashCode(this.oraEvento);
        hash = 41 * hash + this.idStella;
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
        if (this.idEventoCosmico != other.idEventoCosmico) {
            return false;
        }
        if (this.idStella != other.idStella) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (this.tipo != other.tipo) {
            return false;
        }
        if (!Objects.equals(this.dataEvento, other.dataEvento)) {
            return false;
        }
        return Objects.equals(this.oraEvento, other.oraEvento);
    }

}
