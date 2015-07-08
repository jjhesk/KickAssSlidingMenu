package com.hkm.slidingmenulib.menucontent.treelist;

import java.util.List;

/**
 * @Author Zheng Haibo
 * @PersonalWebsite http://www.mobctrl.net
 * @Description
 */
public class ItemData  implements Comparable<ItemData> {

    private String uuid;

    private int type;// 显示类型
    private String text;
    private String path;// 路径
    private int treeDepth = 0;// 路径的深度

    private List<ItemData> children;

    private boolean expand;// 是否展开

    public boolean isExpand() {
        return expand;
    }

    public void setExpand(boolean expand) {
        this.expand = expand;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<ItemData> getChildren() {
        return children;
    }

    public void setChildren(List<ItemData> children) {
        this.children = children;
    }

    public ItemData(int type, String text, String path, String uuid,
                    int treeDepth, List<ItemData> children) {
        super();
        this.type = type;
        this.text = text;
        this.uuid = uuid;
        this.path = path;
        this.treeDepth = treeDepth;
        this.children = children;
    }

    public ItemData() {

    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getTreeDepth() {
        return treeDepth;
    }

    public void setTreeDepth(int treeDepth) {
        this.treeDepth = treeDepth;
    }

    @Override
    public int compareTo(ItemData another) {
        return this.getText().compareTo(another.getText());
    }

}