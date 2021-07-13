package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;
/*
@Repository //controller에서 외부요청받고, service에서 비즈니스 로직만들고, repository에서 데이터를 저장 -> 정형화된 패턴이래
 */
public class MemoryMemberRepository implements  MemberRepository {

    private static Map<Long, Member> store=new HashMap<>();
    private static long sequence=0L; //키값 생성해주는애?

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(),member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member->member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
