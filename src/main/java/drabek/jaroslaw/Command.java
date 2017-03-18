package drabek.jaroslaw;

public interface Command {
    static Command createCommand(String programLine) {
        return new OverCommand("1","2");
    }
}
