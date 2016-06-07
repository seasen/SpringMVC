package Java.Alogrithm;

public class AllSort{
    public static void main(String[] args) {
        char buf[]={'a','b','c'};
        //perm(buf,0,buf.length-1);
        getGrayCode(3);
    }
    public static void getGrayCode(int bitNum){
        for(int i = 0; i < (int)Math.pow(2, bitNum); i++){
            int grayCode = (i >> 1) ^ i;
            System.out.println(num2Binary(grayCode, bitNum));
        }
    }
    public static String num2Binary(int num, int bitNum){
        String ret = "";
        for(int i = bitNum-1; i >= 0; i--){
            ret += (num >> i) & 1;
        }
        return ret;
    }

    public static void perm(char[] buf,int start,int end){
        if(start==end){
            for (int i = 0; i <=end; i++) {
                System.out.print(buf[i]);
            }
            System.out.println();
        }
        else{//多个字母全排列（普遍情况）
            for (int i = start; i <=end; i++) {
                char temp = buf[start];
                buf[start] = buf[i];
                buf[i] = temp;
                perm(buf,start+1,end);
                temp = buf[start];
                buf[start] = buf[i];
                buf[i] = temp;
            }
        }
    }
}