package com.hkm.hbstore.life;

import android.os.Build;

/**
 * Android App settings
 * Created by hesk on 2/2/15.
 */
public class Config {
    public final static int SINGLE_ARTICLE = 9019;
    public final static int SETTING_INTENT = 1001;
    public final static int RETURN_WITH_NEW_FEED_URL = 102;
    public final static int RETURN_WITH_NOTHING = 100;
    public final static String KEYURL = "feed_list_url";
    public final static String SURL = "product_url";

    public static final String base_tc = "http://cn.hypebeast.com/t";
    public static final String base_cn = "http://cn.hypebeast.com";
    public static final String base_jp = "http://jp.hypebeast.com";
    public static final String base_en = "http://hypebeast.com";
    public static final String menuendpoint = "http://hypebeast.com/api/mobile-app-config";
    /**
     * the specific page view from the browser or from other intents
     */
    public static final String action_name_page_view = "com.hb.editorial.SINGLE_PAGE_VIEW";

    public static class setting {
        public static int show_share_items = 44;
        public static int single_page_items = 18;
        public static int APIversion = 2;
        public static String wv_usergent = "HypebeastEditorial/1.0";
        public static String useragent = "HypebeastEditorial/1.0 Android" + Build.VERSION.SDK_INT;
        public static final float PORTION_CONFIG = 3f / 2f;
        //   public static final float PORTION_CONFIG = 11f / 10f;
        public static final int LANG_JP = 2, LANG_CN = 1, LANG_TC = 3, LANG_EN = 0;
    }

    public static class urlmask {
        public static final String u1 = "";
        public static final String u2 = "";
        public static final String u3 = "";
    }

    public static String save_pref_private_id = "HBAUTHENTICATION";
    public static String save_login = "LOGIN";
    public static String save_password = "PASSWORD";
    public static String PARSETAG = "com.hb.parse";

    public static int HANDHELD_VERTICAL = 2, TABLET_HORIZATONAL = 3;
    /**
     * using hesk.kam@101medialab.com    @parse.com
     */
    public static final String DFP_INTER_ID = "/1015938/Hypebeast_Interstital_320x480";
    public static final String DFP_BANNER_UNIT_ID = "/1015938/Hypebeast_App_320x50";
    public static final String DFP_LIST_UNIT_ID = "/1015938/Hypebeast_App_List";

    private static String getBase() {
      //  return retentioneeee.currentlanguagebaseurl + "/";
        return "";
    }

    private static String getRecentListEndpoint() {
       // return retentioneeee.currentlanguagebaseurl;
        return "";
    }

    public static class menu {
        public static String fashion() {
            return getBase() + "fashion";
        }

        public static String footwear() {
            return getBase() + "footwear";
        }

        public static String automotive() {
            return getBase() + "automotive";
        }

        public static String arts() {
            return getBase() + "arts";
        }

        public static String editorial() {
            return getBase() + "editorial";
        }

        public static String sports() {
            return getBase() + "sports";
        }

        public static String food() {
            return getBase() + "food";
        }

        public static String tech() {
            return getBase() + "tech";
        }

        public static String travel() {
            return getBase() + "travel";
        }

        public static String design() {
            return getBase() + "design";
        }

        public static String music() {
            return getBase() + "music";
        }

        public static String entertainment() {
            return getBase() + "entertainment";
        }

        public static String tv() {
            return getBase() + "tv";
        }

        public static String lifestyle() {
            return getBase() + "lifestyle";
        }


        public static String search() {
            return getBase() + "search?s=";
        }

        public static String searchbypage() {
            return getBase() + "search/page/%d?s=";
        }

        /**
         * the first tap
         *
         * @return on the left side
         */
        public static String recent() {
            return getRecentListEndpoint();
        }

        /**
         * the second tap
         *
         * @return on the right side
         */
        public static final String popular() {
            return getBase() + "popular";
        }

    }


}
