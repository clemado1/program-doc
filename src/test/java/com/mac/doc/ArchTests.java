package com.mac.doc;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import com.tngtech.archunit.library.dependencies.SlicesRuleDefinition;
import org.junit.jupiter.api.Test;

class ArchTests {

    @Test
    void packageDependencyTests() {
        JavaClasses classes = new ClassFileImporter().importPackages("com.mac.doc");

        ArchRule domainPackageRule = ArchRuleDefinition.classes().that()
                .resideInAPackage("..repository..")
                .should()
                .onlyBeAccessed()
                .byClassesThat()
                .resideInAnyPackage("..service..");

        domainPackageRule.check(classes);

        ArchRule freeOfCycles = SlicesRuleDefinition.slices().matching("..doc.(*)..")
                .should().beFreeOfCycles();

        freeOfCycles.check(classes);
    }

}
