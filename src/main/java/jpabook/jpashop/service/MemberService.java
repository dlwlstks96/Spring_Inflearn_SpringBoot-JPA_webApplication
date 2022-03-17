package jpabook.jpashop.service;


import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service //스프링에서 제공하는 서비스, 자동으로 빈으로 등록
@Transactional(readOnly = true) //읽기 기능의 트랜잭셔널, DB에게 읽기 전용이니 리소스 많이 쓰지 말라함, public 메소드들에 적용(아래 읽기 메소드들이 많기 때문)
@RequiredArgsConstructor //final 가지고 있는 필드들에 대해서 생성자 자동 생성(Lombok)
public class MemberService {

    private final MemberRepository memberRepository; //final로 변경 가능성 차단

//    @Autowired //setter 인젝션, 테스트 코드 작성 시 내가 원하는 구현체로 직접 주입 가능
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

//    @Autowired //생성자 인젝션, setter를 통한 변경 가능성 차단
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    /**
     * 회원 가입
     */
    @Transactional //회원 가입 메소드의 경우 쓰기 기능이 있어야 하므로 readOnly = true 가 아님
    public Long join(Member member) {
        validateDuplicateMember(member); //중복 회원 확인
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) { //중복 회원이면 예외 발생
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    //하나의 회원 조회
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }



}
