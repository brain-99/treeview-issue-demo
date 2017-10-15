package demo;

import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class Controller {

    @FXML
    private TreeView<CustomTreeNode> treeView;

    private CustomTreeNode rootNode = null;

    private TreeItem<CustomTreeNode> rootItem = null;

    @FXML
    private void initialize() {
        // set root node
        rootNode = new CustomTreeNode(null);
        rootItem = new TreeItem<>(rootNode);
        treeView.setRoot(rootItem);
        treeView.setShowRoot(false);

        // add some children...
        CustomTreeNode item1Node = new CustomTreeNode("Item 1");
        TreeItem<CustomTreeNode> item1TreeItem = new TreeItem<>(item1Node);
        CustomTreeNode item2Node = new CustomTreeNode("Item 2");
        TreeItem<CustomTreeNode> item2TreeItem = new TreeItem<>(item2Node);
        CustomTreeNode item3Node = new CustomTreeNode("Item 3");
        TreeItem<CustomTreeNode> item3TreeItem = new TreeItem<>(item3Node);
        CustomTreeNode item4Node = new CustomTreeNode("Item 4");
        TreeItem<CustomTreeNode> item4TreeItem = new TreeItem<>(item4Node);
        CustomTreeNode item5Node = new CustomTreeNode("Item 5");
        TreeItem<CustomTreeNode> item5TreeItem = new TreeItem<>(item5Node);
        rootNode.add(item1Node);
        rootNode.add(item2Node);
        rootNode.add(item3Node);
        rootNode.add(item4Node);
        rootNode.add(item5Node);
        rootItem.getChildren().addAll(item1TreeItem, item2TreeItem, item3TreeItem, item4TreeItem, item5TreeItem);

        CustomTreeNode item1_1Node = new CustomTreeNode("Item 1.1");
        item1_1Node.setMyStatus(true);
        TreeItem<CustomTreeNode> item1_1TreeItem = new TreeItem<>(item1_1Node);
        CustomTreeNode item1_2Node = new CustomTreeNode("Item 1.2");
        TreeItem<CustomTreeNode> item1_2TreeItem = new TreeItem<>(item1_2Node);
        CustomTreeNode item1_3Node = new CustomTreeNode("Item 1.3");
        item1_3Node.setMyStatus(true);
        TreeItem<CustomTreeNode> item1_3TreeItem = new TreeItem<>(item1_3Node);
        CustomTreeNode item1_4Node = new CustomTreeNode("Item 1.4");
        TreeItem<CustomTreeNode> item1_4TreeItem = new TreeItem<>(item1_4Node);
        item1Node.add(item1_1Node);
        item1Node.add(item1_2Node);
        item1Node.add(item1_3Node);
        item1Node.add(item1_4Node);
        item1TreeItem.getChildren().addAll(item1_1TreeItem, item1_2TreeItem, item1_3TreeItem, item1_4TreeItem);

        CustomTreeNode item4_1Node = new CustomTreeNode("Item 4.1");
        TreeItem<CustomTreeNode> item4_1TreeItem = new TreeItem<>(item4_1Node);
        CustomTreeNode item4_2Node = new CustomTreeNode("Item 4.2");
        TreeItem<CustomTreeNode> item4_2TreeItem = new TreeItem<>(item4_2Node);
        item4Node.add(item4_1Node);
        item4Node.add(item4_2Node);
        item4TreeItem.getChildren().addAll(item4_1TreeItem, item4_2TreeItem);

        // set cell factory
        treeView.setCellFactory((TreeView<CustomTreeNode> tv) -> new CustomTreeCellImpl());
    }

}
