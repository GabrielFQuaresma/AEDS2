

import java.io.*;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


class Jogador {
    private int id;
    private String nome;
    private int altura;
    private int peso;
    private String universidade;
    private int anoNascimento;
    private String cidadeNascimento;
    private String estadoNascimento;


    public Jogador() {
        this.id = 0;
        this.nome = new String();
        this.altura = 0;
        this.peso = 0;
        this.universidade = new String();
        this.anoNascimento = 0;
        this.cidadeNascimento = new String();
        this.estadoNascimento = new String();
    }

    public Jogador(int id, String nome, int altura, int peso, String universidade, int anoNascimento, String cidadeNascimento, String estadoNascimento) {
        this.id = id;
        this.nome = nome;
        this.altura = altura;
        this.peso = peso;
        this.universidade = universidade;
        this.anoNascimento = anoNascimento;
        this.cidadeNascimento = cidadeNascimento;
        this.estadoNascimento = estadoNascimento;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public void setUniversidade(String universidade) {
        this.universidade = universidade;
    }

    public void setAnoNascimento(int anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public void setCidadeNasciemento(String cidadeNascimento) {
        this.cidadeNascimento = cidadeNascimento;
    }

    public void setEstadoNascimento(String estadoNascimento) {
        this.estadoNascimento = estadoNascimento;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getAltura() {
        return altura;
    }

    public int getPeso() {
        return peso;
    }

    public String getUniversidade() {
        return universidade;
    }

    public int getAnoNascimento() {
        return anoNascimento;
    }

    public String getCidadeNascimento() {
        return cidadeNascimento;
    }

    public String getEstadoNascimento() {
        return estadoNascimento;
    }

    public Jogador clone() {
        return (new Jogador(this.id, this.nome, this.altura, this.peso, this.universidade, this.anoNascimento, this.cidadeNascimento, this.estadoNascimento));
    }

    public static void print(Jogador tmp) {
        System.out.print("[" + tmp.id + " ## ");
        System.out.print(tmp.nome + " ## ");
        System.out.print(tmp.altura + " ## ");
        System.out.print(tmp.peso + " ## ");
        System.out.print(tmp.anoNascimento + " ## ");
        System.out.print(tmp.universidade + " ## ");
        System.out.print(tmp.cidadeNascimento + " ## ");
        System.out.println(tmp.estadoNascimento + "]");
    }

    public static Jogador newJogadorByString(String[] strings) {
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

    public static String[] isEmpty(String[] strings) {
        for (int i = 0; i < strings.length; i++) {
            if (strings[i].isBlank()) {
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
            String[] strings = isEmpty(str.split(",", -1));

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

    public static int strcmpr(Jogador jog1, Jogador jog2)
    {
        return jog1.getNome().compareTo(jog2.getNome());
    }

    public static int birthcmpr(Jogador jog1, Jogador jog2)
    {
        int bigger = 0;

        if(jog1.anoNascimento > jog2.anoNascimento)
            bigger = 1;
        else if(jog1.anoNascimento < jog2.anoNascimento)
            bigger = -1;
        return bigger;
    }

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


    public void show()
    {
        for(int i = 0; i < size; i++)
        {
            Jogador.print(array[i]);
        }
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

    private static void swap(Jogador[] array, int i, int j) 
    {
        Jogador tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public static Queue readFile(String filePath) throws Exception
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
            String[] strings = Jogador.isEmpty(line.split(",", -1));
            
            Jogador tmp = Jogador.newJogadorByString(strings);

            try{
                queue.insert(tmp);
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return queue;
    }


// --------------------------------------------  Pesquisa  --------------------------------------------

    public boolean sequencialSearch(String name)
    {
        boolean findIt = false;
        for (int i = 0; i < size; i++)
        {
            Jogador tmp = array[i];
            if(tmp.getNome().contains(name))
            {
                findIt = true;
                i = size;
            }
        }
        return findIt;
    }



// -------------------------------------------- Ordenação --------------------------------------------


    //Desempate por nome com metodo de inserção
    public void heightInsertionSort() throws Exception
    {

        if (size <= 0) {
            throw new Exception("Erro: Fila com tamanho inválido");
        }


        for (int i = 1; i < size; i++)
        {
            int j = i;
            Jogador tmp = array[i];
            while(j > 0 && array[j-1].getAltura() == tmp.getAltura() && Jogador.strcmpr(tmp, array[j-1]) < 0)
            {
                array[j] = array[--j];
            }
            array[j] = tmp;
        }
    }


    public void nameSelectionSort() throws Exception
    {
        if (size <= 0) {
            throw new Exception("Erro: Fila com tamanho inválido");
        }

        for(int i = 0; i < size - 1; i++)
        {
            int menor = i;
            for(int j = i + 1; j < size; j++)
            {
                if(Jogador.strcmpr(array[menor], array[j]) > 0)
                {
                    menor = j;
                }
            }
            swap(array, i, menor);
        }
            
    }


    public ResultadoPesquisa birthYearInsertionSort() throws Exception
    {
        ResultadoPesquisa resultado = new ResultadoPesquisa();
        if (size <= 0) {
            throw new Exception("Erro: Fila com tamanho inválido");
        }


        
        for (int i = 1; i < size; i++)
        {
            int j = i;
            Jogador tmp = array[i];
            while(j > 0 && Jogador.birthcmpr(tmp, array[j-1]) < 0)
            {
                array[j] = array[--j];
                resultado.incrementarComparacoes();
                resultado.incrementarMovimentacoes(1);
            }
            array[j] = tmp;
            resultado.incrementarComparacoes();
            resultado.incrementarMovimentacoes(1);
        }
        return resultado;
    }



    //Ordenação por nome com metodo de inserção
    public void nameInsertionSort() throws Exception
    {

        if (size <= 0) {
            throw new Exception("Erro: Fila com tamanho inválido");
        }


        for (int i = 1; i < size; i++)
        {
            int j = i;
            Jogador tmp = array[i];
            while(j > 0 && Jogador.strcmpr(tmp, array[j-1]) < 0)
            {
                array[j] = array[--j];
            }
            array[j] = tmp;
        }
    }
        
    public ResultadoPesquisa heapSort() {
      
        ResultadoPesquisa resultado = new ResultadoPesquisa();


        for (int i = size / 2 - 1; i >= 0; i--) {
            heapify(size, i, resultado);
        }

        
        for (int i = size - 1; i >= 0; i--) {
            swap(array, 0, i);

            resultado.incrementarComparacoes();
            heapify(i, 0, resultado);
        }
        return resultado;
    }

    private void heapify(int n, int root, ResultadoPesquisa resultado) {
        int largest = root;
        int left = 2 * root + 1;
        int right = 2 * root + 2;

        if (left < n && array[left].getAltura() > array[largest].getAltura()) {
            largest = left;
        }
        resultado.incrementarComparacoes();

        if (right < n && array[right].getAltura() > array[largest].getAltura()) {
            largest = right;
        }
        resultado.incrementarComparacoes();

        if (largest != root) {
            swap(array, root, largest);

            resultado.incrementarMovimentacoes(1);
            heapify(n, largest, resultado);
        }
        resultado.incrementarComparacoes();
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

/*

    Metodos Gerais

 */





public class TP02Q09 {

    public static void writeLog(int comparations, int movimentations, Timer timer) throws Exception
    {
        String fileName = "814832_heapSort.txt";
        PrintWriter printWriter = new PrintWriter(new FileWriter(fileName));

        printWriter.printf("Matrícula: 814832\t");
        printWriter.printf("Tempo de execução: %.3fs\t", timer.Time());
        printWriter.printf("Número de comparações: %d", comparations);
        printWriter.printf("Número de movimentações: %d", movimentations);

        printWriter.close();
    }


    public static void main(String[] Args)
    {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();

        Queue queue = new Queue(6000);


        try{

            ResultadoPesquisa resultado = new ResultadoPesquisa();
            while(!line.equals("FIM"))
            {
                Jogador tmp = Jogador.read("/tmp/players.csv", Integer.parseInt(line));
                queue.insert(tmp);
                line = sc.nextLine();
            }
            Timer timer = new Timer(true);

            resultado = queue.heapSort();
            queue.heightInsertionSort();

            timer.Stop();
            writeLog(resultado.comparacoes, resultado.movimentacoes, timer);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        queue.show();

        sc.close();
    }
}

