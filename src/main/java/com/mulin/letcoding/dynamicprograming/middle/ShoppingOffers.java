package com.mulin.letcoding.dynamicprograming.middle;

import java.util.*;

/**
 * Created by
 *
 * @author mulin
 * @date 2018/9/20
 * @description:

在LeetCode商店中， 有许多在售的物品。

然而，也有一些大礼包，每个大礼包以优惠的价格捆绑销售一组物品。

现给定每个物品的价格，每个大礼包包含物品的清单，以及待购物品清单。请输出确切完成待购清单的最低花费。

每个大礼包的由一个数组中的一组数据描述，最后一个数字代表大礼包的价格，其他数字分别表示内含的其他种类物品的数量。

任意大礼包可无限次购买。

示例 1:

输入: [2,5], [[3,0,5],[1,2,10]], [3,2]
输出: 14
解释:
有A和B两种物品，价格分别为¥2和¥5。
大礼包1，你可以以¥5的价格购买3A和0B。
大礼包2， 你可以以¥10的价格购买1A和2B。
你需要购买3个A和2个B， 所以你付了¥10购买了1A和2B（大礼包2），以及¥4购买2A。
示例 2:

输入: [2,3,4], [[1,1,0,4],[2,2,1,9]], [1,2,1]
输出: 11
解释:
A，B，C的价格分别为¥2，¥3，¥4.
你可以用¥4购买1A和2B，也可以用¥9购买2A，2B和1C。
你需要买1A，2B和1C，所以你付了¥4买了1A和1B（大礼包1），以及¥3购买1B， ¥4购买1C。
你不可以购买超出待购清单的物品，尽管购买大礼包2更加便宜。
说明:

最多6种物品， 100种大礼包。
每种物品，你最多只需要购买6个。
你不可以购买超出待购清单的物品，即使更便宜。
 */
public class ShoppingOffers {

    Map<List<Integer>, Integer> map = new HashMap<>();

    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {

        int zeros = 0;
        for (Integer need : needs) {
            if (need==0) {
                zeros++;
            }
        }

        if (needs.size() == zeros) {
            return 0;
        }

        for (int i = 0; i < price.size(); i++) {
            List<Integer> ns = new ArrayList<>(price.size() + 1);

            for (int j = 0; j < price.size() + 1; j++) {
                ns.add(0);
            }
            ns.set(i, 1);
            ns.set(price.size(), price.get(i));

            special.add(ns);
        }

        for (List<Integer> spec : special) {
            List<Integer> list = new ArrayList<>();

            int sum = 0;

            for (int i = 0; i < spec.size() - 1; i++) {
                list.add(spec.get(i));
                sum+= spec.get(i) * price.get(i);
            }

            if (spec.get(spec.size()-1) <= sum) {
                Integer v = map.get(list);
                if (null != v) {
                    v = Math.min(spec.get(spec.size()-1), v);
                } else {
                    v = spec.get(spec.size()-1);
                }

                map.put(list, v);
            }
        }

        Stack<List<Integer>> needsStack = new Stack<>();
        needsStack.push(needs);

        return dfs(special, null, needsStack);
    }

    public Integer dfs(List<List<Integer>> special, List<Integer> curSpec, Stack<List<Integer>> needsStack) {
        int cost = 0;

        List<Integer> needs = needsStack.peek();


//        int state = state(needs);
//
//        if (state == 0) {
//            return cost;
//        } else if (state == 1) {
//            return Integer.MAX_VALUE;
//
//        } else {
            int min = Integer.MAX_VALUE;

            int zero = 0;
            if (null == curSpec) {
                needsStack.push(needs);
            } else {
                List<Integer> newNeeds = new ArrayList<>(needs.size());
                for (int i = 0; i < needs.size(); i++) {
                    Integer v = needs.get(i) - curSpec.get(i);
                    if (v < 0) {
                        return Integer.MAX_VALUE;
                    } else if (v == 0) {
                        zero++;
                    }
                    newNeeds.add(v);
                }

                if (zero == needs.size()) {
                    return cost;
                }

                needs = newNeeds;

                cost+=curSpec.get(curSpec.size()-1);
                needsStack.push(needs);
            }

            for (List<Integer> spec : special) {

                Integer result = map.get(needs);
                if (null == result) {
                    result = dfs(special, spec, needsStack);
                }

                min = min > result ? result : min;
            }

            if (min==Integer.MAX_VALUE) {
                cost=min;
            } else {
                cost+=min;
            }

            map.put(needs, cost);

            needsStack.pop();

            return cost;
//        }
    }


