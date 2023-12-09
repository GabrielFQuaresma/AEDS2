import java.util.Scanner;
import java.io.File;

class Player {
    private int id;
    private String name;
    private int height;
    private int weight;
    private String university;
    private int birthYear;
    private String birthCity;
    private String birthState;


    public Player() {
        this.id = 0;
        this.name = new String();
        this.height = 0;
        this.weight = 0;
        this.university = new String();
        this.birthYear = 0;
        this.birthCity = new String();
        this.birthState = new String();
    }

    public Player(int id, String name, int height, int weight, String university, int birthYear, String birthCity, String birthState) {
        this.id = id;
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.university = university;
        this.birthYear = birthYear;
        this.birthCity = birthCity;
        this.birthState = birthState;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public void setBirthcity(String birthCity) {
        this.birthCity = birthCity;
    }

    public void setBirthState(String birthState) {
        this.birthState = birthState;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public String getUniversity() {
        return university;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public String getBirthCity() {
        return birthCity;
    }

    public String getBirthState() {
        return birthState;
    }

    public Player clone() {
        return (new Player(this.id, this.name, this.height, this.weight, this.university, this.birthYear, this.birthCity, this.birthState));
    }

    @Override
    public String toString(){
        return "## " + name + " ## " + height + " ## " +  weight + " ## " + birthYear + " ## " + university + " ## " +  birthCity + " ## " + birthState + " ##"; 
    }

    public static void print(Player tmp) {
        System.out.print("[" + tmp.id + " ## ");
        System.out.print(tmp.name + " ## ");
        System.out.print(tmp.height + " ## ");
        System.out.print(tmp.weight + " ## ");
        System.out.print(tmp.birthYear + " ## ");
        System.out.print(tmp.university + " ## ");
        System.out.print(tmp.birthCity + " ## ");
        System.out.println(tmp.birthState + "]");
    }

    public static Player newPlayerByString(String[] strings) {
        int id = Integer.parseInt(strings[0]);
        String name = strings[1];
        int height = Integer.parseInt(strings[2]);
        int weight = Integer.parseInt(strings[3]);
        String university = strings[4];
        int birthYear = Integer.parseInt(strings[5]);
        String birthCity = strings[6];
        String birthState = strings[7];

        return new Player(id, name, height, weight, university, birthYear, birthCity, birthState);
    }

    public static String[] isEmpty(String[] strings) {
        for (int i = 0; i < strings.length; i++) {
            if (strings[i].isBlank()) {
                strings[i] = "nao informado";
            }
        }
        return strings;
    }

    public static Player read(String filePath, int id) throws Exception {
        Player tmp = new Player();
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

            String name = strings[1];
            int height = Integer.parseInt(strings[2]);
            int weight = Integer.parseInt(strings[3]);
            String university = strings[4];
            int birthYear = Integer.parseInt(strings[5]);
            String birthCity = strings[6];
            String birthState = strings[7];

            tmp = new Player(tmpId, name, height, weight, university, birthYear, birthCity, birthState);

        }
        sc.close();
        return tmp;
    }

    public static Player searchByName(String filePath, String name) throws Exception {
        Player tmp = new Player();
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("Deu excessao");
            throw new Exception("Arquivo não existe");
        }

        Scanner sc = new Scanner(file);
        sc.nextLine();
        sc.useDelimiter(",");

        int tmpId = sc.nextInt();
        String tmpName = sc.next();

        while (!tmpName.contains(name) && sc.hasNext()) {
            sc.nextLine();
            tmpId = sc.nextInt();
            tmpName = sc.next();
        }
        
        if (tmpName.contains(name)) {
            String str = sc.nextLine();
            String[] strings = isEmpty(str.split(",", -1));
            
            int height = Integer.parseInt(strings[1]);
            int weight = Integer.parseInt(strings[2]);
            String university = strings[3];
            int birthYear = Integer.parseInt(strings[4]);
            String birthCity = strings[5];
            String birthState = strings[6];
            
            tmp = new Player(tmpId, name, height, weight, university, birthYear, birthCity, birthState);

        }
        sc.close();
        return tmp;
    }

    public static int strcmpr(Player player1, Player player2)
    {
        return player1.getName().compareTo(player2.getName());
    }

    public static int birthcmpr(Player player1, Player player2)
    {
        int bigger = 0;

        if(player1.birthYear > player2.birthYear)
            bigger = 1;
        else if(player1.birthYear < player2.birthYear)
            bigger = -1;
        return bigger;
    }

    public static int statecmpr(Player player1, Player player2)
    {
        return player1.getBirthState().compareTo(player2.getBirthState());
    }

}

class NoAN {
  public boolean cor;
  public String elemento;
  public NoAN esq, dir;

