package drabek.jaroslaw;

import com.google.common.base.Objects;

class OverCommand extends Command {
    private final String from;
    private final String to;

    public OverCommand(String from, String to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OverCommand that = (OverCommand) o;
        return Objects.equal(from, that.from) &&
                Objects.equal(to, that.to);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(from, to);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OverCommand{");
        sb.append("from='").append(from).append('\'');
        sb.append(", to='").append(to).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
