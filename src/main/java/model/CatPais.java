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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author AllanRamiro
 */
@Entity
@Table(name = "cat_pais")
@NamedQueries({
 @NamedQuery(name = "CatPais.ListarTodo", query = "SELECT c FROM CatPais c"),
 @NamedQuery(name = "CatPais.Buscar_x_id", query = "SELECT c FROM CatPais c WHERE c.paisId = :paisId"),
 @NamedQuery(name = "CatPais.Buscar_x_descripcion", query = "SELECT c FROM CatPais c WHERE c.descripPais LIKE :descripPais")})
@XmlAccessorType(XmlAccessType.FIELD)
public class CatPais implements Serializable {

    private static final long serialVersionUID = 1L;
  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pais_id")
    private Integer paisId;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "descrip_pais")
    private String descripPais;

    public CatPais() {
    }

    public CatPais(Integer paisId) {
        this.paisId = paisId;
    }

    public CatPais(Integer paisId, String descripPais) {
        this.paisId = paisId;
        this.descripPais = descripPais;
    }

    public Integer getPaisId() {
        return paisId;
    }

    public void setPaisId(Integer paisId) {
        this.paisId = paisId;
    }

    public String getDescripPais() {
        return descripPais;
    }

    public void setDescripPais(String descripPais) {
        this.descripPais = descripPais;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (paisId != null ? paisId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatPais)) {
            return false;
        }
        CatPais other = (CatPais) object;
        if ((this.paisId == null && other.paisId != null) || (this.paisId != null && !this.paisId.equals(other.paisId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.CatPais[ paisId=" + paisId + " ]";
    }
    
}
