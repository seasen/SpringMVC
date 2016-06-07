package crawl.weibo.sina.parse.worker;

import java.util.*;

import crawl.weibo.sina.parse.bean.Stars;

import javax.persistence.criteria.CriteriaBuilder;

/**
 * Created by seasen on 2016/3/30.
 */
public class TestReflect {
    public static void main(String[] args) {
        Vector<String> v= new Vector<String>();
        HashMap<String,Integer> hashMap = new HashMap<String, Integer>();
        hashMap.put("shaosen",1);
        hashMap.put("who",2);
        for (Map.Entry<String, Integer> entry:hashMap.entrySet()) {
            System.out.println(entry.getKey()+":"+entry.getValue());
        }
        GetInfor gi = new GetInfor();
        List<Stars> list = gi.selectStarsTest("weiId");
        Iterator it = list.iterator();
        int i  = 19;
        while (it.hasNext()&&i-->0){
            System.out.print(((Stars) it.next()).getWeiId()+" ");

        }
    }
}
