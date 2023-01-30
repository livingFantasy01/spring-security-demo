package livingfantasy.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "client_id")
    private String clientId;
    private String secret;
    @Column(name = "grant_type")
    private String grantType;
    private String scope;


}
