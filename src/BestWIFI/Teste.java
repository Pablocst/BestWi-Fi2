package BestWIFI;
import java.io.BufferedReader;
import java.io.FileReader;
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
	
	static ArrayList<String> msg = new ArrayList<String>();
	static ArrayList<String> msg2 = new ArrayList<String>();
	static ArrayList<String> msg3 = new ArrayList<String>();
	static ArrayList<String> msg4 = new ArrayList<String>();
	static ArrayList<String> msg5 = new ArrayList<String>();
	static ArrayList<String> jittertratado = new ArrayList<String>();
	static ArrayList<String> perdidostratado = new ArrayList<String>();
	static ArrayList<String> janelatratada = new ArrayList<String>();
	static ArrayList<String> throughputtratado = new ArrayList<String>();
	static ArrayList<String> retransmissoestratado = new ArrayList<String>();
	//ArrayList<String> perdidos = new ArrayList<String>();	

	

  public static void main(String[] args) throws Exception {
	  
	  String rede = "Falante";
	  try {
	      FileReader arq = new FileReader("C:/Users/pablo/Desktop/Python/capturajitter"+rede+".txt");
	      BufferedReader lerArq = new BufferedReader(arq);
	 
	      String linha = lerArq.readLine(); // l
	      while (linha != null) {
	        //System.out.printf("%s\n", linha);
	        msg.add(linha);
	        linha = lerArq.readLine(); // lê da segunda até a última linha
	      }
	 
	      arq.close();
	    } catch (IOException e) {
	        System.err.printf("Erro na abertura do arquivo: %s.\n",
	          e.getMessage());
	    }
	  for(int i=0; i<msg.size(); i++)
	  {
		  String msgtratamento = msg.get(i).toString();
		  //System.out.println(msg.get(i).toString());
		  String parts[] = msgtratamento.split(",");
		  jittertratado.add(parts[1]);
	  }
	//System.out.println(msg.toString());
	System.out.println(jittertratado.toString());
	long mediajitter = 0;
	long totaljitter = 0;
	for(int i=0; i<jittertratado.size(); i++)
	{
		long temp = Long.parseLong(jittertratado.get(i));
		totaljitter = totaljitter + temp;
	}
	
	long tamanhojitter = (long)jittertratado.size();
	mediajitter = totaljitter/tamanhojitter;
	System.out.println(mediajitter);
	
	 try {
	      FileReader arq = new FileReader("C:/Users/pablo/Desktop/Python/capturajanela"+rede+".txt");
	      BufferedReader lerArq = new BufferedReader(arq);
	 
	      String linha = lerArq.readLine(); // l
	      while (linha != null) {
	        //System.out.printf("%s\n", linha);
	        msg2.add(linha);
	        linha = lerArq.readLine(); // lê da segunda até a última linha
	      }
	 
	      arq.close();
	    } catch (IOException e) {
	        System.err.printf("Erro na abertura do arquivo: %s.\n",
	          e.getMessage());
	    }
	  for(int i=0; i<msg2.size(); i++)
	  {
		  String msgtratamento = msg2.get(i).toString();
		  String parts[] = msgtratamento.split(",");
		  janelatratada.add(parts[1]);
	  }
	//System.out.println(msg.toString());
	//System.out.println(janelatratada.toString());
	long mediajanela = 0;
	long totaljanela = 0;
	for(int i=0; i<janelatratada.size(); i++)
	{
		long temp = Long.parseLong(janelatratada.get(i));
		totaljanela = totaljanela + temp;
	}
	
	long tamanhojanela = (long)janelatratada.size();
	mediajanela = totaljanela/tamanhojanela;
	System.out.println(mediajanela);
	
	
	 try {
	      FileReader arq = new FileReader("C:/Users/pablo/Desktop/Python/capturapacotesperdidos"+rede+".txt");
	      BufferedReader lerArq = new BufferedReader(arq);
	 
	      String linha = lerArq.readLine(); // l
	      while (linha != null) {
	        //System.out.printf("%s\n", linha);
	        msg3.add(linha);
	        linha = lerArq.readLine(); // lê da segunda até a última linha
	      }
	 
	      arq.close();
	    } catch (IOException e) {
	        System.err.printf("Erro na abertura do arquivo: %s.\n",
	          e.getMessage());
	    }
	  for(int i=0; i<msg3.size(); i++)
	  {
		  String msgtratamento = msg3.get(i).toString();
		  String parts[] = msgtratamento.split(",");
		  perdidostratado.add(parts[1]);
	  }
	//System.out.println(msg.toString());
	//System.out.println(perdidostratado.toString());
	long mediaperdidos = 0;
	long totalperdidos = 0;
	for(int i=0; i<perdidostratado.size(); i++)
	{
		long temp = Long.parseLong(perdidostratado.get(i));
		totalperdidos = totalperdidos + temp;
	}
	
	long tamanhoperdidos = (long)perdidostratado.size();
	mediaperdidos = totalperdidos/tamanhoperdidos;
	System.out.println(mediaperdidos);
	
	 try {
	      FileReader arq = new FileReader("C:/Users/pablo/Desktop/Python/capturaindiceretransmissao"+rede+".txt");
	      BufferedReader lerArq = new BufferedReader(arq);
	 
	      String linha = lerArq.readLine(); // l
	      while (linha != null) {
	        //System.out.printf("%s\n", linha);
	        msg4.add(linha);
	        linha = lerArq.readLine(); // lê da segunda até a última linha
	      }
	 
	      arq.close();
	    } catch (IOException e) {
	        System.err.printf("Erro na abertura do arquivo: %s.\n",
	          e.getMessage());
	    }
	  for(int i=0; i<msg4.size(); i++)
	  {
		  String msgtratamento = msg4.get(i).toString();
		  String parts[] = msgtratamento.split(",");
		  retransmissoestratado.add(parts[1]);
	  }
	//System.out.println(msg.toString());
	//System.out.println(perdidostratado.toString());
	long mediaretransmissoes = 0;
	long totalretransmissoes = 0;
	for(int i=0; i<retransmissoestratado.size(); i++)
	{
		long temp = Long.parseLong(retransmissoestratado.get(i));
		totalretransmissoes = totalretransmissoes + temp;
	}
	
	long tamanhoretransmissoes = (long)retransmissoestratado.size();
	mediaperdidos = totalretransmissoes/tamanhoretransmissoes;
	System.out.println(mediaretransmissoes);

 
    
  }

  
  
}
