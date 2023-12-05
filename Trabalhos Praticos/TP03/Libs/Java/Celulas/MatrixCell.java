package Libs.Java.Celulas;

public class MatrixCell {
    public int number;
    public MatrixCell right;
    public MatrixCell left;
    public MatrixCell up;
    public MatrixCell down;

    public MatrixCell()
    {
        this.number = 0;
        right = left = up = down = null;
    }

    public MatrixCell(int number)
    {
        this.number = number;
        right = left = up = down = null;
    }

    public MatrixCell(int number, MatrixCell left, MatrixCell up)
    {
        this.number = number;
        this.left = left;
        this.up = up;
        right = down = null;
    }
}
