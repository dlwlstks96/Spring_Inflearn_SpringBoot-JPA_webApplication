package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue //GeneratedValue -> 시퀀스값 사용
    @Column(name = "member_id")
    private Long id; //PK

    private String name;

    //jpa 엔티티 안의 column을 하나의 객체로 사용
    //zipCode, address1, 2..등 다 나누어서 관리하는 것보다
    //하나로 묶는것이 편리하다
    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member") //하나의 고객이 여러 개의 상품 주문
    //mapped by를 통해 나는 연관 관계 주인이 아니에요, 거울이에요
    private List<Order> orders = new ArrayList<>();

}
