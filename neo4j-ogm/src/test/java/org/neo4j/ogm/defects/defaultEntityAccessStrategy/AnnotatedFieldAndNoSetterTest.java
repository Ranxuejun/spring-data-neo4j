package org.neo4j.ogm.defects.defaultEntityAccessStrategy;

import org.junit.Ignore;
import org.junit.Test;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;
import org.neo4j.ogm.entityaccess.DefaultEntityAccessStrategy;
import org.neo4j.ogm.entityaccess.EntityAccess;
import org.neo4j.ogm.entityaccess.FieldWriter;
import org.neo4j.ogm.metadata.info.ClassInfo;
import org.neo4j.ogm.metadata.info.DomainInfo;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@Ignore
public class AnnotatedFieldAndNoSetterTest {

    private DefaultEntityAccessStrategy entityAccessStrategy = new DefaultEntityAccessStrategy();
    private DomainInfo domainInfo = new DomainInfo("org.neo4j.ogm.defects");


    @Test
    public void shouldPreferAnnotatedFieldInAbsenceOfSetterForRelationshipEntity() {
        ClassInfo classInfo = this.domainInfo.getClass(End.class.getName());
        Set<? extends RelEntity> parameter = new HashSet<>();

        EntityAccess objectAccess = this.entityAccessStrategy.getRelationalWriter(classInfo, "REL_ENTITY_TYPE", parameter);
        assertNotNull("The resultant object accessor shouldn't be null", objectAccess);
        assertTrue("The access mechanism should be via the field", objectAccess instanceof FieldWriter);
        End end = new End();
        objectAccess.write(end, parameter);
        assertEquals(end.getRelEntities(), parameter);
    }

    @RelationshipEntity(type="REL_ENTITY_TYPE")
    public static class RelEntity {
        Long id;
        @StartNode Start start;
        @EndNode End end;

        public RelEntity() {
        }

        public End getEnd() {
            return end;
        }

        public void setEnd(End end) {
            this.end = end;
        }

        public Start getStart() {
            return start;
        }

        public void setStart(Start start) {
            this.start = start;
        }
    }

    public static class Start {
        Long id;
        String name;
        @Relationship(type = "REL_ENTITY_TYPE", direction = "OUTGOING")
        Set<RelEntity> relEntities;

        public Start() {
        }
    }

    public static class End {
        Long id;
        String name;
        @Relationship(type = "REL_ENTITY_TYPE", direction="INCOMING")
        Set<RelEntity> relEntities;

        public End() {
        }

        public Set<RelEntity> getRelEntities() {
            return relEntities;
        }

    }


}