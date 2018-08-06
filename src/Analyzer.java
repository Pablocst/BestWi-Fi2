
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class Analyzer implements Runnable  {

private static ArrayList<Long> janelaglob = new ArrayList<Long>();
private static ArrayList<Long> nseqglob = new ArrayList<Long>();
private static ArrayList<Long> jitter = new ArrayList<Long>();
private static ArrayList<Long> retransmissoes = new ArrayList<Long>();
private static ArrayList<Long> perdasglob = new ArrayList<Long>();
static ArrayList<String> escolhidos = new ArrayList<String>();

static String rede;
long counter= 0;
private long[] pings;
static int decisao;


	
	
	public void run() 
	{
		 System.out.println(rede);
		 int flag = 0;
		 //System.out.println("teste");
		 PcapNetworkInterface nif = null;
		 //System.out.println("teste");
	     // Lista as interfaces
		
	           if(decisao > 0)
	           {
	        	   try {
		            
		             	//System.out.printf("ssid da rede sem fio Para a análise: ");
		            	//String wifissid =  scanner.nextLine();
		            	//System.out.println(wifissid);
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
		     //System.out.println("Número de sequencia:" + nseq);
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
		     //System.out.println("Window: " + janela);
		     //System.out.println(seqtratamento.substring(530, 550));
			 //System.out.println(payload);
		     //System.out.println(payload.toString());
			 
			 //handler = nif.openLive(65536, PromiscuousMode.PROMISCUOUS, 10);
			 pacote = null;
			 //handler.setFilter("tcp", BpfProgram.BpfCompileMode.OPTIMIZE);
			 try {
				handler = nif.openLive(65537, PromiscuousMode.PROMISCUOUS, 10);
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
			 long rettemp = 0;
			 while(pacote == null)
			 {
				 //System.out.println("super wololo1");
				 try
				 {
					 //ServerSocket servidor = new ServerSocket(5000);
				     //System.out.println("Servidor ouvindo a porta 5000");
				 long count = System.currentTimeMillis();
				 long countend = 0;
				 System.out.println(count);
				 while (flag == 0 && countend - count <= 20000)
				 {
					 long ms=0;
					 long[] ping = new long[3];
					 long jittertemp = 0;
					 for (int i = 0; i<=2; i++)
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
						 ping[i] = delta;
					 }
						 if(ping[0] < ping[1])
						 {
							 long temp;
							 temp = ping[0];
							 ping[0] = ping[1];
							 ping[1] = temp;
						 }
						 jittertemp = ping[0] - ping[1];
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
					 counter++;
					 //seqtratamento = seqtratamento.substring(530, 550);
				     //seqtratamento = seqtratamento.replaceAll("[^0-9]", "");
				     //nseq = seqtratamento;
				     //System.out.println("Número de sequencia:" + nseq);
				     String[] partes2 = payloadstring.split(":");
				     janela = partes2[28];
				     janela = janela.replaceAll("[^0-9]", "");
				     String janelatemp = janela;
				     String nseqtemp = nseq;
				     long janelalong = Long.parseLong(janelatemp);
				     String nseqtemp2 = String.valueOf(nseq);
				     long nseqlong = Long.parseLong(nseqtemp2);
				     if(janelalong != 0)
				     {
				    	 System.out.println("Número de sequencia:" + nseq);
				    	 System.out.println("Window: " + janela);
					     System.out.println("Jitter: " + jittertemp);
					     jitter.add(jittertemp);
					     janelaglob.add(janelalong);
					     nseqglob.add(nseqlong);
					     
					     if(nseqglob.size() > 23)
					     {
					    	 for(int  i = nseqglob.size()-1; i> nseqglob.size()-21; i--)
						     {
						    	 if(nseqlong == nseqglob.get(i) && janelalong != janelaglob.get(i))
							     {
						    		 rettemp ++;
							    	 retransmissoes.add(rettemp);
							     }
						     }
					     }
					     Analyzer an = new Analyzer();
					     long aux = 0;
					     System.out.println("parei aqui");
					     if(perdasglob.size() > 2)
					     {
					    	  aux = perdasglob.get(perdasglob.size()-1);
					     }
						 long pacotesperdidos = an.taxadepacotesperdidos();
						 aux = aux + pacotesperdidos;
						 perdasglob.add(aux);
					     long percentage = (long) ((rettemp*100)/counter);
					     System.out.println("numero de retransmissoes:" + rettemp);
					     System.out.println("indice de retransmissão:" + percentage + "%");
					     System.out.println("numero de pacotes perdidos:" + perdasglob.get(perdasglob.size()-1));
					     Pacotesperdidos pa;
					     new Analyzer().escreverarquivos(rede);
					     new Analyzer().escreverteste();
				     }
				     
				     //System.out.println("super wololo4");
				     //ObjectOutputStream saida = new ObjectOutputStream(janelalong);
				     countend = System.currentTimeMillis(); 
				     System.out.println (countend - count);
				 }
				 
				 }
				 catch(Exception e)
				 {
					 System.out.println("Error" + e.getMessage());
				 }
			 }
			 //System.out.println(pacote.getPayload());
			 //System.out.println(pacote.getHeader());
			 //ms = ms/10;
			 //System.out.println("ms médio:" + ms);
			 
		}
	           }
	


	
	
	                      
	            	
	public static void main(String args[]) throws InterruptedException, IOException
	{
		Scanner scanner = new Scanner(System.in);
	 	System.out.printf("Deseja ler todas as redes sem fio disponíveis automaticamente digite 1 para sim 2 para não: ");
	 	decisao = scanner.nextInt();
	 	Analyzer an = new Analyzer();
	 	an.verifywifi();
	 	for(int i=0; i<escolhidos.size(); i++)
	 	{
	 		rede = escolhidos.get(i);
			(new Thread(new Analyzer())).start();
			Thread.sleep(10000);
	 	}
		/*while(true)
		{
			for(int i=0;i<janelaglob.size(); i++)
			{
				long janetemp = janelaglob.get(i);
				System.out.println(janetemp);
			}
		}
		
	*/
	//new Analyzer().go();
	//new Analyzer().go2();
	}
	
	public void escreverarquivos(String rede) throws IOException 
	{
		File file = new File("C:/Users/pablo/Desktop/Python/capturajanela"+rede+".txt");
		FileOutputStream fos = new FileOutputStream(file);
      
            file = new File("C:/Users/pablo/Desktop/Python/capturajanela"+rede+".txt");
            if(file.exists());
            {
            	file.delete();
            	file = new File("C:/Users/pablo/Desktop/Python/capturajanela"+rede+".txt");
            }
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
            int tamanho1 = janelaglob.size();
            for (int i = 0; i < tamanho1; i++) 
            {
            	bw.write(i+1 + "," + janelaglob.get(i).toString());
                bw.newLine();
            }
            bw.close();
            
            File file2 = new File("C:/Users/pablo/Desktop/Python/capturanseq"+rede+".txt");
    		FileOutputStream fos2 = new FileOutputStream(file2);
          
                file2 = new File("C:/Users/pablo/Desktop/Python/capturanseq"+rede+".txt");
                if(file2.exists());
                {
                	file2.delete();
                	file2 = new File("C:/Users/pablo/Desktop/Python/capturanseq"+rede+".txt");
                }
                BufferedWriter bw2 = new BufferedWriter(new OutputStreamWriter(fos2));
                int tamanho2 = nseqglob.size();
                for (int i = 0; i < tamanho2; i++) 
                {
                    bw2.write(i+1 + "," + nseqglob.get(i).toString());
                    bw2.newLine();
                }
                bw2.close();
                
                File file3 = new File("C:/Users/pablo/Desktop/Python/capturajitter"+rede+".txt");
        		FileOutputStream fos3 = new FileOutputStream(file3);
              
                    file3 = new File("C:/Users/pablo/Desktop/Python/capturajitter"+rede+".txt");
                    if(file3.exists());
                    {
                    	file3.delete();
                    	file3 = new File("C:/Users/pablo/Desktop/Python/capturajitter"+rede+".txt");
                    }
                    BufferedWriter bw3 = new BufferedWriter(new OutputStreamWriter(fos3));
                    int tamanho3 = jitter.size();
                    for (int i = 0; i < tamanho3; i++) 
                    {
                        bw3.write(i+1 + "," + jitter.get(i).toString());
                        bw3.newLine();
                    }
                    bw3.close();
                    
               
                    File file4 = new File("C:/Users/pablo/Desktop/Python/capturaretransmissoes"+rede+".txt");
            		FileOutputStream fos4 = new FileOutputStream(file4);
                  
                        file4 = new File("C:/Users/pablo/Desktop/Python/capturaretransmissoes"+rede+".txt");
                        if(file4.exists());
                        {
                        	file4.delete();
                        	file4 = new File("C:/Users/pablo/Desktop/Python/capturaretransmissoes"+rede+".txt");
                        }
                        BufferedWriter bw4 = new BufferedWriter(new OutputStreamWriter(fos4));
                        int tamanho4 = retransmissoes.size();
                        for (int i = 0; i < tamanho4; i++) 
                        {
                            bw4.write(i+1 + "," + retransmissoes.get(i).toString());
                            bw4.newLine();
                        }
                        bw4.close();
         
        
	}
	public void escreverteste() throws Exception
	{
		File file;
		FileOutputStream out;
		DataOutputStream dout = null;
		try
		{
			file = new File("testandoo.txt");
			if(file.exists());
            {
            	file.delete();
            	file = new File("testandoo.txt");
            }
			out = new FileOutputStream(file);
			dout = new DataOutputStream(out);
			file.createNewFile();
			int tamanho = janelaglob.size();
			for(int i = 0; i<tamanho; i++)
			{
				String temp = janelaglob.get(i).toString();
				dout.writeUTF(janelaglob.get(i).toString() + "\n");
				dout.writeChars(janelaglob.get(i).toString() + "\n");
			}
			out.close();
			dout.close();
		}
		catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
	}
	public long taxadepacotesperdidos()
	{
		ArrayList<String> msg = new ArrayList<String>();
		try 
        { 
            Process p=Runtime.getRuntime().exec("ping -n 1 8.8.8.8");
            p.waitFor(); 
            BufferedReader reader=new BufferedReader(
                new InputStreamReader(p.getInputStream())
            ); 
            String line; 
            int flag=0;
            int i = 0;
            while((line = reader.readLine()) != null) 
            { 
           	 //System.out.println(line);
                msg.add(i, line);
                i++;
            } 
        }
        catch(IOException e1) {e1.printStackTrace();} 
        catch(InterruptedException e2) {e2.printStackTrace();} 
		 String msgtratamento = msg.toString();
		 //msgtratamento.replaceAll("\n", "");
		 //msgtratamento.substring(3, 4);
		 String parts[] = msgtratamento.split(",");
		 msgtratamento = parts[7];
		 msgtratamento = msgtratamento.replaceAll("[^0-9]", "");
		 String parts2[] = msgtratamento.split("");
		 msgtratamento = parts2[1];
		 long perdidoslong = Long.parseLong(msgtratamento);
		 
		 //msgtratamento.replaceAll("[^0-9]", "");
		 //
		 //System.out.print(msgtratamento);
        //System.out.println("Done"); 
		 return perdidoslong;
	}
	public void verifywifi()
	{
		
	ArrayList<String> msg = new ArrayList<String>();
	 ArrayList<String> msg2 = new ArrayList<String>();
	 ArrayList<String> comp = new ArrayList<String>();
	 ArrayList<String> comp2 = new ArrayList<String>();
	 try 
       { 
           Process p=Runtime.getRuntime().exec("cmd /c netsh wlan show network");
           p.waitFor(); 
           BufferedReader reader=new BufferedReader(
               new InputStreamReader(p.getInputStream())
           ); 
           String line; 
           int flag=1;
           int i = 0;
           while((line = reader.readLine()) != null) 
           { 
          	 //System.out.println(line);
               msg.add(i, line);
               //System.out.println(line);
               i++;
           } 
           //System.out.println(msg.toString());
           String msgtratamento = msg.toString();
			 //msgtratamento.replaceAll("\n", "");
			 //msgtratamento.substring(3, 4);
			 String parts[] = msgtratamento.split("SSID");
			 for(i=0; i<parts.length-1; i++)
			 {
				 msgtratamento = parts[i+1];
				 String parts2[] = msgtratamento.split(",");
				 msgtratamento = parts2[0];
				 msgtratamento = msgtratamento.replaceAll("[^A-Za-z0-9]", "");
				 msgtratamento = msgtratamento.replaceAll("[0-9]", "");
				 comp.add(msgtratamento);
			 }
       }
       catch(IOException e1) {e1.printStackTrace();} 
       catch(InterruptedException e2) {e2.printStackTrace();}
	 try 
       { 
           Process p=Runtime.getRuntime().exec("cmd /c netsh wlan show profiles");
           p.waitFor(); 
           BufferedReader reader=new BufferedReader(
           		new InputStreamReader(p.getInputStream())
           ); 
           String line; 
           int flag=1;
           int i = 0;
           while((line = reader.readLine()) != null) 
           { 
          	 //System.out.println(line);
               msg2.add(i, line);
               //System.out.println(line);
               i++;
           } 
           //System.out.println(msg2.toString());
           String msgtratamento = msg2.toString();
			 //msgtratamento.replaceAll("\n", "");
			 //msgtratamento.substring(3, 4);
			 String parts[] = msgtratamento.split("s:");
			 for(i=0; i<parts.length-1; i++)
			 {
				 msgtratamento = parts[i+1];
				 String parts2[] = msgtratamento.split(",");
				 msgtratamento = parts2[0].replaceAll(" ", "");
				 comp2.add(msgtratamento);
				 
			 }

			 for (i=0; i<comp.size(); i++)
			 {
				 for (int j=0; j<comp2.size(); j++)
				 {
					 if(comp.get(i).equals(comp2.get(j)))
					 {
						 escolhidos.add(comp.get(i));
					 }
				 }
			 }
			
			 
			 
       }
       catch(IOException e1) {e1.printStackTrace();} 
       catch(InterruptedException e2) {e2.printStackTrace();}	
		
	}
	private void go() {

	    // Create Chart
		  /*List<XYChart> charts = new ArrayList<XYChart>();
		  List<XYChart> charts2 = new ArrayList<XYChart>();
	    chart =
	        QuickChart.getChart(
	            "SwingWorker XChart Real-time Demo",
	            "Time",
	            "Value",
	            "randomWalk",
	            new double[] {0},
	            new double[] {0});
	    chart2 =
		        QuickChart.getChart(
		            "SwingWorker XChart Real-time Demo",
		            "Time",
		            "Value",
		            "randomWalk",
		            new double[] {0},
		            new double[] {0});
	    
	    chart.getStyler().setLegendVisible(false);
	    chart.getStyler().setXAxisTicksVisible(false);
	    
	    chart2.getStyler().setLegendVisible(false);
	    chart2.getStyler().setXAxisTicksVisible(false);

	    // Show it
	    sw = new SwingWrapper<XYChart>(chart);
	    sw.displayChart();
	    sw2 = new SwingWrapper<XYChart>(chart);
	    sw2.displayChart();
	    
		    //new SwingWrapper<XYChart>(charts).displayChartMatrix();
        MySwingWorker janelagraph1 = new MySwingWorker(janelaglob);
        janelagraph1.execute();
        MySwingWorker nseqgraph2 = new MySwingWorker(nseqglob);
        nseqgraph2.execute();
        */
	    //mySwingWorker = new MySwingWorker();
	    //mySwingWorker.execute();
	  }

	  private class MySwingWorker extends SwingWorker<Boolean, double[]>  {
		  
		  
		/*ArrayList<Long> teste = new ArrayList<Long>();
		public MySwingWorker(ArrayList<Long> teste)
		{
			this.teste = teste;
		}

		protected Boolean doInBackground() throws Exception {

		      while (!isCancelled()) {
		        double[] array = new double[teste.size()];
		        for (int i = 0; i < teste.size(); i++) {
		          array[i] = teste.get(i);
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
	    //@Override
	     * */
	     
	   
	    
	     protected Boolean doInBackground() throws Exception {

	      /*while (!isCancelled()) {
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
	      }*/
	    	 System.out.println("funfouuuuuuuuuu");

	      return true;
	    }

	    //@Override
	    protected void process(List<double[]> chunks) {

	      //System.out.println("number of chunks: " + chunks.size());
	    	System.out.println("funfouuuuuuuuuu");

	      //double[] mostRecentDataSet = chunks.get(chunks.size() - 1);

	      //chart.updateXYSeries("randomWalk", null, mostRecentDataSet, null);
	      //sw.repaintChart();

	      long start = System.currentTimeMillis();
	      long duration = System.currentTimeMillis() - start;
	      try {
	        Thread.sleep(40 - duration); // 40 ms ==> 25fps
	         Thread.sleep(400 - duration); // 40 ms ==> 2.5fps
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

