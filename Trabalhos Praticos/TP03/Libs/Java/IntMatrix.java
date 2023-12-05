package Libs.Java;

import Libs.Java.Celulas.MatrixCell;

public class IntMatrix {
    private MatrixCell start;
    private int linha;
    private int coluna;

    public IntMatrix()
    {
        this.start = null;
        linha = coluna = 0;
    }

    public IntMatrix(int linha, int coluna)
    {
        this.linha = linha;
        this.coluna = coluna;
        this.start = new MatrixCell();

        MatrixCell tmp, nextCell;
        tmp = start;

        for(int i = 0, contador = 0; i < coluna; i++){
            tmp.right = new MatrixCell(contador++, tmp, null);
            nextCell = tmp;
            for(int j = 0; j < linha; j++){
                nextCell.down = new MatrixCell(contador++, null, nextCell);
                nextCell = nextCell.down;
            }
            tmp = tmp.right;
        }
    }
}
