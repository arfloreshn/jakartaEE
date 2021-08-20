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
 
/**
 *
 * @author AllanRamiro
 */
@Entity
@Table(name = "cat_menu")
@NamedQueries({
    @NamedQuery(name = "CatMenu.findAll", query = "SELECT c FROM CatMenu c"),
    @NamedQuery(name = "CatMenu.findByItemId", query = "SELECT c FROM CatMenu c WHERE c.itemId = :itemId"),
    @NamedQuery(name = "CatMenu.findByItemIdPadre", query = "SELECT c FROM CatMenu c WHERE c.itemIdPadre = :itemIdPadre"),
    @NamedQuery(name = "CatMenu.findByAppId", query = "SELECT c FROM CatMenu c WHERE c.app_Id = :app_Id"),
    @NamedQuery(name = "CatMenu.findByTipoNodo", query = "SELECT c FROM CatMenu c WHERE c.tipoNodo = :tipoNodo"),
    @NamedQuery(name = "CatMenu.findByDescripcion", query = "SELECT c FROM CatMenu c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "CatMenu.findByRuta", query = "SELECT c FROM CatMenu c WHERE c.ruta = :ruta")})

public class CatMenu implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "item_id")
    private Integer itemId;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "item_id_padre")
    private int itemIdPadre;
  
    @Basic(optional = false)
    @NotNull
    @Column(name = "app_id")
    private int app_Id;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_nodo")
    private Character tipoNodo;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "descripcion")
    private String descripcion;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 180)
    @Column(name = "ruta")
    private String ruta;

    public CatMenu() {
    }

    public CatMenu(Integer itemId) {
        this.itemId = itemId;
    }

    public CatMenu(Integer itemId, int itemIdPadre, int app_Id, Character tipoNodo, String descripcion, String ruta) {
        this.itemId = itemId;
        this.itemIdPadre = itemIdPadre;
        this.app_Id = app_Id;
        this.tipoNodo = tipoNodo;
        this.descripcion = descripcion;
        this.ruta = ruta;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public int getItemIdPadre() {
        return itemIdPadre;
    }

    public void setItemIdPadre(int itemIdPadre) {
        this.itemIdPadre = itemIdPadre;
    }

    public int getAppId() {
        return app_Id;
    }

    public void setAppId(int app_Id) {
        this.app_Id = app_Id;
    }

    public Character getTipoNodo() {
        return tipoNodo;
    }

    public void setTipoNodo(Character tipoNodo) {
        this.tipoNodo = tipoNodo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemId != null ? itemId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatMenu)) {
            return false;
        }
        CatMenu other = (CatMenu) object;
        if ((this.itemId == null && other.itemId != null) || (this.itemId != null && !this.itemId.equals(other.itemId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.CatMenu[ itemId=" + itemId + " ]";
    }

}
