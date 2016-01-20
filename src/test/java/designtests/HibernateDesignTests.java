package designtests;

import java.util.Set;

import org.designwizard.api.DesignWizard;
import org.designwizard.design.ClassNode;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import br.edu.ufcg.splab.designtests.designrules.HashCodeAndEqualsRule;
import br.edu.ufcg.splab.designtests.util.PersistenceRuleUtil;

public class HibernateDesignTests {

    DesignWizard dw;
    Set<ClassNode> entities;
    HashCodeAndEqualsRule rule;

    PersistenceRuleUtil util = new PersistenceRuleUtil();



    @BeforeClass
    public void setUp() throws Exception {
        // Design All Classes
        dw = new DesignWizard("target/classes/");
        entities = util.getClassesAnnotated(dw, "javax.persistence.Entity");
    }

    @AfterClass
    public void tearDown() throws Exception {
        dw = null;
        entities = null;
    }

    @Test
    public void testHashCodeAndEqualsRuleAll() {
        SoftAssert softAssert = new SoftAssert();
        rule = new HashCodeAndEqualsRule(dw);
        rule.setClassNodes(entities);
        softAssert.assertTrue(rule.checkRule(), "checkRuleAll");
        softAssert.assertEquals("", rule.getReport(), "getReport");

        softAssert.assertAll();
    }

    @Test
    public void testHashCodeAndEqualsRule() {
        SoftAssert softAssert = new SoftAssert();
        rule = new HashCodeAndEqualsRule(dw);
        for (ClassNode entity : entities) {
            rule.setClassNode(entity);
            softAssert.assertTrue(rule.checkRule(), "checkRule-"+entity.getClassName());
            softAssert.assertEquals("", rule.getReport(), "getReport-"+entity.getClassName());
        }
        softAssert.assertAll();
    }
}
