import javax.swing.*;
import java.awt.*;


class HuffmanTreePanel extends JPanel {
    private HuffmanNode root;
    private int xStart = 500;
    private int yStart = 50;
    private int xSpace = 100;
    private int ySpace = 70;

    //public HuffmanTreePanel(TreeNode root) {
    public HuffmanTreePanel(HuffmanNode root) {
        this.root = root;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawTree(g, root, xStart, yStart, xSpace);
    }

    private void drawTree(Graphics g, HuffmanNode node, int x, int y, int hSpace) {
        if (node == null) return;

        g.setColor(Color.BLACK);
        g.fillOval(x - 20, y - 20, 50, 50);
        g.setColor(Color.WHITE);
        //g.drawString("@", x - 5, y + 5);
        String ourDispString =
                new String(String.valueOf(node.data) + ":" +
                        String.valueOf(node.frequency));
        //g.drawString(String.valueOf(node.data), x - 5, y + 5);
        g.drawString(String.valueOf(ourDispString), x - 5, y + 5);

        if (node.left != null) {
            int xLeft = x - hSpace;
            int yLeft = y + ySpace;
            g.setColor(Color.RED);
            g.drawLine(x, y, xLeft, yLeft);
            drawTree(g, node.left, xLeft, yLeft, hSpace / 2);
        }

        if (node.right != null) {
            int xRight = x + hSpace;
            int yRight = y + ySpace;
            g.setColor(Color.GREEN);
            g.drawLine(x, y, xRight, yRight);
            drawTree(g, node.right, xRight, yRight, hSpace / 2);
        }
    }
}

