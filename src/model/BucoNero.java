package model;

import java.util.Objects;

/**
 *
 * @author loren
 */
public class BucoNero {

    private int idBucoNero;
    private String nome;
    private TipoBucoNero tipo;
    private double massa;
    private double rotazione;
    private Integer idGalassia; // può essere null decidiamo poi?

    public BucoNero() {
    }

    public BucoNero(int idBucoNero, String nome, TipoBucoNero tipo,
            double massa, double rotazione, Integer idGalassia) {
        this.idBucoNero = idBucoNero;
        this.nome = nome;
        this.tipo = tipo;
        this.massa = massa;
        this.rotazione = rotazione;
        this.idGalassia = idGalassia;
    }

    public int getIdBucoNero() {
        return idBucoNero;
    }

    public void setIdBucoNero(int idBucoNero) {
        this.idBucoNero = idBucoNero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoBucoNero getTipo() {
        return tipo;
    }

    public void setTipo(TipoBucoNero tipo) {
        this.tipo = tipo;
    }

    public double getMassa() {
        return massa;
    }

    public void setMassa(double massa) {
        this.massa = massa;
    }

    public double getRotazione() {
        return rotazione;
    }

    public void setRotazione(double rotazione) {
        this.rotazione = rotazione;
    }

    public Integer getIdGalassia() {
        return idGalassia;
    }

    public void setIdGalassia(Integer idGalassia) {
        this.idGalassia = idGalassia;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + this.idBucoNero;
        hash = 19 * hash + Objects.hashCode(this.nome);
        hash = 19 * hash + Objects.hashCode(this.tipo);
        hash = 19 * hash + (int) (Double.doubleToLongBits(this.massa) ^ (Double.doubleToLongBits(this.massa) >>> 32));
        hash = 19 * hash + (int) (Double.doubleToLongBits(this.rotazione) ^ (Double.doubleToLongBits(this.rotazione) >>> 32));
        hash = 19 * hash + Objects.hashCode(this.idGalassia);
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
        final BucoNero other = (BucoNero) obj;
        if (this.idBucoNero != other.idBucoNero) {
            return false;
        }
        if (Double.doubleToLongBits(this.massa) != Double.doubleToLongBits(other.massa)) {
            return false;
        }
        if (Double.doubleToLongBits(this.rotazione) != Double.doubleToLongBits(other.rotazione)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (this.tipo != other.tipo) {
            return false;
        }
        return Objects.equals(this.idGalassia, other.idGalassia);
    }

    @Override
    public String toString() {
        return "BucoNero{" + "idBucoNero=" + idBucoNero + ", nome=" + nome + ", tipo=" + tipo + ", massa=" + massa + ", rotazione=" + rotazione + ", idGalassia=" + idGalassia + '}';
    }

}
