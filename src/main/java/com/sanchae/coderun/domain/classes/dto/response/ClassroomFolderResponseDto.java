package com.sanchae.coderun.domain.classes.dto.response;

import com.sanchae.coderun.domain.classes.entity.ClassroomFolder;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClassroomFolderResponseDto {
    private Long id;
    private Long classroomId;
    private String title;
    private String description;

    public static ClassroomFolderResponseDto from(ClassroomFolder folder) {
       return ClassroomFolderResponseDto.builder()
                .id(folder.getId())
                .classroomId(folder.getClassroomId())
                .title(folder.getTitle())
                .description(folder.getDescription())
                .build();
    }
}
