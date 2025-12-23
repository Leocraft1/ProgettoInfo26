package model;

import java.util.Objects;
import model.enums.TipoPianeta;

public class Pianeta {

    private int idPianeta;
    private String nome;
    private String sistema;
    private TipoPianeta tipo;
    private int temperatura;
    private int idGalassia;

    /**
     * Object constructor.
     *
     * @param idPianeta
     * @param nome
     * @param tipo
     * @param temperatura
     */
    public Pianeta(int idPianeta, String nome, TipoPianeta tipo, int temperatura) {
        this.idPianeta = idPianeta;
        this.nome = nome;
        this.tipo = tipo;
        this.temperatura = temperatura;
    }

    /**
     * Object constructor.
     *
     * @param idPianeta
     * @param nome
     * @param sistema
     * @param tipo
     * @param temperatura
     */
    public Pianeta(int idPianeta, String nome, String sistema,
            TipoPianeta tipo, int temperatura) {
        this.idPianeta = idPianeta;
        this.nome = nome;
        this.sistema = sistema;
        this.tipo = tipo;
        this.temperatura = temperatura;
    }

    /**
     * Object constructor.
     *
     * @param idPianeta
     * @param nome
     * @param tipo
     * @param temperatura
     * @param idGalassia
     */
    public Pianeta(int idPianeta, String nome, TipoPianeta tipo,
            int temperatura, int idGalassia) {
        this.idPianeta = idPianeta;
        this.nome = nome;
        this.tipo = tipo;
        this.temperatura = temperatura;
        this.idGalassia = idGalassia;
    }

    /**
     * Object constructor.
     *
     * @param idPianeta
     * @param nome
     * @param sistema
     * @param tipo
     * @param temperatura
     * @param idGalassia
     */
    public Pianeta(int idPianeta, String nome, String sistema,
            TipoPianeta tipo, int temperatura, int idGalassia) {
        this.idPianeta = idPianeta;
        this.nome = nome;
        this.sistema = sistema;
        this.tipo = tipo;
        this.temperatura = temperatura;
        this.idGalassia = idGalassia;
    }

    public int getIdPianeta() {
        return idPianeta;
    }

    public void setIdPianeta(int idPianeta) {
        this.idPianeta = idPianeta;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSistema() {
        return sistema;
    }

    public void setSistema(String sistema) {
        this.sistema = sistema;
    }

    public TipoPianeta getTipo() {
        return tipo;
    }

    public void setTipo(TipoPianeta tipo) {
        this.tipo = tipo;
    }

    public int getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }

    public int getIdGalassia() {
        return idGalassia;
    }

    public void setIdGalassia(int idGalassia) {
        this.idGalassia = idGalassia;
    }

    public boolean equals(Pianeta other) {
        return idPianeta == other.idPianeta;
    }

    @Override
    public String toString() {
        return "Pianeta [idPianeta=" + idPianeta + ", nome=" + nome
                + ", sistema=" + sistema + ", tipo=" + tipo
                + ", temperatura=" + temperatura + ", idGalassia=" + idGalassia + "]";
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + this.idPianeta;
        hash = 73 * hash + Objects.hashCode(this.nome);
        hash = 73 * hash + Objects.hashCode(this.sistema);
        hash = 73 * hash + Objects.hashCode(this.tipo);
        hash = 73 * hash + this.temperatura;
        hash = 73 * hash + this.idGalassia;
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
        final Pianeta other = (Pianeta) obj;
        if (this.idPianeta != other.idPianeta) {
            return false;
        }
        if (this.temperatura != other.temperatura) {
            return false;
        }
        if (this.idGalassia != other.idGalassia) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.sistema, other.sistema)) {
            return false;
        }
        return this.tipo == other.tipo;
    }
}
