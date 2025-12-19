package model;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author loren
 */
public class Astronauta {

    private int idAstronauta;
    private String nome;
    private String cognome;
    private String nazionalita;
    private LocalDate dataNascita;

    public Astronauta() {
    }

    public Astronauta(int idAstronauta, String nome, String cognome,
            String nazionalita, LocalDate dataNascita) {
        this.idAstronauta = idAstronauta;
        this.nome = nome;
        this.cognome = cognome;
        this.nazionalita = nazionalita;
        this.dataNascita = dataNascita;
    }

    public int getIdAstronauta() {
        return idAstronauta;
    }

    public void setIdAstronauta(int idAstronauta) {
        this.idAstronauta = idAstronauta;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getNazionalita() {
        return nazionalita;
    }

    public void setNazionalita(String nazionalita) {
        this.nazionalita = nazionalita;
    }

    public LocalDate getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.idAstronauta;
        hash = 89 * hash + Objects.hashCode(this.nome);
        hash = 89 * hash + Objects.hashCode(this.cognome);
        hash = 89 * hash + Objects.hashCode(this.nazionalita);
        hash = 89 * hash + Objects.hashCode(this.dataNascita);
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
        final Astronauta other = (Astronauta) obj;
        if (this.idAstronauta != other.idAstronauta) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.cognome, other.cognome)) {
            return false;
        }
        if (!Objects.equals(this.nazionalita, other.nazionalita)) {
            return false;
        }
        return Objects.equals(this.dataNascita, other.dataNascita);
    }

    @Override
    public String toString() {
        return "Astronauta{" + "idAstronauta=" + idAstronauta + ", nome=" + nome + ", cognome=" + cognome + ", nazionalita=" + nazionalita + ", dataNascita=" + dataNascita + '}';
    }

}
