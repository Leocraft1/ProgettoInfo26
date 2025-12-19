package model;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author loren
 */
public class EventoCosmico {

    private int idEvento;
    private TipoEventoCosmico tipo;
    private LocalDate dataEvento;
    private Double energiaRilasciata;
    private Integer idStella;

    public EventoCosmico() {
    }

    public EventoCosmico(int idEvento, TipoEventoCosmico tipo,
            LocalDate dataEvento, Double energiaRilasciata,
            Integer idStella) {
        this.idEvento = idEvento;
        this.tipo = tipo;
        this.dataEvento = dataEvento;
        this.energiaRilasciata = energiaRilasciata;
        this.idStella = idStella;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
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

    public Double getEnergiaRilasciata() {
        return energiaRilasciata;
    }

    public void setEnergiaRilasciata(Double energiaRilasciata) {
        this.energiaRilasciata = energiaRilasciata;
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
        hash = 97 * hash + this.idEvento;
        hash = 97 * hash + Objects.hashCode(this.tipo);
        hash = 97 * hash + Objects.hashCode(this.dataEvento);
        hash = 97 * hash + Objects.hashCode(this.energiaRilasciata);
        hash = 97 * hash + Objects.hashCode(this.idStella);
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
        if (this.idEvento != other.idEvento) {
            return false;
        }
        if (this.tipo != other.tipo) {
            return false;
        }
        if (!Objects.equals(this.dataEvento, other.dataEvento)) {
            return false;
        }
        if (!Objects.equals(this.energiaRilasciata, other.energiaRilasciata)) {
            return false;
        }
        return Objects.equals(this.idStella, other.idStella);
    }

    @Override
    public String toString() {
        return "EventoCosmico{" + "idEvento=" + idEvento + ", tipo=" + tipo + ", dataEvento=" + dataEvento + ", energiaRilasciata=" + energiaRilasciata + ", idStella=" + idStella + '}';
    }

}
