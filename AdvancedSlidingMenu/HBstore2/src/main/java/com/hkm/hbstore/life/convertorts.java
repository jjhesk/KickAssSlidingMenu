package com.hkm.hbstore.life;

import android.content.Context;

import com.hkm.hbstore.R;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by hesk on 16/6/15.
 */
public class convertorts {
    private Context ctx;
    private final static HashMap<Character, Character> dictionary = new HashMap<Character, Character>();

    public convertorts(Context ctx) {
        this.ctx = ctx;
        //   init();
    }


    private static StringBuilder testCBConvert(byte[] src) throws CharacterCodingException {
        Charset charset = Charset.forName("UTF-8");
        CharsetDecoder decoder = charset.newDecoder();
        ByteBuffer srcBuffer = ByteBuffer.wrap(src);
        CharBuffer resBuffer = decoder.decode(srcBuffer);
        StringBuilder b = new StringBuilder(resBuffer);
        return b;
    }

    private static StringBuilder testStringConvert(byte[] src) throws UnsupportedEncodingException {
        String s = new String(src, "UTF-8");
        StringBuilder b = new StringBuilder(s);
        return b;
    }

    public void init(int RawResourfes) {

        final int ResId = RawResourfes;
        //R.raw.words;
        StringBuilder sb = new StringBuilder();
        Scanner s = new Scanner(ctx.getResources().openRawResource(ResId));
        while (s.hasNextLine()) {
            sb.append(s.nextLine());
        }
        final int sbL = sb.toString().length();
        //final byte[] byesHold = sbL.toString().getBytes("UTF8");
        for (int n = 0; n < sbL; n += 2) {
            Character ts = sb.charAt(n);
            Character sf = sb.charAt(n + 1);
            dictionary.put(ts, sf);
        }

    }
}
