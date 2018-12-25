package cn.spark.calculate.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@ConfigurationProperties(prefix = "spark")
@PropertySource(name = "spark.properties",
        value ={"classpath:properties/spark.properties"},
        ignoreResourceNotFound = true,
        encoding = "UTF-8")
public class SparkInfo {
    private String appName;
    private String master;
    private String driverMemory;
    private String workerMemory;
    private String executorMemory;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public String getDriverMemory() {
        return driverMemory;
    }

    public void setDriverMemory(String driverMemory) {
        this.driverMemory = driverMemory;
    }

    public String getWorkerMemory() {
        return workerMemory;
    }

    public void setWorkerMemory(String workerMemory) {
        this.workerMemory = workerMemory;
    }

    public String getExecutorMemory() {
        return executorMemory;
    }

    public void setExecutorMemory(String executorMemory) {
        this.executorMemory = executorMemory;
    }
}
