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
package tech.metacontext.beancoin.common.intf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import org.json.JSONArray;
import org.json.JSONObject;
import static tech.metacontext.beancoin.common.Settings.*;
import tech.metacontext.beancoin.common.model.*;
import tech.metacontext.beancoin.common.model.abs.*;

/**
 *
 * @author Jonathan Chang
 */
public class APIImpl implements API {

   private static API instance;
   private static Map<String, Farmer> farmers;
   private static List<Transaction> transactions;
   private static final Contract contract = new Contract(PriceTable_SoyBean.getInstance());
   private static final Random r = new Random();

   public APIImpl() {
      farmers = new HashMap<>();
      transactions = new ArrayList<>();
   }

   public static API getInstance() {
      if (instance == null) {
         instance = new APIImpl();
      }
      return instance;
   }

   /**
    * Create a new Farmer.
    *
    * @param params size(Optional).
    * @return id, size;
    */
   @Override
   public JSONObject createFarmer(JSONObject params) {
      JSONObject retVal = new JSONObject();
      try {
         double size;
         if (params.has("size")) {
            size = params.getDouble("size");
         } else {
            size = (r.nextInt(100) + 1) / 10.0;
         }
         String id;
         do {
            id = idGenerator(2, 4);
         } while (farmers.containsKey(id));
         Field field = new Field_SoyBean(size);
         Farmer farmer = new Farmer(id, contract, field);
         farmers.put(id, farmer);
         retVal.put("id", id);
         retVal.put("size", size);
      } catch (Exception ex) {
         logger.log(Level.SEVERE, "Exception", ex);
         return retVal.put("error", ex.getClass().getSimpleName())
                 .put("message", ex.getMessage());
      }
      retVal.put("message", "API: createFarmer successful.");
      return retVal;
   }

   public static String idGenerator(int num_alphabet, int num_num) {
      return randomChars(num_alphabet) + randomNums(num_num);
   }

   private static String randomChars(int num) {
      String result = "";
      for (int i = 0; i < num; i++) {
         char c = 'O';
         while (c == 'O' || c == 'I') {
            c = (char) (r.nextInt(26) + 'A');
         }
         result += c;
      }
      return result;
   }

   private static String randomNums(int num) {
      String result = "";
      for (int i = 0; i < num; i++) {
         result += (char) (r.nextInt(10) + '0');
      }
      return result;
   }

   /**
    * Get a Farmer with id.
    *
    * @param params id.
    * @return id, size, cash, beancoin.
    */
   @Override
   public JSONObject getFarmer(JSONObject params) {
      JSONObject retVal = new JSONObject();
      try {
         String id;
         if (params.has("id")) {
            id = params.getString("id");
         } else {
            throw new Exception("Bad params: id.");
         }
         if (!farmers.containsKey(id)) {
            throw new Exception("Farmer id not found.");
         }
         Farmer f = farmers.get(id);
         retVal.put("id", id)
                 .put("size", f.getField().getSize())
                 .put("cash", f.getCash())
                 .put("beancoin", f.getBeanCoin().getAmount());
      } catch (Exception ex) {
         logger.log(Level.SEVERE, "Exception", ex);
         return retVal.put("error", ex.getClass().getSimpleName())
                 .put("message", ex.getMessage());
      }
      retVal.put("message", "API: getFarmer successful.");
      return retVal;
   }

   /**
    * Produce Crop for a specific Farmer.
    *
    * @param params
    * @return
    */
   @Override
   public JSONObject produceCrop(JSONObject params) {
      JSONObject retVal = new JSONObject();
      try {
         if (params.has("id")) {
            String farmer_id = params.getString("id");
            Farmer<Crop_SoyBean> farmer = farmers.get(farmer_id);
            Crop_SoyBean crop = farmer.getField().produce(farmer);
            if (crop == null) {
               throw new Exception("API: Not enough funds.");
            }
            Transaction transaction = new Transaction(idGenerator(3, 7), farmer, crop);
            transactions.add(transaction);
            retVal.put("farmer_id", farmer_id)
                    .put("transaction_id", transaction.getId())
                    .put("crop_amount", crop.getAmount())
                    .put("crop_level", farmer.getContract().getPriceTable().getLevel(crop))
                    .put("cash_earned", transaction.getCash())
                    .put("beancoin_earned", transaction.getBeancoin());
         }
      } catch (Exception ex) {
         logger.log(Level.SEVERE, "Exception", ex);
         return retVal.put("error", ex.getClass().getSimpleName())
                 .put("message", ex.getMessage());
      }
      retVal.put("message", "API: produceCrop successful.");
      return retVal;
   }

