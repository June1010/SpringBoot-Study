package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.item.Item;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback(false)
public class Test {

    @Autowired MemberService memberService;
    @Autowired ItemService itemService;
    @Autowired EntityManager em;

    @org.junit.Test
    public void test() {
        Order order = new Order();
        Member member = new Member();

        order.setMember(member);
        em.persist(order);
    }

    @org.junit.Test
    @Rollback(false)
    public void NPlusOneTest() {
        Order order1 = new Order();
        Order order2 = new Order();
        Order order3 = new Order();

        em.persist(order1);
        em.persist(order2);
        em.persist(order3);

        Member m1 = new Member();
        Member m2 = new Member();
        Member m3 = new Member();

        em.persist(m1);
        em.persist(m2);
        em.persist(m3);

        order1.setMember(m1);
        order2.setMember(m2);
        order3.setMember(m3);

        em.flush();
        em.clear();

        em.find(Member.class, m1.getId());

//        em.createQuery("select m from Member m", Member.class)
//                .getResultList();
    }
}
