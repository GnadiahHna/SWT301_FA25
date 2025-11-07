package danghaianh190966;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CatchGenericExceptionExample {
    private static final Logger LOGGER = Logger.getLogger(CatchGenericExceptionExample.class.getName());

    public static void main(String[] args) {
        String s = null;

        // ✅ Tránh lỗi S2259: kiểm tra null trước khi gọi length()
        if (s == null) {
            LOGGER.warning("Chuỗi 's' là null — không thể lấy length.");
        } else {
            System.out.println("Length: " + s.length());
        }

        // ✅ Minh họa bắt lỗi NullPointerException cụ thể
        try {
            String t = null;
            System.out.println(t.length());  // sẽ ném NPE
        } catch (NullPointerException npe) {
            // ✅ Không dùng printStackTrace(), dùng Logger
            LOGGER.log(Level.SEVERE, "NullPointerException caught", npe);
        }
    }
}
