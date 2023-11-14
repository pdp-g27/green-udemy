package com.udemy.common.service;


import com.udemy.common.repository.GenericRepository;
import com.udemy.common.rsql.SpecificationBuilder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
public abstract class GenericCrudService<ENTITY, ID, CREATE_DTO, RESPONSE_DTO, UPDATE_DTO> {
    protected abstract GenericRepository<ENTITY, ID> getRepository();
    protected abstract GenericModelMapper<ENTITY, CREATE_DTO,RESPONSE_DTO,UPDATE_DTO> getMapper();

    public ResponseEntity<RESPONSE_DTO> create(CREATE_DTO createDto){
        ENTITY entity = getMapper().toEntity(createDto);
        ENTITY saved = getRepository().save(entity);

        return ResponseEntity.ok(getMapper().toResponseDto(saved));
    }

    public ResponseEntity<RESPONSE_DTO> getById(ID id){
        ENTITY entity = getRepository().
                findById(id).
                orElseThrow(() -> new RuntimeException("user with '%s' is not found".formatted(id)));

        return ResponseEntity.ok(getMapper().toResponseDto(entity));
    }
    public ResponseEntity<RESPONSE_DTO> update(ID id, UPDATE_DTO updateDto){
        ENTITY entity = getRepository().findById(id).orElseThrow(() -> new RuntimeException("user with '%s' is not found".formatted(id)));
        getMapper().update(updateDto,entity);
        ENTITY saved = getRepository().save(entity);
        RESPONSE_DTO responseDto = getMapper().toResponseDto(saved);
        return ResponseEntity.ok(responseDto);
    }

    public ResponseEntity<?> delete(ID id){
        getRepository().deleteById(id);
        return ResponseEntity.ok("DELETED successfully");
    }

    public ResponseEntity<Page<RESPONSE_DTO>> getAll(Pageable pageable, String predicate) {
        Specification<ENTITY> specification = SpecificationBuilder.build(predicate);

        if (specification == null) {
            Page<RESPONSE_DTO> pageWithoutSpe = getRepository().findAll(pageable)
                    .map(entity -> getMapper().toResponseDto(entity));
            return ResponseEntity.ok(pageWithoutSpe);
        }
        Page<RESPONSE_DTO> pageWithSpe = getRepository().findAll(specification, pageable)
                .map(entity -> getMapper().toResponseDto(entity));
        return ResponseEntity.ok(pageWithSpe);
    }

}