   @Override
   public JSONObject getManagementInfo(JSONObject params) {
      JSONObject retVal = new JSONObject();
      try {
      } catch (Exception ex) {
         logger.log(Level.SEVERE, "Exception", ex);
         return retVal.put("error", ex.getClass().getSimpleName())
                 .put("message", ex.getMessage());
      }
      retVal.put("message", "API: getManagementInfo successful.");
      return retVal;
   }

   @Override
   public JSONObject manageMaterial(JSONObject params) {
      JSONObject retVal = new JSONObject();
      try {
         if (params.has("id") && params.has("material_id") && params.has("amount")) {
            String id = params.getString("id");
            int material_id = params.getInt("material_id");
            double amount = params.getDouble("amount");
            Farmer farmer = farmers.get(id);
            Material material = materials.get(material_id);
            if (farmer == null || material == null) {
               throw new Exception("Farmer id or Material id not exists.");
            }
            retVal.put("farmer_id", id)
                    .put("material", material.getName())
                    .put("amount", amount)
                    .put("unit_price", material.getPrice());
            JSONObject retBuyMaterial = farmer.buyMaterial(material, amount);
            if (retBuyMaterial == null) {
               throw new Exception("API: not enough funds.");
            }
            retVal.put("beancoin_spent", retBuyMaterial.getDouble("beancoin_spent"))
                    .put("cash_spent", retBuyMaterial.getDouble("cash_spent"));
         } else {
            throw new Exception("API: params required.");
         }
      } catch (Exception ex) {
         logger.log(Level.SEVERE, "Exception", ex);
         return retVal.put("error", ex.getClass().getSimpleName())
                 .put("message", ex.getMessage());
      }
      retVal.put("message", "API: manageMaterial successful.");
      return retVal;
   }

   @Override
   public JSONObject equipIoT(JSONObject params) {
      JSONObject retVal = new JSONObject();
      try {
      } catch (Exception ex) {
         logger.log(Level.SEVERE, "Exception", ex);
         return retVal.put("error", ex.getClass().getSimpleName())
                 .put("message", ex.getMessage());
      }
      retVal.put("message", "API: equipIoT successful.");
      return retVal;
   }

   @Override
   public JSONObject getMembers(JSONObject params) {
      JSONObject retVal = new JSONObject();
      try {
         JSONArray array = new JSONArray();
         farmers.forEach((String id, Farmer f) -> {
            JSONObject member = new JSONObject()
                    .put("id", id)
                    .put("size", f.getField().getSize())
                    .put("cash", f.getCash())
                    .put("beancoin", f.getBeanCoin().getAmount())
                    .put("equippedIoT", f.isEquippedIoT());
            array.put(member);
         });
         retVal.put("members", array);
      } catch (Exception ex) {
         logger.log(Level.SEVERE, "Exception", ex);
         return retVal.put("error", ex.getClass().getSimpleName())
                 .put("message", ex.getMessage());
      }
      retVal.put("message", "API: getMembers successful.");
      return retVal;
   }

   @Override
   public JSONObject getTransactions(JSONObject params) {
      JSONObject retVal = new JSONObject();
      try {
         JSONArray array = new JSONArray();
         transactions.forEach((Transaction t) -> {
            JSONObject transaction = new JSONObject()
                    .put("timestamp", t.getTimestamp())
                    .put("transaction_id", t.getId())
                    .put("farmer_id", t.getFarmer().getId())
                    .put("production", t.getCrop().getAmount())
                    .put("cash", t.getCash())
                    .put("beancoin", t.getBeancoin());
            array.put(transaction);
         });
         retVal.put("transactions", array);
         retVal.put("message", "API: getTransactions successful.");
      } catch (Exception ex) {
         logger.log(Level.SEVERE, "Exception", ex);
         return retVal.put("error", ex.getClass().getSimpleName())
                 .put("message", ex.getMessage());
      }
      return retVal;
   }

   @Override
   public JSONObject setBeanCoinRatio(JSONObject params) {
      JSONObject retVal = new JSONObject();
      try {
         double ratio;
         if (params.has("ratio")) {
            ratio = params.getDouble("ratio");
            BeanCoin.setRatio(ratio);
            retVal.put("message", "API: setBeanCoinRatio successful.");
         } else {
            ratio = BeanCoin.getRatio();
            retVal.put("message", "API: get current BeanCoinRatio successful.");
         }
         retVal.put("ratio", ratio);
      } catch (Exception ex) {
         logger.log(Level.SEVERE, "Exception", ex);
         return retVal.put("error", ex.getClass().getSimpleName())
                 .put("message", ex.getMessage());
      }
      return retVal;
   }

}
