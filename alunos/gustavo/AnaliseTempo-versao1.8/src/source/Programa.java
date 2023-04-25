package source;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Programa extends JFrame {
	long t[];

	public Programa() {
		initUI(); // Call the initUI method to initialize the user interface
	}
	public void initUI() {
		XYDataset dataset = createDataset(); // Create a dataset for the chart
        JFreeChart chart = createChart(dataset); // Create a chart using the dataset
        ChartPanel chartPanel = new ChartPanel(chart); // Create a panel to hold the chart
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15)); // Set the border of the panel
        chartPanel.setBackground(Color.white); // Set the background color of the panel
        add(chartPanel); // Add the panel to the frame

        pack(); // Size the frame to fit its contents
        setTitle("Line chart"); // Set the title of the frame
        setLocationRelativeTo(null); // Center the frame on screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set the default close operation for the frame
	}

	private XYDataset createDataset() {
		//create new XYSeries object for multiply functions
		XYSeries series4 = new XYSeries("multiplicacao vetor por vetor");
		XYSeries series5 = new XYSeries("multiplicacao de duas matrizes quadradas");
		//XYSeries series5x = new XYSeries("multiplicacao de duas matrizes quadradas com threads");
		XYSeries series6 = new XYSeries("multiplicacao de matrix por vetor");
	
	//create new Principal object and execute multiply function	for vector by vector
		Principal p1 = new Principal();
		t = p1.executeV();
		// Add the results to the first series
			for (int x = 0; x < t.length; x++) {
				series4.add(x, t[x]);
			}
	//print out that it done the vector by vector multiply
			System.out.println("vector by vector multiply done");		

	//create new Principal object and execute multiply function	for matrix by matrix
		Principal p5 = new Principal();
		long t5[] = p5.executeM();
		// Add the results to the fifth series
			for (int x = 0; x < t5.length; x++) {
				series5.add(x, t5[x]);
			}
		//print out that it done the matrix by matrix multiply
			System.out.println("matrix by matrix multiply done");


		//create new Principal object and execute multiply function	for matrix by vector
		Principal p6 = new Principal();
		long t6[] = p6.executeMV();
		// Add the results to the sixth series
			for (int x = 0; x < t6.length; x++) {
				series6.add(x, t6[x]);
			}
			
		//print out that it done the matrix by vector multiply
			System.out.println("matrix by vector multiply done");

		// Create a new XYSeriesCollection and add all six series to it
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series4);
		dataset.addSeries(series5);
		dataset.addSeries(series6);
	
		// Return the dataset
		return dataset;
	}

		private JFreeChart createChart(XYDataset dataset) {
			// Create a new XY line chart
			JFreeChart chart = ChartFactory.createXYLineChart(
				"Tempo de execucao de algoritmo", // chart title
				"Tamanho dados", // x axis label
				"Tempo execucao", // y axis label
				dataset, // data
				PlotOrientation.VERTICAL,
				true, // include legend
				true, // tooltips
				false // urls
			);
		
			// Get the plot and set the renderer
			XYPlot plot = chart.getXYPlot();
			XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
			renderer.setSeriesPaint(0, Color.GREEN);
			renderer.setSeriesPaint(1, Color.BLACK);
			renderer.setSeriesPaint(2, Color.BLUE);

			// Set the stroke for each series
			renderer.setSeriesStroke(0, new BasicStroke(2.0f));
			renderer.setSeriesStroke(1, new BasicStroke(2.0f));
			renderer.setSeriesStroke(2, new BasicStroke(2.0f));
		
			// Set the font for the chart title
			chart.setTitle(new TextTitle("Grafico comparativo de algoritmos",
				new Font("Serif", Font.BOLD, 18)
			));
		
			// Set the font for the axis labels
			plot.getDomainAxis().setLabelFont(new Font("Serif", Font.PLAIN, 14));
			plot.getRangeAxis().setLabelFont(new Font("Serif", Font.PLAIN, 14));
		
			// Set the block borders for the plot
			plot.setOutlinePaint(Color.BLACK);
			plot.setOutlineStroke(new BasicStroke(2.0f));
			plot.setDomainGridlinesVisible(true);
			plot.setDomainGridlinePaint(Color.BLACK);
			plot.setRangeGridlinesVisible(true);
			plot.setRangeGridlinePaint(Color.BLACK);
			plot.setBackgroundPaint(Color.WHITE);

			plot.setRenderer(renderer);
		
			// Set the background color of the plot
			plot.setBackgroundPaint(Color.WHITE);
		
			return chart;
		}

	private static final long serialVersionUID = 1L;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		EventQueue.invokeLater(() -> {

			Programa ex = new Programa();
            ex.setVisible(true);
        });
	}

}
