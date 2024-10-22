package org.example9.annotation;

import org.example9.config.AImportSelect;
import org.example9.config.BImportSelect;
import org.example9.config.CImportSelect;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({CImportSelect.class, BImportSelect.class,AImportSelect.class,})
public @interface EnableImport {
}
