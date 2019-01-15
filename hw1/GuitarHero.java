import synthesizer.GuitarString;
public class GuitarHero {
    private static final int VARIETY = 37;
    private static GuitarString[] concert;
    private static char[] keyboard;
    public GuitarHero(String key) {
        concert = new GuitarString[VARIETY];
        keyboard = new char[VARIETY];
        keyboard = key.toCharArray();
        for (int i = 0; i < concert.length; i++) {
            concert[i] = new synthesizer.GuitarString(440 *
                    Math.pow(2,(i - 24) / 12.0));
        }
    }
    private static int indexOf(char key) {
        int indexOfKey = -1;
        for (int i = 0; i < keyboard.length; i++) {
            if (keyboard[i] == key) {
                indexOfKey = i;
                break;
            }
        }
        return indexOfKey;
    }
    public static void main(String[] args) {
        String KeyBoard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        GuitarHero guitar = new GuitarHero(KeyBoard);
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                try {
                    int index = indexOf(key);
                    concert[index].pluck();
                } catch (ArrayIndexOutOfBoundsException e) {
                    int index = 0;
                    concert[index].pluck();
                }
            }
            double sample = 0.0;
            for (GuitarString s : concert) {
                sample = sample + s.sample();
            }
            StdAudio.play(sample);
            for (GuitarString s : concert) {
                s.tic();
            }
        }
    }
}
