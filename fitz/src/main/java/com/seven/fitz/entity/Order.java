package com.seven.fitz.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer totalAmount;

   // CREATED, PAID, SHIPPED
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private Long addressId;
    
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy="order", cascade=CascadeType.ALL)
    private List<OrderItem> items;

    public Long getId() { return id; }

    public Integer getTotalAmount() { return totalAmount; }

    public void setTotalAmount(Integer totalAmount) { this.totalAmount = totalAmount; }

    public Long getAddressId() { return addressId; }

    public void setAddressId(Long addressId) { this.addressId = addressId; }

    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public List<OrderItem> getItems() { return items; }

    public void setItems(List<OrderItem> items) { this.items = items; }

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	
    
    
}
