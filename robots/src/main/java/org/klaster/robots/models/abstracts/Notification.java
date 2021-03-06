package org.klaster.robots.models.abstracts;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Nikita Lepesevich <lepesevich.nikita@yandex.ru> on 10/16/19
 * @project robots
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subscribable_id", referencedColumnName = "id")
    private Subscribable subscribable;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public Notification() {
        if (getCreatedAt() == null) {
            setCreatedAt(LocalDateTime.now());
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Subscribable getSubscribable() {
        return subscribable;
    }

    public void setSubscribable(Subscribable subscribable) {
        this.subscribable = subscribable;
    }

    public String getSubscribableName() {
        return hasSubscribable() ? getSubscribable().getName() : "";
    }

    public boolean hasSubscribable() {
        return subscribable != null;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }


    public abstract String getMessage();
}
