import java.util.ArrayList;
import java.util.Random;

public class StreamParallel {
	
	public static int calcular_f(int n){
		for(int i=1;i<20000; i++){
			n = (n ^ i) % i;
			if (n <= 0)
				n = 10;
		}
		return n;
	}
	
	public static void main(String[] args) {
		Random aleatorio = new Random();
		ArrayList<Integer> lista = new ArrayList<>();
		
		for(int i=0;i< 500000;i++){
			//Se agregan medio millon de números aleatorios para su procesamiento
			lista.add(aleatorio.nextInt());
		}
		System.out.println("Comenzo a procesar...");
		long inicio = System.currentTimeMillis();
			
			//Procesamiento Secuencial Tradicional
			
			/*int resultado = 0;
			for(int n : lista){
				resultado += calcular_f(n);
			}*/
			
		
			//Procesamiento usando Paralell Stream
			int resultado = lista.parallelStream()
								  .map( n -> calcular_f(n) )
								  .reduce(Integer::sum).get();
	
		long termino = System.currentTimeMillis();
		System.out.println("Resultado de aplicar la funcion calcular_f sobre la lista: " + resultado);
		System.out.println("Termino de porcesar..." + (inicio - termino) + " mili-segundos");
	}
}