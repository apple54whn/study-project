package top.conanan.studyproject.utils.tree;

import top.conanan.studyproject.utils.tree.Node;

import java.util.*;
import java.util.stream.Collectors;

//public class TreeUtil {
//
//
//    public static <E extends Node<E>> List<E> buildTree(List<E> nodeList, List<String> rootIdList) {
//        Map<String, List<E>> nodeByParentIdMap = nodeList.stream().collect(Collectors.groupingBy(E::getParentId));
//        nodeList.forEach(e -> {
//            List<E> routeTreeSelects = nodeByParentIdMap.get(e.getId());
//            if (Objects.nonNull(routeTreeSelects)) routeTreeSelects.sort(Comparator.comparingInt(E::getSort));
//            e.setChildren(routeTreeSelects);
//        });
//
//        return nodeList.stream().filter(node -> rootIdList.contains(node.getParentId())).sorted(Comparator.comparingInt(E::getSort)).collect(Collectors.toList());
//    }
//
//
//    public static void main(String[] args) {
//
//        // List<BizNode> list = new ArrayList<>();
//        //
//        // BizNode node = new BizNode();
//        // node.setId("11");
//        // node.setName("11");
//        // node.setParentId("1");
//        // node.setLevel(1L);
//        // list.add(node);
//        //
//        // node = new BizNode();
//        // node.setId("111");
//        // node.setName("111");
//        // node.setParentId("11");
//        // node.setLevel(2L);
//        // list.add(node);
//        //
//        // node = new BizNode();
//        // node.setId("112");
//        // node.setName("112");
//        // node.setParentId("11");
//        // node.setLevel(2L);
//        // list.add(node);
//        //
//        // node = new BizNode();
//        // node.setId("22");
//        // node.setName("22");
//        // node.setParentId("2");
//        // node.setLevel(1L);
//        // list.add(node);
//        //
//        // node = new BizNode();
//        // node.setId("33");
//        // node.setName("33");
//        // node.setParentId("3");
//        // node.setLevel(1L);
//        // list.add(node);
//        //
//        //
//        // // 全部查询时，rootIdList可能就是 0 或者 null
//        // List<String> rootIdList = list.stream().filter(e -> e.getLevel() == 1L).map(BizNode::getParentId).collect(Collectors.toList());
//        //
//        // List<BizNode> tree = buildTree(list, rootIdList);
//        // System.out.println(tree);
//
//
//
//        List<BizNode> list = new ArrayList<>();
//
//        BizNode node = new BizNode();
//        node.setId("11");
//        node.setName("11");
//        node.setParentId(null);
//        node.setLevel(1L);
//        list.add(node);
//
//        node = new BizNode();
//        node.setId("111");
//        node.setName("111");
//        node.setParentId("11");
//        node.setLevel(2L);
//        list.add(node);
//
//        node = new BizNode();
//        node.setId("112");
//        node.setName("112");
//        node.setParentId("11");
//        node.setLevel(2L);
//        list.add(node);
//
//        node = new BizNode();
//        node.setId("22");
//        node.setName("22");
//        node.setParentId(null);
//        node.setLevel(1L);
//        list.add(node);
//
//        node = new BizNode();
//        node.setId("33");
//        node.setName("33");
//        node.setParentId(null);
//        node.setLevel(1L);
//        list.add(node);
//
//
//        // 全部查询时，rootIdList可能就是 0 或者 null
//        List<String> rootIdList = list.stream().filter(e -> e.getLevel() == 1L).map(BizNode::getParentId).collect(Collectors.toList());
//
//        List<BizNode> tree = buildTree(list, rootIdList);
//        System.out.println(tree);
//
//    }
//
//
//}
