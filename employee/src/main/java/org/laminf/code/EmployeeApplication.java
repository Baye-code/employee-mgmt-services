package org.laminf.code;

//import org.laminf.code.clients.department.DepartmentClient;
import org.laminf.code.config.DepartmentClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;


@EnableEurekaClient
@EnableFeignClients(
//        basePackages = "org.laminf.code.clients",
        basePackages = "org.laminf.code"
)
//@ComponentScan({"org.laminf.code", "org.laminf.code.config"})
@SpringBootApplication()
public class EmployeeApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeApplication.class, args);
    }

}
