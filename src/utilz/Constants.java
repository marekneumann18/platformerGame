package utilz;

import game.Game;

/**
 * Shared constants used by the UI.
 *
 * @author Marek
 */
public class Constants {
    /**
     * UI-related constants.
     *
     * @author Marek
     */
    public static class UI {
        /**
         * Button size constants.
         *
         * @author Marek
         */
        public static class Buttons {
            public static final int B_WIDTH_DEFAULT = 140;
            public static final int B_HEIGHT_DEFAULT = 56;
            public static final int B_WIDTH = (int) (B_WIDTH_DEFAULT * Game.SCALE);
            public static final int B_HEIGHT = (int) (B_HEIGHT_DEFAULT * Game.SCALE);
        }
    }
}
