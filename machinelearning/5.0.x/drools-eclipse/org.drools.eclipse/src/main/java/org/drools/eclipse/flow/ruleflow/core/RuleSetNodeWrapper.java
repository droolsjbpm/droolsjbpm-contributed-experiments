package org.drools.eclipse.flow.ruleflow.core;
/*
 * Copyright 2005 JBoss Inc
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

import org.drools.eclipse.flow.common.editor.core.ElementConnection;
import org.drools.eclipse.flow.common.editor.core.ElementWrapper;
import org.drools.workflow.core.node.RuleSetNode;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

/**
 * Wrapper for a RuleSet node.
 * 
 * @author <a href="mailto:kris_verlaenen@hotmail.com">Kris Verlaenen</a>
 */
public class RuleSetNodeWrapper extends EventBasedNodeWrapper {

    private static final long serialVersionUID = 400L;

    public static final String RULE_FLOW_GROUP = "RuleFlowGroup";

    protected void initDescriptors() {
    	super.initDescriptors();
    	IPropertyDescriptor[] oldDescriptors = descriptors; 
        descriptors = new IPropertyDescriptor[oldDescriptors.length + 1];
        System.arraycopy(oldDescriptors, 0, descriptors, 0, oldDescriptors.length);
//        descriptors[descriptors.length - 3] = getOnEntryPropertyDescriptor();
//        descriptors[descriptors.length - 2] = getOnExitPropertyDescriptor();
        descriptors[descriptors.length - 1] = 
            new TextPropertyDescriptor(RULE_FLOW_GROUP, "RuleFlowGroup");
    }

    public RuleSetNodeWrapper() {
        setNode(new RuleSetNode());
        getRuleSetNode().setName("RuleSet");
        getRuleSetNode().setRuleFlowGroup("");
    }
    
    public RuleSetNode getRuleSetNode() {
        return (RuleSetNode) getNode();
    }
    
    public boolean acceptsIncomingConnection(ElementConnection connection, ElementWrapper source) {
        return super.acceptsIncomingConnection(connection, source)
        	&& getIncomingConnections().isEmpty();
    }

    public boolean acceptsOutgoingConnection(ElementConnection connection, ElementWrapper target) {
        return super.acceptsOutgoingConnection(connection, target)
        	&& getOutgoingConnections().isEmpty();
    }
    
    public Object getPropertyValue(Object id) {
        if (RULE_FLOW_GROUP.equals(id)) {
            return getRuleSetNode().getRuleFlowGroup();
        }
        return super.getPropertyValue(id);
    }

    public void resetPropertyValue(Object id) {
        if (RULE_FLOW_GROUP.equals(id)) {
        	getRuleSetNode().setRuleFlowGroup("");
        } else {
            super.resetPropertyValue(id);
        }
    }

    public void setPropertyValue(Object id, Object value) {
        if (RULE_FLOW_GROUP.equals(id)) {
            getRuleSetNode().setRuleFlowGroup((String) value);
        } else {
            super.setPropertyValue(id, value);
        }
    }
}
