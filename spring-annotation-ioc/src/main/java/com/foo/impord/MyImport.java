package com.foo.impord;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;
import java.util.Set;

public class MyImport implements ImportSelector {
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        Map<String, Object> map = importingClassMetadata.getAnnotationAttributes("Import");
        System.out.println("----"+map);
        return new String[]{"com.foo.bean.Blue", "com.foo.bean.Yellow"};
    }
}
