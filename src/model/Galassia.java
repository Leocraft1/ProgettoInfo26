package model;

import java.util.Objects;

/**
 *
 * @author loren
 */
public class Galassia {

    private int idGalassia;
    private String nome;
    private TipoGalassia tipo;
    private Double redshift;
    private Double massa;

    public Galassia() {
    }

    public Galassia(int idGalassia, String nome, TipoGalassia tipo,
            Double redshift, Double massa) {
        this.idGalassia = idGalassia;
        this.nome = nome;
        this.tipo = tipo;
        this.redshift = redshift;
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

    public TipoGalassia getTipo() {
        return tipo;
    }

    public void setTipo(TipoGalassia tipo) {
        this.tipo = tipo;
    }

    public Double getRedshift() {
        return redshift;
    }

    public void setRedshift(Double redshift) {
        this.redshift = redshift;
    }

    public Double getMassa() {
        return massa;
    }

    public void setMassa(Double massa) {
        this.massa = massa;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.idGalassia;
        hash = 89 * hash + Objects.hashCode(this.nome);
        hash = 89 * hash + Objects.hashCode(this.tipo);
        hash = 89 * hash + Objects.hashCode(this.redshift);
        hash = 89 * hash + Objects.hashCode(this.massa);
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
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (this.tipo != other.tipo) {
            return false;
        }
        if (!Objects.equals(this.redshift, other.redshift)) {
            return false;
        }
        return Objects.equals(this.massa, other.massa);
    }

    @Override
    public String toString() {
        return "Galassia{" + "idGalassia=" + idGalassia + ", nome=" + nome + ", tipo=" + tipo + ", redshift=" + redshift + ", massa=" + massa + '}';
    }

}
