package livingfantasy.manager;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class TokenManager {

    private Set<String> tokens = new HashSet<String>();

    public void add(String token) {
        this.tokens.add(token);
    }

    public boolean contains(String token){
        return tokens.contains(token);
    }
}
