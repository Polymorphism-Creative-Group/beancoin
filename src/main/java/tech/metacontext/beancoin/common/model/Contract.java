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

import tech.metacontext.beancoin.common.model.abs.Crop;
import tech.metacontext.beancoin.common.model.abs.PriceTable;

/**
 *
 * @author Jonathan Chang, Chun-yien <ccy@musicapoetica.org>
 * @param <C>
 */
public class Contract<C extends Crop> {

    private PriceTable<C> priceTable;

    public Contract(PriceTable<C> priceTable) {
        this.priceTable = priceTable;
    }

    public PriceTable<C> getPriceTable() {
        return priceTable;
    }

    public void setPriceTable(PriceTable<C> priceTable) {
        this.priceTable = priceTable;
    }

}
