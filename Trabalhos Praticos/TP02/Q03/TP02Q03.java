import java.io.*;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import javax.naming.spi.DirStateFactory.Result;


class Jogador{
    private int id;
    private String nome;
    private int altura;
    private int peso;
    private String universidade;
    private int anoNascimento;
    private String cidadeNascimento;
    private String estadoNascimento;


    public Jogador()
    {
        this.id = 0;
        this.nome = new String();
        this.altura = 0;
        this.peso = 0;
        this.universidade = new String();
        this.anoNascimento = 0;
        this.cidadeNascimento = new String();
        this.estadoNascimento = new String();
    }

    public Jogador(int id, String nome, int altura, int peso, String universidade, int anoNascimento, String cidadeNascimento, String estadoNascimento){
        this.id = id;
        this.nome = nome;
        this.altura = altura;
        this.peso = peso;
        this.universidade = universidade;
        this.anoNascimento = anoNascimento;
        this.cidadeNascimento = cidadeNascimento;
        this.estadoNascimento = estadoNascimento;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public void setAltura(int altura)
    {
        this.altura = altura;
    }

    public void setPeso(int peso)
    {
        this.peso = peso;
    }

    public void setUniversidade(String universidade)
    {
        this.universidade = universidade;
    }

    public void setAnoNascimento(int anoNascimento)
    {
        this.anoNascimento = anoNascimento;
    }

    public void setCidadeNasciemento(String cidadeNascimento)
    {
        this.cidadeNascimento = cidadeNascimento;
    }

    public void setEstadoNascimento(String estadoNascimento)
    {
        this.estadoNascimento = estadoNascimento;
    }

    public int getId()
    {
        return id;
    }
    
    public String getNome()
    {
        return nome;
    }

    public int getAltura()
    {
        return altura;
    }

    public int getPeso()
    {
        return peso;
    }

    public String getUniversidade()
    {
        return universidade;
    }

    public int getAnoNascimento()
    {
        return anoNascimento;
    }

    public String getCidadeNascimento()
    {
        return cidadeNascimento;
    }
    
    public String getEstadoNascimento()
    {
        return estadoNascimento;
    }

    public Jogador clone()
    {
        return (new Jogador(this.id, this.nome, this.altura, this.peso, this.universidade, this.anoNascimento, this.cidadeNascimento, this.estadoNascimento));
    }

    public static void print(Jogador tmp)
    {
        System.out.print("[" + tmp.id + " ## ");
        System.out.print (tmp.nome + " ## ");
        System.out.print(tmp.altura + " ## ");
        System.out.print (tmp.peso + " ## ");
        System.out.print (tmp.anoNascimento + " ## ");
        System.out.print (tmp.universidade + " ## ");
        System.out.print (tmp.cidadeNascimento + " ## ");
        System.out.println (tmp.estadoNascimento + "]");
    }

    public static Jogador newJogadorByString(String[] strings)
    {
        int id = Integer.parseInt(strings[0]);
        String nome = strings[1];
        int altura = Integer.parseInt(strings[2]);
        int peso = Integer.parseInt(strings[3]);
        String universidade = strings[4];
        int anoNascimento = Integer.parseInt(strings[5]);
        String cidadeNascimento = strings[6];
        String estadoNascimento = strings[7];

        return new Jogador(id, nome, altura, peso, universidade, anoNascimento, cidadeNascimento, estadoNascimento);
    }
    public static String[] isEmpty(String[] strings)
    {
        for (int i = 0; i < 8; i++)
        {
            if(strings[i].isBlank())
            {
                strings[i] = "nao informado";
            }
        }
        return strings;
    }

    public static Jogador read(String filePath, int id) throws Exception {
        Jogador tmp = new Jogador();
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("Deu excessao");
            throw new Exception("Arquivo não existe");
        }

        Scanner sc = new Scanner(file);
        sc.nextLine();
        sc.useDelimiter(",");

        int tmpId = sc.nextInt();

        while (tmpId != id && sc.hasNext()) {
            sc.nextLine();
            tmpId = sc.nextInt();
        }

        if (tmpId == id) {
            String str = sc.nextLine();
           // System.out.println(str);
            String[] strings = str.split(",", -1);

            for (int i = 1; i < strings.length; i++) {
                if (strings[i].isEmpty()) {
                    strings[i] = "nao informado";
                }
               // System.out.println(strings[i]);
            }

            String nome = strings[1];
            int altura = Integer.parseInt(strings[2]);
            int peso = Integer.parseInt(strings[3]);
            String universidade = strings[4];
            int anoNascimento = Integer.parseInt(strings[5]);
            String cidadeNascimento = strings[6];
            String estadoNascimento = strings[7];

            tmp = new Jogador(tmpId, nome, altura, peso, universidade, anoNascimento, cidadeNascimento, estadoNascimento);

            }
        return tmp;
    }
}

