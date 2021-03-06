/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.eagle.app;


import com.google.inject.Guice;
import com.google.inject.Injector;
import com.typesafe.config.ConfigFactory;
import org.apache.eagle.app.module.ApplicationGuiceModule;
import org.apache.eagle.app.service.ApplicationProviderService;
import org.apache.eagle.app.service.impl.ApplicationProviderServiceImpl;
import org.apache.eagle.app.spi.ApplicationProvider;
import org.apache.eagle.common.module.CommonGuiceModule;
import org.apache.eagle.metadata.model.ApplicationDesc;
import org.apache.eagle.metadata.service.memory.MemoryMetadataStore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

public class ApplicationProviderServiceTest {
    private final static Logger LOGGER = LoggerFactory.getLogger(ApplicationProviderServiceTest.class);
    private Injector injector = Guice.createInjector(new CommonGuiceModule(),new ApplicationGuiceModule(new ApplicationProviderServiceImpl(ConfigFactory.load())), new MemoryMetadataStore());

    @Test
    public void testApplicationProviderManagerInit(){
        ApplicationProviderService providerManager = injector.getInstance(ApplicationProviderService.class);
        Collection<ApplicationDesc> applicationDescs = providerManager.getApplicationDescs();
        Collection<ApplicationProvider> applicationProviders = providerManager.getProviders();

        applicationDescs.forEach((d)-> LOGGER.debug(d.toString()));
        applicationProviders.forEach((d)-> LOGGER.debug(d.toString()));
    }
}