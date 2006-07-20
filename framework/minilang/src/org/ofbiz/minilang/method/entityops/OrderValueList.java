/*
 * Copyright 2001-2006 The Apache Software Foundation
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package org.ofbiz.minilang.method.entityops;

import java.util.List;

import org.ofbiz.entity.util.EntityUtil;
import org.ofbiz.minilang.SimpleMethod;
import org.ofbiz.minilang.method.ContextAccessor;
import org.ofbiz.minilang.method.MethodContext;
import org.ofbiz.minilang.method.MethodOperation;
import org.w3c.dom.Element;

/**
 * Order the given list of GenericValue objects
 *
 * @author     <a href="mailto:jonesde@ofbiz.org">David E. Jones</a>
 * @version    $Rev$
 * @since      2.0
 */
public class OrderValueList extends MethodOperation {
    
    ContextAccessor listAcsr;
    ContextAccessor toListAcsr;
    ContextAccessor orderByListAcsr;

    public OrderValueList(Element element, SimpleMethod simpleMethod) {
        super(element, simpleMethod);
        listAcsr = new ContextAccessor(element.getAttribute("list-name"));
        toListAcsr = new ContextAccessor(element.getAttribute("to-list-name"), element.getAttribute("list-name"));
        orderByListAcsr = new ContextAccessor(element.getAttribute("order-by-list-name"));
    }

    public boolean exec(MethodContext methodContext) {
        List orderByList = null;

        if (!orderByListAcsr.isEmpty()) {
            orderByList = (List) orderByListAcsr.get(methodContext);
        }
        toListAcsr.put(methodContext, EntityUtil.orderBy((List) listAcsr.get(methodContext), orderByList));
        return true;
    }

    public String rawString() {
        // TODO: something more than the empty tag
        return "<order-value-list/>";
    }
    public String expandedString(MethodContext methodContext) {
        // TODO: something more than a stub/dummy
        return this.rawString();
    }
}
