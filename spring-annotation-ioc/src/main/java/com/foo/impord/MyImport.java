package com.foo.impord;

import com.foo.bean.Blue;
import com.foo.bean.Yellow;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

public class MyImport implements ImportSelector {

    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        Map<String, Object> map = importingClassMetadata.getAnnotationAttributes("Import");
        System.out.println("----" + map);
        return new String[]{Blue.class.getName(), Yellow.class.getName()};
    }
}
