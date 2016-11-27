package com.loan.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QBaseEntity is a Querydsl query type for BaseEntity
 */
@Generated("com.mysema.query.codegen.SupertypeSerializer")
public class QBaseEntity extends EntityPathBase<BaseEntity> {

    private static final long serialVersionUID = -1893536979L;

    public static final QBaseEntity baseEntity = new QBaseEntity("baseEntity");

    public final DateTimePath<org.joda.time.DateTime> createdDate = createDateTime("createdDate", org.joda.time.DateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<org.joda.time.DateTime> modifiedDate = createDateTime("modifiedDate", org.joda.time.DateTime.class);

    public QBaseEntity(String variable) {
        super(BaseEntity.class, forVariable(variable));
    }

    public QBaseEntity(Path<? extends BaseEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBaseEntity(PathMetadata<?> metadata) {
        super(BaseEntity.class, metadata);
    }

}

