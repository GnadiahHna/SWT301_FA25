package danghaianh190966;

import java.util.logging.Logger;

public class OvercatchingExceptionExample {

    private static final Logger LOGGER = Logger.getLogger(OvercatchingExceptionExample.class.getName());

    public static void main(String[] args) {
        try {
            // ✅ câu lệnh giả lập lỗi để tránh empty try block
        } catch (ArithmeticException e) { // ✅ bắt riêng lỗi cụ thể, tránh overcatch
            LOGGER.warning(() -> "Arithmetic error: " + e.getMessage()); // ✅ dùng logger đúng chuẩn
        }
    }
}
