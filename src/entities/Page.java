package src.entities;

import java.awt.image.BufferedImage;

import src.commands.CommandManager;
import src.gfx.Animation;
import src.gfx.Assets;
import src.global.Global.Direction;

public class Page {
    private static final int DEFAULT_ANIMATION_SPEED = 375;

    private Direction direction;
    private Animation[] animations;
    private Conditions conditions;
    private boolean dFix;
    private int trigger;
    // ! 0: Action Button, 1: Player Touch, 2: Parallel, 3: Autorun
    private CommandManager list;
    private boolean passable;

    public Page(int direction, String name, int index, Conditions conditions, boolean directionFix, int trigger,
            CommandManager list) {
        this.direction = Direction.values()[direction];
        this.conditions = conditions;
        this.dFix = directionFix;
        this.trigger = trigger;
        this.list = list;

        this.animations = new Animation[4];
        BufferedImage[][] images = Assets.getSprites(name, index);
        for (int i = 0; i < animations.length; i++)
            animations[i] = new Animation(DEFAULT_ANIMATION_SPEED, images[i]);

        passable = false;

    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Animation[] getAnimations() {
        return animations;
    }

    public void setAnimations(Animation[] animations) {
        this.animations = animations;
    }

    public Conditions getConditions() {
        return conditions;
    }

    public void setConditions(Conditions conditions) {
        this.conditions = conditions;
    }

    public boolean isdFix() {
        return dFix;
    }

    public void setdFix(boolean dFix) {
        this.dFix = dFix;
    }

    public int getTrigger() {
        return trigger;
    }

    public void setTrigger(int trigger) {
        this.trigger = trigger;
    }

    public CommandManager getList() {
        return list;
    }

    public void setList(CommandManager list) {
        this.list = list;
    }

    public boolean isPassable() {
        return passable;
    }

    public void setPassable(boolean passable) {
        this.passable = passable;
    }
}