class ResultadoPesquisa {
	boolean encontrado;
	int comparacoes, movimentacoes;

	public ResultadoPesquisa() {
		this(false, 0, 0);
	}
	public ResultadoPesquisa(boolean encontrado, int comparacoes, int movimentacoes) {
		this.encontrado = encontrado;
		this.comparacoes = comparacoes;
		this.comparacoes = comparacoes;
	}

	public boolean getEncontrado() { return encontrado; }
	public void setEncontrado(boolean encontrado) { this.encontrado = encontrado; }
	public int getComparacoes() { return comparacoes; }
	public void incrementarComparacoes() { this.comparacoes++; }
	public void incrementarMovimentacoes(int movimentacoes) { this.comparacoes += movimentacoes; }
}

class Queue{
    private Jogador[] array;
    private int size;
    private int maxSize;

    public Queue()
    {
        this.array = new Jogador[1];
        this.size = 0;
        this.maxSize = 1;
    }

    public Queue(int maxSize) {
        this.array = new Jogador[maxSize];
        this.size = 0;
        this.maxSize = maxSize;
    }
    public Queue(Jogador[] array, int size, int maxSize) {
        this.array = array;
        this.size = size;
        this.maxSize = maxSize;
    }

    public void setArray(Jogador[] array) {
        this.array = array;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public void insert(Jogador jogador) throws Exception
    {
        if(size == maxSize){
            throw new Exception("Erro: Inserindo numa fila cheia");
        }

        this.array[size++] = jogador;
    }

    public Jogador remove() throws Exception
    {
        if(size <= 0){
            throw new Exception("Erro: Removendo numa fila vazia");
        }

        Jogador removed = this.array[0];
        size--;
        for(int i = 0; i < size; i++)
        {
            this.array[i] = this.array[i + 1];
        }
        return removed;
    }

    @Override
    public String toString()
    {
        return "Queue{" +
                "array=" + Arrays.toString(array) +
                ", size=" + size +
                ", maxSize=" + maxSize +
                '}';
    }

    public Queue readFile(String filePath) throws Exception
    {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new Exception("Arquivo não existe");
        }
        Scanner sc = new Scanner(file);
        sc.useDelimiter(",");
        sc.nextLine();

        Queue queue = new Queue(6000);

        while(sc.hasNextLine())
        {
            String line = sc.nextLine();

            String[] strings = Jogador.isEmpty(line.split(","));
            Jogador tmp = Jogador.newJogadorByString(strings);

            try{
                queue.insert(tmp);
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return queue;
    }

    public ResultadoPesquisa sequencialSearch(String name)
    {
        ResultadoPesquisa resultado = new ResultadoPesquisa();
        resultado.setEncontrado(false);
        for (int i = 0; i < size; i++)
        {
            Jogador tmp = array[i];
            if(tmp.getNome().contains(name))
            {
                resultado.setEncontrado(true);
                i = size;
            }
            resultado.incrementarComparacoes(); //Variável para contar as comparações
        }
        return resultado;
    }
}

class Timer {
	
	private long tempoInicial, tempoFinal, tempoDecorrido;

	Timer() {}
	
	Timer(boolean start) {
		if (start) this.Start();
	}
	
	public void Start() {
		tempoInicial = System.currentTimeMillis();
	}

	public void Stop() {
		tempoFinal = System.currentTimeMillis();
		tempoDecorrido = tempoFinal - tempoInicial;
	}
	
	public double Time() { return tempoDecorrido / 1000.0; }

}

class TP02Q03{

    public static void writeLog(int comparations, Timer timer) throws Exception
    {
        String fileName = "814832_sequencial.txt";
		PrintWriter printWriter = new PrintWriter(new FileWriter(fileName));

		printWriter.printf("Matrícula: 814832\t");
		printWriter.printf("Tempo de execução: %.3fs\t", timer.Time());
		printWriter.printf("Número de comparações: %d", comparations);

		printWriter.close();
    }

    public static void main(String[] Args) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();

        Jogador tmp = new Jogador();
        Queue queue = new Queue(4000);
        while(!str.equals("FIM"))
        {
            try{
                tmp = Jogador.read("/workspaces/AEDS2/Trabalhos Praticos/TP02/Q03/tmp/players.csv", Integer.parseInt(str));
                queue.insert(tmp);
            }catch (Exception e)
            {
                System.out.println(e.getMessage());
                str = "FIM";
            }
            str = sc.nextLine();
        }

        Timer timer = new Timer(true);

        str = sc.nextLine();
        ResultadoPesquisa resultado = new ResultadoPesquisa();
        while(!str.equals("FIM"))
        {
            resultado = queue.sequencialSearch(str);
            System.out.println(resultado.encontrado ? "SIM" : "NAO");
            str = sc.nextLine();
            
        }
        timer.Stop();
        writeLog(resultado.comparacoes, timer);
    }
}