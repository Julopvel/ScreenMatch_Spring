package com.reto.screenMatchSpring.service;

public interface ITransformData {

    //Tipo de dato generico
    <T> T gteData(String json, Class<T> clase);
}
