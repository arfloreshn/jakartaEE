/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author AllanRamiro
 */
@Entity
@Table(name = "factura_mae")
@NamedQueries({
    @NamedQuery(name = "FacturaMae.findAll", query = "SELECT f FROM FacturaMae f"),
    @NamedQuery(name = "FacturaMae.findByFacturaId", query = "SELECT f FROM FacturaMae f WHERE f.facturaId = :facturaId"),
    @NamedQuery(name = "FacturaMae.findByNomCliente", query = "SELECT f FROM FacturaMae f WHERE f.nomCliente = :nomCliente"),
    @NamedQuery(name = "FacturaMae.findBySubtotal", query = "SELECT f FROM FacturaMae f WHERE f.subtotal = :subtotal"),
    @NamedQuery(name = "FacturaMae.findByImpuesto", query = "SELECT f FROM FacturaMae f WHERE f.impuesto = :impuesto"),
    @NamedQuery(name = "FacturaMae.findByTotal", query = "SELECT f FROM FacturaMae f WHERE f.total = :total")})
public class FacturaMae implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "factura_id")
    private Integer facturaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nom_cliente")
    private String nomCliente;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "subtotal")
    private double subtotal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "impuesto")
    private double impuesto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "total")
    private double total;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "facturaMae")
    private List<FacturaDet> facturaDetList;

    @JoinColumn(name = "persona_id", referencedColumnName = "persona_id")
    @ManyToOne(optional = false)
    private PersonasMae personasMae;

    public FacturaMae() {
    }

    public FacturaMae(Integer facturaId) {
        this.facturaId = facturaId;
    }

    public FacturaMae(Integer facturaId, String nomCliente, double subtotal, double impuesto, double total) {
        this.facturaId = facturaId;
        this.nomCliente = nomCliente;
        this.subtotal = subtotal;
        this.impuesto = impuesto;
        this.total = total;
    }

    public Integer getFacturaId() {
        return facturaId;
    }

    public void setFacturaId(Integer facturaId) {
        this.facturaId = facturaId;
    }

    public String getNomCliente() {
        return nomCliente;
    }

    public void setNomCliente(String nomCliente) {
        this.nomCliente = nomCliente;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(double impuesto) {
        this.impuesto = impuesto;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<FacturaDet> getFacturaDetList() {
        return facturaDetList;
    }

    public void setFacturaDetList(List<FacturaDet> facturaDetList) {
        this.facturaDetList = facturaDetList;
    }

    public PersonasMae getPersonasMae() {
        return personasMae;
    }

    public void setPersonasMae(PersonasMae personasMae) {
        this.personasMae = personasMae;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (facturaId != null ? facturaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FacturaMae)) {
            return false;
        }
        FacturaMae other = (FacturaMae) object;
        if ((this.facturaId == null && other.facturaId != null) || (this.facturaId != null && !this.facturaId.equals(other.facturaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.FacturaMae[ facturaId=" + facturaId + " ]";
    }

}
