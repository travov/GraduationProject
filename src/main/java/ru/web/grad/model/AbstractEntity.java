package ru.web.grad.model;

import javax.persistence.*;

@MappedSuperclass
@Access(AccessType.FIELD)
public abstract class AbstractEntity {

    public static final int START_SEQ = 100000;

    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    protected int id;

    protected AbstractEntity() {
    }

    public int getId() {
        return id;
    }

    public boolean isNew() {
        return getId() == 0;
    }
    public void setId(int id) {
        this.id = id;
    }
}
