package com.beta.replyservice.rule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RuleRepository extends JpaRepository<Rule, Long> {

    Rule findOneByIndex(int index);

    List<Rule> findAllByIndexIn(List<Integer> index);
}
