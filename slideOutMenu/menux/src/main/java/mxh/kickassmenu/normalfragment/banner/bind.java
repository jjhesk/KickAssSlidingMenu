package mxh.kickassmenu.normalfragment.banner;

import android.support.annotation.Nullable;

/**
 * Created by hesk on 23/2/16.
 */
public class bind {
    public final static int FULL = 0, HALF = 1;
    public int size;
    public String link_url, tab_name;
    public String image;
    public Type mtype;


    public bind(int size_con,
                String tabName,
                String url,
                String imageUrl,
                @Nullable String type) {
        size = size_con;
        link_url = url;
        image = imageUrl;
        tab_name = tabName;
        if (type != null) {
            if (type.equalsIgnoreCase("webpage")) {
                mtype = Type.WEB;
            }
        } else {
            mtype = Type.TAB;
        }
    }
}
