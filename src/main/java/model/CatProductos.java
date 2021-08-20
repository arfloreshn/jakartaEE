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
@Table(name = "cat_productos")
@NamedQueries({
    @NamedQuery(name = "CatProductos.ListarTodo", query = "SELECT c FROM CatProductos c"),
    @NamedQuery(name = "CatProductos.BuscarProductoId", query = "SELECT c FROM CatProductos c WHERE c.productoId = :productoId"),
    @NamedQuery(name = "CatProductos.BuscarCodigoBarra", query = "SELECT c FROM CatProductos c WHERE c.codBarra = :codbarra"),
    @NamedQuery(name = "CatProductos.BuscarDescripProducto", query = "SELECT c FROM CatProductos c WHERE c.descripProducto = :descripProducto")})
public class CatProductos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "producto_id")
    private Integer productoId;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "cod_barra")
    private String codBarra;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "descrip_producto")
    private String descripProducto;

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "costo_vta")
    private double costoVta;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombreFoto")
    private String nomFoto;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "catProductos")
    private List<FacturaDet> facturaDetList;

    public CatProductos() {
    }

    public CatProductos(Integer productoId) {
        this.productoId = productoId;
    }

    public CatProductos(Integer productoId, String codBarra, String descripProducto, String nomFoto, double costoVta) {
        this.productoId = productoId;
        this.codBarra = codBarra;
        this.descripProducto = descripProducto;
        this.nomFoto = nomFoto;
        this.costoVta = costoVta;
    }

    public Integer getProductoId() {
        return productoId;
    }

    public void setProductoId(Integer productoId) {
        this.productoId = productoId;
    }

    public String getCodBarra() {
        return codBarra;
    }

    public void setCodBarra(String codBarra) {
        this.codBarra = codBarra;
    }

    public String getDescripProducto() {
        return descripProducto;
    }

    public void setDescripProducto(String descripProducto) {
        this.descripProducto = descripProducto;
    }

    public double getCostoVta() {
        return costoVta;
    }

    public void setCostoVta(double costoVta) {
        this.costoVta = costoVta;
    }

    public String getNomFoto() {
        return nomFoto;
    }

    public void setNomFoto(String nomFoto) {
        this.nomFoto = nomFoto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productoId != null ? productoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatProductos)) {
            return false;
        }
        CatProductos other = (CatProductos) object;
        if ((this.productoId == null && other.productoId != null) || (this.productoId != null && !this.productoId.equals(other.productoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return productoId.toString();
    }

    public List<FacturaDet> getFacturaDetList() {
        return facturaDetList;
    }

    public void setFacturaDetList(List<FacturaDet> facturaDetList) {
        this.facturaDetList = facturaDetList;
    }

}
