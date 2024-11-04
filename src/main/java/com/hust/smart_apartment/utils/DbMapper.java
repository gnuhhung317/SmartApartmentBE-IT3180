package com.hust.smart_apartment.utils;

import com.hust.smart_apartment.annotations.DbColumnMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Tuple;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;
import java.util.function.BiFunction;

@Log4j2
@Component
public class DbMapper {

    @Data
    @Builder
    public static class DbFieldHolder{

        private Method getter;

        private Method setter;

        private Object converter;

        private Method convertFunc;

        private BiFunction<Tuple,String,?> preprocessor;
    }

    private final Map<Class<?>, BiFunction<Tuple, String, ?>> typeConverterMap;

    @Getter
    @Autowired
    private FormattingConversionService converterRegistry;

    public DbMapper(FormattingConversionService converterRegistry){
        typeConverterMap = new HashMap<>();
        typeConverterMap.put(String.class, this::getString);
        typeConverterMap.put(Boolean.class, this::getBoolSafe);
        typeConverterMap.put(Byte.class, this::getByteSafe);
        typeConverterMap.put(Short.class, this::getShortSafe);
        typeConverterMap.put(Integer.class, this::getIntegerSafe);
        typeConverterMap.put(Long.class, this::getLongSafe);
        typeConverterMap.put(BigInteger.class, this::getBigIntegerSafe);
        typeConverterMap.put(Float.class, this::getFloatSafe);
        typeConverterMap.put(Double.class, this::getDoubleSafe);
        typeConverterMap.put(BigDecimal.class, this::getBigDecimalSafe);
        typeConverterMap.put(Date.class, (tuple, field) -> tuple.get(field, Date.class));
        typeConverterMap.put(Timestamp.class, (tuple, field) -> tuple.get(field, Timestamp.class));
        typeConverterMap.put(Instant.class, this::getInstantSafe);
    }

    public String getString(Tuple tuple, String field){
        return tuple.get(field,String.class);
    }
    public Boolean getBoolSafe(Tuple tuple, String field){
        Object val = tuple.get(field);
        if(val instanceof Number) return ((Number) val).intValue()>0;
        else if(val instanceof Boolean) return (Boolean) val;
        return null;
    }

    public Byte getByteSafe(Tuple tuple, String field) {
        Number number = tuple.get(field, Number.class);
        return number == null ? null : number.byteValue();
    }

    public Short getShortSafe(Tuple tuple, String field) {
        Number number = tuple.get(field, Number.class);
        return number == null ? null : number.shortValue();
    }

    public Integer getIntegerSafe(Tuple tuple, String field) {
        Number number = tuple.get(field, Number.class);
        return number == null ? null : number.intValue();
    }

    public Long getLongSafe(Tuple tuple, String field) {
        Number number = tuple.get(field, Number.class);
        return number == null ? null : number.longValue();
    }

    public BigInteger getBigIntegerSafe(Tuple tuple, String field) {
        Number number = tuple.get(field, Number.class);
        return number == null ? null : (BigInteger) number;
    }

    public Float getFloatSafe(Tuple tuple, String field) {
        Number number = tuple.get(field, Number.class);
        return number == null ? null : number.floatValue();
    }

    public Double getDoubleSafe(Tuple tuple, String field) {
        Number number = tuple.get(field, Number.class);
        return number == null ? null : number.doubleValue();
    }

    public BigDecimal  getBigDecimalSafe(Tuple tuple, String field) {
        Number number = tuple.get(field, Number.class);
        return number == null ? null : (BigDecimal) number;
    }

    public Instant getInstantSafe(Tuple tuple, String field) {
        Timestamp timestamp = tuple.get(field, Timestamp.class);
        return timestamp == null ? null : timestamp.toInstant();
    }

    @SneakyThrows
    private Object getValue(Tuple tuple, String field, Object attributeConverter, Method convertFunc, BiFunction<Tuple, String, ?> preprocessor) {
        Object val;
        if (preprocessor != null) {
            val = preprocessor.apply(tuple, field);
        } else {
            val = tuple.get(field);
        }
        return convertFunc.invoke(attributeConverter, val);
    }

    private Object getValue(Tuple tuple, String field, Class<?> clazz) {
        BiFunction<Tuple, String, ?> converterFunc = typeConverterMap.get(clazz);
        if (converterFunc == null) {
            Object obj = tuple.get(field);
            return this.getConverterRegistry().convert(obj, clazz);
        }
        return converterFunc.apply(tuple, field);
    }

    protected Object getAnnotation(Field field) {
        return field.getAnnotation(DbColumnMapper.class);
    }

