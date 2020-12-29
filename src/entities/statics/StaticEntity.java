package src.entities.statics;

import src.Handler;
import src.entities.Entity;

public abstract class StaticEntity extends Entity {
    public StaticEntity(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
    }
}
