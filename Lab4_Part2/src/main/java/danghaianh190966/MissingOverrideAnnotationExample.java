package danghaianh190966;

import java.util.logging.Logger;

class Parent {

    void greet() {
    }
}

class Child extends Parent {

    private static final Logger LOGGER = Logger.getLogger(Child.class.getName());

    @Override
    void greet() {
        LOGGER.info(() -> "Hello from Child");
    }

    public static void main(String[] args) {
        Parent parent = new Parent();
        parent.greet();

        Child child = new Child();
        child.greet();
    }
}
