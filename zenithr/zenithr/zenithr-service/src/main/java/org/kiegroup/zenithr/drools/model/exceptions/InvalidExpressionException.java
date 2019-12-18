/*
 * Copyright 2018 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kiegroup.zenithr.drools.model.exceptions;

public class InvalidExpressionException extends RuntimeException {

    private static final long serialVersionUID = -1456775705375961560L;

    public InvalidExpressionException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidExpressionException(String message) {
        super(message);
    }

    public InvalidExpressionException(Throwable cause) {
        super(cause);
    }

}
