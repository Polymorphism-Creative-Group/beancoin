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

import static tech.metacontext.beancoin.common.model.Settings.*;
import tech.metacontext.beancoin.common.model.abs.PriceTable;

/**
 *
 * @author Jonathan Chang, Chun-yien <ccy@musicapoetica.org>
 */
public class PriceTable_SoyBean extends PriceTable {

    private static PriceTable_SoyBean instance;

    private PriceTable_SoyBean(double... prices) {
        super(4, prices);
    }

    public static PriceTable_SoyBean getInstance(double... prices) {
        instance = new PriceTable_SoyBean(prices);
        return getInstance();
    }

    public static PriceTable_SoyBean getInstance() {
        if (instance == null) {
            instance = new PriceTable_SoyBean(default_prices);
        }
        return instance;
    }

    @Override
    public String getLevelLabel(int level) {
        return default_level_label[level];
    }

    @Override
    public int getLevel(double... params) {
        if (params[0] < 0 || params[0] > 3.5) {
            System.out.println("夾雜率超標");
            return -1;
        }
        if (params[1] < 11 || params[1] > 25) {
            System.out.println("含水率超標");
            return -1;
        }
        int x = (int) (params[0] / 0.5);
        int y = (int) params[1] - 11;
        return default_levels[x][y];
    }

    @Override
    public double adjust(int param) {
        return (param < 5) ? 0.0 : -5.0;
    }

    @Override
    public double getBeanCoin(int level, int param) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
