package com.example.qikserve.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Basket {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;

    private Double total;

    private Double totalSaved;

    private Double totalPayble;

    @NotNull
    @ManyToOne(optional = false)
    private User user;

    @OneToMany(mappedBy = "basket", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> items = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Double getTotalSaved() {
        return totalSaved;
    }

    public void setTotalSaved(Double totalSaved) {
        this.totalSaved = totalSaved;
    }

    public Double getTotalPayble() {
        return totalPayble;
    }

    public void setTotalPayble(Double totalPayble) {
        this.totalPayble = totalPayble;
    }
}



