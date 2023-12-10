package librerias;
/**
 * 
 * @author Mariel Sofía Gutiérrez Zapién
 * @version 1.1 Atributos, constructores, métodos de requerimiento mínimo.
 * Clase que al instanciarla crea un escrito (amparo, artículo, ensayo, etc) con su información correspondiente.
 */
public class Escrito {
	
	private int tipo; //amparo, ensayo, articulo 
	private int area; //corrupcón, constitucional, electoral, medio ambiente, penal, finanzas, política pública, salud
	private String destinatario; //ante quien fue presentado el escrito (ej. Tribunal 8)
	private String titulo; 
	private String direccion; //dónde esta guardado el documento (ej. google drive, carpeta en la computadora)
	private boolean terminado; //ya se termino el asunto?
	private String[] persona;
	private String [] fecha;
	private static int cont = 1;
	private int folio;
	
	public Escrito(){
		fecha = new String[2]; //fecha[0] = cuando se presento & fecha[1] cuando se publico
		persona = new String[4]; //persona[0] = procedencia & persona[1] a quien se represento & persona[2] datos del representado && persona[3] persona que apoyo a realizarlo el trabajo 
		folio = cont;
		cont++;
	}
	
	//Crea un Escrito pero SIN folio
	public Escrito(int tipo, String titulo, int area, String destinatario, String fechaE, String representandoA, String contacto,
			String procedencia, String apoyadoPor, String fechaP, String direccion) {
		fecha = new String[2]; 
		persona = new String[4];
		this.tipo = tipo;
		this.titulo = titulo;
		this.area = area;
		this.destinatario = destinatario;
		fecha[0] = fechaE;
		fecha[1] = fechaP; //no es totalmente necesario. Si hay . es que no aplica en el caso de ese Escrito.
		persona[0] = procedencia;
		persona[1] = representandoA;//no es totalmente necesario. Si hay . es que no aplica en el caso de ese Escrito.
		persona[2] = contacto;//no es totalmente necesario. Si hay . es que no aplica en el caso de ese Escrito.
		persona[3] = apoyadoPor;//no es totalmente necesario. Si hay . es que no aplica en el caso de ese Escrito.
		this.direccion = direccion;
	}
	
	//Creo un escrito con los 13 datos.
	public Escrito(int tipo, String titulo, int area, String destinatario, String fechaE, String representandoA, String contacto,
			String procedencia, String apoyadoPor, String fechaP, String direccion, String concluido) {
		this();
		this.tipo = tipo;
		this.titulo = titulo;
		this.area = area;
		this.destinatario = destinatario;
		fecha[0] = fechaE;
		fecha[1] = fechaP;
		persona[0] = procedencia;
		persona[1] = representandoA;
		persona[2] = contacto;
		persona[3] = apoyadoPor;
		this.direccion = direccion;
		if(concluido.equalsIgnoreCase("si"))
			terminado = true;
		else
			terminado = false;
	}
	

	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}

	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public boolean isTerminado() {
		return terminado;
	}

	public void setTerminado(boolean terminado) {
		this.terminado = terminado;
	}

	public String getFecha( int i) {
		return fecha[i];
	}

	public void setFecha(String fecha, int i) {
		this.fecha[i] = fecha;
	}

	public int getTipo() {
		return tipo;
	}
	
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public String getPersona(int i) {
		return persona[i];
	}
	
	public void setPersona(String persona, int i) {
		this.persona[i] = persona;
	}
	
	public int getFolio() {
		return folio;
	}
	
	
	/**
	 * @return La información del escrito tal y como está en el archivo de texto.
	 */
	public String formatoArchivo() {
		StringBuilder sb;
		sb = new StringBuilder();
		sb.append(tipo+"-");
		sb.append(titulo+"-");
		sb.append(area+"-");
		sb.append(destinatario+"-");
		sb.append(fecha[0]+"-");
		sb.append(persona[1]+"-");
		sb.append(persona[2]+"-");
		sb.append(persona[0]+"-");
		sb.append(persona[3]+"-");
		sb.append(fecha[1]+"-");
		sb.append(direccion+"-");
		
		return sb.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Escrito other = (Escrito) obj;
		if (area != other.area)
			return false;
		if (tipo != other.tipo)
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		return true;
	}

	public String toString() {
		StringBuilder sb;
		sb = new StringBuilder();
		sb.append("Titulo: " + titulo+"\n");
		sb.append("Tipo: " + tipo+"\n");
		sb.append("Area: " + area+"\n");
		sb.append("Se presentó ante: "+ destinatario+"\n");
		sb.append("Fue presentado en: " + fecha[0]+"\n");
		sb.append("Fue publicado en: " + fecha[1]+"\n");
		sb.append("Se presento a nombre de: " + persona[0]+"\n");
		if(persona[2]!=null)
			sb.append("Se hizo con apoyo de: "+persona[3]+"\n");
		sb.append("Representando a: " + persona[1]+"\n");
		sb.append("El contacto de "+persona[1]+ "es: "+persona[2]+"\n");
		if(terminado)
			sb.append("Ya esta finiquitado el asunto"+"\n");
		else
			sb.append("Todavia no esta finiquitado el asunto"+"\n");
		sb.append("El documento esta guardado en: " + direccion+"\n");
		sb.append("Su folio es: "+ folio);
		
		return sb.toString();
	}
	
	public int compareTo(Escrito e) {
		return folio - e.folio;
	}
}
	
