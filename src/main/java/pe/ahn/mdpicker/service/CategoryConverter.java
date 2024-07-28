package pe.ahn.mdpicker.service;

import jakarta.persistence.AttributeConverter;
import pe.ahn.mdpicker.model.category.CategoryInfo;
import pe.ahn.mdpicker.model.response.ErrorCode;
import pe.ahn.mdpicker.system.ApiException;

import java.util.EnumSet;

public class CategoryConverter implements AttributeConverter<CategoryInfo, String> {
    @Override
    public String convertToDatabaseColumn(CategoryInfo attribute) {
        return attribute.getCode().toString();
    }

    @Override
    public CategoryInfo convertToEntityAttribute(String dbData) {
        return EnumSet.allOf(CategoryInfo.class).stream()
                .filter(e -> e.getCode().equals(Long.valueOf(dbData)))
                .findAny()
                .orElseThrow(() -> new ApiException("Enum 변환 실패", ErrorCode.INTER_SERVER_ERROR));
    }
}
