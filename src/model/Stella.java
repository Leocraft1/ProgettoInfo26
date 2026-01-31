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
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stella other = (Stella) obj;
		return fase == other.fase && idGalassia == other.idGalassia && idStella == other.idStella
				&& Objects.equals(nome, other.nome) && Objects.equals(sistema, other.sistema)
				&& temperatura == other.temperatura;
	}

	@Override
    public String toString() {
        return "Stella{" + "idStella=" + idStella + ", nome=" + nome + ", sistema=" + sistema + ", temperatura=" + temperatura + ", fase=" + fase + ", idGalassia=" + idGalassia + '}';
    }
}
