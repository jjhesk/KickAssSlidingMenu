package com.asynhkm.productchecker.Util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Hesk on
 */
public class Tool {
    public static String get_mac_address(Context ctx) {
        WifiManager manager = (WifiManager) ctx
                .getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = manager.getConnectionInfo();
        String address = info.getMacAddress();
        return address;
    }

    public static boolean isOnline(Context ctx) {
        try {
            ConnectivityManager cm = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
            return cm.getActiveNetworkInfo().isConnectedOrConnecting();
        } catch (Exception e) {
            return false;
        }

    }

    public static boolean isEmpty(EditText t) {
        try {
            final String contenttext = t.getText().toString();
            Log.d("TAG STRING CONTENT", contenttext);
            if (contenttext.equals("")) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            Log.d("TAG STRING CONTENT", "NO CONTEST");
            e.printStackTrace();
            return false;
        }
    }

    public static void trace(Context ctx, int resId, Object... param) {
        String f = "resId not found";
        try {
            f = ctx.getResources().getString(resId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Toast.makeText(ctx, f, Toast.LENGTH_LONG).show();
        }
        Toast.makeText(ctx, String.format(f, param), Toast.LENGTH_LONG).show();
    }

    public static void trace(Context ctx, String format, Object... param) {
        Toast.makeText(ctx, String.format(format, param), Toast.LENGTH_LONG)
                .show();
    }

    public static void trace(Context ctx, String str) {
        Toast.makeText(ctx, str, Toast.LENGTH_LONG).show();
    }

    public static void trace(Context ctx, int resId) {
        String f = "resId not found";
        try {
            f = ctx.getResources().getString(resId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Toast.makeText(ctx, f, Toast.LENGTH_LONG).show();
        }
    }

    public static void showKeyBoard(Context ctx, EditText focused_textfield) {
        InputMethodManager imm = (InputMethodManager) ctx
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(focused_textfield, InputMethodManager.SHOW_FORCED);
    }

    public static void hideKeyBoard(Context ctx, EditText focused_textfield) {
        InputMethodManager imm = (InputMethodManager) ctx
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(focused_textfield.getWindowToken(),
                InputMethodManager.RESULT_UNCHANGED_SHOWN);
    }


    public static <K extends Comparable, V extends Comparable> LinkedHashMap<K, V> sortByKeys(LinkedHashMap<K, V> map) {
        List<K> keys = new LinkedList<K>(map.keySet());
        Collections.sort(keys, (Comparator<? super K>) new Comparator<String>() {
            @Override
            public int compare(String first, String second) {
                Collator collator = Collator.getInstance(Locale.getDefault());
                //Collator collator = Collator.getInstance(new Locale("tr", "TR"));
                return collator.compare(first, second);
            }
        });

        LinkedHashMap<K, V> sortedMap = new LinkedHashMap<K, V>();
        for (K key : keys) {
            sortedMap.put(key, map.get(key));
        }

        return sortedMap;
    }

    public static Map<String, Integer> sortByComparator(Map<String, Integer> unsortMap, final boolean order) {
        List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(unsortMap.entrySet());
        // Sorting the list based on values
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                if (order) {
                    return o1.getValue().compareTo(o2.getValue());
                } else {
                    return o2.getValue().compareTo(o1.getValue());

                }
            }
        });
        // Maintaining insertion order with the help of LinkedList
        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

    public static LinkedHashMap sortHashMapByValuesD(HashMap passedMap) {
        List mapKeys = new ArrayList(passedMap.keySet());
        List mapValues = new ArrayList(passedMap.values());
        Collections.sort(mapValues);
        Collections.sort(mapKeys);

        LinkedHashMap sortedMap = new LinkedHashMap();

        Iterator valueIt = mapValues.iterator();
        while (valueIt.hasNext()) {
            Object val = valueIt.next();
            Iterator keyIt = mapKeys.iterator();

            while (keyIt.hasNext()) {
                Object key = keyIt.next();
                String comp1 = passedMap.get(key).toString();
                String comp2 = val.toString();

                if (comp1.equals(comp2)) {
                    passedMap.remove(key);
                    mapKeys.remove(key);
                    sortedMap.put((String) key, (Double) val);
                    break;
                }

            }

        }
        return sortedMap;
    }

    public static String[] sortMapToArray(final String sortkey, HashMap<String, String> map) {
        ArrayList<String> t1 = new ArrayList<String>();

        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (key.equalsIgnoreCase(sortkey)) {
                t1.add(value);
            }
        }

        return t1.toArray(new String[t1.size()]);
    }
}
