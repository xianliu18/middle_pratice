package com.noodles.ch15.digestertest;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.RuleSetBase;

/**
 * @Description
 * @Author liuxian
 * @Date 2023/11/28 15:20
 **/
public class EmployeeRuleSet extends RuleSetBase {
    @Override
    public void addRuleInstances(Digester digester) {
        // add rules
        digester.addObjectCreate("employee", "com.noodles.ch15.digestertest.Employee");
        digester.addSetProperties("employee");
        digester.addObjectCreate("employee/office", "com.noodles.ch15.digestertest.Office");
        digester.addSetProperties("employee/office");
        digester.addSetNext("employee/office", "addOffice");
        digester.addObjectCreate("employee/office/address", "com.noodles.ch15.digestertest.Address");
        digester.addSetProperties("employee/office/address");
        digester.addSetNext("employee/office/address", "setAddress");

    }
}
