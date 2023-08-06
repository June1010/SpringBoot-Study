package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    // mappedBy: 읽기 전용으로 바뀜 -> 거울이 된다!
    // @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();
}