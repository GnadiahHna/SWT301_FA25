package danghaianh190966;

interface Drawable {
    void draw();
}
class Circle implements Drawable {
    @Override
    public void draw() {
        // method implemented (no operation)
    }
}

public class UnimplementedInterfaceExample {
    public static void main(String[] args) {
        Drawable d = new Circle();
        d.draw();
    }
}


