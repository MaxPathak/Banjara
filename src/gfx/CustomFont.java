package src.gfx;

import java.awt.Font;

public class CustomFont extends Font {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public CustomFont(Font font) {
        super(font);
    }

    public Font get(float size) {
        return this.deriveFont(size);
    }

}
