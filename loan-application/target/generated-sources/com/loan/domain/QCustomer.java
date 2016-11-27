package com.loan.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QCustomer is a Querydsl query type for Customer
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCustomer extends EntityPathBase<Customer> {

    private static final long serialVersionUID = 314976055L;

    public static final QCustomer customer = new QCustomer("customer");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath address = createString("address");

    public final DatePath<java.time.LocalDate> birthday = createDate("birthday", java.time.LocalDate.class);

    //inherited
    public final DateTimePath<org.joda.time.DateTime> createdDate = _super.createdDate;

    public final EnumPath<Customer.CreditStatus> creditStatus = createEnum("creditStatus", Customer.CreditStatus.class);

    public final EnumPath<Customer.CustomerType> customerType = createEnum("customerType", Customer.CustomerType.class);

    public final StringPath email = createString("email");

    public final StringPath firstName = createString("firstName");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath lastName = createString("lastName");

    public final StringPath mobile = createString("mobile");

    //inherited
    public final DateTimePath<org.joda.time.DateTime> modifiedDate = _super.modifiedDate;

    public final StringPath password = createString("password");

    public final EnumPath<Customer.StatusType> status = createEnum("status", Customer.StatusType.class);

    public QCustomer(String variable) {
        super(Customer.class, forVariable(variable));
    }

    public QCustomer(Path<? extends Customer> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCustomer(PathMetadata<?> metadata) {
        super(Customer.class, metadata);
    }

}

