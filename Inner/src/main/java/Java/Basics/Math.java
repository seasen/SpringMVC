package Java.Basics;

/**
 * Created by seasen on 2015/12/21.
 */
public class Math  {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        double[][] a={{0.65,0.28,0.07},{0.15,0.67,0.18},{0.12,0.36,0.52}};//自己定义矩阵
        double[][] b={{0.21,0.68,0.11}};//自己定义矩阵
        for (int i = 0; i < 1000; i++) {
            double[][] c = printMatrix(a,b);
            for (int j = 0; j < 3; j++)
                b[0][j] = c[0][j];
        }

    }
     public static double[][] printMatrix(double[][] a,double[][] b){
        double c[][] = new double[b[0].length][a.length];
        int x,i,j;
        for(i = 0;i<b.length ;i++)
        {
            for(j = 0;j<a.length;j++)
            {
                double temp = 0;
                for(x = 0;x<a.length;x++)
                {
                    temp+=b[i][x]*a[x][j];
                }
                c[i][j] = temp;
            }
        }
        for(int m = 0;m<b.length;m++)
        {
            for(int n = 0;n<a[0].length;n++)
            {
                System.out.print(c[m][n]+"\t");
            }
            System.out.println();
        }
         return c;
    }

}
