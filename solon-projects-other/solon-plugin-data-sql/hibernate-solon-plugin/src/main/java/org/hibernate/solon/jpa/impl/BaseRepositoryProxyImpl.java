package org.hibernate.solon.jpa.impl;

import org.hibernate.SessionFactory;

/**
 * @author noear
 * @since 2.5
 */
public class BaseRepositoryProxyImpl {
    protected SessionFactory sessionFactory;
    public BaseRepositoryProxyImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }
}