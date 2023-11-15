import java.io.*;
import java.net.*;

public class AD_Drone {
	public String leeSocket (Socket p_sk, String p_Datos)
	{
		try
		{
			InputStream aux = p_sk.getInputStream();
			DataInputStream flujo = new DataInputStream(aux);
			p_Datos = flujo.readUTF();
		}
		catch (Exception e)
		{
			System.out.println("Error: " + e.toString());
		}
      return p_Datos;
	}

	public void escribeSocket (Socket p_sk, String p_Datos)
	{
		try
		{
			OutputStream aux = p_sk.getOutputStream();
			DataOutputStream flujo= new DataOutputStream(aux);
			flujo.writeUTF(p_Datos);      
		}
		catch (Exception e)
		{
			System.out.println("Error: " + e.toString());
		}
		return;
	}

	public void menu(String p_host, String p_puerto)
	{
		int accion;
		int id = 0;
		int cont_id = 1;
		int salir = 0;
		int x = 1;
		int y = 1;
		char resp = 'x';
		
		String Cadena = "";
		String  ac = "";
		String dron = "";
		String move = "";
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader (isr);
		
		try
		{
			Socket skDron = new Socket(p_host, Integer.parseInt(p_puerto));
			while (salir == 0)
			{
				accion = 0;
				while (ac == "reg" || ac == "esp" || accion == 0){
					while (accion != 1 && accion != 2 && accion != 3)
					{
						System.out.println("[1] Registrar");
						System.out.println("[2] Espectáculo");
						System.out.println("[3] Salir");
						System.out.println("Indica la accion a realizar: ");
						accion = Integer.parseInt(br.readLine());
					}

					switch(accion){
						case 1:
							ac="reg";
							id = cont_id;
							cont_id++;
							move = "Verde";
							/*Llamar a AD_Registry */
							break;

						case 2: 
							ac="esp";
							/*Llamar al Engine */
							break;

						case 3: 
							ac="exit";
							break;
					}
				}
				
				salir = 1;
					
				escribeSocket (skDron, "fin");
					
				Cadena = leeSocket (skDron, Cadena);
				int resultado = Integer.parseInt(Cadena);
		 			
				if (resultado == -1)
				{
					skDron.close();
					System.out.println("Conexion cerrada.");
					System.exit(0);	
				}

				Cadena = "";
				ac = "";
			}
		}
		catch(Exception e)
		{
			System.out.println("Error: " + e.toString());
		}
		return;		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AD_Drone dr = new AD_Drone();
		int i = 0;
		String ip;
		String puerto;

		if (args.length < 2) {
			System.out.println ("Debe indicar la direccion del servidor y el puerto");
			System.out.println ("$./Cliente ip puerto_servidor");
			System.exit(-1);
		}
		
		ip = args[0];
		puerto = args[1];

		while(i==0)
		{
			dr.menu(ip,puerto);
		}
	}

}