package com.alan.springbasic.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    // concurrency issue로 ConcurrencyHashMap을 쓰는것이 더 적합.

    @Override
    public void save(Member member) {
       store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
