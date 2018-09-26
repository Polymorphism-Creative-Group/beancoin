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
package tech.metacontext.beancoin.common.model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static tech.metacontext.beancoin.common.Settings.materials;
import tech.metacontext.beancoin.common.model.abs.Crop;
import tech.metacontext.beancoin.common.model.abs.Field;

/**
 *
 * @author Jonathan Chang
 */
public class Field_SoyBeanTest {

    Field_SoyBean instance = new Field_SoyBean(1.0);
    Farmer farmer;
    double size = 1.0;

    public Field_SoyBeanTest() {
        farmer = new Farmer("TESTID", new Contract(PriceTable_SoyBean.getInstance()), instance);
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of produce method, of class SoyBeanField.
     */
    @Test
    public void testProduce() {
        System.out.println("produce");
        Crop_SoyBean result = instance.produce(farmer);
        assertNull(result);
        farmer.addInventory(materials.get(0), size);
        farmer.addInventory(materials.get(1), size);
        farmer.addInventory(materials.get(2), size);
        result = instance.produce(farmer);
        assertNotNull(result);
        System.out.println("Production = " + result.getAmount() + " kg");
        System.out.println("Moisture = " + result.getMoisture());
        System.out.println("Impurity = " + result.getImpurity());
        System.out.println("Turbidity = " + result.getTurbidity());
    }

    @Test
    public void main() {
        System.out.println("main");
        int n = 10;
        farmer.addInventory(materials.get(0), 9);
        farmer.addInventory(materials.get(1), 9);
        farmer.addInventory(materials.get(2), 9);
        for (int i = 1; i <= n; i++) {
            Field field = new Field_SoyBean(i);
            Crop crop = field.produce(farmer);
            if (crop == null) {
                System.out.printf("size = %d, no production due to the lack of materials.\n", i);
            } else {
                System.out.printf("size = %d, production = %.0f kg (%.1f kg/unit)\n",
                        i, crop.getAmount(), crop.getAmount() / field.getSize());
            }
        }
    }

}
