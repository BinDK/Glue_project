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
import model.BillModel;
import model.ItemModel;

public class PieChart extends javax.swing.JFrame {
	public PieChart(String title) {
		super(title);
		setContentPane(createDemoPanel());
	}

	public static PieDataset createDataset() {
		DefaultPieDataset<String> dataset = new DefaultPieDataset();
		BillModel billModel = new BillModel();
		
		ItemModel modelItem = new ItemModel();
		for (BillDetail bill : billModel.findItemSoldinMONTH()) {
			int id = bill.getItem_id();
			String name = String.valueOf(modelItem.itemName(id).toString());
			dataset.setValue(name, bill.getItem_quantity());
		}

		return dataset;
	}

	public static JFreeChart createChart(PieDataset dataset) {
		JFreeChart chart = ChartFactory.createPieChart("Item sale trend of the month", // chart title
				dataset, // data
				true, // include legend
				true, false);

		return chart;
	}
	public JPanel createDemoPanel() {
		JFreeChart chart = createChart(createDataset());
		return new ChartPanel(chart);
	}
//    public PieChart() {
//        try {
//
//            JPanel jPanel1 = new JPanel();
//            jPanel1.setLayout(new java.awt.BorderLayout());
//            jPanel1.setVisible(true);
//            jPanel1.setSize(300, 300);
//
//            ChartPanel CP = new ChartPanel(chart);
//            CP.setPreferredSize(new Dimension(100, 200));
//            CP.setMouseWheelEnabled(true);
//
//            jPanel1.add(CP, BorderLayout.CENTER);
//            jPanel1.validate();
//
//        } catch (Exception e) {
//            System.out.print("Chart exception:" + e);
//        }
//    }


    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
        		JFreeChart chart = createChart(createDataset());
//        		chart.set(560, 367);
//        		RefineryUtilities.centerFrameOnScreen(chart);
        		new ChartPanel(chart);
            }
        });
    }
}
    // Variables declaration - do not modify                     
    // End of variables declaration  
