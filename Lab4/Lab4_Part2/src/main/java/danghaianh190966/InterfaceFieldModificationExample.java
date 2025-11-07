package danghaianh190966;

import java.util.logging.Logger;

class Constants {
    private Constants() {
    }
    static final int VALUE = 10; // ✅ chuyển hằng qua class riêng -> hết S1214
}

interface ExampleInterface {
}

class InterfaceFieldModificationExample implements ExampleInterface {

    private static final Logger LOGGER = Logger.getLogger(InterfaceFieldModificationExample.class.getName());

    public void displayValue() {
        LOGGER.info(() -> "Value: " + Constants.VALUE);
    }

    public static void main(String[] args) {
        new InterfaceFieldModificationExample().displayValue();
    }
}
