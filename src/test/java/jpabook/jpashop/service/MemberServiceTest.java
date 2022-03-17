package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class) //스프링이랑 같이 엮어서 테스트 실행
@SpringBootTest //RunWith, SpringBootTest 가 있어야 실제 스프링에 올려 테스트 가능
@Transactional //테스트에서 데이터 변경해야 하기 때문에, 이게 있으면 테스트에서 롤백 가능
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception {
        //given
        Member member  = new Member();
        member.setName("kim");

        //when
        Long saveId = memberService.join(member);

        //then
        Assertions.assertEquals(member, memberRepository.findOne(saveId));

    }

    //아래 Test 어노테이션에 expected 옵션으로 body 코드의 가독성 증가
    @Test(expected = IllegalStateException.class) //아래 코드에서 터진 예외가 해당 라인의 예외이면 테스트 성공
    public void 중복_회원_예외() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        //when
        memberService.join(member1);
        memberService.join(member2); //여기서 예외 발생해야함!!

        //then
        fail("예외가 발생해야 한다."); //위에서 예외 발생 안하고 여기까지 도달하면 테스트 실패

    }



}