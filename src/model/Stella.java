package model;

import java.util.Objects;
import model.enums.FaseStella;

public class Stella {

    private Integer idStella;
    private String nome;
    private String sistema;
    private Integer temperatura;
    private FaseStella fase;
    private Integer idGalassia;

    /**
     * Object constructor.
     *
     * @param idStella
     * @param nome
     * @param temperatura
     * @param fase
     */
    public Stella(Integer idStella, String nome, Integer temperatura, FaseStella fase) {
        this.idStella = idStella;
        this.nome = nome;
        this.temperatura = temperatura;
        this.fase = fase;
    }

    /**
     * Object constructor.
     *
     * @param idStella
     * @param nome
     * @param sistema
     * @param temperatura
     * @param fase
     */
    public Stella(Integer idStella, String nome, String sistema, Integer temperatura, FaseStella fase) {
        this.idStella = idStella;
        this.nome = nome;
        this.sistema = sistema;
        this.temperatura = temperatura;
        this.fase = fase;
    }

    /**
     * Object constructor.
     *
     * @param idStella
     * @param nome
     * @param temperatura
     * @param fase
     * @param idGalassia
     */
    public Stella(Integer idStella, String nome, Integer temperatura, FaseStella fase, Integer idGalassia) {
        super();
        this.idStella = idStella;
        this.nome = nome;
        this.temperatura = temperatura;
        this.fase = fase;
        this.idGalassia = idGalassia;
    }

    /**
     * Object constructor.
     *
     * @param idStella
     * @param nome
     * @param sistema
     * @param temperatura
     * @param fase
     * @param idGalassia
     */
    public Stella(Integer idStella, String nome, String sistema, Integer temperatura, FaseStella fase, Integer idGalassia) {
        this.idStella = idStella;
        this.nome = nome;
        this.sistema = sistema;
        this.temperatura = temperatura;
        this.fase = fase;
        this.idGalassia = idGalassia;
    }

    public Integer getIdStella() {
        return idStella;
    }

    public void setIdStella(Integer idStella) {
        this.idStella = idStella;
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

    public Integer getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Integer temperatura) {
        this.temperatura = temperatura;
    }

    public FaseStella getFase() {
        return fase;
    }

    public void setFase(FaseStella fase) {
        this.fase = fase;
    }

    public Integer getIdGalassia() {
        return idGalassia;
    }

    public void setIdGalassia(int idGalassia) {
        this.idGalassia = idGalassia;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.idStella);
        hash = 29 * hash + Objects.hashCode(this.nome);
        hash = 29 * hash + Objects.hashCode(this.sistema);
        hash = 29 * hash + Objects.hashCode(this.temperatura);
        hash = 29 * hash + Objects.hashCode(this.fase);
        hash = 29 * hash + Objects.hashCode(this.idGalassia);
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
        final Stella other = (Stella) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.sistema, other.sistema)) {
            return false;
        }
        if (!Objects.equals(this.idStella, other.idStella)) {
            return false;
        }
        if (!Objects.equals(this.temperatura, other.temperatura)) {
            return false;
        }
        if (this.fase != other.fase) {
            return false;
        }
        return Objects.equals(this.idGalassia, other.idGalassia);
    }

    @Override
    public String toString() {
        return "Stella{" + "idStella=" + idStella + ", nome=" + nome + ", sistema=" + sistema + ", temperatura=" + temperatura + ", fase=" + fase + ", idGalassia=" + idGalassia + '}';
    }
}
