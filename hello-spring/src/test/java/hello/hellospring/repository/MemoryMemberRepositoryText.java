package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

//import org.junit.jupiter.api.Assertions; ---1이랑 세트

class MemoryMemberRepositoryText { //테스트는 서로 의존관계없이, 순서관계없이 실행되어야 함, 실행순서 컴퓨터 마음대로

    MemoryMemberRepository repository=new MemoryMemberRepository();

    @AfterEach //메서드들 끝날때마다 얘가 계속 호출됨
    public void afterEach() { //spring1,2..만들어놓은거 지워줌
        repository.clearStore(); //메서드 테스트 하나씩 끝날때마다 저장소 다 지움
    }

    @Test
    public void save() {
        Member member=new Member();
        member.setName("spring");

        repository.save(member);

        Member result=repository.findById(member.getId()).get();
        //Assertions.assertEquals(member,result); //기대값 안나오면 에러남 ---1
        assertThat(member).isEqualTo(result); // ---2 alt+enter해서 static import하면 assertThat 바로 쓸 수 있다.
    }

    @Test
    public void findByName() {
        Member member1=new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2=new Member(); //shift+F6 해서 rename 가능
        member2.setName("spring2");
        repository.save(member2);

        Member result=repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1=new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2=new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result=repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
