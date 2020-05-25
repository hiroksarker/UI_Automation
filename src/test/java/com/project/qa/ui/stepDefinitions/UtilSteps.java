package com.project.qa.ui.stepDefinitions;

import com.project.qa.ui.runners.TestContext;
import com.project.qa.ui.utils.DBConnectionManager;
import io.cucumber.java.en.Given;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class UtilSteps {
    static final Logger LOGGER = LoggerFactory.getLogger(UtilSteps.class);
    private TestContext testContext;
    private Map<String, Object> keyObjectMap;

    public UtilSteps(TestContext context) {
        testContext = context;
    }

    @Given("Verify DBConnectionManager utility is working correctly")
    public void verify_EXCEL_file_reader_function_for_specific_file() {
        DBConnectionManager dbConnectionManager = new DBConnectionManager("testdata/db.properties");
        List<Map<String, Object>> listOfRows = dbConnectionManager.getResultSetToList("SELECT * FROM employees.employees limit 10;");
        for (Map<String, Object> row : listOfRows) {
            for (Map.Entry<String, Object> rowEntry : row.entrySet()) {
                LOGGER.info(rowEntry.getKey() + " = " + rowEntry.getValue() + ", ");
            }
        }
    }
}
