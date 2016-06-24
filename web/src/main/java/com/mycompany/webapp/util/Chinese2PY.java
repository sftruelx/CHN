package com.mycompany.webapp.util;

/**
 * Created by liaoxiang on 2016/6/17.
 */

import net.sourceforge.pinyin4j.*;

import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;

import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;

import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;


public class Chinese2PY

{

    private HanyuPinyinOutputFormat format = null;

    private String[] pinyin;


    public Chinese2PY()

    {

        format = new HanyuPinyinOutputFormat();

        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);


        pinyin = null;

    }


    //转换单个字符

    public String getCharacterPinYin(char c)

    {

        try

        {
            format.setVCharType(HanyuPinyinVCharType.WITH_V);
            format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
            pinyin = PinyinHelper.toHanyuPinyinStringArray(c, format);

        } catch (BadHanyuPinyinOutputFormatCombination e)

        {

            e.printStackTrace();

        }


        // 如果c不是汉字，toHanyuPinyinStringArray会返回null

        if (pinyin == null) return null;


        // 只取一个发音，如果是多音字，仅取第一个发音
        if(pinyin.length==0){
            return String.valueOf(c).toUpperCase();
        }else {
            return pinyin[0];
        }
    }


    //转换一个字符串

    public String getStringPinYin(String str)

    {

        StringBuilder sb = new StringBuilder();

        String tempPinyin = null;

        for (int i = 0; i < str.length(); ++i)

        {

            tempPinyin = getCharacterPinYin(str.charAt(i));

            if (tempPinyin == null)

            {

                // 如果str.charAt(i)非汉字，则保持原样

                sb.append(str.charAt(i));

            } else

            {

                sb.append(tempPinyin);

            }

        }

        return sb.toString();

    }

    public static void main(String[] arg) {

        Chinese2PY hanyu = new Chinese2PY();

        String strPinyin = hanyu.getStringPinYin("sss多福多寿");
        System.out.println(strPinyin);
    }

}
