import java.io.Serializable;

public class Account implements Serializable {
    private String username;
    private int tokens;

    /**
     * No argument constructor
     */
    public Account(){
        this("No username", 0);
    }

    /**
     *
     * @param username user Id
     * @param tokens Currency for game
     */
    public Account(String username, int tokens) {
        setUsername(username);
        setTokens(tokens);
    }

    @Override
    /**
     * To String Method prints username and amount of tokens
     */
    public String toString() {
        return
                "Username:'" + username + '\n' +
                        "Tokens:" + tokens +
                        "\n\n";
    }

    /**
     *
     * @param username user Id
     * Method takes in a String and sets it as the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @param tokens Game currency
     *               Method takes in an integer and sets tokens to that amount
     */
    public void setTokens(int tokens) {
        this.tokens = tokens;
    }

    /**
     *
     * @return a username
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @return an integer amount of tokens
     */
    public int getTokens() {
        return tokens;
    }

    /**
     *
     * @param tokens Game Currency
     *               Method takes an integer amount and adds it to the existing tokens in the Account object
     */
    public void addTokens(int tokens){this.tokens+=tokens;}
}
