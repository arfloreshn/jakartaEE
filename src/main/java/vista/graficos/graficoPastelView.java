/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.graficos;

import controladores.entidades.transaccional.MaestroVacunacionControl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.MapJoin;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import model.MaeVacunacion;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author AllanRamiro
 */
@Named
@RequestScoped
public class graficoPastelView implements Serializable {

    @Inject
    MaestroVacunacionControl vacuncacionControl;

    ArrayList array = new ArrayList();
    private PieChartModel model;

    @PostConstruct
    public void init() {
        createPieModel();

    }

    private void createPieModel() {

        //List<Object[]> list = vacuncacionControl.graficodePie();
        List<Tuple> list = vacuncacionControl.graficoporHospitales();
        
        model = new PieChartModel();
        for (Tuple obj : list) {
            model.set(String.valueOf(obj.get(0)), Double.valueOf(String.valueOf(obj.get(1))));
        }

        //followings are some optional customizations:
        //set title
        model.setTitle("Resumen de vacunacion por Centros de Atencion");
        //set legend position to 'e' (east), other values are 'w', 's' and 'n'
        model.setLegendPosition("e");
        //enable tooltips
        model.setShowDatatip(true);
        //show labels inside pie chart
        model.setShowDataLabels(true);
        //show label text  as 'value' (numeric) , others are 'label', 'percent' (default). Only one can be used.
        model.setDataFormat("value");
        //format: %d for 'value', %s for 'label', %d%% for 'percent'
        model.setDataLabelFormatString("%d Atenciones");
        //pie sector colors
        model.setSeriesColors("aaf,afa,faa,ffa,aff,faf,ddd");
    }

    public PieChartModel getModel() {
        return model;
    }

}
