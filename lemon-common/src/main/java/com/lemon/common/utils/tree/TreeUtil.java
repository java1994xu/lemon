//package com.lemon.common.utils.tree;
//
//
//import cn.hutool.core.collection.CollUtil;
//
//import java.util.ArrayList;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.stream.Collectors;
//
///**
// * @author xubb
// * @Description
// * @create 2020-11-10 2:25
// */
//public class TreeUtil {
//
//
//    /**
//     * 树构建
//     * T:list数据源内的对象类型
//     * S:根节点的对象类型
//     *
//     * @param list     list源数据
//     * @param parentId 最顶层父id值 一般为 0 之类
//     * @param children 子节点集合的名称
//     * @return List
//     */
//    public static <T, S, C> List<Tree<T>> build(List<T> list, S parentId, C children) {
//        return buildTree(list, parentId, children);
//    }
//
//
//    /**
//     * 树构建
//     *
//     * @param <T>        转换的实体 为数据源里的对象类型
//     * @param <S>        根节点的类型
//     * @param list       源数据集合
//     * @param parentId   最顶层父id值 一般为 0 之类
//     * @param children   子节点集合的名称
//     * @return List
//     */
//    public static <T, S, C> List<Tree<T>> buildTree(List<T> list, S parentId, C children) {
//        final List<Tree<T>> treeNodes =   new ArrayList<>();
//        Tree<T> treeNode;
//        for (T obj : list) {
//            treeNode = new Tree<T>(obj);
//            treeNodes.add(treeNode);
//        }
//
//        List<Tree<T>> finalTreeNodes =  new ArrayList<>();
//        for (Tree<T> node : treeNodes) {
//            if (parentId.equals(node.getParentId())) {
//                finalTreeNodes.add(node);
//                innerBuild(treeNodes, node, 0, treeNodeConfig.getDeep());
//            }
//        }
//        // 内存每层已经排过了 这是最外层排序
//        finalTreeNodes = finalTreeNodes.stream().sorted().collect(Collectors.toList());
//        return finalTreeNodes;
//    }
//}
