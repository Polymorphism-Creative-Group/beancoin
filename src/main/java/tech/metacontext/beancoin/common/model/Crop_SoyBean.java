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

/**
 *
 * @author Jonathan Chang, Chun-yien <ccy@musicapoetica.org>
 */
public class Crop_SoyBean extends Crop {

    private double moisture;
    private double impurity;
    private double turbidity;

    public Crop_SoyBean(double amount, double moisture, double impurity, double turbidity) {
        super(amount);
        this.moisture = moisture;
        this.impurity = impurity;
        this.turbidity = turbidity;
    }

    public double getImpurity() {
        return impurity;
    }

    public void setImpurity(double impurity) {
        this.impurity = impurity;
    }

    public double getMoisture() {
        return moisture;
    }

    public void setMoisture(double moisture) {
        this.moisture = moisture;
    }

    public double getTurbidity() {
        return turbidity;
    }

    public void setTurbidity(double turbidity) {
        this.turbidity = turbidity;
    }

}
