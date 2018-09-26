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

import tech.metacontext.beancoin.common.model.BeanCoin;
import tech.metacontext.beancoin.common.model.Contract;

/**
 *
 * @author Jonathan Chang, Chun-yien <ccy@musicapoetica.org>
 */
public class Member {

    protected String id;
    protected double cash;
    protected BeanCoin beanCoin;

    public Member(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public double spendCash(double cashSpent) {
        double result = this.cash - cashSpent;
        this.cash = (result < 0.0) ? 0.0 : result;
        return result;
    }

    public BeanCoin getBeanCoin() {
        return beanCoin;
    }

    public void setBeanCoin(BeanCoin beanCoin) {
        this.beanCoin = beanCoin;
    }

}
