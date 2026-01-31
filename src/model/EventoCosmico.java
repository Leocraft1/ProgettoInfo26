package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import model.enums.TipoEventoCosmico;

public class EventoCosmico {

    private int idEventoCosmico;
    private String nome;
    private TipoEventoCosmico tipo;
    private LocalDate dataEvento;
    private LocalTime oraEvento;
    private int idStella;

    /**
     * Object constructor.
     *
     * @param idEventoCosmico
     * @param nome
     * @param tipo
     * @param dataEvento
     * @param oraEvento
     * @param idStella
     */
    public EventoCosmico(int idEventoCosmico, String nome, TipoEventoCosmico tipo,
            LocalDate dataEvento, LocalTime oraEvento, int idStella) {
        this.idEventoCosmico = idEventoCosmico;
        this.nome = nome;
        this.tipo = tipo;
        this.dataEvento = dataEvento;
        this.oraEvento = oraEvento;
        this.idStella = idStella;
    }

    public int getIdEventoCosmico() {
        return idEventoCosmico;
    }

    public void setIdEventoCosmico(int idEventoCosmico) {
        this.idEventoCosmico = idEventoCosmico;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoEventoCosmico getTipo() {
        return tipo;
    }

    public void setTipo(TipoEventoCosmico tipo) {
        this.tipo = tipo;
    }

    public LocalDate getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(LocalDate dataEvento) {
        this.dataEvento = dataEvento;
    }

    public LocalTime getOraEvento() {
        return oraEvento;
    }

    public void setOraEvento(LocalTime oraEvento) {
        this.oraEvento = oraEvento;
    }

    public int getIdStella() {
        return idStella;
    }

    public void setIdStella(int idStella) {
        this.idStella = idStella;
    }
    
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EventoCosmico other = (EventoCosmico) obj;
		return Objects.equals(dataEvento, other.dataEvento) && idEventoCosmico == other.idEventoCosmico
				&& idStella == other.idStella && Objects.equals(nome, other.nome)
				&& Objects.equals(oraEvento, other.oraEvento) && tipo == other.tipo;
	}

	@Override
    public String toString() {
        return "EventoCosmico{" + "idEventoCosmico=" + idEventoCosmico + ", nome=" + nome + ", tipo=" + tipo + ", dataEvento=" + dataEvento + ", oraEvento=" + oraEvento + ", idStella=" + idStella + '}';
    }
}
