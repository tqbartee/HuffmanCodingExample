import javax.swing.*;
import java.awt.*;

public class HuffmanBinaryTreeGUI extends JFrame {
    public HuffmanBinaryTreeGUI(HuffmanNode root, String title) {
        //setTitle("Binary Tree Visualization");
        setLocation(20, 20);
        setTitle(title);
        setSize(1000, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        HuffmanTreePanel treePanel = new HuffmanTreePanel(root);
        add(treePanel);
        setVisible(true);
    }

    public static void main(String[] args) {
    }
}