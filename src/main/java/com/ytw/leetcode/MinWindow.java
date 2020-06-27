package com.ytw.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * *@Description [描述类的功能]
 * *@Author YTW
 * *@Date 2020-05-27 23:30
 * *@Version V1.0
 **/
public class MinWindow {
    public static void main(String[] args) {
        MinWindow minWindow = new MinWindow();
        String result = minWindow.minWindow("ADAOBECODEBANC", "ABC");
        System.out.println(result);
    }

    public String minWindow(String s, String t) {
        if (s == null || "".equals(s) || t == null || "".equals(t)) {
            return "";
        }

        char[] chars = s.toCharArray();
        // 按题目要求，还不能去重，因此使用list保存
        List<Character> lists = getOriginSet(t);
        List<Character> originLists = getOriginSet(t);
        int tSize = lists.size();
        // 符合要求的子串的左指针
        int left = 0;
        // 符合要求的子串的右指针
        int right = 0;
        // 移动中的左指针
        int leftTmp = 0;

        int leftTmp2 = s.length();
        int minLength = Integer.MAX_VALUE;
        int count = 0;
        for (int i = 0, size = chars.length ; i < size ; i++) {
            if (originLists.contains(chars[i])) {
                if (tSize == lists.size()) {
                    // 首次遇到t中字符，则设置左指针
                    leftTmp = i;
                } else if (count == 1) {
                    // 记录左指针，用于第二个符合要求的子串遍历使用
                    leftTmp2 = i;
                }
                count++;
                if (lists.contains(chars[i])) {
                    lists.remove(lists.indexOf(chars[i]));
                }
            }
            if (lists.isEmpty()) {
                // 表示已经得到一个子串
                if (minLength > i - leftTmp + 1) {
                    minLength = i - leftTmp + 1;
                    left = leftTmp;
                    right = i + 1;
                }
                if (leftTmp2 + tSize > size) {
                    // 剩下的数量已经不足t的长度，直接退出
                    break;
                }
                // 重置set
                lists = getOriginSet(t);
                // 重置游标
                i = leftTmp2 - 1;
                count = 0;
            }
        }

        return s.substring(left, right);
    }

    private List<Character> getOriginSet(String t) {
        char[] chars = t.toCharArray();
        List<Character> sets = new ArrayList<Character>();
        for (char c : chars) {
            sets.add(c);
        }
        return sets;
    }
}
