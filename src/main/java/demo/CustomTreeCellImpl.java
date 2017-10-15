package demo;

import com.sun.javafx.scene.control.skin.LabeledText;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.WeakInvalidationListener;
import javafx.css.PseudoClass;
import javafx.geometry.Insets;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.Glyph;
import org.controlsfx.glyphfont.GlyphFont;
import org.controlsfx.glyphfont.GlyphFontRegistry;

import java.lang.ref.WeakReference;

public class CustomTreeCellImpl extends TreeCell<CustomTreeNode> {

    private static final GlyphFont fontAwesome = GlyphFontRegistry.font("FontAwesome");
    
    private static final PseudoClass myStatusClass = PseudoClass.getPseudoClass("mystatus");

    /* *********************
     * Boilerplate similar to DefaultTreeCell
     * *********************/

    private WeakReference<TreeItem<CustomTreeNode>> treeItemRef;

    private InvalidationListener treeItemGraphicListener = observable -> updateDisplay(getItem(), isEmpty());

    private InvalidationListener treeItemListener = new InvalidationListener() {
        @Override public void invalidated(Observable observable) {
            TreeItem<CustomTreeNode> oldTreeItem = treeItemRef == null ? null : treeItemRef.get();
            if (oldTreeItem != null) {
                oldTreeItem.graphicProperty().removeListener(weakTreeItemGraphicListener);
            }

            TreeItem<CustomTreeNode> newTreeItem = getTreeItem();
            if (newTreeItem != null) {
                addListeners(newTreeItem);
                treeItemRef = new WeakReference<>(newTreeItem);
            }
        }
    };

    private WeakInvalidationListener weakTreeItemGraphicListener =
            new WeakInvalidationListener(treeItemGraphicListener);

    private WeakInvalidationListener weakTreeItemListener =
            new WeakInvalidationListener(treeItemListener);

    public CustomTreeCellImpl() {
        treeItemProperty().addListener(weakTreeItemListener);

        TreeItem<CustomTreeNode> treeItem = getTreeItem();
        if (treeItem != null) {
            addListeners(treeItem);
        }
    }

    private void addListeners(TreeItem<CustomTreeNode> treeItem) {
        treeItem.graphicProperty().addListener(weakTreeItemGraphicListener);
    }

    @Override
    public void updateItem(CustomTreeNode item, boolean empty) {
        super.updateItem(item, empty);
        updateDisplay(item, empty);
    }

    private void updateDisplay(CustomTreeNode item, boolean empty) {
        // default pseudoclass to false
        pseudoClassStateChanged(myStatusClass, false);

        if (item == null || empty) {
            setText(null);
            setGraphic(null);
        } else {
            //System.out.println("Creating cell contents for " + item.getName());

            // ensure we have a hbox which is empty
            HBox hbox = new HBox(3);

            // add label
            LabeledText text = new LabeledText(this);
            text.setText(item.getName());

            // add css class if needed
            if(item.isMyStatus()) {
                pseudoClassStateChanged(myStatusClass, true);
            }
            hbox.getChildren().add(text);

            // add a auto-growing spacing region to ensure any icons/buttons are right-aligned
            Region spacer = new Region();
            HBox.setHgrow(spacer, Priority.ALWAYS);
            hbox.getChildren().add(spacer);

            Glyph someIcon = fontAwesome.create(FontAwesome.Glyph.INFO_CIRCLE);
            HBox.setMargin(someIcon, new Insets(0, 10, 0, 0));
            hbox.getChildren().add(someIcon);

            setGraphic(hbox);
        }
        
    }
    
}
