package com.reto.screenMatchSpring.service;

public interface ITransformData {

    //Tipo de dato generico
    <T> T getData(String json, Class<T> clase);
}
