package org.zerock.guestbook.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.guestbook.entity.Member;

import java.util.stream.IntStream;

@SpringBootTest
public class MemberRepositoryTests {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void insertMembers(){

        IntStream.rangeClosed(1,100).forEach(i ->{
            Member member = Member.builder()
                    .email("r"+i+"@aaa.com")
                    .pw("1111")
                    .nickname("REVIEWER"+i)
                    .build();
            memberRepository.save(member);
        });

    }
}
