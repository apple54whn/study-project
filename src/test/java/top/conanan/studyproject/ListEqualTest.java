package top.conanan.studyproject;

import com.google.common.collect.Lists;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;

import java.util.*;

public class ListEqualTest {

    public static void main(String[] args) {


        Inner i11 = Inner.builder().id("11").name("11").build();
        Inner i12 = Inner.builder().id("12").name("12").build();
        List<Inner> o1Children = Lists.newArrayList(i11, i12);
        Outer o1 = Outer.builder().id("1").name("1").children(o1Children).build();

        Inner i21 = Inner.builder().id("21").name("21").build();
        List<Inner> o2Children = Lists.newArrayList(i21);
        Outer o2 = Outer.builder().id("2").name("2").children(o2Children).build();

        List<Outer> o1List = Lists.newArrayList(o1, o2);
        System.out.println(o1List);



        Inner i31 = Inner.builder().id("11").name("11").build();
        Inner i32 = Inner.builder().id("12").name("12").build();
        List<Inner> o3Children = Lists.newArrayList(i31, i32);
        Outer o3 = Outer.builder().id("1").name("1").children(o3Children).build();

        Inner i41 = Inner.builder().id("21").name("21").build();
        List<Inner> o4Children = Lists.newArrayList(i41);
        Outer o4 = Outer.builder().id("2").name("2").children(o4Children).build();

        List<Outer> o2List = Lists.newArrayList(o3, o4);
        System.out.println(o2List);


        // System.out.println(checkEqual(null, null));
        // System.out.println(checkEqual(Lists.newArrayList(), null));
        // System.out.println(checkEqual(null, Lists.newArrayList(Outer.builder().build())));

        System.out.println("checkEqualWithApache: " + checkEqualWithApache(o1List, o2List));
        // System.out.println("checkEqualWithRetainAll: " + checkEqualWithRetainAll(o1List, o2List));

    }


    /**
     * 必须实现 equals、hashcode, getCardinalityMap 中的 Map<O, Integer> count = new HashMap();Integer c = (Integer)count.get(obj);需要
     * @see org.apache.commons.collections4.CollectionUtils#getCardinalityMap
     * @param o1List o1List
     * @param o2List o2List
     * @return flag
     */
    public static boolean checkEqualWithApache(List<Outer> o1List, List<Outer> o2List) {
        if (Objects.isNull(o1List) && Objects.isNull(o2List)) {
            return true;
        }

        if (Objects.isNull(o1List) || Objects.isNull(o2List)) {
            return false;
        }

        if (o1List.size() != o2List.size()) {
            return false;
        }

        return CollectionUtils.isEqualCollection(o1List, o2List);
    }


    /**
     * 必须实现 equals、hashcode, retainAll 需要
     * @see List#retainAll(Collection)
     * @param o1List o1List
     * @param o2List o2List
     * @return flag
     */
    public static boolean checkEqualWithRetainAll(List<Outer> o1List, List<Outer> o2List) {
        if (Objects.isNull(o1List) && Objects.isNull(o2List)) {
            return true;
        }

        if (Objects.isNull(o1List) || Objects.isNull(o2List)) {
            return false;
        }

        if (o1List.size() != o2List.size()) {
            return false;
        }

        o1List.retainAll(o2List);
        return o1List.size() == o2List.size();
    }



}


@Getter
@Setter
@Builder
class Outer {
    private String id;
    private String name;
    private List<Inner> children;
    private String other;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Outer outer = (Outer) o;
        // 注意：Objects.equals(children, outer.children) 会调用 List 的 equals 方法，并且和自然顺序有关，调换 list 中对象的顺序结果不同
        // return Objects.equals(id, outer.id) && Objects.equals(name, outer.name) && Objects.equals(children, outer.children);

        if (Objects.isNull(children) && Objects.isNull(outer.children)) return true;
        if (Objects.isNull(children) || Objects.isNull(outer.children)) return false;
        if (children.size() != outer.children.size()) return false;


        // 1 retainAll 实现
        // 1 2 1
        // 2 1 1
        // children.retainAll(outer.children);
        // return Objects.equals(id, outer.id) && Objects.equals(name, outer.name) && children.size() == outer.children.size();

        // 2 CollectionUtils.isEqualCollection 实现
        return Objects.equals(id, outer.id) && Objects.equals(name, outer.name) && CollectionUtils.isEqualCollection(children, outer.children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, children);
    }
}

@Getter
@Setter
@Builder
class Inner {
    private String id;
    private String name;
    private String other;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inner inner = (Inner) o;
        return Objects.equals(id, inner.id) && Objects.equals(name, inner.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}

