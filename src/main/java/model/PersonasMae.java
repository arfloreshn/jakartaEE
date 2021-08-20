/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author AllanRamiro
 */
@Entity(name = "PersonasMae")
@Table(name = "personas_mae")
@NamedQueries({
    @NamedQuery(name = "PersonasMae.ListarTodo", query = "SELECT p FROM PersonasMae p"),
    @NamedQuery(name = "PersonasMae.BuscarNroDocumento", query = "SELECT p FROM PersonasMae p WHERE p.nroDocumento = :nrodocumento")
})
public class PersonasMae implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "persona_id")
    private Integer personaId;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombres")
    private String nombres;
    @Basic(optional = false)
    @NotNull()
    @Size(min = 1, max = 50)
    @Column(name = "primer_apellido")
    private String primerApellido;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "segundo_apellido")
    private String segundoApellido;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sexo")
    private Character sexo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado_civil")
    private Character estadoCivil;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fec_nacimiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecNacimiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_documento")
    private int tipoDocumento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nro_documento")
    private String nroDocumento;

    @Lob()
    @Column(name = "fotografia")
    private byte[] fotografia;

    // @OneToMany(cascade = CascadeType.ALL, mappedBy = "personasMae")
    // private List<FacturaMae> facturaMaeList;
    @Column(name = "fec_alta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecAlta;
    @Column(name = "fec_baja")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecBaja;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "persona_id", unique = true, nullable = true, insertable = true, updatable = true)
    private PersonasDireccion personasDireccion;

    public PersonasMae() {
    }

    public PersonasMae(Integer personaId) {
        this.personaId = personaId;
    }

    public PersonasMae(Integer personaId, String nombres, String primerApellido, String segundoApellido, Character sexo, Character estadoCivil, Date fecNacimiento, int tipoDocumento, String nroDocumento) {
        this.personaId = personaId;
        this.nombres = nombres;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.sexo = sexo;
        this.estadoCivil = estadoCivil;
        this.fecNacimiento = fecNacimiento;
        this.tipoDocumento = tipoDocumento;
        this.nroDocumento = nroDocumento;
    }

    public Integer getPersonaId() {
        return personaId;
    }

    public void setPersonaId(Integer personaId) {
        this.personaId = personaId;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public Character getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(Character estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public Date getFecNacimiento() {
        return fecNacimiento;
    }

    public void setFecNacimiento(Date fecNacimiento) {
        this.fecNacimiento = fecNacimiento;
    }

    public int getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(int tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNroDocumento() {
        return nroDocumento;
    }

    public void setNroDocumento(String nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    public Date getFecAlta() {
        return fecAlta;
    }

    public void setFecAlta(Date fecAlta) {
        this.fecAlta = fecAlta;
    }

    public Date getFecBaja() {
        return fecBaja;
    }

    public void setFecBaja(Date fecBaja) {
        this.fecBaja = fecBaja;
    }

    public PersonasDireccion getPersonasDireccion() {
        return personasDireccion;
    }

    public void setPersonasDireccion(PersonasDireccion personasDireccion) {
        this.personasDireccion = personasDireccion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personaId != null ? personaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonasMae)) {
            return false;
        }
        PersonasMae other = (PersonasMae) object;
        if ((this.personaId == null && other.personaId != null) || (this.personaId != null && !this.personaId.equals(other.personaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.PersonasMae[ personaId=" + personaId + " ]";
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public Character getSexo() {
        return sexo;
    }

    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }

    public byte[] getFotografia() {
        return fotografia;
    }

    public void setFotografia(byte[] fotografia) {
        this.fotografia = fotografia;
    }

}
