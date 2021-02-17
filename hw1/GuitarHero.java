import synthesizer.GuitarString;

/** A client that uses the synthesizer package to replicate a plucked guitar string sound */

public class GuitarHero {
    private static final String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";


    public static void main(String[] args) {
        GuitarString[] strings =  new GuitarString[keyboard.length()];
        for (int i = 0; i < strings.length; i += 1) {
            strings[i] = new GuitarString(440 * Math.pow(2, (i - 24.0) / 12.0));
        }

        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int indexOfKey = keyboard.indexOf(key);
                if (indexOfKey >= 0) {
                    strings[indexOfKey].pluck();
                }
            }

            /* compute the superposition of samples */
            double sample = 0.0;
            for (GuitarString s : strings) {
                sample += s.sample();
            }

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            for (GuitarString s : strings) {
                s.tic();
            }
        }
    }
}

