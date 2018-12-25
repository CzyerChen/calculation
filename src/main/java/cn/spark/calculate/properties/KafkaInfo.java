package cn.spark.calculate.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Desciption
 *
 * @author Claire.Chen
 * @create_time 2018 -12 - 24 18:37
 */
@Configuration
@ConfigurationProperties(prefix = "spark")
@PropertySource(name = "kafka.properties",
        value ={"classpath:properties/kafka.properties"},
        ignoreResourceNotFound = true,
        encoding = "UTF-8")
public class KafkaInfo {

    private String groupId;
    private String topics;
    private String brokerList;
    private boolean enableAutoCommit;
    private String autoOffsetReset;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getTopics() {
        return topics;
    }

    public void setTopics(String topics) {
        this.topics = topics;
    }

    public String getBrokerList() {
        return brokerList;
    }

    public void setBrokerList(String brokerList) {
        this.brokerList = brokerList;
    }

    public boolean isEnableAutoCommit() {
        return enableAutoCommit;
    }

    public void setEnableAutoCommit(boolean enableAutoCommit) {
        this.enableAutoCommit = enableAutoCommit;
    }

    public String getAutoOffsetReset() {
        return autoOffsetReset;
    }

    public void setAutoOffsetReset(String autoOffsetReset) {
        this.autoOffsetReset = autoOffsetReset;
    }
}
