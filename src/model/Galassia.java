package model;

import java.util.Objects;
import model.enums.TipoGalassia;

public class Galassia {

    private int idGalassia;
    private String nome;
    private TipoGalassia tipo;
    private int massa;

    /**
     * Object constructor.
     *
     * @param idGalassia
     * @param nome
     * @param tipo
     * @param massa
     */
    public Galassia(int idGalassia, String nome, TipoGalassia tipo, int massa) {
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

    public TipoGalassia getTipo() {
        return tipo;
    }

    public void setTipo(TipoGalassia tipo) {
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
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Galassia other = (Galassia) obj;
		return idGalassia == other.idGalassia && massa == other.massa && Objects.equals(nome, other.nome)
				&& tipo == other.tipo;
	}
}
