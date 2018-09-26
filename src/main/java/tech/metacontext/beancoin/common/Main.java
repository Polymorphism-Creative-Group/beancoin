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
package tech.metacontext.beancoin.common;

import java.util.ArrayList;
import java.util.List;
import tech.metacontext.beancoin.common.model.*;
import static tech.metacontext.beancoin.common.Settings.*;
import tech.metacontext.beancoin.common.model.abs.Field;

/**
 *
 * @author Jonathan Chang
 */
public class Main {

   List<Farmer> farmers = new ArrayList<>();

   public Main() {

   }

   public static void main(String[] args) {
      Main main = new Main();
      Contract c = new Contract(PriceTable_SoyBean.getInstance());
      Field field = new Field_SoyBean(10);
      Farmer farmer = new Farmer("random id", c, field);
      main.farmers.add(farmer);
      System.out.println(farmer);
      materials.stream().forEach(System.out::println);
   }
}