    public static void main(String[] args) {
        ShoppingOffers sp = new ShoppingOffers();


        List<Integer> price = new ArrayList<Integer>(6) {{
            add(4);
            add(10);
            add(1);
            add(5);
            add(5);
            add(3);
        }};

        List<List<Integer>> special = new ArrayList<List<Integer>>(3) {{
            add(new ArrayList<Integer>(7) {{ add(1); add(2); add(3); add(3); add(4); add(1); add(8);}});
            add(new ArrayList<Integer>(7) {{ add(3); add(4); add(5); add(5); add(5); add(2); add(14);}});
            add(new ArrayList<Integer>(7) {{ add(2); add(4); add(5); add(1); add(1); add(3); add(22);}});
        }};

        List<Integer> needs = new ArrayList<Integer>(){{
            add(1);
            add(6);
            add(5);
            add(1);
            add(1);
            add(4);
        }};

//        List<Integer> price = new ArrayList<Integer>(3) {{
//            add(0);
//            add(0);
//            add(0);
//        }};
//
//        List<List<Integer>> special = new ArrayList<List<Integer>>(2) {{
//            add(new ArrayList<Integer>(4) {{ add(1); add(1); add(0); add(4);}});
//            add(new ArrayList<Integer>(4) {{ add(2); add(2); add(1); add(9);}});
//        }};
//
//        List<Integer> needs = new ArrayList<Integer>(){{
//            add(2);
//            add(2);
//            add(1);
//        }};
//        List<Integer> price = new ArrayList<Integer>(3) {{
//            add(2);
//            add(5);
//        }};
//
//        List<List<Integer>> special = new ArrayList<List<Integer>>(2) {{
//            add(new ArrayList<Integer>(3) {{ add(3); add(0); add(5);}});
//            add(new ArrayList<Integer>(3) {{ add(1); add(2); add(10);}});
//        }};
//
//        List<Integer> needs = new ArrayList<Integer>(){{
//            add(3);
//            add(2);
//        }};

//        List<Integer> price = new ArrayList<Integer>(3) {{
//            add(2);
//            add(3);
//            add(4);
//        }};
//
//        List<List<Integer>> special = new ArrayList<List<Integer>>(2) {{
//            add(new ArrayList<Integer>(4) {{ add(1); add(1); add(0); add(4); }});
//            add(new ArrayList<Integer>(4) {{ add(2); add(2); add(1); add(9); }});
//        }};
//
//        List<Integer> needs = new ArrayList<Integer>(){{
//            add(1);
//            add(2);
//            add(1);
//        }};

        System.out.println(sp.shoppingOffers(price, special, needs));

//
//        int[] i = new int[1000];
//
//        for (Map.Entry<List<Integer>, Integer> entry : sp.map.entrySet()) {
//
//            List<Integer> l = entry.getKey();
//            Integer v = entry.getValue();
//
//            int index = 0;
//            int mul = 100;
//
//            for (Integer in : l) {
//                index += (in * mul);
//                mul /= 10;
//            }
//
//            if (i[index] == 0) {
//                i[index] = v;
//            } else {
//                System.out.println("conflict");
//            }
//        }

    }

    public int shoppingOffersAns(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < special.size(); i++) {
            boolean isValid = true;
            List<Integer> offer = special.get(i);
            for (int j = 0; j < needs.size(); j++) {
                int remain = needs.get(j) - offer.get(j);
                if (remain < 0) {
                    isValid = false;
                }
                needs.set(j, remain);
            }
            if (isValid) {
                res = Math.min(res, shoppingOffers(price, special, needs) + offer.get(needs.size()));
            }
            for (int j = 0; j < needs.size(); j++) {
                needs.set(j, needs.get(j) + offer.get(j));
            }
        }
        int nonOffer = 0;
        for (int i = 0; i < needs.size(); i++) {
            nonOffer += needs.get(i) * price.get(i);
        }
        return Math.min(nonOffer, res);
    }
}
