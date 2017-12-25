package pl.wat.mnd.db.domain.user;

import javax.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ")
    @SequenceGenerator(sequenceName = "USER_SEQ", initialValue = 1, allocationSize = 1, name = "USER_SEQ")
    Long id;

    @Column
    String username;
}
