/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    long maxProduct = 0;
    long totalSum = 0;
    final long MOD = 1_000_000_007;

    // First DFS: compute total sum
    public long dfsSum(TreeNode root) {
        if (root == null) return 0;
        return root.val + dfsSum(root.left) + dfsSum(root.right);
    }

    // Second DFS: compute subtree sums and update max product
    public long dfsProduct(TreeNode root) {
        if (root == null) return 0;

        long left = dfsProduct(root.left);
        long right = dfsProduct(root.right);

        long subTreeSum = root.val + left + right;

        // consider cutting this subtree
        long product = subTreeSum * (totalSum - subTreeSum);
        maxProduct = Math.max(maxProduct, product);

        return subTreeSum;
    }

    public int maxProduct(TreeNode root) {
        totalSum = dfsSum(root);
        dfsProduct(root);
        return (int) (maxProduct % MOD);
    }
}
s