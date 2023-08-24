class Ponto{
	
	//Váriaveis;
	private int x;
	private int y;
	private int id;
	private static int nextId = 0;
	
	
	//Construtores
	
	public Ponto(){
		x = 0;
		y = 0;
	    id = nextId;
		nextId = nextId + 1;
	}
	
	public Ponto(int x, int y){
		this.x = x;
		this.y = y;
	    this.id = nextId;
		nextId = nextId + 1;
	}
	
	
	//Metodos Get
	
	public int getX(){
		return(this.x);
	}
	
	public int getY(){
		return(this.y);
	}
	
	public int getId(){
		return(this.id);
	}
	
	public static int getNextId(){
		return(nextId);
	}
	
	public int getAreaRetangulo(Ponto p2){
		int segmento1 = this.dist(this.x, this.y);
		int segmento2 = p2.dist(this.x, this.y);
		
		return(segmento1*segmento2);
	}
	
	//Métodos Set
	
	public void setX(int value){
		this.x = value;
	}
	
	public void setY(int value){
		this.y = value;
	}
	
	public void setId(int value){
		this.id = value;
	}
	
	public static void setNextId(int value){
		nextId = value;
	}
	
	//Funções
	
	//Função que retorna a distancia entre duas variáveis tipo Pontos
	public int dist(Ponto p2){
		int distancia = 0;
		
		distancia = this.x - p2.x;
		distancia += this.y - p2.y;
		return(distancia < 0 ? -distancia : distancia);
	}
	
	//Função que retorna a distancia entre váriavel tipo ponto e dois inteiros
	public int dist(int x, int y){
		int distancia = 0;
		
		distancia = this.x - x + this.y - y;
		return(distancia < 0 ? -distancia : distancia);
	}
	
	//Função que retorna a distancia entre quatros inteiros
	public static int dist(int x1, int y1, int x2, int y2){
		int distancia = 0;
		
		distancia = x1 - x2 + y1 - y2;
		return(distancia < 0 ? -distancia : distancia);
	}
	
	//Função que testa se 3 pontos são triangulos
	public static boolean isTriangulo(Ponto p1, Ponto p2, Ponto p3)
	{
		return(true);
	}
	
	
}