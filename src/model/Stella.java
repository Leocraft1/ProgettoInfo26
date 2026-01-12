package model;

import java.util.Objects;
import model.enums.FaseStella;

public class Stella {

    private int idStella;
    private String nome;
    private String sistema;
    private int temperatura;
    private FaseStella fase;
    private int idGalassia;

    /**
     * Object constructor.
     *
     * @param idStella
     * @param nome
     * @param temperatura
     * @param fase
     */
    public Stella(int idStella, String nome, int temperatura, FaseStella fase) {
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
    public Stella(int idStella, String nome, String sistema, int temperatura, FaseStella fase) {
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
    public Stella(int idStella, String nome, int temperatura, FaseStella fase, int idGalassia) {
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
    public Stella(int idStella, String nome, String sistema, int temperatura, FaseStella fase, int idGalassia) {
        this.idStella = idStella;
        this.nome = nome;
        this.sistema = sistema;
        this.temperatura = temperatura;
        this.fase = fase;
        this.idGalassia = idGalassia;
    }

    public int getIdStella() {
        return idStella;
    }

    public void setIdStella(int idStella) {
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

    public int getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }

    public FaseStella getFase() {
        return fase;
    }

    public void setFase(FaseStella fase) {
        this.fase = fase;
    }

    public int getIdGalassia() {
        return idGalassia;
    }

    public void setIdGalassia(int idGalassia) {
        this.idGalassia = idGalassia;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + this.idStella;
        hash = 67 * hash + Objects.hashCode(this.nome);
        hash = 67 * hash + Objects.hashCode(this.sistema);
        hash = 67 * hash + this.temperatura;
        hash = 67 * hash + Objects.hashCode(this.fase);
        hash = 67 * hash + this.idGalassia;
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
        if (this.idStella != other.idStella) {
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
        return this.fase == other.fase;
    }

    @Override
    public String toString() {
        return "Stella{" + "idStella=" + idStella + ", nome=" + nome + ", sistema=" + sistema + ", temperatura=" + temperatura + ", fase=" + fase + ", idGalassia=" + idGalassia + '}';
    }
}
