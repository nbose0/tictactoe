package ttt.models;

public class Player {

    Token _token;
    String _name;

    Player(Token token, String name) {
        _token = token;
        _name = name;
    }

    public Token getToken() {
        return _token;
    }

    public String getName() {
        return _name;
    }

    public static Player of(Token token, String name) {
        if (name.toLowerCase().contains("nikhi")) {
            name = "Sucka";
        }
        return new Player(token, name);
    }

    public static Player of(Token token) {
        return new Player(token, token.name());
    }
}
