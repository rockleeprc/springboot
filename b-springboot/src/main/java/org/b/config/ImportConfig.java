package org.b.config;

import org.b.bean.Blue;
import org.b.bean.Green;
import org.b.bean.Red;
import org.b.bean.Yellow;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Optional;
import java.util.Set;

@Configuration
@Import({Green.class, ImportConfig.MyImportSelector.class, ImportConfig.MyImportBeanDefinitionRegistrar.class})
public class ImportConfig {

    protected static class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
        @Override
        public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
            boolean hasRed = registry.containsBeanDefinition(Blue.class.getName());
            if (!hasRed) {
                RootBeanDefinition yellowBeanDefinition = new RootBeanDefinition(Yellow.class);
                registry.registerBeanDefinition(Yellow.class.getName(), yellowBeanDefinition);
            }
        }
    }

    protected static class MyImportSelector implements ImportSelector {

        @Override
        public String[] selectImports(AnnotationMetadata importingClassMetadata) {
            Set<String> annotationTypes = importingClassMetadata.getAnnotationTypes();
            for (String annotationType : annotationTypes) {
                System.out.println("annotationType = " + annotationType);
            }

            MergedAnnotations annotations = importingClassMetadata.getAnnotations();
            MergedAnnotation<Import> importMergedAnnotation = annotations.get(Import.class);
            Optional<Object> value = importMergedAnnotation.getValue("value");
            value.ifPresent(obj -> {
                Class<?>[] classes = (Class<?>[]) obj;
                for (Class<?> clazz : classes) {
                    System.out.println("@Import value = " + clazz.getName());
                }
            });

            return new String[]{Red.class.getName()};
//            return  null;
        }
    }
}
