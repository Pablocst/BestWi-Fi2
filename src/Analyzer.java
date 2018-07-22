
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.SwingWorker;
import java.util.Iterator;
import java.util.LinkedList;
import org.pcap4j.core.NotOpenException;
import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapNativeException;
import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.packet.Packet;
import org.pcap4j.packet.Packet.Header;
import org.pcap4j.util.NifSelector;
import org.pcap4j.*;
import org.pcap4j.core.PcapNetworkInterface.PromiscuousMode;
import org.pcap4j.core.BpfProgram;
import org.pcap4j.packet.factory.*;
import jpcap.packet.ICMPPacket;
import jpcap.JpcapCaptor;
import java.time.*;
import org.jfree.chart.ChartFactory;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import java.io.IOException;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.markers.SeriesMarkers;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.swing.SwingWorker;

public class Analyzer implements Runnable  {

private static ArrayList<Long> janelaglob = new ArrayList<Long>();
private static ArrayList<Long> nseqglob = new ArrayList<Long>();

MySwingWorker mySwingWorker;
SwingWrapper<XYChart> sw;
XYChart chart;

MySwingWorker mySwingWorker2;
SwingWrapper<XYChart> sw2;
XYChart chart2;


	
	public void run() 
	{
		 int flag = 0;
		 //System.out.println("teste");
		 PcapNetworkInterface nif = null;
		 //System.out.println("teste");
	         // Lista as interfaces
	            try {
					nif = new NifSelector().selectNetworkInterface();
				} catch (IOException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
	            if(nif == null)
	            {
	            	System.exit(1);
	            }
		 PcapHandle handler = null;
		try {
			handler = nif.openLive(65536, PromiscuousMode.PROMISCUOUS, 10);
		} catch (PcapNativeException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		 Packet pacote = null;
		 //System.out.println("wololo");
		 try {
			handler.setFilter("tcp", BpfProgram.BpfCompileMode.OPTIMIZE);
		} catch (PcapNativeException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NotOpenException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 while(pacote == null)
		 {
			 try {
				pacote = handler.getNextPacket();
			} catch (NotOpenException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		 Packet payload = null;
		 //System.out.println("wololo2");
		 while(payload == null)
		 {
		      payload = pacote.getPayload();
		 }
		 //System.out.println(cabecalho.toString());
		 InetAddress inet = null;
		 String nseq;
	     String seqtratamento;
	     String janela;
	     String payloadstring;
	     seqtratamento = payload.toString();
	     payloadstring = payload.toString();
	     //System.out.println(payload);
	     seqtratamento = seqtratamento.substring(530, 550);
	     seqtratamento = seqtratamento.replaceAll("[^0-9]", "");
	     nseq = seqtratamento;
	     System.out.println("Número de sequencia:" + nseq);
	     //jantratamento = payload.toString();
	     //int valinicial = payloadstring.length() - 110;
	     //int valfinal = valinicial +30;
	     //jantratamento = jantratamento.substring(valinicial, valfinal);
	     //janela = jantratamento;
	     //System.out.println(janela);
	     //System.out.println(payloadstring.length());
	     //System.out.println(payload.getRawData().length);
	     String[] parts = payloadstring.split(":");
	     janela = parts[28];
	     janela = janela.replaceAll("[^0-9]", "");
	     System.out.println("Window: " + janela);
	     //System.out.println(seqtratamento.substring(530, 550));
		 //System.out.println(payload);
	     //System.out.println(payload.toString());
		 long ms=0;
		 for (int i = 0; i<=10; i++)
		 {
			 Instant before = Instant.now();
			 try {
				inet = InetAddress.getByAddress(new byte[] { 8, 8, 8, 8 });
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 //System.out.println("Sending Ping Request to " + inet);
			 //System.out.println(inet.isReachable(5000) ? "Host is reachable" : "Host is NOT reachable");
			 try {
				inet.isReachable(5000);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 Instant after = Instant.now();
			 long delta = Duration.between(before, after).toMillis();
			 //System.out.println("ms:" + delta);
			 ms = delta+ms;
		 }
		 //handler = nif.openLive(65536, PromiscuousMode.PROMISCUOUS, 10);
		 pacote = null;
		 //handler.setFilter("tcp", BpfProgram.BpfCompileMode.OPTIMIZE);
		 try {
			handler = nif.openLive(65536, PromiscuousMode.PROMISCUOUS, 10);
		} catch (PcapNativeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 try {
			handler.setFilter("tcp", BpfProgram.BpfCompileMode.OPTIMIZE);
		} catch (PcapNativeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotOpenException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 while(pacote == null)
		 {
			 //System.out.println("super wololo1");
			 try
			 {
				 //ServerSocket servidor = new ServerSocket(5000);
			     //System.out.println("Servidor ouvindo a porta 5000");
			 while (flag == 0)
			 {
				 //Socket client = servidor.accept();
				 pacote = null;
				 while(pacote == null)
				 {
					 try {
						pacote = handler.getNextPacket();
					} catch (NotOpenException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				 }
				 payload = null;
				 while(payload == null)
				 {
					 payload = pacote.getPayload();
				 }
				 //System.out.println("super wololo2");
				 //System.out.println(payload.toString());
				 payloadstring = payload.toString();
				 seqtratamento = pacote.toString();
				 //System.out.println("super wololo3");
				 nseq = "";
				 while(nseq.equals("") || nseq == null)
				 {
					 String[] partes = payloadstring.split(":");
				     nseq = partes[18];
				     nseq = nseq.replaceAll("[^0-9]", "");
				 }
				 //seqtratamento = seqtratamento.substring(530, 550);
			     //seqtratamento = seqtratamento.replaceAll("[^0-9]", "");
			     //nseq = seqtratamento;
			     System.out.println("Número de sequencia:" + nseq);
			     String[] partes2 = payloadstring.split(":");
			     janela = partes2[28];
			     janela = janela.replaceAll("[^0-9]", "");
			     String janelatemp = janela;
			     String nseqtemp = nseq;
			     long janelalong = Long.parseLong(janelatemp);
			     String nseqtemp2 = String.valueOf(nseq);
			     long nseqlong = Long.parseLong(nseqtemp2);
			     System.out.println("Window: " + janela);
			     janelaglob.add(janelalong);
			     nseqglob.add(nseqlong);
			     //System.out.println("super wololo4");
			     //ObjectOutputStream saida = new ObjectOutputStream(janelalong);
			     
			 }
			 
			 }
			 catch(Exception e)
			 {
				 System.out.println("Error" + e.getMessage());
			 }
		 }
		 //System.out.println(pacote.getPayload());
		 //System.out.println(pacote.getHeader());
		 ms = ms/10;
		 System.out.println("ms médio:" + ms);
		 
	}
	public static void main(String args[]) throws InterruptedException
	{
		(new Thread(new Analyzer())).start();
		Thread.sleep(10000);
		/*while(true)
		{
			for(int i=0;i<janelaglob.size(); i++)
			{
				long janetemp = janelaglob.get(i);
				System.out.println(janetemp);
			}
		}
		
	*/
	new Analyzer().go();
	new Analyzer().go2();
	}
	private void go() {

	    // Create Chart
		  List<XYChart> charts = new ArrayList<XYChart>();
	    chart =
	        QuickChart.getChart(
	            "SwingWorker XChart Real-time Demo",
	            "Time",
	            "Value",
	            "randomWalk",
	            new double[] {0},
	            new double[] {0});
	    chart.getStyler().setLegendVisible(false);
	    chart.getStyler().setXAxisTicksVisible(false);

	    // Show it
	    sw = new SwingWrapper<XYChart>(chart);
	    sw.displayChart();
		    new SwingWrapper<XYChart>(charts).displayChartMatrix();

	    mySwingWorker = new MySwingWorker();
	    mySwingWorker.execute();
	  }

	  private class MySwingWorker extends SwingWorker<Boolean, double[]> {

	    @Override
	    protected Boolean doInBackground() throws Exception {

	      while (!isCancelled()) {
	        double[] array = new double[janelaglob.size()];
	        for (int i = 0; i < janelaglob.size(); i++) {
	          array[i] = janelaglob.get(i);
	        }
	        publish(array);

	        try {
	          Thread.sleep(5);
	        } catch (InterruptedException e) {
	          // eat it. caught when interrupt is called
	          System.out.println("MySwingWorker shut down.");
	        }
	      }

	      return true;
	    }

	    @Override
	    protected void process(List<double[]> chunks) {

	      //System.out.println("number of chunks: " + chunks.size());

	      double[] mostRecentDataSet = chunks.get(chunks.size() - 1);

	      chart.updateXYSeries("randomWalk", null, mostRecentDataSet, null);
	      sw.repaintChart();

	      long start = System.currentTimeMillis();
	      long duration = System.currentTimeMillis() - start;
	      try {
	        Thread.sleep(40 - duration); // 40 ms ==> 25fps
	        // Thread.sleep(400 - duration); // 40 ms ==> 2.5fps
	      } catch (InterruptedException e) {
	        System.out.println("InterruptedException occurred.");
	      }
	    }
	  }
	  private void go2() {

		    // Create Chart
			  List<XYChart> charts = new ArrayList<XYChart>();
		    chart2 =
		        QuickChart.getChart(
		            "SwingWorker XChart Real-time Demo",
		            "Time",
		            "Value",
		            "randomWalk",
		            new double[] {0},
		            new double[] {0});
		    chart2.getStyler().setLegendVisible(false);
		    chart2.getStyler().setXAxisTicksVisible(false);

		    // Show it
		    sw2 = new SwingWrapper<XYChart>(chart2);
		    sw2.displayChart();
			    new SwingWrapper<XYChart>(charts).displayChartMatrix();

		    mySwingWorker2 = new MySwingWorker();
		    mySwingWorker2.execute();
		  }

		  private class MySwingWorker2 extends SwingWorker<Boolean, double[]> {

		    @Override
		    protected Boolean doInBackground() throws Exception {

		      while (!isCancelled()) {
		        double[] array = new double[nseqglob.size()];
		        for (int i = 0; i < nseqglob.size(); i++) {
		          array[i] = nseqglob.get(i);
		        }
		        publish(array);

		        try {
		          Thread.sleep(5);
		        } catch (InterruptedException e) {
		          // eat it. caught when interrupt is called
		          System.out.println("MySwingWorker shut down.");
		        }
		      }

		      return true;
		    }

		    @Override
		    protected void process(List<double[]> chunks) {

		      //System.out.println("number of chunks: " + chunks.size());

		      double[] mostRecentDataSet = chunks.get(chunks.size() - 1);

		      chart2.updateXYSeries("randomWalk", null, mostRecentDataSet, null);
		      sw2.repaintChart();

		      long start = System.currentTimeMillis();
		      long duration = System.currentTimeMillis() - start;
		      try {
		        Thread.sleep(40 - duration); // 40 ms ==> 25fps
		        // Thread.sleep(400 - duration); // 40 ms ==> 2.5fps
		      } catch (InterruptedException e) {
		        System.out.println("InterruptedException occurred.");
		      }
		    }
		  }
	  
	  
	  
	  
		


     
}


/*System.out.println("teste");
PcapNetworkInterface nif = null;
System.out.println("teste");
    // Lista as interfaces
       nif = new NifSelector().selectNetworkInterface();
       if(nif == null)
       {
       	System.exit(1);
       }
PcapHandle handler = nif.openLive(65536, PromiscuousMode.PROMISCUOUS, 10);
Packet pacote = null;
//System.out.println("wololo");
handler.setFilter("tcp", BpfProgram.BpfCompileMode.OPTIMIZE);
while(pacote == null)
{
	 pacote = handler.getNextPacket();
}
Packet payload = null;
//System.out.println("wololo2");
while(payload == null)
{
     payload = pacote.getPayload();
}
//System.out.println(cabecalho.toString());
InetAddress inet;
String nseq;
String seqtratamento;
String janela;
String payloadstring;
seqtratamento = payload.toString();
payloadstring = payload.toString();
//System.out.println(payload);
seqtratamento = seqtratamento.substring(530, 550);
seqtratamento = seqtratamento.replaceAll("[^0-9]", "");
nseq = seqtratamento;
System.out.println("Número de sequencia:" + nseq);
//jantratamento = payload.toString();
//int valinicial = payloadstring.length() - 110;
//int valfinal = valinicial +30;
//jantratamento = jantratamento.substring(valinicial, valfinal);
//janela = jantratamento;
//System.out.println(janela);
//System.out.println(payloadstring.length());
//System.out.println(payload.getRawData().length);
String[] parts = payloadstring.split(":");
janela = parts[28];
janela = janela.replaceAll("[^0-9]", "");
System.out.println("Window: " + janela);
//System.out.println(seqtratamento.substring(530, 550));
//System.out.println(payload);
//System.out.println(payload.toString());
long ms=0;
for (int i = 0; i<=10; i++)
{
	 Instant before = Instant.now();
	 inet = InetAddress.getByAddress(new byte[] { 8, 8, 8, 8 });
	 //System.out.println("Sending Ping Request to " + inet);
	 //System.out.println(inet.isReachable(5000) ? "Host is reachable" : "Host is NOT reachable");
	 inet.isReachable(5000);
	 Instant after = Instant.now();
	 long delta = Duration.between(before, after).toMillis();
	 //System.out.println("ms:" + delta);
	 ms = delta+ms;
}
//handler = nif.openLive(65536, PromiscuousMode.PROMISCUOUS, 10);
pacote = null;
//handler.setFilter("tcp", BpfProgram.BpfCompileMode.OPTIMIZE);
handler = nif.openLive(65536, PromiscuousMode.PROMISCUOUS, 10);
handler.setFilter("tcp", BpfProgram.BpfCompileMode.OPTIMIZE);
int flag = 0;
while(pacote == null)
{
	 System.out.println("super wololo1");
	 while (flag == 0)
	 {
		 pacote = null;
		 while(pacote == null)
		 {
			 pacote = handler.getNextPacket();
		 }
		 payload = null;
		 while(payload == null)
		 {
			 payload = pacote.getPayload();
		 }
		 //System.out.println("super wololo2");
		 payloadstring = payload.toString();
		 seqtratamento = pacote.toString();
		 //System.out.println("super wololo3");
		 seqtratamento = seqtratamento.substring(530, 550);
	     seqtratamento = seqtratamento.replaceAll("[^0-9]", "");
	     nseq = seqtratamento;
	     System.out.println("Número de sequencia:" + nseq);
	     String[] partes = payloadstring.split(":");
	     janela = partes[28];
	     janela = janela.replaceAll("[^0-9]", "");
	     System.out.println("Window: " + janela);
	     //System.out.println("super wololo4");
	     
	 }
}
//System.out.println(pacote.getPayload());
//System.out.println(pacote.getHeader());
ms = ms/10;
System.out.println("ms médio:" + ms);*/

