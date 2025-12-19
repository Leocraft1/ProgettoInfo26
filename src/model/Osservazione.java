package model;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author loren
 */
public class Osservazione {

    private int idOsservazione;
    private LocalDate dataOsservazione;
    private String risultati;
    private boolean confermata;
    private Integer idStrumento;
    private TipoOggetto tipoOggetto;
    private int idOggetto;

    public Osservazione() {
    }

    public Osservazione(int idOsservazione, LocalDate dataOsservazione,
            String risultati, boolean confermata,
            Integer idStrumento, TipoOggetto tipoOggetto, int idOggetto) {
        this.idOsservazione = idOsservazione;
        this.dataOsservazione = dataOsservazione;
        this.risultati = risultati;
        this.confermata = confermata;
        this.idStrumento = idStrumento;
        this.tipoOggetto = tipoOggetto;
        this.idOggetto = idOggetto;
    }

    public int getIdOsservazione() {
        return idOsservazione;
    }

    public void setIdOsservazione(int idOsservazione) {
        this.idOsservazione = idOsservazione;
    }

    public LocalDate getDataOsservazione() {
        return dataOsservazione;
    }

    public void setDataOsservazione(LocalDate dataOsservazione) {
        this.dataOsservazione = dataOsservazione;
    }

    public String getRisultati() {
        return risultati;
    }

    public void setRisultati(String risultati) {
        this.risultati = risultati;
    }

    public boolean isConfermata() {
        return confermata;
    }

    public void setConfermata(boolean confermata) {
        this.confermata = confermata;
    }

    public Integer getIdStrumento() {
        return idStrumento;
    }

    public void setIdStrumento(Integer idStrumento) {
        this.idStrumento = idStrumento;
    }

    public TipoOggetto getTipoOggetto() {
        return tipoOggetto;
    }

    public void setTipoOggetto(TipoOggetto tipoOggetto) {
        this.tipoOggetto = tipoOggetto;
    }

    public int getIdOggetto() {
        return idOggetto;
    }

    public void setIdOggetto(int idOggetto) {
        this.idOggetto = idOggetto;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.idOsservazione;
        hash = 89 * hash + Objects.hashCode(this.dataOsservazione);
        hash = 89 * hash + Objects.hashCode(this.risultati);
        hash = 89 * hash + (this.confermata ? 1 : 0);
        hash = 89 * hash + Objects.hashCode(this.idStrumento);
        hash = 89 * hash + Objects.hashCode(this.tipoOggetto);
        hash = 89 * hash + this.idOggetto;
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
        final Osservazione other = (Osservazione) obj;
        if (this.idOsservazione != other.idOsservazione) {
            return false;
        }
        if (this.confermata != other.confermata) {
            return false;
        }
        if (this.idOggetto != other.idOggetto) {
            return false;
        }
        if (!Objects.equals(this.risultati, other.risultati)) {
            return false;
        }
        if (!Objects.equals(this.dataOsservazione, other.dataOsservazione)) {
            return false;
        }
        if (!Objects.equals(this.idStrumento, other.idStrumento)) {
            return false;
        }
        return this.tipoOggetto == other.tipoOggetto;
    }

    @Override
    public String toString() {
        return "Osservazione{" + "idOsservazione=" + idOsservazione + ", dataOsservazione=" + dataOsservazione + ", risultati=" + risultati + ", confermata=" + confermata + ", idStrumento=" + idStrumento + ", tipoOggetto=" + tipoOggetto + ", idOggetto=" + idOggetto + '}';
    }

}
