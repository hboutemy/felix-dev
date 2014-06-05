/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.felix.dm.impl;

import java.net.URL;
import java.util.Dictionary;

public class ResourceEventImpl extends EventImpl implements Comparable {
    final URL m_resource;
    final Dictionary<?, ?> m_resourceProperties;
    
    public ResourceEventImpl(URL resource, Dictionary<?,?> resourceProperties) {
        m_resource = resource;
        m_resourceProperties = resourceProperties;
    }
    
    public URL getResource() {
        return m_resource;
    }
    
    public Dictionary<?, ?> getResourceProperties() {
        return m_resourceProperties;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ResourceEventImpl) {
            ResourceEventImpl r1 = this;
            ResourceEventImpl r2 = (ResourceEventImpl) obj;
            boolean match = r1.getResource().equals(r2.getResource());
            if (match) {
                Dictionary<?,?> d1 = getResourceProperties();
                Dictionary<?,?> d2 = r2.getResourceProperties();
                
                if (d1 == null) {
                	return d2 == null ? match : false;
                }
                else {
                	return d1.equals(d2);
                }
            }
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + getResource().hashCode();
        result = prime * result + ((getResourceProperties() == null) ? 0 : getResourceProperties().hashCode());
        return result;
    }

    @Override
    public int compareTo(Object that) {
        if (this.equals(that)) {
            return 0;
        }
        
        // Sort by resource name.
        return getResource().toString().compareTo(((ResourceEventImpl) that).getResource().toString());
    }
}
