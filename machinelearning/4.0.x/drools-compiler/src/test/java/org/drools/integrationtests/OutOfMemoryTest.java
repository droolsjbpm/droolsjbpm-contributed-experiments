package org.drools.integrationtests;

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

import java.io.InputStreamReader;

import junit.framework.TestCase;

import org.drools.Cheese;
import org.drools.RuleBase;
import org.drools.RuleBaseConfiguration;
import org.drools.RuleBaseFactory;
import org.drools.StatefulSession;
import org.drools.WorkingMemory;
import org.drools.audit.WorkingMemoryFileLogger;
import org.drools.audit.WorkingMemoryInMemoryLogger;
import org.drools.compiler.PackageBuilder;
import org.drools.rule.Package;

/** Run all the tests with the ReteOO engine implementation */
public class OutOfMemoryTest extends TestCase {

    protected RuleBase getRuleBase() throws Exception {

        return RuleBaseFactory.newRuleBase( RuleBase.RETEOO,
                                            null );
    }

    protected RuleBase getRuleBase(final RuleBaseConfiguration config) throws Exception {

        return RuleBaseFactory.newRuleBase( RuleBase.RETEOO,
                                            config );
    }

    public void testStatefulSessionsCreation() throws Exception {

        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "test_OutOfMemoryError.drl" ) ) );
        final Package pkg = builder.getPackage();

        final RuleBase ruleBase = getRuleBase();
        ruleBase.addPackage( pkg );

        int i = 0;

        try {
            for ( i = 0; i < 300000; i++ ) {
                final StatefulSession session = ruleBase.newStatefulSession( true );
                session.dispose();
            }
        } catch ( Throwable e ) {
            System.out.println( "Error at: " + i );
            e.printStackTrace();
            fail( "Should not raise any error or exception." );
        }

    }

    public void testAgendaLoop() throws Exception {
        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "test_OutOfMemory.drl" ) ) );
        final Package pkg = builder.getPackage();

        final RuleBase ruleBase = getRuleBase();
        ruleBase.addPackage( pkg );
        final WorkingMemory workingMemory = ruleBase.newStatefulSession();

        workingMemory.insert( new Cheese( "stilton",
                                          1 ) );

        workingMemory.fireAllRules( 3000000 );

        // just for profiling
        //Thread.currentThread().wait();
    }

    public void testWorkingMemoryFileLogger() throws Exception {

        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "test_OutOfMemoryError.drl" ) ) );
        final Package pkg = builder.getPackage();

        final RuleBase ruleBase = getRuleBase();
        ruleBase.addPackage( pkg );

        try {
            for ( int i = 0; i < 100000; i++ ) {
                StatefulSession session = ruleBase.newStatefulSession();
                WorkingMemoryInMemoryLogger logger = new WorkingMemoryInMemoryLogger( session );
                session.fireAllRules();
                session.dispose();
            }
        } catch ( Throwable e ) {
            e.printStackTrace();
            fail( "Should not raise OOME.");
        }
    }

}