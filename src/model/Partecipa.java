package model;

import java.util.Objects;

/**
 *
 * @author loren
 */
public class Partecipa {

    private int idMissione;
    private int idAstronauta;
    private String ruolo;

    public Partecipa() {
    }

    public Partecipa(int idMissione, int idAstronauta, String ruolo) {
        this.idMissione = idMissione;
        this.idAstronauta = idAstronauta;
        this.ruolo = ruolo;
    }

    public int getIdMissione() {
        return idMissione;
    }

    public void setIdMissione(int idMissione) {
        this.idMissione = idMissione;
    }

    public int getIdAstronauta() {
        return idAstronauta;
    }

    public void setIdAstronauta(int idAstronauta) {
        this.idAstronauta = idAstronauta;
    }

    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + this.idMissione;
        hash = 71 * hash + this.idAstronauta;
        hash = 71 * hash + Objects.hashCode(this.ruolo);
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
        final Partecipa other = (Partecipa) obj;
        if (this.idMissione != other.idMissione) {
            return false;
        }
        if (this.idAstronauta != other.idAstronauta) {
            return false;
        }
        return Objects.equals(this.ruolo, other.ruolo);
    }

    @Override
    public String toString() {
        return "Partecipa{" + "idMissione=" + idMissione + ", idAstronauta=" + idAstronauta + ", ruolo=" + ruolo + '}';
    }

}
