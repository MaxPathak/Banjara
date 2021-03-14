package src.events;

public class PageList {
    private long code;
    private long indent;
    private Object[] parameters;

    public long getCode() {
        return code;
    }

    public void setCode(long value) {
        this.code = value;
    }

    public long getIndent() {
        return indent;
    }

    public void setIndent(long value) {
        this.indent = value;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object... value) {
        this.parameters = value;
    }
}
