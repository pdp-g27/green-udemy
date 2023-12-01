package com.udemy.common.service;

import com.udemy.common.repository.GenericRepository;
import com.udemy.common.rsql.SpecificationBuilder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

@Getter
@Setter
public abstract class GenericCrudService<ENTITY, ID, CREATE_DTO, RESPONSE_DTO, UPDATE_DTO> {
    protected abstract GenericRepository<ENTITY, ID> getRepository();
    protected abstract GenericModelMapper<ENTITY, CREATE_DTO,RESPONSE_DTO,UPDATE_DTO> getMapper();
    protected abstract Class<ENTITY> getEntityClass();
    public abstract ENTITY save(CREATE_DTO createDto);

    public RESPONSE_DTO create(CREATE_DTO createDto){
        ENTITY user = save(createDto);
        ENTITY saved = getRepository().save(user);
        return getMapper().toResponseDto(saved);
    }

    public RESPONSE_DTO getById(ID id){
        ENTITY entity = getRepository().
                findById(id).
                orElseThrow(() -> new RuntimeException("user with '%s' is not found".formatted(id)));

        return getMapper().toResponseDto(entity);
    }
    public RESPONSE_DTO update(ID id, UPDATE_DTO updateDto){
        ENTITY entity = getRepository().findById(id).orElseThrow(() -> new RuntimeException("user with '%s' is not found".formatted(id)));
        getMapper().update(updateDto,entity);
        ENTITY saved = getRepository().save(entity);
        return getMapper().toResponseDto(saved);
    }

    public void delete(ID id){
        getRepository().deleteById(id);
    }

    public Page<RESPONSE_DTO> getAll(Pageable pageable, String predicate) {
        Specification<ENTITY> specification = SpecificationBuilder.build(predicate);

        if (specification == null) {
            Page<RESPONSE_DTO> pageWithoutSpecification = getRepository().findAll(pageable)
                    .map(entity -> getMapper().toResponseDto(entity));
            return pageWithoutSpecification;
        }
        Page<RESPONSE_DTO> pageWithSpecification = getRepository().findAll(specification, pageable)
                .map(entity -> getMapper().toResponseDto(entity));
        return pageWithSpecification;
    }

}
