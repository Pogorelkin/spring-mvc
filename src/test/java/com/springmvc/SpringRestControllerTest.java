package com.springmvc;

import com.springmvc.entities.Employee;
import com.springmvc.services.EmployeeService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebAppConfiguration
public class SpringRestControllerTest extends AbstractJUnit4SpringContextTests {
    private static RestTemplate restTemplate = new RestTemplate();
    private static final String URL_EMPLOYEES = "http://localhost:8081/employee/";
    private static Logger logger = LoggerFactory.getLogger(SpringRestControllerTest.class);

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    EmployeeService employeeService;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void testGetEmployee() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/employee/1")).andDo(print()).andExpect(status().is2xxSuccessful()).andExpect(jsonPath("${id}").value("1")).andReturn();
        Assert.assertEquals("application/json;charset=UTF-8", mvcResult.getResponse().getContentType());
    }

    @Test
    public void testEmployeeAddition() throws Exception {
        Employee employee = new Employee(4, "Test1", "Test2", 123);
        mockMvc.perform(post("/employee")
                .param("employeeId", String.valueOf(employee.getEmployeeId()))
                .param("firstName", employee.getFirstName())
                .param("lastName", employee.getLastName())
                .param("idCardNumber", String.valueOf(employee.getIdCardNumber())))
                .andExpect(status().isFound());
        assertNotNull(employeeService.getEmployeeById(4));
    }
}