package org.suggs.sandbox.eventsourcing.bankaccount.accounts;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by suggitpe on 30/05/17.
 */
@Component
public class DeleteMe {

    @Value("${foo.bar}")
    private String fooBar;

    @Value("${foo.baz}")
    private String fooBaz;

    protected String getBar(){
        return fooBar;
    }

    protected String getBaz(){
        return fooBaz;
    }

}
