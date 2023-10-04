import java.net.*;
import java.util.Timer;
import java.util.TimerTask;

public class AD_Engine {

	/**
	 * @param args
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		* Descriptores de socket servidor y de socket con el cliente
		*/
		String puerto="";
		int MAX_DRONES, IP_gestorColas, puerto_gestorColas, IP_weather, puerto_weather = -1;

		try
		{
			
			if (args.length < 6) {
				System.out.println("Debe indicar el puerto de escucha del servidor, el número máximo de drones, IP gestor de colas, puerto gestor de colas, IP AD_Weather, puerto AD_Weather");
				System.exit (1);
			}
			puerto = args[0];
			ServerSocket skServidor = new ServerSocket(Integer.parseInt(puerto));
		    System.out.println("Escucho el puerto " + puerto);
			MAX_DRONES = Integer.parseInt(args[1]);
			System.out.println("El número maximo de drones es " + MAX_DRONES);
			IP_gestorColas = Integer.parseInt(args[2]);
			System.out.println("La IP del gestor de colas es " + IP_gestorColas);
			puerto_gestorColas = Integer.parseInt(args[3]);
			System.out.println("El puerto del gestor de colas es " + puerto_gestorColas);
			IP_weather = Integer.parseInt(args[4]);
			System.out.println("La IP de AD_Weather es " + IP_weather);
			puerto_weather = Integer.parseInt(args[5]);
			System.out.println("El puerto de AD_Weather es " + puerto_weather);

			Timer timer = new Timer();
			// Helper class extends TimerTask
			TimerTask task =  new TimerTask() {
				public void run() {
					System.out.println("Hola");
					cancel();
				}
			};
	
			
			/*
			* Mantenemos la comunicacion con el cliente
			*/	
			for(;;)
			{
				timer.schedule(task, 1, 1);
				/*
				* Se espera un cliente que quiera conectarse
				*/
				Socket skCliente = skServidor.accept(); // Crea objeto
		        System.out.println("Sirviendo cliente...");

		        Thread t = new HiloServidor(skCliente);
		        t.start();
			}
		}
		catch(Exception e)
		{
			System.out.println("Error: " + e.toString());
			
		}
	}

}
