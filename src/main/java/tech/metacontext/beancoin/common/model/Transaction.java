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

import java.time.LocalDateTime;
import tech.metacontext.beancoin.common.model.abs.Crop;
import tech.metacontext.beancoin.common.model.abs.PriceTable;

/**
 *
 * @author Jonathan Chang, Chun-yien <ccy@musicapoetica.org>
 */
public class Transaction {

    private String id;
    final private String timestamp;
    private Farmer farmer;
    private Crop crop;
    private double cash;
    private double beancoin;

    public Transaction(String id, Farmer farmer, Crop crop) {
        this.id = id;
        this.timestamp = LocalDateTime.now().toString();
        this.farmer = farmer;
        this.crop = crop;
        PriceTable priceTable = farmer.getContract().getPriceTable();
        this.cash = priceTable.getTotalPrice(crop);
        this.beancoin = priceTable.getTotalBeancoin(priceTable.getLevel(crop), farmer);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public Farmer getFarmer() {
        return farmer;
    }

    public void setFarmer(Farmer farmer) {
        this.farmer = farmer;
    }

    public Crop getCrop() {
        return crop;
    }

    public void setCrop(Crop crop) {
        this.crop = crop;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public double getBeancoin() {
        return beancoin;
    }

    public void setBeancoin(double beanCoin) {
        this.beancoin = beanCoin;
    }

    @Override
    public String toString() {
        String result = String.format("%s %s %s Production=%.1f kg, Cash = $%,.0f, Beancoin = %,.0f.",
                this.timestamp, this.id, this.farmer.getId(), this.getCrop().getAmount(), this.getCash(), this.getBeancoin());
        return result;
    }

}
