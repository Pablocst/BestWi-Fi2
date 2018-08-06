package BestWIFI;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Pacotesperdidos {
	static String redes;
	
	public Pacotesperdidos(String rede)
	{
		this.redes = rede;
	}
	
	public static void main() throws IOException
	 {
		 ArrayList<String> msg = new ArrayList<String>();
		 ArrayList<String> perdidos = new ArrayList<String>();
		 
		 long startTime = System.currentTimeMillis(); //fetch starting time
		 while(false||(System.currentTimeMillis()-startTime)<100000)
		 {
			 try 
		        { 
		            Process p=Runtime.getRuntime().exec("cmd /c C:/Users/pablo/Desktop/python/Fping.exe 8.8.8.8 -t500 -n20 -L capturapacotesperdidos"+redes+".txt");
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
		                //System.out.println(line);
		                i++;
		            } 
		        }
		        catch(IOException e1) {e1.printStackTrace();} 
		        catch(InterruptedException e2) {e2.printStackTrace();} 
				 String msgtratamento = msg.toString();
				 //msgtratamento.replaceAll("\n", "");
				 //msgtratamento.substring(3, 4);
				 String parts[] = msgtratamento.split("Lost");
				 msgtratamento = parts[1];
				 String parts2[] = parts[1].split("%");
				 msgtratamento = parts2[0];
				 msgtratamento = msgtratamento.replaceAll("[^0-9]", "");
				 String parts3[] = msgtratamento.split("");
				 msgtratamento = parts3[0];
				 System.out.println(msgtratamento);
				 //System.out.println("aquiiiiiiiiiii");
				 perdidos.add(msgtratamento);
				 long perdidoslong = Long.parseLong(msgtratamento);
				 //System.out.println(perdidoslong);
				 File file = new File("C:/Users/pablo/Desktop/Python/capturapacotesperdidos"+redes+".txt");
	     		 FileOutputStream fos = new FileOutputStream(file);
	             file = new File("C:/Users/pablo/Desktop/Python/capturapacotesperdidos"+redes+".txt");
	             if(file.exists());
	             {
	                 file.delete();
	                 file = new File("C:/Users/pablo/Desktop/Python/capturapacotesperdidos"+redes+".txt");
	             }
	             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
	             for(int i=0; i<perdidos.size(); i++)
	             {
	            	 bw.write(i+1 + ","+ perdidos.get(i).toString());
	            	 bw.newLine();
	   
	             }
	         
	             bw.close();
	       
		     
		 }
			
           
			 //msgtratamento.replaceAll("[^0-9]", "");
			 //
			 //System.out.print(msgtratamento);
	        //System.out.println("Done"); 
		 
	 }

}