    protected String getAnnotationValue(Object object) {
        return ((DbColumnMapper) object).value();
    }

    protected Class<? extends AttributeConverter<?, ?>> getAnnotationConverter(Object object) {
        return ((DbColumnMapper) object).converter();
    }
    protected Class<?> getAnnotationNoneConverter() {
        return DbColumnMapper.None.class;
    }

    public <R> void getMethodMap(Class<R> clazz, Map<String, DbFieldHolder> methodMap) {
        if (clazz.getSuperclass() != null && clazz.getSuperclass() != Object.class) {
            getMethodMap(clazz.getSuperclass(), methodMap);
        }

        for (Field field : clazz.getDeclaredFields()) {
            Object dbColumnMapper = this.getAnnotation(field);
            if (dbColumnMapper != null) {
                String propPascalCase = field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
                String getterName = "get" + propPascalCase;
                String setterName = "set" + propPascalCase;
                try {
                    Method getter = clazz.getMethod(getterName);
                    Method setter = clazz.getMethod(setterName, getter.getReturnType());
                    String label = ObjectUtils.isEmpty(this.getAnnotationValue(dbColumnMapper)) ? StringUtil.camelToSnake(propPascalCase)
                            : this.getAnnotationValue(dbColumnMapper);
                    DbFieldHolder dbFieldHolder = DbFieldHolder.builder()
                            .getter(getter)
                            .setter(setter)
                            .build();
                    this.processCustomConverter(dbColumnMapper, dbFieldHolder);
                    methodMap.put(label, dbFieldHolder);
                } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                         InvocationTargetException ex) {
                    log.debug("Error get getter setter or constructor method of class {}", ex.getMessage());
                }
            }
        }
    }
    private void processCustomConverter(Object dbColumnMapper, DbFieldHolder dbFieldHolder) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Class<? extends AttributeConverter<?, ?>> converterClass = this.getAnnotationConverter(dbColumnMapper);
        if (!(this.getAnnotationNoneConverter().equals(converterClass))) {
            Object converter = converterClass.getDeclaredConstructor().newInstance();
            Arrays.stream(converterClass.getDeclaredMethods())
                    .filter(method -> "convertToEntityAttribute".equals(method.getName()) && Objects.equals(dbFieldHolder.getGetter().getReturnType(), method.getReturnType()))
                    .findFirst().ifPresent(method -> {
                        dbFieldHolder.setConverter(converter);
                        dbFieldHolder.setConvertFunc(method);
                        Class<?> paramType = method.getParameterTypes()[0];
                        if (Number.class.isAssignableFrom(paramType)) {
                            dbFieldHolder.setPreprocessor(this.typeConverterMap.get(paramType));
                        }
                    });
        }
    }
    @SneakyThrows
    private <R> R castSqlResult(Tuple tuple, Constructor<R> constructor, Map<String, DbFieldHolder> methodMap) {
        R instant = constructor.newInstance();
        for (Map.Entry<String, DbFieldHolder> dbFieldHolderEntry : methodMap.entrySet()) {
            try {
                String label = dbFieldHolderEntry.getKey();
                DbFieldHolder dbFieldHolder = dbFieldHolderEntry.getValue();
                Method getter = dbFieldHolder.getGetter();
                Method setter = dbFieldHolder.getSetter();
                Object value;
                if (dbFieldHolder.getConverter() != null && dbFieldHolder.getConvertFunc() != null) {
                    value = this.getValue(tuple, label, dbFieldHolder.getConverter(), dbFieldHolder.getConvertFunc(), dbFieldHolder.getPreprocessor());
                } else {
                    value = this.getValue(tuple, label, getter.getReturnType());
                }
                setter.invoke(instant, value);
            } catch (Exception ex) {
                log.debug("Error convert column value to field {}", ex.getMessage());
            }
        }
        return instant;
    }

    @SneakyThrows
    public <R> R castSqlResult(Tuple tuple, Class<R> clazz) {
        Constructor<R> constructor = clazz.getDeclaredConstructor();
        Map<String, DbFieldHolder> methodMap = new HashMap<>();
        getMethodMap(clazz, methodMap);
        return castSqlResult(tuple, constructor, methodMap);
    }

    @SneakyThrows
    public <R> List<R> castSqlResult(List<Tuple> tuples, Class<R> clazz) {
        Constructor<R> constructor = clazz.getDeclaredConstructor();
        Map<String, DbFieldHolder> methodMap = new HashMap<>();
        getMethodMap(clazz, methodMap);
        return tuples.stream().map(e -> castSqlResult(e, constructor, methodMap)).toList();
    }


}
