package p2;

public class FedeAuto {

	private static final int MAX = 100;
	private Piloto[] pilotos;
	private int totalPilotos;
	
	public FedeAuto() {
		pilotos = new Piloto[MAX];
		totalPilotos = 0;
	}
	
	public boolean alta(Piloto p) {
		boolean res = false;
		boolean nombreRep;
		if(totalPilotos < MAX) {
			//No nombres repetidos
			nombreRep = nombreRepetido(p.getNombre());
			if(nombreRep==false) { //  !nombreRep
				//Meterlos en orden
				//1. Encontrar donde meterlo
				char letra = p.getNombre().charAt(0); //Checa la primera letra del nombre
				int j=0;
				char letraPilotoActual = pilotos[j].getNombre().charAt(0);
				while(j<totalPilotos && letra<letraPilotoActual) {
					j++;
				}
				
				//2. Mover los demas
				corrimientoADerecha(pilotos, totalPilotos, j);
				
				//3. Meterlo
				pilotos[j]=p;
				
				
				//Una vez que lo logramos insertar hay que incrementar nuestro total(pq ya hay otro más y cambiar la variable
				//que registra si se logró o no)
				totalPilotos++;
				res=true;
				
				
			}
		}
		
		return res;
	}
	
	public double calculaProm() {
		double puntos=0;
		double prom;
		for(int i=0; i<totalPilotos; i++) {
			puntos += pilotos[i].getPuntos();  
		}
		
		prom = puntos/totalPilotos;
		
		return prom;
	}
	
	//Si regresa false es que no hay piloto con ese mismo nombre
	public boolean nombreRepetido(String nombre) {
		boolean res=false;
		
		
		//Se sale del ciclo una vez que encuentra nombre reptido
		int i=0;
		while(i<totalPilotos && !(pilotos[i].getNombre()).equals(nombre)) {
			i++;
		}
		
		if(pilotos[i].getNombre().equals(nombre)) {
			res = true;
		}
		
//		//Recorre y checa el nombre de todos los pilotos
//		for(int i=0; i<totalPilotos; i++) {
//			if(pilotos[i].getNombre().equals(nombre)) {
//				res = true;
//			}
//		}
		return res;
	}
	
	//Lo movemos para acá pq estamos trabajando con un arreglo de pilotos no con uno de enteros
		public static void corrimientoADerecha(Piloto[] arreglo, int totalElem, int indice) {
			for(int i=totalElem; i>indice; i--) {
				arreglo[i]=arreglo[i-1];
			}
			arreglo[indice]=null;
		}
	
}

