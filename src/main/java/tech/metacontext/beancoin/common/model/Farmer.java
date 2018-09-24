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
package tech.metacontext.beancoin.common.model;

import tech.metacontext.beancoin.common.model.abs.Field;
import tech.metacontext.beancoin.common.model.abs.Crop;
import tech.metacontext.beancoin.common.model.abs.Member;

/**
 *
 * @author Jonathan Chang, Chun-yien <ccy@musicapoetica.org>
 * @param <C>
 */
public class Farmer<C extends Crop> extends Member {

    private Field<C> field;
    private double cash;
    private double beanCoin;

    public Farmer(String id, Contract contract, Field<C> field) {
        super(id, contract);
        this.field = field;
        this.cash = 60000.0 * field.getSize();
        this.beanCoin = 60000.0 * field.getSize();
    }

    public Field<C> getField() {
        return field;
    }

    public void setField(Field<C> field) {
        this.field = field;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public double getBeanCoin() {
        return beanCoin;
    }

    public void setBeanCoin(double beanCoin) {
        this.beanCoin = beanCoin;
    }

}
