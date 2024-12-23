package main.java.somePackage;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Properties;

/**
 * Класс для инжектирования зависимостей в объекты.
 * Использует аннотацию {@link AutoInjectable} для определения полей, которые нужно инжектировать.
 */
public class Injector {
    private Properties properties;

    /**
     * Конструктор по умолчанию. Загружает свойства из файла config.properties.
     */
    public Injector() {
        properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/resources/config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод для инжектирования зависимостей в объект.
     * @param obj объект, в который нужно инжектировать зависимости
     * @return тот же объект с инъектированными зависимостями
     * @param <T> тип объекта
     */
    public <T> T inject(T obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(AutoInjectable.class)) {
                String interfaceName = field.getType().getName();
                String implementationName = properties.getProperty(interfaceName);
                if (implementationName != null) {
                    try {
                        Class<?> implClass = Class.forName(implementationName);
                        field.setAccessible(true);
                        field.set(obj, implClass.getDeclaredConstructor().newInstance());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return obj;
    }
}

