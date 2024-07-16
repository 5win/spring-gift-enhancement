package gift.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import gift.dto.CategoryDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@Sql("/sql/truncateIdentity.sql")
class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private final CategoryDto categoryDto = new CategoryDto(null, "생일 선물", "노랑", "http",
        "생일 선물 카테고리");

    void insertCategory() throws Exception {
        String inputJson = new ObjectMapper().writeValueAsString(categoryDto);

        mockMvc.perform(post("/api/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(inputJson))
            .andExpect(status().isOk());
    }

    @Test
    @DisplayName("카테고리 추가 테스트")
    void addCategory() throws Exception {
        String inputJson = new ObjectMapper().writeValueAsString(categoryDto);

        mockMvc.perform(post("/api/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(inputJson))
            .andExpect(status().isOk());
    }

    @Test
    @DisplayName("카테고리 수정 테스트")
    void editCategory() throws Exception {
        insertCategory();

        CategoryDto editedCategory = new CategoryDto(1L, "크리스마스", "빨강", "https", "크리스마스 카테고리");
        String inputJson = new ObjectMapper().writeValueAsString(editedCategory);

        mockMvc.perform(put("/api/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(inputJson))
            .andExpect(status().isOk());
    }

    @Test
    @DisplayName("카테고리 삭제 테스트")
    void deleteCategory() throws Exception {
        insertCategory();

        mockMvc.perform(delete("/api/categories/1"))
            .andExpect(status().isOk());
    }
}