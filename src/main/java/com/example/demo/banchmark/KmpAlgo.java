package com.example.demo.banchmark;

public class KmpAlgo {

    public static int[] getNext(String pattern) {
        //因为next数组下标是从1开始的，所以数组长度为字符串长度+1
        int[] next = new int[pattern.length() + 1];

        //数组前两个数字有默认值
        //next[0] 默认为0
        next[0] = 0;

        //next[1] 默认为1
        next[1] = 1;

        //从第二个字符开始计算next数组
        for (int i = 2; i <= pattern.length(); i++) {

            //获取模式串前缀子串
            String subStr = pattern.substring(0, i);

            //前缀子串的相同前后缀的最大长度
            int max = 0;

            //求前缀子串的相同前后缀的最大长度
            for (int j = 1; j < i; j++) {

                //获得长度为j的前缀子串的前缀
                String prefix = subStr.substring(0, j);

                //获得长度为j的前缀子串的后缀
                String suffix = subStr.substring(subStr.length() - j);

                //如果前缀子串的前后缀相等（长度为j）
                if (prefix.equals(suffix)) {

                    //更新前缀子串的相同前后缀的最大长度
                    //这个肯定会越来越大的，j会不断++，最后求出最大长度
                    max = j;
                }
            }

            //将求得的模式串的前缀的最大前后缀长度，写入对应下标的next数组
            next[i] = max;
        }

        //返回求得的next数组
        return next;
    }

    /**
     * KMP算法的字符串匹配算法
     *
     * @param content 内容串
     * @param pattern 模式串
     * @return 模式串在内容串的索引，跟String类的indexOf()
     * 方法的返回值一样，如果未找到返回-1
     */
    public static int match(String content, String pattern, int[] next) {
        //如果模式串比内容串还要长
        if (pattern.length() > content.length()) {

            //直接返回-1
            return -1;
        }

        //获取模式串的next数组
//        int[] next = getNext(pattern);

        //内容串ci指针和模式串pi指针向前遍历
        for (int ci = 0; ci < content.length(); ci++) {
            for (int pi = 0; pi < pattern.length(); pi++) {

                //如果内容串已经越界了
                if (ci >= content.length()) {

                    //超出范围还没找到，返回-1
                    return -1;
                }

                //当前内容串的比较字符
                char charC = content.charAt(ci);
                //当前模式串的比较字符
                char charP = pattern.charAt(pi);

                //如果两个字符相等
                if (charC == charP) {

                    //如果匹配到了模式串最后一个字符
                    if (pi == pattern.length() - 1) {

                        //返回索引
                        return ci - pi;

                        //如果还没到模式串最后一个字符
                    } else {

                        //内容串右移一位
                        ci++;
                    }

                    //如果两个字符不相等
                } else {

                    //找到内容串需要右移的位数
                    int contentRightOffset = pi - next[pi];

                    //找到内容串的下一个起始坐标
                    int restart = ci - pi + 1 + contentRightOffset;

                    //注意，这里需要-1，应对for循环里面的自增
                    ci = restart - 1;

                    //跳出当前模式串的for循环，重新匹配模式串
                    break;
                }
            }
        }

        //找完了还没有找到，返回-1
        return -1;
    }

    /**
     * @param
     */
    public static void main(String[] args) {
        String str = "BBABCDABDEYGFABCDABD";
        String pat = "ABCDABD";
        int[] next = KmpAlgo.getNext(pat);
        System.out.println(KmpAlgo.match(str, pat, next));
    }

}
