package com.udemy.common.service;

public abstract class GenericModelMapper <ENTITY, CREATE_DTO, RESPONSE_DTO, UPDATE_DTO>{

    public abstract ENTITY toEntity(CREATE_DTO createDto);
    public abstract RESPONSE_DTO toResponseDto(ENTITY entity);
    public abstract void update(UPDATE_DTO updateDto, ENTITY entity);
}
