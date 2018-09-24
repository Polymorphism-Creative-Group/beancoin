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

import static tech.metacontext.beancoin.common.model.Settings.*;
import tech.metacontext.beancoin.common.model.abs.Field;

/**
 *
 * @author Jonathan Chang, Chun-yien <ccy@musicapoetica.org>
 */
public class Field_SoyBean extends Field<Crop_SoyBean> {

    public Field_SoyBean(double size) {
        super(size);
    }

    @Override
    public Crop_SoyBean produce() {
        double amount = randomAmount();
        Crop_SoyBean soyBean = new Crop_SoyBean(amount, 11, 1, 4);
        return soyBean;
    }

    private double randomAmount() {
        double r = field_production_adjust(this.getSize());
        return Math.floor(this.getSize() * (unit_production + unit_production_variation * r));
    }

}
