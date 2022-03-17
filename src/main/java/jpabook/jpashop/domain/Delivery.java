package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY) //일대일 매핑, 거울
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING) //ordinal로 들어가면 중간에 다른 데이터 들어가면 idx 값 달라져서 에러 발생
    private DeliveryStatus status; //READY, COMP


}
