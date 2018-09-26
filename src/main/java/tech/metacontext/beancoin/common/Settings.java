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
package tech.metacontext.beancoin.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
import tech.metacontext.beancoin.common.model.Material;

/**
 *
 * @author Jonathan Chang, Chun-yien <ccy@musicapoetica.org>
 */
public class Settings {

   public static class PROJECT {

      public static final String TITLE = "BeanCoin Prototype";
      public static final String ORG = "亞太智農聯盟";
   }

   public static final Logger logger = Logger.getLogger("Bean Coin Commons");
   public static final Random random = new Random();

   public static final double unit_production = 3000.0;
   public static final double unit_production_variation = 300.0;
   public static final double[] default_prices = {47.0, 42.0, 39.0, 35.0};
   public static final int[][] default_levels = {
      {0, 0, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3},
      {0, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3},
      {1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3},
      {1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3},
      {1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3},
      {1, 1, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3},
      {1, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3},
      {2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3}
   };
   public static final String[] default_level_label = {
      "頂級", "特級", "優級", "良級"
   };

   public static final List<Material> materials
           = new ArrayList<>(Arrays.asList(
                   new Material[]{
                      new Material("肥料-台肥一號", 4000.0),
                      new Material("種子", 4800.0),
                      new Material("生物防治", 15000.0),
                      new Material("古特菌", 1000.0),
                      new Material("生物鈣", 500.0)
                   }));
   // 代耕費
   public static final double unitFarmingFee = 36200.0;

   public static final double cashPerUnit = 60000.0;

   public static double field_production_adjust(double size) {
      return 1.0 - random.nextDouble() * size / 2.5;
   }
}
