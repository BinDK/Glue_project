package demo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Window;

import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RefineryUtilities;

import entities.BillDetail;
import entities.ImportDetail;
import model.BillModel;
import model.ItemModel;

public class PieChartImport extends javax.swing.JFrame {
	public PieChartImport(String title) {
		super(title);
		setContentPane(createDemoPanel());
	}

	public static PieDataset createDataset() {
		DefaultPieDataset<String> dataset = new DefaultPieDataset();
		BillModel billModel = new BillModel();
		
		ItemModel modelItem = new ItemModel();
		for (ImportDetail bill : billModel.findItemImportinMONTH()) {

			dataset.setValue(bill.getItem_name(), bill.getItem_quantity());
		}

		return dataset;
	}

	public static JFreeChart createChart(PieDataset dataset) {
		JFreeChart chart = ChartFactory.createPieChart("Item import trend of the month", // chart title
				dataset, // data
				true, // include legend
				true, false);

		return chart;
	}
	public JPanel createDemoPanel() {
		JFreeChart chart = createChart(createDataset());
		return new ChartPanel(chart);
	}



    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
        		JFreeChart chart = createChart(createDataset());

        		new ChartPanel(chart);
            }
        });
    }
}
