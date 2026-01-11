package model;

import java.util.Objects;
import model.enums.TipoPianeta;

public class Pianeta {

    private Integer idPianeta;
    private String nome;
    private String sistema;
    private TipoPianeta tipo;
    private Integer temperatura;
    private Integer idGalassia;

    /**
     * Object constructor.
     *
     * @param idPianeta
     * @param nome
     * @param tipo
     * @param temperatura
     */
    public Pianeta(Integer idPianeta, String nome, TipoPianeta tipo, Integer temperatura) {
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
    public Pianeta(Integer idPianeta, String nome, String sistema,
            TipoPianeta tipo, Integer temperatura) {
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
    public Pianeta(Integer idPianeta, String nome, TipoPianeta tipo,
            int temperatura, Integer idGalassia) {
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
    public Pianeta(Integer idPianeta, String nome, String sistema,
            TipoPianeta tipo, Integer temperatura, Integer idGalassia) {
        this.idPianeta = idPianeta;
        this.nome = nome;
        this.sistema = sistema;
        this.tipo = tipo;
        this.temperatura = temperatura;
        this.idGalassia = idGalassia;
    }

    public Integer getIdPianeta() {
        return idPianeta;
    }

    public void setIdPianeta(Integer idPianeta) {
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

    public Integer getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Integer temperatura) {
        this.temperatura = temperatura;
    }

    public Integer getIdGalassia() {
        return idGalassia;
    }

    public void setIdGalassia(int idGalassia) {
        this.idGalassia = idGalassia;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.idPianeta);
        hash = 37 * hash + Objects.hashCode(this.nome);
        hash = 37 * hash + Objects.hashCode(this.sistema);
        hash = 37 * hash + Objects.hashCode(this.tipo);
        hash = 37 * hash + Objects.hashCode(this.temperatura);
        hash = 37 * hash + Objects.hashCode(this.idGalassia);
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
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.sistema, other.sistema)) {
            return false;
        }
        if (!Objects.equals(this.idPianeta, other.idPianeta)) {
            return false;
        }
        if (this.tipo != other.tipo) {
            return false;
        }
        if (!Objects.equals(this.temperatura, other.temperatura)) {
            return false;
        }
        return Objects.equals(this.idGalassia, other.idGalassia);
    }

    @Override
    public String toString() {
        return "Pianeta{" + "idPianeta=" + idPianeta + ", nome=" + nome + ", sistema=" + sistema + ", tipo=" + tipo + ", temperatura=" + temperatura + ", idGalassia=" + idGalassia + '}';
    }
}
