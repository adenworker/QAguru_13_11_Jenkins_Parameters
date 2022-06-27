package guru.qa.credentials;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:credentials/credentials.properties")
public interface CredentialsConfig extends Config {
    String login();
    String password();
}
