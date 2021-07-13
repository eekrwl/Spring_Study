package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id); //id로 회원찾기
    Optional<Member> findByName(String name);
    List<Member> findAll(); //모든 회원리스트 반환해줌
}
