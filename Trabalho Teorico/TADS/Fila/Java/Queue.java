

public class Queue {
    private int array[];
    private int size;
    private int maxSize;
    
    public Queue()
    {
        array = new int[1];
        size = 0;
        maxSize = 1;
    }

    public Queue(int maxSize)
    {

        if(maxSize <= 0)
        {
            throw new Exception("ERRO! Tamanho tem que ser maior do que 0.");
        }

        array = new int[maxSize];
        size = 0;
        this.maxSize = maxSize;
    }

    public int remove()
    {

        if(size <= 0)
        {
            throw new Exception("ERRO! Não tem número para ser removido.");
        }

        int removed = array[0];
        for(int i = 0; i < size; i++)
        {
            array[i] = array[i + 1];
        }
        size--;
        
        return removed;
    }

    public void insert(int number)
    {
        if(size == maxSize)
        {
            
        }
    }
}
