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

import tech.metacontext.beancoin.common.model.abs.Field;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jonathan Chang, Chun-yien <ccy@musicapoetica.org>
 */
public class FieldTest {

    Field<Crop_SoyBean> instance;
    double size = 1.0;

    public FieldTest() {
        this.instance = new Field<Crop_SoyBean>(size) {
            @Override
            public Crop_SoyBean produce(Farmer farmer) {
                double amount = this.getSize() * 3000;
                Crop_SoyBean soyBean = new Crop_SoyBean(amount, 11, 1, 4);
                return soyBean;
            }
        };
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
     * Test of getSize method, of class Field.
     */
    @Test
    public void testGetSize() {
        System.out.println("getSize");
        double expResult = size;
        double result = instance.getSize();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setSize method, of class Field.
     */
    @Test
    public void testSetSize() {
        System.out.println("setSize");
        instance.setSize(size);
        double result = instance.getSize();
        assertEquals(size, result, 0.0);
    }

    /**
     * Test of produce method, of class Field.
     */
    @Test
    public void testProduce() {
        System.out.println("produce");
        Farmer farmer = new Farmer("TESTID", new Contract(PriceTable_SoyBean.getInstance()), instance);
        Crop_SoyBean result = instance.produce(farmer);
        assertNotNull(result);
        System.out.println("Production = " + result.getAmount() + " kg");
        System.out.println("Moisture = " + result.getMoisture());
        System.out.println("Impurity = " + result.getImpurity());
        System.out.println("Turbidity = " + result.getTurbidity());
    }

}
