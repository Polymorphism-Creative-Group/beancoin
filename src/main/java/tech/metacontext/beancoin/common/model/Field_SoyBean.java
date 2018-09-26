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

import static tech.metacontext.beancoin.common.Settings.*;
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
    public Crop_SoyBean produce(Farmer farmer) {
        if (!checkMaterial(farmer)) {
            System.out.println("** Insufficient materials **");
            return null;
        }
        farmer.addInventory(materials.get(0), -this.getSize());
        farmer.addInventory(materials.get(1), -this.getSize());
        farmer.addInventory(materials.get(2), -this.getSize());

        double totalFarmingFee = unitFarmingFee * farmer.getField().getSize();
        double s = farmer.getBeanCoin().spend(BeanCoin.cashToBeanCoin(totalFarmingFee));
        if (s < 0) {
            farmer.spendCash(BeanCoin.beanCoinToCash(-s));
        }

        double amount = randomAmount();
        double moisture = randomMoisture();
        double impurity = randomImpurity();
        double turbidity = randomTurbidity();

        Crop_SoyBean soyBean = new Crop_SoyBean(amount, moisture, impurity, turbidity);
        return soyBean;
    }

    private boolean checkMaterial(Farmer farmer) {
        return ((double) (farmer.getInventory().get(materials.get(0))) >= this.getSize()
                && (double) (farmer.getInventory().get(materials.get(1))) >= this.getSize()
                && (double) (farmer.getInventory().get(materials.get(2))) >= this.getSize()
                && farmer.getCash() + farmer.getBeanCoin().getCash() >= unitFarmingFee);
    }

    private double randomAmount() {
        double r = field_production_adjust(this.getSize());
        return Math.floor(this.getSize() * (unit_production + unit_production_variation * r));
    }

    private static double randomMoisture() {
        return random.nextInt(25 - 10) + 11;
    }

    private static double randomImpurity() {
        return 0.5 * random.nextInt(7);
    }

    private static double randomTurbidity() {
        return random.nextInt(6) + 1;
    }

}
