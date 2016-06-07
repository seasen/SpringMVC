package Java.Alogrithm;

/**
 * Created by seasen on 2015/12/27.
 */
public class LongestSubstring {
    final int i=0;
    public static void main(String[] args) {
        LongestSubstring ls = new LongestSubstring();
        System.out.println(ls.lengthOfLongestSubstring("weertyu"));

    }
    public int lengthOfLongestSubstring(String s){
        int tempstr1 = 0;
        if(s.length() <= 1)
            return s.length();
        int temp = 0;
        for (int i = 0; i < s.length();i++) {
            int tempstr2 = 1;
            for (int j = i-1; j >=temp; j--) {
                if(s.charAt(j)!=s.charAt(i))
                    tempstr2++;
                else {
                    if (tempstr1<tempstr2)
                    {
                        tempstr1 = tempstr2;
                    }
                    temp = ++j;
                    break;
                }
            }

        }
        return tempstr1;
    }
}
