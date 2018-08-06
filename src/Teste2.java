import java.io.*; 


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
	public static void main(String[] args) {
		 ArrayList<String> msg = new ArrayList<String>();
		 ArrayList<String> msg2 = new ArrayList<String>();
		 ArrayList<String> comp = new ArrayList<String>();
		 ArrayList<String> comp2 = new ArrayList<String>();
		 ArrayList<String> escolhidos = new ArrayList<String>();
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
				 //System.out.println(comp.toString());
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
				 //System.out.println(comp2.toString());
				 System.out.printf("\n\n\n");
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
				 //System.out.println(escolhidos.toString());
				 
				 
	        }
	        catch(IOException e1) {e1.printStackTrace();} 
	        catch(InterruptedException e2) {e2.printStackTrace();}		 
}
	  
}
