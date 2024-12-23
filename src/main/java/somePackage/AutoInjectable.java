package main.java.somePackage;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Аннотация для автоматического инжектирования зависимостей.
 * Используется для пометки полей, которые должны быть автоматически инжектированы.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoInjectable {
}


