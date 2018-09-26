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

import org.json.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jonathan Chang
 */
public class APIImplTest {

    API instance;

    public APIImplTest() {
        instance = APIImpl.getInstance();
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
     * Test of getInstance method, of class APIImpl.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        assertNotNull(instance);
    }

    @Test
    public void testIdGeneration() {
        System.out.println("idGeneration");
        for (int i = 0; i < 100; i++) {
            System.out.print(APIImpl.idGenerator(2, 4) + " ");
        }
        System.out.println();
    }

    /**
     * Test of createFarmer method, of class APIImpl.
     */
    @Test
    public void testCreateFarmer() {
        System.out.println("createFarmer");
        JSONObject params = new JSONObject().put("size", 10.0);
        double expResult = 10.0;
        JSONObject r = instance.createFarmer(params);
        double result = r.getDouble("size");
        assertEquals(expResult, result, 0.0);
        System.out.println(r.toString());
    }

    /**
     * Test of getFarmer method, of class APIImpl.
     */
    @Test
    public void testGetFarmer() {
        System.out.println("getFarmer");
        JSONObject params = new JSONObject();
        String expResult = instance.createFarmer(params).getString("id");
        JSONObject result = instance.getFarmer(params.put("id", expResult));
        assertEquals(expResult, result.get("id"));
        System.out.println(result.toString(2));
    }

    /**
     * Test of produceCrop method, of class APIImpl.
     */
    @Test
    public void testProduceCrop() {
        System.out.println("produceCrop");
        JSONObject params = null;
        APIImpl instance = new APIImpl();
        JSONObject expResult = null;
        JSONObject result = instance.produceCrop(params);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getManagementInfo method, of class APIImpl.
     */
    @Test
    public void testGetManagementInfo() {
        System.out.println("getManagementInfo");
        JSONObject params = null;
        APIImpl instance = new APIImpl();
        JSONObject expResult = null;
        JSONObject result = instance.getManagementInfo(params);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of manageMaterial method, of class APIImpl.
     */
    @Test
    public void testManageMaterial() {
        System.out.println("manageMaterial");
        JSONObject params = null;
        APIImpl instance = new APIImpl();
        JSONObject expResult = null;
        JSONObject result = instance.manageMaterial(params);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMembers method, of class APIImpl.
     */
    @Test
    public void testGetMembers() {
        System.out.println("getMembers");
        int expResult = 10;
        for (int i = 0; i < expResult; i++) {
            instance.createFarmer(new JSONObject());
        }
        JSONObject result = instance.getMembers(new JSONObject());
        assertEquals(expResult, result.getJSONArray("members").length());
        System.out.println(result.toString(2));
    }

    /**
     * Test of getTransaction method, of class APIImpl.
     */
    @Test
    public void testGetTransaction() {
        System.out.println("getTransaction");
        JSONObject params = null;
        APIImpl instance = new APIImpl();
        JSONObject expResult = null;
        JSONObject result = instance.getTransactions(params);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setBeanCoinRate method, of class APIImpl.
     */
    @Test
    public void testSetBeanCoinRate() {
        System.out.println("setBeanCoinRate");
        double expResult = 1.5;
        JSONObject params = new JSONObject();
        System.out.println(instance.setBeanCoinRatio(params));
        params.put("ratio", expResult);
        JSONObject r = instance.setBeanCoinRatio(params);
        double result = r.getDouble("ratio");
        assertEquals(expResult, result, 0.0);
        System.out.println(r.toString());
    }

}
