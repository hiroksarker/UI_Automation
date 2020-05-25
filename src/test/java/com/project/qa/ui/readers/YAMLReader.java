package com.project.qa.ui.readers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Map;

public class YAMLReader {
    static final Logger LOGGER = LoggerFactory.getLogger(YAMLReader.class);
    private String yamlFilePath;

    public YAMLReader() {
        String yamlFileName = new ConfigFileReader().getDefaultYAMLFilePath();
        LOGGER.info("reading yaml file from default location: {}", yamlFileName);
        yamlFilePath = this.getClass().getClassLoader().getResource(yamlFileName).getPath();
    }

    public YAMLReader(String yamlFileName) {
        LOGGER.info("reading yaml file from given location: {}", yamlFileName);
        yamlFilePath = this.getClass().getClassLoader().getResource(yamlFileName).getPath();
    }

    /**
     * Method reads YAML file and casts it into Map
     *
     * @return Map
     */
    public Map<String, Object> readYamlIntoMap() {
        Yaml yaml = new Yaml();
        Map<String, Object> yamlMap = null;
        try (Reader reader = new FileReader(yamlFilePath)) {
            yamlMap = yaml.load(reader);

        } catch (IOException e) {
            LOGGER.error("error while reading yaml file {}", e.getMessage());
        }
        return yamlMap;
    }

    /**
     * Method to get yaml file into map object
     * @return map object
     */
    public Map<String, Object> getYamlMaps() {
        Yaml yaml = new Yaml();
        Map<String, Object> yamlMaps = null;
        try (Reader yamlFile = new FileReader(yamlFilePath)) {
            yamlMaps = (Map<String, Object>) yaml.load(yamlFile);
        } catch (FileNotFoundException e) {
            LOGGER.error("Exception while reading YAML file: {}", e.getMessage());
        } catch (IOException e) {
            LOGGER.error("Exception while reading YAML file: {}", e.getMessage());
        }
        return yamlMaps;
    }
}
