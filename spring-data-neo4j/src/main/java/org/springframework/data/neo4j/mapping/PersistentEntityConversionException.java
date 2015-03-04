/**
 * Copyright 2011 the original author or authors.
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
package org.springframework.data.neo4j.mapping;

import org.springframework.data.mapping.model.MappingException;

/**
 * @author mh
 * @since 21.10.11
 */
public class PersistentEntityConversionException extends MappingException {
    private static final long serialVersionUID = -162835100413817687L;
    private final Class targetType;
    private final Class sourceType;

    public PersistentEntityConversionException(Class targetType, Class sourceType) {
        super("Requested a entity of type '" + targetType + "', but the entity is of type '" + sourceType + "'.");
        this.targetType = targetType;
        this.sourceType = sourceType;
    }

    public Class getTargetType() {
        return targetType;
    }

    public Class getSourceType() {
        return sourceType;
    }
}
