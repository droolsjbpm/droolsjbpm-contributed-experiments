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
package org.drools.reteoo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.drools.WorkingMemory;
import org.drools.common.InternalFactHandle;
import org.drools.rule.Declaration;
import org.drools.spi.Accumulator;
import org.drools.spi.Tuple;

/**
 * A Mock accumulate object.
 * 
 * @author etirelli
 *
 */
public class MockAccumulator
    implements
    Accumulator {

    private static final long serialVersionUID = 400L;

    private Tuple             leftTuple        = null;
    private List              matchingObjects  = Collections.EMPTY_LIST;
    private WorkingMemory     workingMemory    = null;

    public Tuple getLeftTuple() {
        return this.leftTuple;
    }

    public List getMatchingObjects() {
        return this.matchingObjects;
    }

    public WorkingMemory getWorkingMemory() {
        return this.workingMemory;
    }
    
    public Object createContext() {
        return this;
    }

    public void init(Object workingMemoryContext,
                     Object context,
                     Tuple leftTuple,
                     Declaration[] declarations,
                     WorkingMemory workingMemory) throws Exception {
        this.leftTuple = leftTuple;
        this.matchingObjects = new ArrayList();
        this.workingMemory = workingMemory;
    }

    public void accumulate(Object workingMemoryContext,
                           Object context,
                           Tuple leftTuple,
                           InternalFactHandle handle,
                           Declaration[] declarations,
                           Declaration[] innerDeclarations,
                           WorkingMemory workingMemory) throws Exception {
        this.matchingObjects.add( handle.getObject() );
    }

    public Object getResult(Object workingMemoryContext,
                            Object context,
                            Tuple leftTuple,
                            Declaration[] declarations,
                            WorkingMemory workingMemory) throws Exception {
        return this.matchingObjects;
    }

    public void reverse(Object workingMemoryContext,
                        Object context,
                        Tuple leftTuple,
                        InternalFactHandle handle,
                        Declaration[] declarations,
                        Declaration[] innerDeclarations,
                        WorkingMemory workingMemory) throws Exception {
        // nothing to do yet
    }

    public boolean supportsReverse() {
        return false;
    }

    public Object createWorkingMemoryContext() {
        return null;
    }

}
