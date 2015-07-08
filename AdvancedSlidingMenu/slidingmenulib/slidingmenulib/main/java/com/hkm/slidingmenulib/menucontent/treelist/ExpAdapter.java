package com.hkm.slidingmenulib.menucontent.treelist;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hkm.slidingmenulib.R;
import com.hkm.slidingmenulib.Util.TreeList;
import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;

/**
 * @Author Zheng Haibo
 * @PersonalWebsite http://www.mobctrl.net
 * @Description
 */
public class ExpAdapter extends UltimateViewAdapter {

    public class ExpandableViewTypes extends VIEW_TYPES {

        public static final int ITEM_TYPE_PARENT = 1026;
        public static final int ITEM_TYPE_CHILD = 1135;

        protected ExpandableViewTypes() {
            super();
        }
    }

    private Context mContext;
    private List<ItemData> mDataSet;
    private List<OnScrollToListener> monScrollToListenerList = new ArrayList<>();
    private OnScrollToListener onScrollToListener;
    public static final String TAG = "expAdapter";

    public void addOnScrollToListener(OnScrollToListener onScrollToListener) {
        this.monScrollToListenerList.add(onScrollToListener);
    }

    public void setOnScrollToListener(OnScrollToListener onScrollToListener) {
        this.onScrollToListener = onScrollToListener;
    }

    public ExpAdapter(Context context) {
        mContext = context;
        mDataSet = new ArrayList<ItemData>();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType) {
            case ExpandableViewTypes.ITEM_TYPE_PARENT:
                view = LayoutInflater.from(mContext).inflate(
                        R.layout.item_r_parent, parent, false);
                return new ParentViewHolder(view);
            case ExpandableViewTypes.ITEM_TYPE_CHILD:
                view = LayoutInflater.from(mContext).inflate(
                        R.layout.item_r_child, parent, false);
                return new ChildViewHolder(view);
            default:
                view = LayoutInflater.from(mContext).inflate(
                        R.layout.item_r_parent, parent, false);
                return new ParentViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case ExpandableViewTypes.ITEM_TYPE_PARENT:
                ParentViewHolder imageViewHolder = (ParentViewHolder) holder;
                imageViewHolder.bindView(mDataSet.get(position), position, imageClickListener);
                break;
            case ExpandableViewTypes.ITEM_TYPE_CHILD:
                ChildViewHolder textViewHolder = (ChildViewHolder) holder;
                textViewHolder.bindView(mDataSet.get(position), position);
                break;
            default:
                break;
        }
    }


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup viewGroup) {
        return new BaseViewHolder(viewGroup);
    }

    private void triggerBoardCastEventScrollTo(final int n) {
        for (int i = 0; i < monScrollToListenerList.size(); i++) {
            OnScrollToListener m = monScrollToListenerList.get(i);
            m.scrollTo(n);
        }
    }

    private void triggerSingleEventScrollTo(final int n) {
        if (onScrollToListener != null) {
            onScrollToListener.scrollTo(n);
        }
    }

    private ItemDataClickListener imageClickListener = new ItemDataClickListener() {

        @Override
        public void onExpandChildren(ItemData itemData) {
            int position = getCurrentPosition(itemData.getUuid());
            List<ItemData> children = TreeList.getChildrenByPath(itemData.getPath(), itemData.getTreeDepth());
            if (children == null) {
                return;
            }

            addAll(children, position + 1);// 插入到点击点的下方
            itemData.setChildren(children);
            triggerSingleEventScrollTo(position + 1);
            triggerBoardCastEventScrollTo(position + 1);
        }

        @Override
        public void onHideChildren(ItemData itemData) {
            int position = getCurrentPosition(itemData.getUuid());
            List<ItemData> children = itemData.getChildren();
            if (children == null) {
                return;
            }
            removeAll(position + 1, getChildrenCount(itemData) - 1);
            triggerSingleEventScrollTo(position);
            triggerBoardCastEventScrollTo(position);
            itemData.setChildren(null);
        }
    };

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup viewGroup) {
        return null;
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    @Override
    public int getAdapterItemCount() {
        return getItemCount();
    }

    @Override
    public long generateHeaderId(int i) {
        return 0;
    }

    private int getChildrenCount(ItemData item) {
        List<ItemData> list = new ArrayList<ItemData>();
        printChild(item, list);
        return list.size();
    }

    private void printChild(ItemData item, List<ItemData> list) {
        list.add(item);
        if (item.getChildren() != null) {
            for (int i = 0; i < item.getChildren().size(); i++) {
                printChild(item.getChildren().get(i), list);
            }
        }
    }

    /**
     * 从position开始删除，删除
     *
     * @param position  the count position
     * @param itemCount 删除的数目
     */
    protected void removeAll(int position, int itemCount) {
        for (int i = 0; i < itemCount; i++) {
            mDataSet.remove(position);
        }
        notifyItemRangeRemoved(position, itemCount);
    }

    /**
     * the current position from the uuid
     *
     * @param uuid the string in uuid
     * @return the int as the position
     */
    protected int getCurrentPosition(String uuid) {
        for (int i = 0; i < mDataSet.size(); i++) {
            if (uuid.equalsIgnoreCase(mDataSet.get(i).getUuid())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int getItemViewType(int position) {
        return mDataSet.get(position).getType();
    }

    public void add(ItemData text, int position) {
        mDataSet.add(position, text);
        notifyItemInserted(position);
    }

    public void addAll(List<ItemData> list, int position) {
        mDataSet.addAll(position, list);
        notifyItemRangeInserted(position, list.size());
    }

    public void delete(int pos) {
        if (pos >= 0 && pos < mDataSet.size()) {
            if (mDataSet.get(pos).getType() == ExpandableViewTypes.ITEM_TYPE_PARENT
                    && mDataSet.get(pos).isExpand()) {// 父组件并且子节点已经展开
                for (int i = 0; i < mDataSet.get(pos).getChildren().size() + 1; i++) {
                    mDataSet.remove(pos);
                }
                notifyItemRangeRemoved(pos, mDataSet.get(pos).getChildren()
                        .size() + 1);
            } else {// 孩子节点，或没有展开的父节点
                mDataSet.remove(pos);
                notifyItemRemoved(pos);
            }
        }
    }
}
