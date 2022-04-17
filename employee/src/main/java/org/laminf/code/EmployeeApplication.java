package org.laminf.code;


import org.laminf.code.clients.department.DepartmentClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;


@EnableEurekaClient
@EnableFeignClients(
        basePackages = "org.laminf.code.clients",
        basePackageClasses = DepartmentClient.class
)
@SpringBootApplication(
        scanBasePackages = {
                "org.laminf.code",
                "org.laminf.code.amqp"
        }
)
public class EmployeeApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeApplication.class, args);
    }

}
