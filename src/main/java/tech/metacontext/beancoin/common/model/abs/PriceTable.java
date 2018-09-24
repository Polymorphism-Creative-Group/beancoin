/*
 * Copyright 2018 Jonathan Chang, Chun-yien <ccy@musicapoetica.org>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package tech.metacontext.beancoin.common.model.abs;

/**
 *
 * @author Jonathan Chang, Chun-yien <ccy@musicapoetica.org>
 */
public abstract class PriceTable {

    protected int levelNum;  // Good: 0, 1, 2, 3... : Bad
    protected double[] prices;

    public PriceTable(int levelNum, double... prices) {
        this.levelNum = levelNum;
        this.prices = new double[levelNum];
        for (int i = 0; i < levelNum; i++) {
            this.prices[i] = prices[i];
            System.out.println("Level " + i + ", price set to " + this.prices[i]);
        }
    }

    public abstract int getLevel(double... params);

    public abstract String getLevelLabel(int level);

    public double getPrice(int level, int param) {
        return prices[level] + adjust(param);
    }

    public abstract double getBeanCoin(int level, int param);

    public abstract double adjust(int params);

}
