package BananaShooter;

public class Pisang extends Controller {
	
	private final int INITIAL_X = 400;

    public Pisang(int x, int y) {
        super(x, y);

        initPisang();
    }

    private void initPisang() {

        loadImage("img/pisang.png");
        getImageDimensions();
    }

    public void move() {

        if (x < 0) {
            x = INITIAL_X;
        }

        x -= 1;
    }

}

