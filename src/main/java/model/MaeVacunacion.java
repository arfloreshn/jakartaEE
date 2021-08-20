/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author AllanRamiro
 */
@Entity
@Table(name = "vacunacion_mae")
@NamedQueries({
    @NamedQuery(name = "MaeVacunacion.ListarTodo", query = "SELECT v FROM MaeVacunacion v"),
    @NamedQuery(name = "MaeVacunacion.BuscarPorNombrePaciente", query = "SELECT v FROM MaeVacunacion v WHERE v.nombrePaciente LIKE  :nombrePaciente"),
    @NamedQuery(name = "MaeVacunacion.BuscarPorEdad", query = "SELECT v FROM MaeVacunacion v WHERE v.edad = :edad"),
    @NamedQuery(name = "MaeVacunacion.buscarPorRangoFechas", query = "SELECT v FROM MaeVacunacion v WHERE v.fecVacunacion BETWEEN  :inicio AND :final")})
public class MaeVacunacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "vacunacion_id")
    private Integer vacunacionId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombrePaciente")
    private String nombrePaciente;
    @Size(max = 20)
    @Column(name = "dni")
    private String dni;
    @Basic(optional = false)
    @NotNull
    @Column(name = "edad")
    private int edad;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "estatura")
    private BigDecimal estatura;
    @Column(name = "peso")
    private BigDecimal peso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sn_preexistencia")
    private Character snPreexistencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado_civil")
    private Character estadoCivil;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fec_vacunacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecVacunacion;
    @Column(name = "fec_proxima_dosis")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecProximaDosis;
    
    @JoinColumn(name = "cesamoID", referencedColumnName = "ceatenID")
    @ManyToOne(optional = false)
    private CatCeaten catCeaten;
    
    @JoinColumn(name = "vacunaID", referencedColumnName = "vacunaID")
    @ManyToOne(optional = false)
    private CatVacunas catVacunas;

    public MaeVacunacion() {
    }

    public MaeVacunacion(Integer vacunacionId) {
        this.vacunacionId = vacunacionId;
    }

    public MaeVacunacion(Integer vacunacionId, String nombrePaciente, int edad, BigDecimal estatura, Character snPreexistencia, Character estadoCivil, Date fecVacunacion) {
        this.vacunacionId = vacunacionId;
        this.nombrePaciente = nombrePaciente;
        this.edad = edad;
        this.estatura = estatura;
        this.snPreexistencia = snPreexistencia;
        this.estadoCivil = estadoCivil;
        this.fecVacunacion = fecVacunacion;
    }

    public Integer getVacunacionId() {
        return vacunacionId;
    }

    public void setVacunacionId(Integer vacunacionId) {
        this.vacunacionId = vacunacionId;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public BigDecimal getEstatura() {
        return estatura;
    }

    public void setEstatura(BigDecimal estatura) {
        this.estatura = estatura;
    }

    public BigDecimal getPeso() {
        return peso;
    }

    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }

    public Character getSnPreexistencia() {
        return snPreexistencia;
    }

    public void setSnPreexistencia(Character snPreexistencia) {
        this.snPreexistencia = snPreexistencia;
    }

    public Character getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(Character estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public Date getFecVacunacion() {
        return fecVacunacion;
    }

    public void setFecVacunacion(Date fecVacunacion) {
        this.fecVacunacion = fecVacunacion;
    }

    public Date getFecProximaDosis() {
        return fecProximaDosis;
    }

    public void setFecProximaDosis(Date fecProximaDosis) {
        this.fecProximaDosis = fecProximaDosis;
    }

    public CatCeaten getCatCeaten() {
        return catCeaten;
    }

    public void setCatCeaten(CatCeaten catCeaten) {
        this.catCeaten = catCeaten;
    }

    public CatVacunas getCatVacunas() {
        return catVacunas;
    }

    public void setCatVacunas(CatVacunas catVacunas) {
        this.catVacunas = catVacunas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vacunacionId != null ? vacunacionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaeVacunacion)) {
            return false;
        }
        MaeVacunacion other = (MaeVacunacion) object;
        if ((this.vacunacionId == null && other.vacunacionId != null) || (this.vacunacionId != null && !this.vacunacionId.equals(other.vacunacionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.MaeVacunacion[ vacunacionId=" + vacunacionId + " ]";
    }
    
}
