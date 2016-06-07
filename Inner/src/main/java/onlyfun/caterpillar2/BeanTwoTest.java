package onlyfun.caterpillar2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
/** created by seasen on 2016/4/5
 *     <bean id="some1" class="onlyfun.caterpillar2.SomeBean">
 <property name="name" value="shaosen"/>
 <property name="age" value="24"/>
 </bean>
 <bean id="some2" class="onlyfun.caterpillar2.SomeBean">
 <property name="name" value="lllllbbbbbb"/>
 <property name="age" value="24"/>
 </bean>
 <bean id="someBeanTwo" class="onlyfun.caterpillar2.SomeBeanTwo">
 <property name="someStrArray">
 <list>
 <value>how old are you ?</value>
 <value>是我</value>
 </list>
 </property>
 <property name="someList">
 <list>
 <ref bean="some1"/>
 <ref bean="some2"/>
 </list>
 </property>
 <property name="someMap">
 <map>
 <entry key="mapfirst"><value>mapmapmap!</value></entry>
 <entry key="mapsecond"><value>boom boom!</value></entry>
 <entry key="mapthird"><ref bean="some1"/></entry>
 </map>
 </property>
 </bean>
 *
 * */
public class BeanTwoTest {
    public static void main(String[] args) {
        ApplicationContext context = new FileSystemXmlApplicationContext("src/applicationContext.xml");
        SomeBeanTwo sbt = context.getBean("someBeanTwo",SomeBeanTwo.class);
        String[] str = sbt.getSomeStrArray();
        List<String> list = sbt.getSomeList();
        SomeBean[] sb = sbt.getSomeObjArray();
        Map map = sbt.getSomeMap();
        for (int i = 0; i <str.length; i++)
            System.out.println(str[i]);
        Iterator it = list.iterator();
        while(it.hasNext()) {
            SomeBean sbn = (SomeBean) it.next();
            System.out.println(sbn.getName()+" ::::"+sbn.getAge());
        }
        System.out.println(((SomeBean)map.get("mapthird")).getName()+"::"+((SomeBean)map.get("mapthird")).getAge());

    }
}
