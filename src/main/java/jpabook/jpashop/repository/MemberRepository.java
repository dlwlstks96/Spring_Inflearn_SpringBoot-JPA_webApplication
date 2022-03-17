package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository //스프링에서 제공하는 어노테이션, 자동으로 스프링 빈으로 관리됨
@RequiredArgsConstructor //final 필드에 대한 생성자 생성
public class MemberRepository {

//    @PersistenceContext //JPA에서 제공
//    private EntityManager em; //스프링이 자동으로 Entity Manager 생성 후 주입

    private final EntityManager em;

    public void save(Member member) { //멤버 저장
        em.persist(member);
    }

    public Member findOne(Long id) { //멤버 조회
        return em.find(Member.class, id);
    }

    public List<Member> findAll() { //전체 조회 결과
        return em.createQuery("select m from Member m", Member.class) //조회의 대상이 테이블이 아닌 엔티티
                .getResultList(); //JPQL 작성, 두번째론 반환 타입 작성
    }

    public List<Member> findByName(String name) { //이름으로 멤버 검색
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name) //위의 name 파라미터 바인딩(설정)
                .getResultList();
    }

}
