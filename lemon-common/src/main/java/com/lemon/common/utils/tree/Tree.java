package com.lemon.common.utils.tree;

import java.util.List;

/**
 * @author xubb
 * @Description
 * @create 2020-11-10 2:21
 */
public class Tree<T> {

    private List<Tree<T>> children;

    private T t;

    public Tree(T t) {
        this.t = t;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public List<Tree<T>> getChildren() {
        return children;
    }

    public void setChildren(List<Tree<T>> children) {
        this.children = children;
    }


}
