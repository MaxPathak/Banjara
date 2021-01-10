package src.damage;

public class Damage {

    boolean critical;
    int elementId;
    String formula;
    int type;
    int variance;

    public Damage(boolean critical, int elementId, String formula, int type, int variance) {
        this.critical = critical;
        this.elementId = elementId;
        this.formula = formula;
        this.type = type;
        this.variance = variance;
    }

}
