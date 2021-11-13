package com.mac.doc;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import com.tngtech.archunit.library.dependencies.SlicesRuleDefinition;

@AnalyzeClasses(packagesOf = DocApplication.class)
class ArchTests {

    @ArchTest
    ArchRule domainPackageRule = ArchRuleDefinition.classes().that()
            .resideInAPackage("..repository..")
            .should()
            .onlyBeAccessed()
            .byClassesThat()
            .resideInAnyPackage("..service..");

    @ArchTest
    ArchRule freeOfCycles = SlicesRuleDefinition.slices().matching("..doc.(*)..")
            .should().beFreeOfCycles();

}
