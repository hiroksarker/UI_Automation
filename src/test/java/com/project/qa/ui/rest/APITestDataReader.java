package com.project.qa.ui.rest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public interface APITestDataReader {
    Logger LOGGER = LoggerFactory.getLogger(APITestDataReader.class);

    default List<Map<String, Object>> getServiceData(String value, Map<String, Object> modules) {
        ObjectMapper objectMapper = new ObjectMapper();
        return (List<Map<String, Object>>) objectMapper.convertValue(modules.get(value), List.class);
    }

    /**
     * Method to get URL
     * @param service
     * @return URL
     */
    default String getURL(List<Map<String, Object>> service) {
        return (String) service.get(0).get("URL");
    }

    /**
     * Method to get header list
     * @param service
     * @return header list
     */
    default Map getHeader(List<Map<String, Object>> service) {
        return (Map) service.get(2).get("Headers");
    }

    /**
     * Method to get request body
     * @param service
     * @return request body
     */
    default String getBody(List<Map<String, Object>> service) {
        return (String) service.get(4).get("PostBody");
    }

    /**
     * Method to get status code
     * @param service
     * @return status code
     */
    default int getStatusCode(List<Map<String, Object>> service) {
        return (int) service.get(1).get("StatusCode");
    }

    /**
     * Method to get assertion list
     * @param service
     * @return assertion list
     */
    default Map getAssertion(List<Map<String, Object>> service) {
        return (Map) service.get(3).get("Assertions");
    }

    /**
     * Method to update specific key value in request body
     * @param body
     * @param key
     * @param value
     * @param index
     * @return updated request body
     */
    default String updateValueInBody(String body, String key, String value, int index) {
        LOGGER.info("Body before update: {}", body);
        JSONArray jsonArray = new JSONArray(body);
        JSONObject jsonObject = jsonArray.getJSONObject(index);
        jsonObject.put(key, Long.parseLong(value));
        body = jsonObject.toString();
        body = "[" + body + "]";
        LOGGER.info("Update body: {}", body);
        return body;
    }
}