  public NoAN() {
    this("");
  }

  public NoAN(String elemento) {
    this(elemento, false, null, null);
  }

  public NoAN(String elemento, boolean cor) {
    this(elemento, cor, null, null);
  }

  public NoAN(String elemento, boolean cor, NoAN esq, NoAN dir) {
    this.cor = cor;
    this.elemento = elemento;
    this.esq = esq;
    this.dir = dir;
  }
}

class Celeste {
   private NoAN raiz; // Raiz da arvore.

   /**
    * Construtor da classe.
    */
   public Celeste() {
      raiz = null;
   }

   /**
    * Metodo publico iterativo para pesquisar elemento.
    * 
    * @param elemento Elemento que sera procurado.
    * @return <code>true</code> se o elemento existir,
    *         <code>false</code> em caso contrario.
    */
   public boolean pesquisar(String elemento) {
      System.out.print("raiz ");
      return pesquisar(elemento, raiz);
   }

   /**
    * Metodo privado recursivo para pesquisar elemento.
    * 
    * @param elemento Elemento que sera procurado.
    * @param i        NoAN em analise.
    * @return <code>true</code> se o elemento existir,
    *         <code>false</code> em caso contrario.
    */
   private boolean pesquisar(String elemento, NoAN i) {
      boolean resp;
      if (i == null) {
         resp = false;
      } else if (elemento.compareTo(i.elemento) == 0) {
         resp = true;
      } else if (elemento.compareTo(i.elemento) < 0) {
        System.out.print("esq ");
         resp = pesquisar(elemento, i.esq);
      } else {
        System.out.print("dir ");
         resp = pesquisar(elemento, i.dir);
      }
      return resp;
   }

   /**
    * Metodo publico iterativo para exibir elementos.
    */
   public void caminharCentral() {
      System.out.print("[ ");
      caminharCentral(raiz);
    //   System.out.println("]");
   }

   /**
    * Metodo privado recursivo para exibir elementos.
    * 
    * @param i NoAN em analise.
    */
   private void caminharCentral(NoAN i) {
      if (i != null) {
         caminharCentral(i.esq); // Elementos da esquerda.
         System.out.print(i.elemento + ((i.cor) ? "(p) " : "(b) ")); // Conteudo do no.
         caminharCentral(i.dir); // Elementos da direita.
      }
   }

   /**
    * Metodo publico iterativo para exibir elementos.
    */
   public void caminharPre() {
      System.out.print("[ ");
      caminharPre(raiz);
    //   System.out.println("]");
   }

   /**
    * Metodo privado recursivo para exibir elementos.
    * 
    * @param i NoAN em analise.
    */
   private void caminharPre(NoAN i) {
      if (i != null) {
         System.out.print(i.elemento + ((i.cor) ? "(p) " : "(b) ")); // Conteudo do no.
         caminharPre(i.esq); // Elementos da esquerda.
         caminharPre(i.dir); // Elementos da direita.
      }
   }

   /**
    * Metodo publico iterativo para exibir elementos.
    */
   public void caminharPos() {
      System.out.print("[ ");
      caminharPos(raiz);
    //   System.out.println("]");
   }

   /**
    * Metodo privado recursivo para exibir elementos.
    * 
    * @param i NoAN em analise.
    */
   private void caminharPos(NoAN i) {
      if (i != null) {
         caminharPos(i.esq); // Elementos da esquerda.
         caminharPos(i.dir); // Elementos da direita.
         System.out.print(i.elemento + ((i.cor) ? "(p) " : "(b) ")); // Conteudo do no.
      }
   }

