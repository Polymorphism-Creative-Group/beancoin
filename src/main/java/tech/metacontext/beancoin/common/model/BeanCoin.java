/*
 * Copyright 2018 Jonathan Chang.
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
package tech.metacontext.beancoin.common.model;

import tech.metacontext.beancoin.common.model.abs.Currency;

/**
 *
 * @author Jonathan Chang
 */
public class BeanCoin extends Currency {

    private static double ratio = 1.2;

    public BeanCoin(double cash) {
        super(cash / ratio);
    }

    public double getCash() {
        return beanCoinToCash(amount);
    }

    public double addCash(double cash) {
        this.amount += cashToBeanCoin(cash);
        return this.amount;
    }

    public double addAmount(double amount) {
        this.amount += amount;
        return this.amount;
    }

    public double spend(double amountSpent) {
        double result = this.amount - amountSpent;
        this.setAmount((result < 0) ? 0 : result);
        return result;
    }

    public static double getRatio() {
        return ratio;
    }

    public static void setRatio(double ratio) {
        BeanCoin.ratio = ratio;
    }

    public static double cashToBeanCoin(double cash) {
        return cash / ratio;
    }

    public static double beanCoinToCash(double beanCoin) {
        return beanCoin * ratio;
    }

}
