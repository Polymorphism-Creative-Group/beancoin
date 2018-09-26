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
public class PriceTableSoyBeanTest {

    PriceTable_SoyBean instance = PriceTable_SoyBean.getInstance();
//    PrintStream out;

    public PriceTableSoyBeanTest() {
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
     * Test of getLevel method, of class PriceTableSoyBean.
     */
    @Test
    public void testGetLevel() {
        System.out.println("getLevel");
        double[][] params = {
            {0, 11},
            {0.5, 15},
            {1.5, 18},
            {3.5, 25}
        };
        int expResult[] = {0, 1, 2, 3};
        for (int i = 0; i < expResult.length; i++) {
            double[] param = params[i];
            int result = instance.getLevel(param);
            assertEquals(expResult[i], result);
            System.out.printf("Test params = %.1f, %.1f, level = %s, price = %.0f\n",
                    param[0], param[1],
                    instance.getLevelLabel(result), instance.getPrice(result, 1));
        }
    }

    /**
     * Test of adjust method, of class PriceTableSoyBean.
     */
    @Test
    public void testAdjust() {
        System.out.println("adjust");
        int param;
        double expResult;
        param = 4;
        expResult = -0.0;
        assertEquals(expResult, instance.adjust(param), 0.0);
        param = 5;
        expResult = -5.0;
        assertEquals(expResult, instance.adjust(param), 0.0);
    }

}
