package ut02;


public class Disco {

	
		
		enum Formato {MP3, CD, OGG};
		String titulo;
		Formato formato;
		String autor;
		
		public Disco(String titulo, Formato formato, String autor) {
			this.titulo = titulo;
			this.formato = formato;
			this.autor = autor;
		}
		public String getTitulo() {
			return titulo;
		}
		public Formato getFormato() {
			return formato;
		}
		public String getAutor() {
			return autor;
		}
		public void setTitulo(String titulo) {
			this.titulo = titulo;
		}
		public void setFormato(Formato formato) {
			this.formato = formato;
		}
		public void setAutor(String autor) {
			this.autor = autor;
		}
		
		
		
	}


