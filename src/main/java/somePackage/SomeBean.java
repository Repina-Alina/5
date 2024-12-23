package main.java.somePackage;

/**
 * Класс, представляющий бин, в который инжектируются зависимости.
 */
public class SomeBean {
    @AutoInjectable
    private SomeInterface field1;

    @AutoInjectable
    private SomeOtherInterface field2;

    /**
     * Метод, вызывающий методы инжектированных зависимостей.
     */
    public void foo() {
        field1.doSomething();
        field2.doSomeOther();
    }
}

