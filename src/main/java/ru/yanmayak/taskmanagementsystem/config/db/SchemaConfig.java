package ru.yanmayak.taskmanagementsystem.config.db;

import io.micrometer.common.util.StringUtils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Slf4j
@Component
public class SchemaConfig implements BeanPostProcessor {
    @Value("${SCHEMA_NAME:task_management_system}")
    private String schemaName;

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (!StringUtils.isEmpty(schemaName) && bean instanceof DataSource) {
            DataSource dataSource = (DataSource) bean;
            try (Connection connection = dataSource.getConnection();
                 Statement statement = connection.createStatement()) {
                log.info("Going to create schema '{}' if not exist", schemaName);
                statement.executeUpdate("CREATE SCHEMA " + schemaName);
            } catch (SQLException exception) {
                throw new RuntimeException("Failed to create schema '" + schemaName + "'" + exception);
            }
        }
        return bean;
    }
}
