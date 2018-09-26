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

/**
 *
 * @author Jonathan Chang
 */
public class TransactionTest {

    Transaction instance;
    Farmer farmer;
    Field_SoyBean field;

    public TransactionTest() {
        field = new Field_SoyBean(1.0);
        farmer = new Farmer("TESTFARMERID", new Contract(PriceTable_SoyBean.getInstance()), field);
        farmer.addInventory(materials.get(0), 1.0);
        farmer.addInventory(materials.get(1), 1.0);
        farmer.addInventory(materials.get(2), 1.0);
        Crop_SoyBean crop = field.produce(farmer);
        instance = new Transaction("TESTTRANSID", farmer, crop);
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

    @Test
    public void test() {
        for (int i = 0; i < 50; i++) {
            farmer.addInventory(materials.get(0), 1.0);
            farmer.addInventory(materials.get(1), 1.0);
            farmer.addInventory(materials.get(2), 1.0);
            Crop_SoyBean crop = field.produce(farmer);
            Transaction t = new Transaction("" + i, farmer, crop);
            System.out.println("Level = " + PriceTable_SoyBean.getInstance().getLevel(crop));
            System.out.println(t);
        }
    }

    /**
     * Test of getId method, of class Transaction.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        String expResult = "TESTTRANSID";
        String result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class Transaction.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        String id = "ABC";
        instance.setId(id);
        assertEquals(id, instance.getId());
    }

    /**
     * Test of getTimestamp method, of class Transaction.
     */
    @Test
    public void testGetTimestamp() {
        System.out.println("getTimestamp");
        String result = instance.getTimestamp();
        assertNotNull(result);
        System.out.println(result);
    }

    /**
     * Test of getFarmer method, of class Transaction.
     */
    @Test
    public void testGetFarmer() {
        System.out.println("getFarmer");
        Farmer result = instance.getFarmer();
        assertEquals("TESTFARMERID", result.getId());
    }

    /**
     * Test of getCrop method, of class Transaction.
     */
    @Test
    public void testGetCrop() {
        System.out.println("getCrop");
        Crop result = instance.getCrop();
        assertNotNull(result);
    }

    /**
     * Test of getCash method, of class Transaction.
     */
    @Test
    public void testGetCash() {
        System.out.println("getCash");
        double result = instance.getCash();
        assertTrue(result > 0.0);
        System.out.println("Cash = " + result);
    }

    /**
     * Test of getBeanCoin method, of class Transaction.
     */
    @Test
    public void testGetBeancoin() {
        System.out.println("getBeanCoin");
        double result = instance.getBeancoin();
        assertTrue(result > 0.0);
        System.out.println("Beancoin = " + result);
    }

}
