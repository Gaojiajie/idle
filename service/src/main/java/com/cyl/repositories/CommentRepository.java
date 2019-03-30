package com.cyl.repositories;

import com.cyl.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by cyl on 2019-03-03.
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer>, QueryDslPredicateExecutor<Comment> {
}
