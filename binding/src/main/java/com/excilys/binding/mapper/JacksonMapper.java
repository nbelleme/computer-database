package com.excilys.binding.mapper;

import com.excilys.binding.exception.MapperException;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.util.MyPage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.List;

;

@Component
public class JacksonMapper {

    public static String objectToJson(Object obj) {

        ObjectMapper mapper = new ObjectMapper();
        try {
            String jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
            return jsonInString;
        } catch (Exception e) {
            throw new MapperException("Error objectToJson ", e);
        }

    }

    public static String computersToJson(MyPage<Computer> computers) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(computers);
            return jsonInString;
        } catch (Exception e) {
            throw new MapperException("Error objectToJson ", e);
        }
    }

    public static Object jsonToObject(String json, Class classe) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(json, classe);
        } catch (Exception e) {
            throw new MapperException("Error jsonToComputer ", e);
        }
    }

    public static MyPage<Computer> jsonToComputers(String json) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        try {
            return mapper.readValue(json, new TypeReference<MyPage<Computer>>() {
            });
        } catch (Exception e) {
            throw new MapperException("Error computersToJson ", e);
        }
    }

    public static List<Company> jsonToCompanies(String json) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        try {
            return mapper.readValue(json, new TypeReference<List<Company>>() {
            });
        } catch (Exception e) {
            throw new MapperException("Error computersToJson ", e);
        }
    }


}
