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

import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import static tech.metacontext.beancoin.common.Settings.*;
import tech.metacontext.beancoin.common.model.abs.Field;
import tech.metacontext.beancoin.common.model.abs.Crop;
import tech.metacontext.beancoin.common.model.abs.Member;

/**
 *
 * @author Jonathan Chang, Chun-yien <ccy@musicapoetica.org>
 * @param <C>
 */
public class Farmer<C extends Crop> extends Member {

   protected Contract<C> contract;
   private Field<C> field;
   private boolean equippedIoT;
   private Map<Material, Double> inventory;

   public Farmer(String id, Contract<C> contract, Field<C> field) {
      super(id);
      this.contract = contract;
      this.field = field;
      this.cash = 60000.0 * field.getSize();
      this.beanCoin = new BeanCoin(0.0);
      this.inventory = new HashMap<>();
      this.inventory.put(materials.get(0), 0.0);
      this.inventory.put(materials.get(1), 0.0);
      this.inventory.put(materials.get(2), 0.0);
   }

   public Contract getContract() {
      return contract;
   }

   public void setContract(Contract contract) {
      this.contract = contract;
   }

   public Field<C> getField() {
      return field;
   }

   public void setField(Field<C> field) {
      this.field = field;
   }

   public boolean isEquippedIoT() {
      return equippedIoT;
   }

   public void setEquippedIoT(boolean equippedIoT) {
      this.equippedIoT = equippedIoT;
   }

   @Override
   public String toString() {
      String result
              = "Farmer id = " + this.id + "\n"
              + "size = " + this.getField().getSize() + "\n"
              + "cash = " + this.getCash() + "\n"
              + "bean coin = " + this.getBeanCoin().getAmount();
      return result;
   }

   public Map<Material, Double> getInventory() {
      return inventory;
   }

   public void setInventory(Map<Material, Double> inventory) {
      this.inventory = inventory;
   }

   public void addInventory(Material m, double amount) {
      this.inventory.putIfAbsent(m, 0.0);
      this.inventory.put(m, this.inventory.get(m) + amount);
      System.out.println("Add " + amount + " of " + m.getName() + " to Farmer " + id);
   }

   public JSONObject buyMaterial(Material m, double amount) {
      double price = m.getPrice() * amount;
      double totalAsset = this.cash + this.beanCoin.getCash();
      if (totalAsset < price) {
         return null;
      }
      double beancoinSpent = BeanCoin.cashToBeanCoin(price);
      double cashSpent = 0;
      double s = this.beanCoin.spend(beancoinSpent);
      if (s < 0) {
         beancoinSpent += s;
         cashSpent = BeanCoin.beanCoinToCash(-s);
         this.spendCash(cashSpent);
      }
      addInventory(m, amount);
      return new JSONObject()
              .put("beancoin_spent", beancoinSpent)
              .put("cash_spent", cashSpent);
   }
}
