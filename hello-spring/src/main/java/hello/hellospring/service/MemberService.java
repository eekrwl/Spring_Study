package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;

import java.util.List;
import java.util.Optional;
/*
@Service //스프링 컨테이너에 등록해줌, 안에 @Component 들어있다
 */
public class MemberService { //클래스 이름에 커서 놓고 ctrl+shift+t -> create new test

    //private final MemberRepository memberRepository=new MemoryMemberRepository();
    //얘가 테스트에도 하나 더 있고 하니까 헷갈림?문제생김?

    private final MemberRepository memberRepository;

    //@Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
    *회원가입
     */
    public Long join(Member member) {
        //같은 이름이 있는 중복 회원X
        /*
        Optional<Member> result = memberRepository.findByName(member.getName());//ctrl alt v
        result.ifPresent(m-> { //Optional있으면 null이어도 된다?? (if null안씀)
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
        */
        //위에 코드 이렇게 정리함..
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()) //얘네 메서드로 만들건데 ctrl+shift+alt+t 하고 Extract Method, 또는 ctrl+alt+m
                .ifPresent(m-> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     *
     * 전체회원조희
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
