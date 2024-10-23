package org.example9.config;


import org.example9.annotation.EnableImport;
import org.springframework.context.annotation.Configuration;

//@ComponentScan(excludeFilters = { @ComponentScan.Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
//        @ComponentScan.Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class) })
@EnableImport
//@Import({CImportSelect.class,BImportSelect.class,AImportSelect.class})
@Configuration
public class AImportAutoConfiguration {
//    @Bean
//    public Car car() {
//        System.out.println("AImportAutoConfiguration#car");
//        return new Car();
//    }
}
