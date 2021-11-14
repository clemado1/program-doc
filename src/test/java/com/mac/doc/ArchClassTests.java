package com.mac.doc;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;

import javax.persistence.Entity;

@AnalyzeClasses(packagesOf = DocApplication.class)
public class ArchClassTests {

    @ArchTest
    ArchRule controllerClassRule = ArchRuleDefinition.classes().that().haveSimpleNameEndingWith("Controller")
            .should().accessClassesThat().haveSimpleNameEndingWith("Service")
            .orShould().accessClassesThat().haveSimpleNameEndingWith("Repository");
    @ArchTest
    ArchRule repositoryClassRule = ArchRuleDefinition.noClasses().that().haveSimpleNameEndingWith("Repository")
            .should().accessClassesThat().haveSimpleNameEndingWith("Service")
            .orShould().accessClassesThat().haveSimpleNameEndingWith("Controller");

    @ArchTest
    ArchRule docClassesRule = ArchRuleDefinition.classes().that().haveSimpleNameStartingWith("Doc")
            .and().areNotEnums()
            .and().areNotAnnotatedWith(Entity.class)
            .should().resideInAnyPackage("..doc..");
}
