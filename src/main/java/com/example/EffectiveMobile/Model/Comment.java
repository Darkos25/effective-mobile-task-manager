package com.example.EffectiveMobile.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "comment", columnDefinition = "TEXT")
    private String comment;

    @ManyToOne(fetch = FetchType.EAGER)
    private Task task;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

}
