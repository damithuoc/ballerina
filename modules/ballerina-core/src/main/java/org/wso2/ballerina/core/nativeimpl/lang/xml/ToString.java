/*
 *  Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

package org.wso2.ballerina.core.nativeimpl.lang.xml;

import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wso2.ballerina.core.interpreter.Context;
import org.wso2.ballerina.core.model.types.TypeEnum;
import org.wso2.ballerina.core.model.values.BValue;
import org.wso2.ballerina.core.model.values.StringValue;
import org.wso2.ballerina.core.model.values.XMLValue;
import org.wso2.ballerina.core.nativeimpl.AbstractNativeFunction;
import org.wso2.ballerina.core.nativeimpl.annotations.Argument;
import org.wso2.ballerina.core.nativeimpl.annotations.BallerinaFunction;
import org.wso2.ballerina.core.nativeimpl.lang.utils.ErrorHandler;

/**
 * Native function ballerina.lang.xml:toString
 */
@BallerinaFunction(
        packageName = "ballerina.lang.xml",
        functionName = "toString",
        args = {@Argument(name = "xml", type = TypeEnum.XML)},
        returnType = {TypeEnum.STRING},
        isPublic = true
)
@Component(
        name = "func.lang.xml_toString",
        immediate = true,
        service = AbstractNativeFunction.class
)
public class ToString extends AbstractNativeFunction {

    private static final Logger log = LoggerFactory.getLogger(ToString.class);

    @Override
    public BValue<?>[] execute(Context ctx) {
        StringValue xmlStr = null;
        try {
            // Accessing Parameters.
            XMLValue xml = (XMLValue) getArgument(ctx, 0).getBValue();
            xmlStr = xml.getString();
            if (log.isDebugEnabled()) {
                log.debug("XML toString: " + xml.getString().getValue());
            }
        } catch (Throwable e) {
            ErrorHandler.handleJsonException("convert xml to string", e);
        }
        // Setting output value.
        return getBValues(xmlStr);
    }
}