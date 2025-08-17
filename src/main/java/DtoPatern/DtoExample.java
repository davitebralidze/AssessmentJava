package DtoPatern;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DtoExample {
    public static void main(String[] args) throws JsonProcessingException {
        MyDto dto = new MyDto.Builder()
                .withId(1111)
                .withDetails(
                        new MyDto.Details.Builder()
                                .withAge(5)
                                .withEmail("test@gmail.com").build()
                )
                .build();

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(dto);
        System.out.println(json);
    }
}
