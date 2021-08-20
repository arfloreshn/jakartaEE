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
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.optionconfig.animation.Animation;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.legend.LegendLabel;
import org.primefaces.model.charts.optionconfig.title.Title;

/**
 *
 * @author AllanRamiro
 */
@Named
@RequestScoped
public class graficoBarraView implements Serializable {

    @Inject
    MaestroVacunacionControl vacuncacionControl;

    ArrayList array = new ArrayList();
    private BarChartModel model = new BarChartModel();

    @PostConstruct
    public void init() {
        createBarModel();
    }

    private void createBarModel() {

        List<Tuple> list = vacuncacionControl.graficoporHospitales();

    
        ChartSeries series = new ChartSeries();

        Axis xAxis = model.getAxis(AxisType.X);
        xAxis.setLabel("Hospitales");

         Axis yAxis = model.getAxis(AxisType.Y);
        yAxis.setLabel("Atenciones");

        for (Tuple obj : list) {
            series = new ChartSeries();
            series.setLabel(String.valueOf(obj.get(0)));
            series.set(String.valueOf(obj.get(0)), Double.valueOf(String.valueOf(obj.get(1))));
            model.addSeries(series);
        }

        model.setShadow(true);
       
        //followings are some optional customizations:
        //set title
        model.setTitle("Resumen de vacunacion por Centros de Atencion");
        //set legend position to 'e' (east), other values are 'w', 's' and 'n'
        model.setLegendPosition("ne");
        //show label text  as 'value' (numeric) , others are 'label', 'percent' (default). Only one can be used.
        //pie sector colors
        model.setSeriesColors("aaf,afa,faa,ffa,aff,faf,ddd");
    }

    public void setModel(BarChartModel model) {
        this.model = model;
    }

    public BarChartModel getModel() {
        return model;
    }

}
