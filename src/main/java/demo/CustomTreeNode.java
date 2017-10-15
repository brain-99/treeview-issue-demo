package demo;

import java.util.ArrayList;

public class CustomTreeNode extends ArrayList<CustomTreeNode> {

    private String name;

    private boolean myStatus = false;

    public CustomTreeNode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isMyStatus() {
        return myStatus;
    }

    public void setMyStatus(boolean myStatus) {
        this.myStatus = myStatus;
    }

}
