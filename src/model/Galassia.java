package model;

import java.util.Objects;

public class Galassia {

    private int idGalassia;
    private String nome;
    private String tipo;
    private int massa;

    /**
     * Object constructor.
     *
     * @param idGalassia
     * @param nome
     * @param tipo
     * @param massa
     */
    public Galassia(int idGalassia, String nome, String tipo, int massa) {
        this.idGalassia = idGalassia;
        this.nome = nome;
        this.tipo = tipo;
        this.massa = massa;
    }

    public int getIdGalassia() {
        return idGalassia;
    }

    public void setIdGalassia(int idGalassia) {
        this.idGalassia = idGalassia;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getMassa() {
        return massa;
    }

    public void setMassa(int massa) {
        this.massa = massa;
    }

    public boolean equals(Galassia other) {
        return idGalassia == other.idGalassia;
    }

    @Override
    public String toString() {
        return "Galassia [idGalassia=" + idGalassia + ", nome=" + nome
                + ", tipo=" + tipo + ", massa=" + massa + "]";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.idGalassia;
        hash = 47 * hash + Objects.hashCode(this.nome);
        hash = 47 * hash + Objects.hashCode(this.tipo);
        hash = 47 * hash + this.massa;
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
        final Galassia other = (Galassia) obj;
        if (this.idGalassia != other.idGalassia) {
            return false;
        }
        if (this.massa != other.massa) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        return Objects.equals(this.tipo, other.tipo);
    }
}
