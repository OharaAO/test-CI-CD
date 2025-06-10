package com.ohara.corrida_colosseum.models;


import com.fasterxml.jackson.annotation.JsonView;
import com.ohara.corrida_colosseum.view.ViewFriendship;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"user1_id","user2_id"}))
public class Friendship {
    @JsonView(ViewFriendship.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @JsonView(ViewFriendship.class)
    protected String status;

    @ManyToOne
    @JoinColumn(name = "user1_id", nullable = false)
    @JsonView(ViewFriendship.class)
    protected User user1;

    @ManyToOne
    @JoinColumn(name = "user2_id", nullable = false)
    @JsonView(ViewFriendship.class)
    protected User user2;
}



