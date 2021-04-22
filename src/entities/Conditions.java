package src.entities;

public class Conditions {
    private int switch1Id;
    private boolean switch1Valid;
    private int switch2Id;
    private boolean switch2Valid;
    private int variableId;
    private int variableValue;
    private boolean variableValid;
    private String selfSwitchCh;
    private boolean selfswitchValid;
    private int itemId;
    private boolean itemValid;
    private int actorId;
    private boolean actorValid;

    public Conditions(int switch1Id, boolean switch1Valid, int switch2Id, boolean switch2Valid, int variableId,
            int variableValue, boolean variableValid, String selfSwitchCh, boolean selfswitchValid, int itemId,
            boolean itemValid, int actorId, boolean actorValid) {
        this.switch1Id = switch1Id;
        this.switch1Valid = switch1Valid;
        this.switch2Id = switch2Id;
        this.switch2Valid = switch2Valid;
        this.variableId = variableId;
        this.variableValue = variableValue;
        this.variableValid = variableValid;
        this.selfSwitchCh = selfSwitchCh;
        this.selfswitchValid = selfswitchValid;
        this.itemId = itemId;
        this.itemValid = itemValid;
        this.actorId = actorId;
        this.actorValid = actorValid;
    }

    public int getSwitch1Id() {
        return switch1Id;
    }

    public void setSwitch1Id(int switch1Id) {
        this.switch1Id = switch1Id;
    }

    public boolean isSwitch1Valid() {
        return switch1Valid;
    }

    public void setSwitch1Valid(boolean switch1Valid) {
        this.switch1Valid = switch1Valid;
    }

    public int getSwitch2Id() {
        return switch2Id;
    }

    public void setSwitch2Id(int switch2Id) {
        this.switch2Id = switch2Id;
    }

    public boolean isSwitch2Valid() {
        return switch2Valid;
    }

    public void setSwitch2Valid(boolean switch2Valid) {
        this.switch2Valid = switch2Valid;
    }

    public int getVariableId() {
        return variableId;
    }

    public void setVariableId(int variableId) {
        this.variableId = variableId;
    }

    public int getVariableValue() {
        return variableValue;
    }

    public void setVariableValue(int variableValue) {
        this.variableValue = variableValue;
    }

    public boolean isVariableValid() {
        return variableValid;
    }

    public void setVariableValid(boolean variableValid) {
        this.variableValid = variableValid;
    }

    public String getSelfSwitchCh() {
        return selfSwitchCh;
    }

    public void setSelfSwitchCh(String selfSwitchCh) {
        this.selfSwitchCh = selfSwitchCh;
    }

    public boolean isSelfswitchValid() {
        return selfswitchValid;
    }

    public void setSelfswitchValid(boolean selfswitchValid) {
        this.selfswitchValid = selfswitchValid;
    }

    public int getItemID() {
        return itemId;
    }

    public void setItemID(int itemID) {
        this.itemId = itemID;
    }

    public boolean isItemValid() {
        return itemValid;
    }

    public void setItemValid(boolean itemValid) {
        this.itemValid = itemValid;
    }

    public int getActorID() {
        return actorId;
    }

    public void setActorID(int actorID) {
        this.actorId = actorID;
    }

    public boolean isActorValid() {
        return actorValid;
    }

    public void setActorValid(boolean actorValid) {
        this.actorValid = actorValid;
    }

}