   /**
    * Metodo publico iterativo para inserir elemento.
    * 
    * @param elemento Elemento a ser inserido.
    * @throws Exception Se o elemento existir.
    */
   public void inserir(String elemento) throws Exception {
      // Se a arvore estiver vazia
      if (raiz == null) {
         raiz = new NoAN(elemento);
        //  System.out.println("Antes, zero elementos. Agora, raiz(" + raiz.elemento + ").");

      // Senao, se a arvore tiver um elemento
      } else if (raiz.esq == null && raiz.dir == null) {
         if (elemento.compareTo(raiz.elemento) < 0) {
            raiz.esq = new NoAN(elemento);
            // System.out.println("Antes, um elemento. Agora, raiz(" + raiz.elemento + ") e esq(" + raiz.esq.elemento + ").");
         } else {
            raiz.dir = new NoAN(elemento);
            // System.out.println("Antes, um elemento. Agora, raiz(" + raiz.elemento + ") e dir(" + raiz.dir.elemento + ").");
         }

      // Senao, se a arvore tiver dois elementos (raiz e dir)
      } else if (raiz.esq == null) {
         if (elemento.compareTo(raiz.elemento) < 0) {
            raiz.esq = new NoAN(elemento);
            // System.out.println("Antes, dois elementos(A). Agora, raiz(" + raiz.elemento + "), esq (" + raiz.esq.elemento + ") e dir(" + raiz.dir.elemento + ").");

         } else if (elemento.compareTo(raiz.dir.elemento) < 0){
            raiz.esq = new NoAN(raiz.elemento);
            raiz.elemento = elemento;
            // System.out.println("Antes, dois elementos(B). Agora, raiz(" + raiz.elemento + "), esq (" + raiz.esq.elemento + ") e dir(" + raiz.dir.elemento + ").");

         } else {
            raiz.esq = new NoAN(raiz.elemento);
            raiz.elemento = raiz.dir.elemento;
            raiz.dir.elemento = elemento;
            // System.out.println("Antes, dois elementos(C). Agora, raiz(" + raiz.elemento + "), esq (" + raiz.esq.elemento + ") e dir(" + raiz.dir.elemento + ").");
         }
         raiz.esq.cor = raiz.dir.cor = false;

      // Senao, se a arvore tiver dois elementos (raiz e esq)
      } else if (raiz.dir == null) {
         if (elemento.compareTo(raiz.elemento) > 0) {
            raiz.dir = new NoAN(elemento);
            // System.out.println("Antes, dois elementos(D). Agora, raiz(" + raiz.elemento + "), esq (" + raiz.esq.elemento + ") e dir(" + raiz.dir.elemento + ").");

         } else if (elemento.compareTo(raiz.esq.elemento) > 0) {
            raiz.dir = new NoAN(raiz.elemento);
            raiz.elemento = elemento;
            // System.out.println("Antes, dois elementos(E). Agora, raiz(" + raiz.elemento + "), esq (" + raiz.esq.elemento + ") e dir(" + raiz.dir.elemento + ").");

         } else {
            raiz.dir = new NoAN(raiz.elemento);
            raiz.elemento = raiz.esq.elemento;
            raiz.esq.elemento = elemento;
            // System.out.println("Antes, dois elementos(F). Agora, raiz(" + raiz.elemento + "), esq (" + raiz.esq.elemento + ") e dir(" + raiz.dir.elemento + ").");
         }
         raiz.esq.cor = raiz.dir.cor = false;

      // Senao, a arvore tem tres ou mais elementos
      } else {
        //  System.out.println("Arvore com tres ou mais elementos...");
         inserir(elemento, null, null, null, raiz);
      }
      raiz.cor = false;
   }

