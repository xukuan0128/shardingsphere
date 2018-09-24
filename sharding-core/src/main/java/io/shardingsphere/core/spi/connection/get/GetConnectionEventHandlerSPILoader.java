/*
 * Copyright 2016-2018 shardingsphere.io.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package io.shardingsphere.core.spi.connection.get;

import java.util.ServiceLoader;

/**
 * Connection event handler SPI loader.
 *
 * @author zhangliang
 */
public final class GetConnectionEventHandlerSPILoader {
    
    private static final GetConnectionEventHandlerSPILoader INSTANCE = new GetConnectionEventHandlerSPILoader();
    
    private final ServiceLoader<GetConnectionEventHandler> serviceLoader;
    
    private GetConnectionEventHandlerSPILoader() {
        serviceLoader = ServiceLoader.load(GetConnectionEventHandler.class);
    }
    
    /**
     * Get instance.
     * 
     * @return instance
     */
    public static GetConnectionEventHandlerSPILoader getInstance() {
        return INSTANCE;
    }
    
    /**
     * Handle get connection start event.
     *
     * @param event get connection start event
     */
    public void handle(final GetConnectionStartEvent event) {
        for (GetConnectionEventHandler each : serviceLoader) {
            each.handle(event);
        }
    }
    
    /**
     * Handle get connection finish event.
     *
     * @param event get connection finish event
     */
    public void handle(final GetConnectionFinishEvent event) {
        for (GetConnectionEventHandler each : serviceLoader) {
            each.handle(event);
        }
    }
}
