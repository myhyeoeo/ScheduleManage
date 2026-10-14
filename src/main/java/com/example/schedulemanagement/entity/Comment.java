package com.example.schedulemanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "comment")
@EntityListeners(AuditingEntityListener.class)
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String content;
    @Column(nullable = false)
    private String writer;
    @Column(nullable = false)
    private String password;

    public Comment(Long id, String content, String writer, String password) {
        this.id = id;
        this.content = content;
        this.writer = writer;
        this.password = password;
    }
}
