import java.io.IOException;

import org.jfree.data.xy.XYSeries;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.markers.SeriesMarkers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.swing.SwingWorker;


public class Teste2 
{
	
	
	static ArrayList<Long> janelaglob = new ArrayList<Long>();
	static ArrayList<Long> nseqglob = new ArrayList<Long>();
	MySwingWorker mySwingWorker;
	SwingWrapper<XYChart> sw;
	XYChart chart;	
	
	public static void main(String[] args) {
		 
	    int numCharts = 4;
	 
	    List<XYChart> charts = new ArrayList<XYChart>();
	    
       
        	  for (int i = 0; i < numCharts; i++) 
        	  {
        	      XYChart chart = new XYChartBuilder().xAxisTitle("X").yAxisTitle("Y").width(600).height(400).build();
        	      chart.getStyler().setYAxisMin((double) -10);
        	      chart.getStyler().setYAxisMax((double) 10);
        	      org.knowm.xchart.XYSeries series = chart.addSeries("" + i, null, getRandomWalk(200));
        	      series.setMarker(SeriesMarkers.NONE);
        	      charts.add(chart);
        	    }
        	    new SwingWrapper<XYChart>(charts).displayChartMatrix();
        	
 
	  
	  }
	 
	  /**
	   * Generates a set of random walk data
	   *
	   * @param numPoints
	   * @return
	   */
	private static double Gerar(int index, int tamanho)
    {
		double[]  a = 1.000;
		double  b = 2.000;
		double  c = 3.000;
		double  d = 4.000;
		double def = 0.0000;
		
   	  switch(index)
   	  {
   	      case 1:
   	      for(int i = 0; i>tamanho;i++)	
   	      {
   	    	  
   	      }
   	      return a;	
   	      case 2:
   	      return b;
   	      case 3:
   	      return c;
   	      case 4:
   	      return d;
   	      default:
   	      return def;
   	  }
    }	
	
	
	
	  private static double[] getRandomWalk(int numPoints) {
	 
	    double[] y = new double[numPoints];
	    y[0] = 0;
	    for (int i = 1; i < y.length; i++) {
	      y[i] = y[i - 1] + Math.random() - .5;
	    }
	    return y;
	  }
	  
	  private void go()
	  {
		  int numCharts = 4;
			 
		    List<XYChart> charts = new ArrayList<XYChart>();
		    
	       Teste2 gera;
	        	  for (int i = 0; i < numCharts; i++) 
	        	  {
	        	      XYChart chart = new XYChartBuilder().xAxisTitle("X").yAxisTitle("Y").width(600).height(400).build();
	        	      chart.getStyler().setYAxisMin((double) -10);
	        	      chart.getStyler().setYAxisMax((double) 10);
	        	      org.knowm.xchart.XYSeries series = chart.addSeries("" + i, null, getRandomWalk(200));
	        	      series.setMarker(SeriesMarkers.NONE);
	        	      charts.add(chart);
	        	    }
	        	    new SwingWrapper<XYChart>(charts).displayChartMatrix();
		  
	  }
	  
	  private class MySwingWorker extends SwingWorker<Boolean, double[]> {

		    final LinkedList<Double> fifo = new LinkedList<Double>();

		    public MySwingWorker() {

		      fifo.add(0.0);
		    }

			@Override
			protected Boolean doInBackground() throws Exception {
				// TODO Auto-generated method stub
				return null;
			}
   

}
	  
}
