package BestWIFI;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.markers.SeriesMarkers;
import org.pcap4j.core.PcapNetworkInterface;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.swing.SwingWorker;



public class Teste{
	
	//static ArrayList<String> msg = new ArrayList<String>();
	//ArrayList<String> perdidos = new ArrayList<String>();	

	

  public static void main(String[] args) throws Exception {
	  
	  String teste = "AndroidAP";
	  try 
      { 
          Process p=Runtime.getRuntime().exec("cmd /c netsh wlan connect ssid="+teste+" name="+teste);
          p.waitFor(); 
          System.out.println("eita que tristeza");
      }
      catch(IOException e1) {e1.printStackTrace();} 
      catch(InterruptedException e2) {e2.printStackTrace();} 

 
    
  }

  
  
}
