package org.example9.config;

import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class BImportSelect implements DeferredImportSelector {

    @Override
    public Class<? extends Group> getImportGroup() {
        System.out.println("BImportSelect#getImportGroup");
        return DeferredImportSelector.super.getImportGroup();
    }


    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        System.out.println("BImportSelect#selectImports");
        return new String[0];
    }
}
