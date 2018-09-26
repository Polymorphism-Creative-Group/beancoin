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

import tech.metacontext.beancoin.common.model.Farmer;

/**
 *
 * @author Jonathan Chang, Chun-yien <ccy@musicapoetica.org>
 * @param <C>
 */
public abstract class PriceTable<C extends Crop> {

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

    public abstract int getLevel(C crop);

    public abstract String getLevelLabel(int level);

    public double getUnitPrice(int level, double param) {
        return prices[level] + adjust(param);
    }

    public abstract double getTotalPrice(C crop);

    public abstract double getTotalBeancoin(int level, Farmer farmer);

    public abstract double adjust(double params);

}
