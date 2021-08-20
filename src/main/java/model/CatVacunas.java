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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author AllanRamiro
 */
@Entity
@Table(name = "cat_vacunas")
@NamedQueries({
@NamedQuery(name = "CatVacunas.ListarTodo", query = "SELECT c FROM CatVacunas c"),
@NamedQuery(name = "CatVacunas.buscarVacuna", query = "SELECT c FROM CatVacunas c WHERE c.descripcionVacuna = :descripcionVacuna"),
@NamedQuery(name = "CatVacunas.ContarPorGrupoDeVacunas", query = "SELECT c.descripcionVacuna, COUNT(d) FROM CatVacunas c LEFT OUTER JOIN c.maeVacunacionList d GROUP BY  c.descripcionVacuna")})
public class CatVacunas implements Serializable {

    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "vacunaID")
    private Integer vacunaID;

    @Size(max = 50)
    @Column(name = "descripcion_vacuna")
    private String descripcionVacuna;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "catVacunas")
    private List<MaeVacunacion> maeVacunacionList;

    public CatVacunas() {
    }

    public CatVacunas(Integer vacunaID) {
        this.vacunaID = vacunaID;
    }

    public Integer getVacunaID() {
        return vacunaID;
    }

    public void setVacunaID(Integer vacunaID) {
        this.vacunaID = vacunaID;
    }

    public String getDescripcionVacuna() {
        return descripcionVacuna;
    }

    public void setDescripcionVacuna(String descripcionVacuna) {
        this.descripcionVacuna = descripcionVacuna;
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
        hash += (vacunaID != null ? vacunaID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatVacunas)) {
            return false;
        }
        CatVacunas other = (CatVacunas) object;
        if ((this.vacunaID == null && other.vacunaID != null) || (this.vacunaID != null && !this.vacunaID.equals(other.vacunaID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.CatVacunas[ vacunaID=" + vacunaID + " ]";
    }

  
    
}