   private void balancear(NoAN bisavo, NoAN avo, NoAN pai, NoAN i) {
      // Se o pai tambem e preto, reequilibrar a arvore, rotacionando o avo
      if (pai.cor == true) {
         // 4 tipos de reequilibrios e acoplamento
         if (pai.elemento.compareTo(avo.elemento) > 0) { // rotacao a esquerda ou direita-esquerda
            if (i.elemento.compareTo(pai.elemento) > 0) {
               avo = rotacaoEsq(avo);
            } else {
               avo = rotacaoDirEsq(avo);
            }
         } else { // rotacao a direita ou esquerda-direita
            if (i.elemento.compareTo(pai.elemento) < 0) {
               avo = rotacaoDir(avo);
            } else {
               avo = rotacaoEsqDir(avo);
            }
         }
         if (bisavo == null) {
            raiz = avo;
         } else if (avo.elemento.compareTo(bisavo.elemento) < 0) {
            bisavo.esq = avo;
         } else {
            bisavo.dir = avo;
         }
         // reestabelecer as cores apos a rotacao
         avo.cor = false;
         avo.esq.cor = avo.dir.cor = true;
        //  System.out.println("Reestabeler cores: avo(" + avo.elemento + "->branco) e avo.esq / avo.dir("
            //    + avo.esq.elemento + "," + avo.dir.elemento + "-> pretos)");
      } // if(pai.cor == true)
   }

   /**
    * Metodo privado recursivo para inserir elemento.
    * 
    * @param elemento Elemento a ser inserido.
    * @param avo      NoAN em analise.
    * @param pai      NoAN em analise.
    * @param i        NoAN em analise.
    * @throws Exception Se o elemento existir.
    */
   private void inserir(String elemento, NoAN bisavo, NoAN avo, NoAN pai, NoAN i) throws Exception {
      if (i == null) {
         if (elemento.compareTo(pai.elemento) < 0) {
            i = pai.esq = new NoAN(elemento, true);
         } else {
            i = pai.dir = new NoAN(elemento, true);
         }
         if (pai.cor == true) {
            balancear(bisavo, avo, pai, i);
         }
      } else {
         // Achou um 4-no: eh preciso fragmeta-lo e reequilibrar a arvore
         if (i.esq != null && i.dir != null && i.esq.cor == true && i.dir.cor == true) {
            i.cor = true;
            i.esq.cor = i.dir.cor = false;
            if (i == raiz) {
               i.cor = false;
            } else if (pai.cor == true) {
               balancear(bisavo, avo, pai, i);
            }
         }
         if (elemento.compareTo(i.elemento) < 0) {
            inserir(elemento, avo, pai, i, i.esq);
         } else if (elemento.compareTo(i.elemento) > 0) {
            inserir(elemento, avo, pai, i, i.dir);
         } else {
            throw new Exception("Erro inserir (elemento repetido)!");
         }
      }
   }

   private NoAN rotacaoDir(NoAN no) {
    //   System.out.println("Rotacao DIR(" + no.elemento + ")");
      NoAN noEsq = no.esq;
      NoAN noEsqDir = noEsq.dir;

      noEsq.dir = no;
      no.esq = noEsqDir;

      return noEsq;
   }

   private NoAN rotacaoEsq(NoAN no) {
    //   System.out.println("Rotacao ESQ(" + no.elemento + ")");
      NoAN noDir = no.dir;
      NoAN noDirEsq = noDir.esq;

      noDir.esq = no;
      no.dir = noDirEsq;
      return noDir;
   }

   private NoAN rotacaoDirEsq(NoAN no) {
      no.dir = rotacaoDir(no.dir);
      return rotacaoEsq(no);
   }

   private NoAN rotacaoEsqDir(NoAN no) {
      no.esq = rotacaoEsq(no.esq);
      return rotacaoDir(no);
   }

   boolean isNoTipo4(NoAN i){
        return (i.esq != null && i.dir != null && i.esq.cor == true && i.dir.cor == true);
    }
}

public class V04Q04{

    public static void main(String[] Args){
        Scanner sc = new Scanner(System.in);

         Celeste arvore = new Celeste();
        try{
            String line = sc.nextLine();

            //Leitura dos players da lista
            while(!line.equals("FIM")){
                Player tmp = Player.read("/tmp/players.csv", Integer.parseInt(line));
                arvore.inserir(tmp.getName());
                line = sc.nextLine();
            }

            line = sc.nextLine();
            //Leitura dos comandos
            while(!line.equals("FIM")){
                Player tmp = Player.searchByName("/tmp/players.csv", line);
                System.out.print(line + " ");
                System.out.println(arvore.pesquisar(line) ? "SIM" : "NAO");
                line = sc.nextLine();
            }

        }catch(Exception e){
            e.getMessage();
        }
        sc.close();
    }
}