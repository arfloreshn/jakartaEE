/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;

/**
 *
 * @author AllanRamiro
 */
@Entity
@Table(name = "factura_det")
@NamedQueries({
    @NamedQuery(name = "FacturaDet.findAll", query = "SELECT f FROM FacturaDet f"),
    @NamedQuery(name = "FacturaDet.findById", query = "SELECT f FROM FacturaDet f WHERE f.id = :id"),
    @NamedQuery(name = "FacturaDet.findByCantidad", query = "SELECT f FROM FacturaDet f WHERE f.cantidad = :cantidad"),
    @NamedQuery(name = "FacturaDet.findByPreciovta", query = "SELECT f FROM FacturaDet f WHERE f.preciovta = :preciovta"),
    @NamedQuery(name = "FacturaDet.findBySubtotal", query = "SELECT f FROM FacturaDet f WHERE f.subtotal = :subtotal")})
public class FacturaDet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad")
    private double cantidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "preciovta")
    private double preciovta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "subtotal")
    private double subtotal;

    @Basic(optional = false)
    @Column(name = "rowIndex")
    private Integer rowIndex;

    @JoinColumn(name = "producto_id", referencedColumnName = "producto_id")
    @ManyToOne(optional = false)
    private CatProductos catProductos;

    @JoinColumn(name = "factura_id", referencedColumnName = "factura_id")
    @ManyToOne(optional = false)
    private FacturaMae facturaMae;


    public FacturaDet() {
    }

    public FacturaDet(Integer id) {
        this.id = id;
    }

    public FacturaDet(Integer id,  double cantidad, double preciovta, double subtotal, Integer rowIndex) {
        this.id = id;
        this.cantidad = cantidad;
        this.preciovta = preciovta;
        this.subtotal = subtotal;
        this.rowIndex = rowIndex;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getPreciovta() {
        return preciovta;
    }

    public void setPreciovta(double preciovta) {
        this.preciovta = preciovta;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public Integer getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(Integer rowIndex) {
        this.rowIndex = rowIndex;
    }

    
    public CatProductos getCatProductos() {
        return catProductos;
    }

    public void setCatProductos(CatProductos catProductos) {
        this.catProductos = catProductos;
    }

    public FacturaMae getFacturaMae() {
        return facturaMae;
    }

    public void setFacturaMae(FacturaMae facturaMae) {
        this.facturaMae = facturaMae;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FacturaDet)) {
            return false;
        }
        FacturaDet other = (FacturaDet) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.FacturaDet[ id=" + id + " ]";
    }
    
}
