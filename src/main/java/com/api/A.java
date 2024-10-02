package com.api;


import java.util.ArrayList;
import java.util.Iterator;


public class A
{
    public static void main(String[] args)
    {
        String str = "aa bb aaa cab";
        char result = getMaxConsecutiveChar(str);
        System.out.println("Character with max consecutive occurrence: " + result);
    }


    public static char getMaxConsecutiveChar(String str) {
        char maxChar = '\0';
        int maxCount = 0;
        int count = 1;
        for (int i = 1; i < str.length(); i++)
        {
            if (str.charAt(i) == str.charAt(i - 1))
            {
                count++;
            }
            else
            {
                if (count > maxCount)
                {
                    maxCount = count;
                    maxChar = str.charAt(i - 1);
                }
                count = 1;
            }
        }
        if (count > maxCount) {
            maxCount = count;
            maxChar = str.charAt(str.length() - 1);
        }
        return maxChar;
    }
}












