package onlyfun.caterpillar2;

import java.util.List;
import java.util.Map;

/**
 * Created by seasen on 2016/1/9.
 */
public class SomeBeanTwo {
    private String[] someStrArray;
    private SomeBean[] someObjArray;
    private List someList;
    private Map someMap;
    public String[] getSomeStrArray() {
        return someStrArray;
    }

    public void setSomeStrArray(String[] someStrArray) {
        this.someStrArray = someStrArray;
    }

    public SomeBean[] getSomeObjArray() {
        return someObjArray;
    }

    public void setSomeObjArray(SomeBean[] someObjArray) {
        this.someObjArray = someObjArray;
    }

    public List getSomeList() {
        return someList;
    }

    public void setSomeList(List someList) {
        this.someList = someList;
    }

    public Map getSomeMap() {
        return someMap;
    }

    public void setSomeMap(Map someMap) {
        this.someMap = someMap;
    }
}
