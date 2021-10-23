/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplos;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author AllanRamiro
 */
@Entity
@Table(name = "cat_impuesto")
public class CatImpuestos {
    
     private static final long serialVersionUID = 1L;

     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "impuestoID")
    private Integer impuestoID;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "descripcion_impuesto")
    private String descripcionImpuesto;

    public CatImpuestos() {
    }

        
    public CatImpuestos(Integer impuestoID, String descripcionImpuesto) {
        this.impuestoID = impuestoID;
        this.descripcionImpuesto = descripcionImpuesto;
    }

    public Integer getImpuestoID() {
        return impuestoID;
    }

    public void setImpuestoID(Integer impuestoID) {
        this.impuestoID = impuestoID;
    }

    public String getDescripcionImpuesto() {
        return descripcionImpuesto;
    }

    public void setDescripcionImpuesto(String descripcionImpuesto) {
        this.descripcionImpuesto = descripcionImpuesto;
    }
   
    
    
}
