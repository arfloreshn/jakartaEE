/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author AllanRamiro
 */
@Entity
@Table(name = "cat_ceaten")
@NamedQueries({
    @NamedQuery(name = "CatCeaten.ListarTodo", query = "SELECT c FROM CatCeaten c"),
    @NamedQuery(name = "CatCeaten.buscarCentroAtencion", query = "SELECT c FROM CatCeaten c WHERE c.descripcionCeaten = :descripcionCeaten")})
public class CatCeaten implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ceatenID")
    private Integer ceatenID;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "descripcion_ceaten")
    private String descripcionCeaten;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "catCeaten")
    private List<MaeVacunacion> maeVacunacionList;

    public CatCeaten() {
    }

    public CatCeaten(Integer ceatenID) {
        this.ceatenID = ceatenID;
    }

    public CatCeaten(Integer ceatenID, String descripcionCeaten) {
        this.ceatenID = ceatenID;
        this.descripcionCeaten = descripcionCeaten;
    }

    public Integer getCeatenID() {
        return ceatenID;
    }

    public void setCeatenID(Integer ceatenID) {
        this.ceatenID = ceatenID;
    }

    public String getDescripcionCeaten() {
        return descripcionCeaten;
    }

    public void setDescripcionCeaten(String descripcionCeaten) {
        this.descripcionCeaten = descripcionCeaten;
    }

    @JsonbTransient
    public List<MaeVacunacion> getMaeVacunacionList() {
        return maeVacunacionList;
    }

    public void setMaeVacunacionList(List<MaeVacunacion> maeVacunacionList) {
        this.maeVacunacionList = maeVacunacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ceatenID != null ? ceatenID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatCeaten)) {
            return false;
        }
        CatCeaten other = (CatCeaten) object;
        if ((this.ceatenID == null && other.ceatenID != null) || (this.ceatenID != null && !this.ceatenID.equals(other.ceatenID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.CatCeaten[ ceatenID=" + ceatenID + " ]";
    }

}
