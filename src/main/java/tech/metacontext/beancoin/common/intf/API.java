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

/**
 *
 * @author Jonathan Chang
 */
public interface API {

    public JSONObject createFarmer(JSONObject params);

    public JSONObject getFarmer(JSONObject params);
    
    public JSONObject produceCrop(JSONObject params);
    
    public JSONObject getManagementInfo(JSONObject params);
    
    public JSONObject manageMaterial(JSONObject params);
    
    public JSONObject getTransaction(JSONObject params);
    
    public JSONObject setBeanCoinRate(JSONObject params);
    
}
