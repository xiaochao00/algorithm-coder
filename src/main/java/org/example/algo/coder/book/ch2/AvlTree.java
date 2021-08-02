package org.example.algo.coder.book.ch2;

import java.util.Objects;

/**
 * avl tree
 * 1) 是一颗空树，或者左右子树的高度差绝对值不超过1;
 * 2) 左右子树也是平衡二叉树
 * <p>
 * 遇到的问题：
 * 1.不知道如何初始化平衡树对象;
 * 2.更新某节点的左右节点的同时，要更新对应新子节点的父节点;
 * 3.如何平衡完当前部分的子树，使其和外面的大树关联起来; ---插入动作需要返回值
 *
 * @author shichao
 * @since 1.0.0
 * 2021/8/2 23:09
 */
public class AvlTree {

    private TreeNode root;

    public AvlTree() {
    }

    public void add(Integer val) {
        // 比较大小
        this.root = insert(root, val);
    }

    /**
     * 插入新元素
     *
     * @param root 指定的子树节点对象
     * @param val  插入的值
     * @return 插入调整后新的根节点
     */
    private TreeNode insert(TreeNode root, Integer val) {
        if (Objects.isNull(root)) {
            return new TreeNode(val);
        }
        if (root.val < val) {
            TreeNode rightNode = insert(root.right, val);
            root.updateRight(rightNode);
            // 判断调整平衡
            root = rotating(root, false, val);
        } else if (root.val > val) {
            TreeNode leftNode = insert(root.left, val);
            root.updateLeft(leftNode);
            // 判断调整平衡
            root = rotating(root, true, val);
        } else {
            // 相等的话，就忽略
            System.err.println("Here exist val:" + val);
            return root;
        }
        adjustTreeNodeHeight(root);
        return root;
    }

    private void adjustTreeNodeHeight(TreeNode node) {
        if (Objects.isNull(node.left) && Objects.isNull(node.right)) {
            return;
        }
        if (Objects.nonNull(node.left) && Objects.nonNull(node.right)) {
            node.height = Math.max(node.left.height, node.right.height) + 1;
            return;
        }
        if (Objects.isNull(node.left)) {
            node.height = node.right.height + 1;
            return;
        }
        node.height = node.left.height + 1;
    }


    /**
     * 旋转调整
     *
     * @param node 开始调整的节点
     */
    private TreeNode rotating(TreeNode node, boolean isLeft, Integer val) {
        if (!isUnbalance(node)) {
            return node;
        }
        if (isLeft && node.left.val > val) {
            // 直接左子树 的左子树失衡，右旋转
            return leftLeftUnbalance(node);
        }
        if (isLeft && node.left.val < val) {
            // 直接左子树 的右子树失衡
            return leftRightUnbalance(node);
        }
        if (node.right.val < val) {
            // 直接右子树 的右子树失衡，左旋转
            return rightRightUnBalance(node);
        }
        // 直接右子树 的左子树失衡
        return rightLeftUnbalance(node);
    }

    private boolean isUnbalance(TreeNode node) {
        int leftHei = height(node.left);
        int rightHei = height(node.right);
        return Math.abs(leftHei - rightHei) > 1;
    }

    private TreeNode leftLeftUnbalance(TreeNode node) {
        // 右旋转
        TreeNode leftNode = node.left;
        // 节点的左子树，调整为节点左子树的右子树，
        node.updateLeft(leftNode.right);
        // 节点的左子树是当前节点的父节点
        // 左子树节点的右子树为当前节点
        leftNode.updateRight(node);
        // 更新高度
        adjustTreeNodeHeight(node);
        adjustTreeNodeHeight(leftNode);
        //
        return leftNode;
    }

    private TreeNode rightRightUnBalance(TreeNode node) {
        // 左旋转
        TreeNode rightNode = node.right;
        //节点的右子树，调整为右子树的左子树
        node.updateRight(rightNode.left);
        //右子树的左子树，调整为当前节点
        rightNode.updateLeft(node);
        // 调整高度
        adjustTreeNodeHeight(node);
        adjustTreeNodeHeight(rightNode);
        //
        return rightNode;
    }

    private TreeNode leftRightUnbalance(TreeNode node) {
        // 对左子树左转，使其变成 左左失衡
        node.left = rightRightUnBalance(node.left);
        // 对根节点按左左失衡处理
        return leftLeftUnbalance(node);
    }

    private TreeNode rightLeftUnbalance(TreeNode node) {
        //对右子树右转，使其变成 右右失衡
        node.right = leftLeftUnbalance(node.right);
        // 对根节点按，右右失衡处理
        return rightRightUnBalance(node);
    }

    private int height(TreeNode node) {
        if (Objects.isNull(node)) {
            return 0;
        }
        return node.height;
    }

    public static class TreeNode {

        public TreeNode(Integer val) {
            this.val = val;
            this.height = 0;
        }

        private int height;
        private Integer val;
        private TreeNode left;
        private TreeNode right;
        private TreeNode parent;


        public void updateLeft(TreeNode node) {
            this.left = node;
            if (Objects.nonNull(node)) {
                node.parent = this;
            }
        }

        public void updateRight(TreeNode node) {
            this.right = node;
            if (Objects.nonNull(node)) {
                node.parent = this;
            }
        }
    }
}
